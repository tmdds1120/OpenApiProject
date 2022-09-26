package domain.dao;

import domain.dto.JsonDTO;
import domain.util.MyKey;
import domain.vo.NearVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WifiDAO {

    private static MyKey myKey = new MyKey();
    Connection con = null;
    PreparedStatement psmt = null;
    ResultSet rs = null;

    void getConnection() {
        String url = "jdbc:mariadb://localhost:3306/project";
        String id = myKey.getId();
        String password = myKey.getPassword();

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            con = DriverManager.getConnection(url, id, password);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void insertWifi(List<JsonDTO> list) throws SQLException {
        String InsertSQL = "insert into open_data(x_swifi_mgr_no, x_swifi_wrdofc, x_swifi_main_nm,\n" +
        "                 x_swifi_adres1, x_swifi_adres2, x_swifi_instl_floor,\n" +
                "                 x_swifi_instl_ty, x_swifi_instl_mby, x_swifi_svc_se,\n" +
                "                 x_swifi_cmcwr, x_swifi_cnstc_year, x_swifi_inout_door,\n" +
                "                 x_swifi_remars3, lat, lnt, work_dttm)\n" +
                "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        getConnection();
        con.setAutoCommit(false);

        try {
            psmt = con.prepareStatement(InsertSQL);
            int tmp_Size = 1000;
            int flag = 0;

            for (JsonDTO jsonDTO : list) {

                //관리번호
                psmt.setString(1, jsonDTO.getMgrNo());
                // 자치구
                psmt.setString(2, jsonDTO.getRegion());
                // 와이파이명
                psmt.setString(3, jsonDTO.getMainName());
                // 도로명주소
                psmt.setString(4, jsonDTO.getRoadAdd());
                // 상세주소
                psmt.setString(5, jsonDTO.getDetailAdd());
                // 설치위치(층)
                psmt.setString(6, jsonDTO.getInstFloor());
                // 설치유형
                psmt.setString(7, jsonDTO.getInstType());
                // 설치기관
                psmt.setString(8, jsonDTO.getInstOrgan());
                // 서비스구분
                psmt.setString(9, jsonDTO.getServType());
                // 망종류
                psmt.setString(10, jsonDTO.getNetType());
                // 설치년도
                psmt.setString(11, jsonDTO.getInstYear());
                // 실내외구분
                psmt.setString(12, jsonDTO.getWhichDoor());
                // WIFI접속환경
                psmt.setString(13, jsonDTO.getAccess());
                // X좌표
                psmt.setString(14, jsonDTO.getLat());
                // Y좌표
                psmt.setString(15, jsonDTO.getLnt());
                // 작업일자
                psmt.setString(16, jsonDTO.getWorkDay());

                psmt.addBatch();
                flag++;

                if (flag == tmp_Size) {
                    psmt.executeBatch();
                    psmt.clearBatch();
                    flag = 0;
                }
            }
            psmt.executeBatch();
            con.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            con.rollback();
            con.setAutoCommit(true);
        } finally {
            close();
        }
    }

    public void deleteAll() throws SQLException {
        String sql = "truncate table open_data";

        getConnection();
        con.setAutoCommit(false);

        try {
            psmt = con.prepareStatement(sql);
            psmt.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close();
        }
    }

    public List<NearVO> NearFindInfo(Double lat, Double lnt) throws SQLException {
        String NearFindSQL = "select * " +
                ", format((6371 * acos(cos(radians(" + lat + ")) * cos(radians(lat)) * cos(radians(lnt) - radians(" + lnt + ")) " +
                "+ sin(radians(" + lat + ")) * sin(radians(lat)))), 4) as distance " +
                " from open_data " +
                " order by distance , X_SWIFI_MGR_NO" +
                " limit 20";

        getConnection();

        try {
            psmt = con.prepareStatement(NearFindSQL);
            rs = psmt.executeQuery();

            List<NearVO> list = new ArrayList<>();

            while (rs.next()) {
                list.add(new NearVO(
                        rs.getString("distance"),
                        rs.getString("X_SWIFI_MGR_NO"),
                        rs.getString("X_SWIFI_WRDOFC"),
                        rs.getString("X_SWIFI_MAIN_NM"),
                        rs.getString("X_SWIFI_ADRES1"),
                        rs.getString("X_SWIFI_ADRES2"),
                        rs.getString("X_SWIFI_INSTL_FLOOR"),
                        rs.getString("X_SWIFI_INSTL_TY"),
                        rs.getString("X_SWIFI_INSTL_MBY"),
                        rs.getString("X_SWIFI_SVC_SE"),
                        rs.getString("X_SWIFI_CMCWR"),
                        rs.getString("X_SWIFI_CNSTC_YEAR"),
                        rs.getString("X_SWIFI_INOUT_DOOR"),
                        rs.getString("X_SWIFI_REMARS3"),
                        rs.getString("LAT"),
                        rs.getString("LNT"),
                        rs.getString("WORK_DTTM")
                ));
            }

            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close();
        }

    }

    public void close() {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        if (psmt != null) {
            try {
                psmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

}

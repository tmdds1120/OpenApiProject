package domain.dao;

import domain.util.MyKey;
import domain.vo.HistoryVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistoryDAO {

    Connection con = null;
    PreparedStatement psmt = null;
    ResultSet rs = null;
    private static MyKey myKey = new MyKey();

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

    public int saveHistory(Double lat, Double lnt) throws SQLException {
        String InsertSQL = "insert into history (lat, lnt, date) values(?, ?, now())";

        getConnection();
        con.setAutoCommit(false);

        try {
            psmt = con.prepareStatement(InsertSQL);
            psmt.setDouble(1, lat);
            psmt.setDouble(2, lnt);

            int cnt = psmt.executeUpdate();

            con.commit();

            return cnt;
        } catch (SQLException e) {
            e.printStackTrace();
            con.rollback();
            con.setAutoCommit(true);
            throw new RuntimeException(e);
        } finally {
            close();
        }
    }

    public List<HistoryVO> getHistoryList() throws SQLException {
        String sql = "select * from history order by id desc";

        getConnection();

        try {

            psmt = con.prepareStatement(sql);
            rs = psmt.executeQuery();

            List<HistoryVO> list = new ArrayList<>();

            while (rs.next()) {
                list.add(new HistoryVO((
                        rs.getString("id")),
                        rs.getDouble("lat"),
                        rs.getDouble("lnt"),
                        rs.getString("date")
                ));
            }

            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            close();
        }
    }

    public void deleteFindById(int id) throws SQLException {
        String DeleteSQL = "delete from history where id = ?";

        getConnection();
        con.setAutoCommit(false);

        try {
            psmt = con.prepareStatement(DeleteSQL);
            psmt.setInt(1, id);
            psmt.executeUpdate();

            con.commit();
        } catch (SQLException e) {
            con.rollback();
            con.setAutoCommit(true);
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

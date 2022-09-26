package domain.dto;

import com.google.gson.annotations.SerializedName;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JsonDTO {

    // 관리 번호 1
    @SerializedName("X_SWIFI_MGR_NO")
    private String mgrNo;

    // 자치구 2
    @SerializedName("X_SWIFI_WRDOFC")
    private String region;

    // 와이파이명 3
    @SerializedName("X_SWIFI_MAIN_NM")
    private String mainName;

    // 도로명주소 4
    @SerializedName("X_SWIFI_ADRES1")
    private String roadAdd;

    // 상세주소 5
    @SerializedName("X_SWIFI_ADRES2")
    private String detailAdd;

    // 설치위치(층) 6
    @SerializedName("X_SWIFI_INSTL_FLOOR")
    private String instFloor;

    // 설치유형 7
    @SerializedName("X_SWIFI_INSTL_TY")
    private String instType;

    // 설치기관 8
    @SerializedName("X_SWIFI_INSTL_MBY")
    private String instOrgan;

    // 서비스구분 9
    @SerializedName("X_SWIFI_SVC_SE")
    private String servType;

    // 망종류 10
    @SerializedName("X_SWIFI_CMCWR")
    private String netType;

    // 설치년도 11
    @SerializedName("X_SWIFI_CNSTC_YEAR")
    private String instYear;

    // 실내외구분 12
    @SerializedName("X_SWIFI_INOUT_DOOR")
    private String whichDoor;

    // WIFI접속환경 13
    @SerializedName("X_SWIFI_REMARS3")
    private String access;

    // X좌표 14
    @SerializedName("LAT")
    private String lat;

    // Y좌표 15
    @SerializedName("LNT")
    private String lnt;

    // 작업일자 16
    @SerializedName("WORK_DTTM")
    private String workDay;

}

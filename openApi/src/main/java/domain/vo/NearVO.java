package domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NearVO {

    private String distance;

    private String mgrNo;

    private String region;

    private String mainName;

    private String roadAdd;

    private String detailAdd;

    private String instFloor;

    private String instType;

    private String instOrgan;

    private String servType;

    private String netType;

    private String instYear;

    private String WhichDoor;

    private String access;

    private String lat;

    private String lnt;

    private String workDay;

}

package domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoryVO {

    private String id;

    private Double lat;

    private Double lnt;

    private String date;

}

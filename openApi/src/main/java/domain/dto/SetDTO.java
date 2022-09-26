package domain.dto;

import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SetDTO {

    @SerializedName("list_total_count")
    private int totalCount;

//    @SerializedName("RESULT")
//    private ResultDTO result;

    @SerializedName("row")
    private List<JsonDTO> jsonDtos;

    public List<JsonDTO> getJsonDtos() {
        return jsonDtos;
    }
}

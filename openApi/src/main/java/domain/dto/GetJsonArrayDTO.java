package domain.dto;

import com.google.gson.annotations.SerializedName;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetJsonArrayDTO {

    @SerializedName("TbPublicWifiInfo")
    private SetDTO tbPublicWifiInfo;

}

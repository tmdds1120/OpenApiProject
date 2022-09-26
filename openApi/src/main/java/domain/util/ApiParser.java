package domain.util;

import com.google.gson.Gson;
import domain.dto.GetJsonArrayDTO;
import domain.dto.SetDTO;

public class ApiParser {

    Gson gson = new Gson();

    public SetDTO parse(String json) {
        GetJsonArrayDTO getJsonArrayDTO = gson.fromJson(json, GetJsonArrayDTO.class);

        return getJsonArrayDTO.getTbPublicWifiInfo();
    }

}

package domain.util;

import domain.dto.SetDTO;

import java.io.IOException;

public class TotalCnt {

    public int getCnt() throws IOException {
        OpenApiJson openApiJson = new OpenApiJson();

        SetDTO setDTO = openApiJson.getWifiJson(1, 100);

        int totalCount = setDTO.getTotalCount();
        int num = totalCount / 1000;

        if (totalCount > num * 1000) {
            num++;
        }

        return num;
    }

}

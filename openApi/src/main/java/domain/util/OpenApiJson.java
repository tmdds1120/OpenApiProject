package domain.util;

import domain.dto.SetDTO;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.net.URL;

public class OpenApiJson {

    private  static MyKey  myKey = new MyKey();
    public SetDTO getWifiJson(int start, int end) throws IOException {
        String SetURL = myKey.getURLSET() + start + "/" + end;

        OkHttpClient okHttpClient = new OkHttpClient();

        URL tmp_url = new URL(SetURL);

        Request req = new Request.Builder().url(tmp_url).get().build();
        Response res = okHttpClient.newCall(req).execute();

        String json = res.body().string();

        ApiParser apiParser = new ApiParser();

        return apiParser.parse(json);
    }

}

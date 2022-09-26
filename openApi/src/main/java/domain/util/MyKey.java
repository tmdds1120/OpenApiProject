package domain.util;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MyKey {
    private String myKey= "4b656b616967697435386951584c56";
    private String id = "root";
    private String password = "tmdds";
    private String URLSET = "http://openapi.seoul.go.kr:8088/"
            +myKey+"/json/TbPublicWifiInfo/";
}

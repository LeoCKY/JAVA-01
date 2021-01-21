package nio.utils;


import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.net.URI;

public class HttpUtil {

    public static String get(String url) {
        // 創建 Httpclient 對象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            // 定義請求的參數
            URI uri = new URIBuilder(url).build();
            // 創建http GET請求
            HttpGet httpGet = new HttpGet(uri);
            //response 對象
            CloseableHttpResponse response = null;
            // 執行http get請求
            response = httpclient.execute(httpGet);
            // 判斷返回狀態是否為200
            if (response.getStatusLine().getStatusCode() == 200) {
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                System.out.println("內容長度：" + content.length());
                return content;
            }
            if (response != null) {
                response.close();
            }
            httpclient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

}

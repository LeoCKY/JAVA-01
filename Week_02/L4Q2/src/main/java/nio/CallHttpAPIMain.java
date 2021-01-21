package nio;

import nio.utils.HttpUtil;

/**
 * 2、写一段代码，使用 HttpClient 或 OkHttp 访问 http://localhost:8801，代码提交到 Github。
 */
public class CallHttpAPIMain {

    public static void main(String[] args) {
        System.out.println(HttpUtil.get("http://localhost:8801/test"));
    }
}

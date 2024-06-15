package damn;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class IpInfo {
    public static void main(String[] args) {

        
        try {
            // 定义请求的 URL
            String url = "http://ipinfo.io/json";
            // 打开连接
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            // 设置请求方法为 GET
            connection.setRequestMethod("GET");

            // 从输入流中读取响应数据
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            // 关闭 BufferedReader 和连接
            in.close();
            connection.disconnect();

            // 将响应内容解析为 JSON 对象
            JSONObject json = new JSONObject(content.toString());

            // 遍历 JSON 对象的所有键，并打印它们的值
            for (String key : json.keySet()) {
                System.out.println(key + ": " + json.get(key));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String city() {
        try {
            // 定义请求的 URL
            String url = "http://ipinfo.io/json";
            // 打开连接
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            // 设置请求方法为 GET
            connection.setRequestMethod("GET");

            // 从输入流中读取响应数据
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            // 关闭 BufferedReader 和连接
            in.close();
            connection.disconnect();

            // 将响应内容解析为 JSON 对象
            JSONObject json = new JSONObject(content.toString());

            // 提取并打印城市信息
            return json.getString("city");
        } catch (Exception e) {
            e.printStackTrace();
            TextToSpeech.speak("城市資訊獲取失敗");
            return "城市資訊獲取失敗";
        }
        
    }
}
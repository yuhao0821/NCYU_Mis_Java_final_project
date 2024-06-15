package damn;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class IpInfo {
    public static void main(String[] args) {
        try {
            JSONObject json = getIpInfo();

            for (String key : json.keySet())
                System.out.println(key + ": " + json.get(key));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static String city() {
        try {
            JSONObject json = getIpInfo();
            return json.getString("city");

        } catch (Exception e) {
            return "ChiaYi";
        }

    }

    private static JSONObject getIpInfo() {
        try {
            String url = "https://ipinfo.io/json";
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) content.append(inputLine);

            in.close();
            connection.disconnect();

            return new JSONObject(content.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
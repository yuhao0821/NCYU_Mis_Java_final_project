package damn;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


import tw.kewang.cwb.Cwb;
import tw.kewang.cwb.pretty.FutureWeatherByTown;

public class TaiwanWeather {


    public static void main(String[] args) {
        Cwb cwb = Cwb.getInstance().init(System.getenv("CWA-626BD426-0D43-4921-BA53-D8F6078C134A"));
        
        FutureWeatherByTown weather1 = cwb.getFutureWeatherByTown(getCityChineseName("Taipei"), 12);

        System.out.println(weather1.getDescription().getStartDate());
        System.out.println(weather1.getDescription().getEndDate());
        System.out.println(weather1.getDescription().getDetail());
        System.out.println(weather1.getDescription().getShort());
        System.out.println(weather1.getComfortable().getDataDate());
        //System.out.println(weather1.getComfortable().getString());
        System.out.println(weather1.getComfortable().getValue());
        System.out.println(weather1.getApparent());
        System.out.println(weather1.getTemperature());
        System.out.println(weather1.getPoP());
        System.out.println(weather1.getRH());
        System.out.println(weather1.getWind().getDataDate());
        System.out.println(weather1.getWind().getDirectionDetail());
        System.out.println(weather1.getWind().getDirectionShort());
        System.out.println(weather1.getWind().getScale());
        System.out.println(weather1.getWind().getSpeed());  
    }

    // 英文城市名轉換成中文
    public static String getCityChineseName(String cityName) {
        Map<String, String> cityMap = new HashMap<>();
        cityMap.put("Taipei", "臺北市");
        cityMap.put("New Taipei", "新北市");
        cityMap.put("Taoyuan", "桃園市");
        cityMap.put("Taichung", "臺中市");
        cityMap.put("Tainan", "臺南市");
        cityMap.put("Kaohsiung", "高雄市");
        // 添加更多城市...

        return cityMap.get(cityName);
    }
}


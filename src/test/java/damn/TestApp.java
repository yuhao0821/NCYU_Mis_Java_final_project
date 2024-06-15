package damn;

public class TestApp {
    public static void main(String[] args){
        System.out.println(IpInfo.city());
        String weatherInfo;
        try {
            weatherInfo = getWeather.getWeatherInfo(IpInfo.city());
        } catch (Exception e) {
            weatherInfo = "天氣資訊獲取失敗";
        }

        System.out.println(weatherInfo);
        
        Alarm alarm = new Alarm();
        alarm.setMinute(1);
        System.out.println(123);
        alarm.cancel();
        alarm.setTime("05:27");

    }
}

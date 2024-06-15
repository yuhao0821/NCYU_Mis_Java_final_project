package com.example;

import java.io.IOException;
import java.util.Date;

public class broadcast {
    public static void main(String[] args){
        play();
    }
    public static void play() {
        TextToSpeak.speak("請稍候!正在生成今日的播報");
        TextToSpeak tts = new TextToSpeak();
        try {
            String weatherInfo = Weather.getWeatherInfo(IpInfo.city());

            String broadcast = langchain4j.ChatGPT("請根據以下資訊用中文生成一份口語化的日報：\n" +
                                                   "- 日期和時間：{"+new Date()+"}\n" +
                                                   "- 所在城市：{" + IpInfo.city()+"}\n"+
                                                   "- 課程資訊:{"+ClassTable.read(DateTime.getWeekday())+"}"+
                                                   "-代辦事項資訊:{"+ToDoList.read(DateTime.getShortDateString(new Date())+".txt")+"}"+
                                                   "- 天氣資訊:{"+weatherInfo+"}\n\n" +
                                                   "日報應包括以下內容：\n" +
                                                   "1. 請根據{currentTime}回復適當的招呼語：\n" + 
                                                   "2. 今天的簡短日期和時間(hh:mm)。\n" +
                                                   "3. 今天是星期幾、有什麼課程\n"+
                                                   "4. 有什麼代辦事項\n"+
                                                   "5. 今天的城市的天氣狀況。\n天氣狀況不用講出氣壓風速雲量\n" +
                                                   "6. 綜合天氣、濕度、溫度給出天氣會給人的感覺，例如有點悶、涼爽、炎熱\n"+
                                                   "7. 根據天氣和現在的時間，給出合理的出門建議。\n\n" +
                                                   "範例格式：\n" +
                                                   "\"{greet}！今天是 {currentDate}，時間是 {currentTime}。今天有{Class}課程，今天的代辦行程有{To-do list}。現在{cityname}的天氣是 {weatherInfo}。 {feel} ，建議 {suggestion}。\"\n\n" +
                                                   "請用全中文生成一份日報。");
            tts.speak(broadcast);
        } catch (IOException e) {
            tts.speak("天氣資訊獲取失敗");
            System.err.println(e.getMessage());
            //System.err.println("天氣資訊獲取失敗: " + e.getMessage());
        }
    }
}


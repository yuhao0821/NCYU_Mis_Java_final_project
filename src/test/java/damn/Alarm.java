package damn;

import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Alarm {
    private static ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1); // 使用ScheduledExecutorService

    public static void main(String[] args) {
        setMinute(1);
    }

    public static void setMinute(int min) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, min);
        Date alarmDate = calendar.getTime();
        setTime(alarmDate);
    }
    
    public static void setTime(String alarmTime) {
        setTime(DateTime.StringToDate(alarmTime));
    }
    public static void setTime(Date alarmDate) {
        // 設置鬧鐘時間

        // 計算當前時間和鬧鐘時間之間的延遲
        long delay = alarmDate.getTime() - System.currentTimeMillis();
        if (delay > 0) {
            Alarm alarm = new Alarm();
            alarm.scheduleAlarm(delay, alarmDate); // 排程鬧鐘
            TextToSpeech.speak("鬧鐘已設置，將在 " + DateTime.DateToString(alarmDate) + " 響起");
        } else {
            TextToSpeech.speak("設定的鬧鐘時間已經過去了！");
        }
    }

    // 排程鬧鐘任務
    public  void scheduleAlarm(long delay, Date alarmDate) {
        scheduler.schedule(() -> {
            TextToSpeech.speak("鬧鐘響了！");

            while (true) {
                Scanner scn = new Scanner(System.in);
                
                TextToSpeech.speak("輸入 'y' 關閉鬧鐘 或 'n' 貪睡");

                String tmp = scn.next();
                if (tmp.equals("y")) {
                    // 關閉鬧鐘
                    TextToSpeech.speak("請稍候!正在生成今日的播報");
                    broadcast.play();
                    scheduler.shutdown();
                    break;
                } else if (tmp.equals("n")) {
                    // 貪睡五分鐘
                    //TextToSpeak.speak("貪睡五分鐘");
                    //scheduleSnooze(5 * 60 * 1000, alarmDate); // 5分鐘後再次響起

                    TextToSpeech.speak("貪睡20秒");
                    scheduleSnooze(20 * 1000, alarmDate);
                    break;
                } else {
                    System.out.println("無效輸入，請重新輸入 'y' 或 'n'");
                }
            }
        }, delay, TimeUnit.MILLISECONDS);
    }

    // 排程貪睡任務
    public void scheduleSnooze(long delay, Date alarmDate) {
        scheduler.schedule(() -> {
            TextToSpeech.speak("貪睡時間到了！");

            while (true) {
                Scanner scn = new Scanner(System.in);
                TextToSpeech.speak("輸入 'y' 關閉鬧鐘 或 'n' 貪睡");

                String tmp = scn.next();
                if (tmp.equals("y")) {
                    // 關閉鬧鐘
                    TextToSpeech.speak("請稍候!正在生成今日的播報");
                    broadcast.play();
                    scheduler.shutdown();
                    break;
                } else if (tmp.equals("n")) {
                    // 貪睡五分鐘
                    //TextToSpeak.speak("貪睡五分鐘");
                    //scheduleSnooze(5 * 60 * 1000, alarmDate); // 5分鐘後再次響起

                    TextToSpeech.speak("貪睡20秒");
                    scheduleSnooze(20 * 1000, alarmDate);
                    break;
                } else {
                    System.out.println("無效輸入，請重新輸入 'y' 或 'n'");
                }
            }
        }, delay, TimeUnit.MILLISECONDS);
    }

    public void cancel() {
        TextToSpeech.speak("鬧鐘已關閉");
        scheduler.shutdownNow();
        scheduler = Executors.newScheduledThreadPool(1);
  
    }
}


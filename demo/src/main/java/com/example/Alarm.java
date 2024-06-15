package com.example;

import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Alarm {
    private static ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1); // 使用ScheduledExecutorService

    public static void setMinute(int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, minute);
        Date alarmDate = calendar.getTime();
        setTime(alarmDate);
    }

    public static void setTime(String alarmTime) {
        setTime(DateTime.StringToDate(alarmTime, "HH:mm"));
    }

    public static void setTime(Date alarmDate) {
        long delay = alarmDate.getTime() - System.currentTimeMillis();
        if (delay > 0) {
            Alarm alarm = new Alarm();
            alarm.scheduleAlarm(delay, alarmDate);
            TextToSpeak.speak("鬧鐘已設置，將在 " + DateTime.DateToString(alarmDate, "HH:mm:ss") + " 響起");
        } else {
            TextToSpeak.speak("設定的鬧鐘時間已經過去了！");
        }
    }

    public static void setTimer(int Minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, Minute);
        Date timerDate = calendar.getTime();

        long delay = timerDate.getTime() - System.currentTimeMillis();
        if (delay > 0) {
            Alarm alarm = new Alarm();
            alarm.scheduleTimer(delay, timerDate);
            TextToSpeak.speak("計時器已設置，將在 " + DateTime.DateToString(timerDate, "HH:mm:ss") + " 響起");
        }
    }

    public void scheduleAlarm(long delay, Date alarmDate) {
        scheduler.schedule(() -> {
            TextToSpeak.speak("鬧鐘響了！");

            synchronized (App.lock) {
                while (true) {
                    Scanner scn = new Scanner(System.in);
                    
                    TextToSpeak.speak("輸入 'y' 關閉鬧鐘 或 'n' 貪睡");
                    Character tmp = scn.next().charAt(0);
                    if (tmp.equals('y')) {
                        broadcast.play();
                        scheduler.shutdown();
                        scn.close();
                        App.lock.notify(); // 通知App繼續執行
                        break;
                    } else if (tmp.equals('n')) {
                        TextToSpeak.speak("貪睡20秒");
                        scheduleSnooze(20 * 1000, alarmDate);
                        break;
                    } else {
                        System.out.println("無效輸入，請重新輸入 'y' 或 'n'");
                    }
                }
            }
        }, delay, TimeUnit.MILLISECONDS);
    }

    public void scheduleSnooze(long delay, Date alarmDate) {
        scheduler.schedule(() -> {
            TextToSpeak.speak("貪睡時間到了！");
            synchronized (App.lock) {
                while (true) {
                    Scanner scn = new Scanner(System.in);
                    TextToSpeak.speak("輸入 'y' 關閉鬧鐘 或 'n' 貪睡");

                    Character tmp = scn.next().charAt(0);
                    
                    if (tmp.equals('y')) {
                        broadcast.play();
                        scheduler.shutdown();
                        scn.close();
                        App.lock.notify(); // 通知App繼續執行
                        break;
                    } else if (tmp.equals('n')) {
                        TextToSpeak.speak("貪睡20秒");
                        scheduleSnooze(20 * 1000, alarmDate);
                        break;
                    } else {
                        System.out.println("無效輸入，請重新輸入 'y' 或 'n'");
                    }
                }
            }
        }, delay, TimeUnit.MILLISECONDS);
    }
    public void scheduleTimer(long delay, Date alarmDate) {
        scheduler.schedule(() -> {
            TextToSpeak.speak("時間到了！");
            scheduler.shutdown();
            App.lock.notify(); // 通知App繼續執行
        }, delay, TimeUnit.MILLISECONDS);
    }

    public void cancel() {
        TextToSpeak.speak("鬧鐘已關閉");
        scheduler.shutdownNow();
        scheduler = Executors.newScheduledThreadPool(1);
    }
}




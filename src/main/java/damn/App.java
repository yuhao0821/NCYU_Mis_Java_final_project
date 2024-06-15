package damn;

import java.util.Scanner;

public class App {
    static final Object lock = new Object();
    static Scanner scn = new Scanner(System.in);
    static Character choose = '0';

    public static void main(String[] args) {
        while (choose != '5')
            getUserSelection();
    }

    public static void setAlarm(String Time) {
        Alarm alarm = new Alarm();
        Alarm.setTime(Time);

        synchronized (lock) {
            try {
                lock.wait(); // 等待闹钟事件
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public static void setTimer(int minute) {
        Alarm timer = new Alarm();
        Alarm.setTimer(minute);
    }

    private static void getUserSelection() {
        System.out.println("Hello! 請選擇功能 1.設定鬧鐘 2.設定倒計時 3.收聽今天的日報 4.我想聊天 5.Bye~");
        choose = scn.next().charAt(0);
        switch (choose) {
            case '1':
                String Time = scn.next();
                setAlarm(Time);
                break;

            case '2':
                System.out.println("請輸入分鐘數");
                int minute = scn.nextInt();
                if (minute < 1) System.out.println("輸入錯誤喔");
                else setTimer(minute);
                break;

            case '3':
                broadcast.play();
                break;

            case '4':

                break;

            case '5':
                System.out.println("ByeBye~");
                break;

            default:
                System.out.println("輸入錯誤喔");
        }
    }
}


package com.example;

import java.util.Scanner;

import javax.swing.JFrame;
/**
 * Hello world!
 *
 */
public class App {
    


    static Scanner scn = new Scanner(System.in);
    static String choose = "0";
    static String tmpString;

    // 锁对象
    static final Object lock = new Object();

    public static void setUpFrame(){
        JFrame frm = new JFrame("Weather Waker");
        frm.setSize(800,450);
        frm.setVisible(true);
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args){
        setUpFrame();
        while(choose.charAt(0)!='e')Functions();

        
    }

    public static void setAlarm(String Time){
        Alarm alarm = new Alarm();
        alarm.setTime(Time);
        
        synchronized (lock) {
            try {
                lock.wait(); // 等待闹钟事件
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static void setTimer(int minute){
        Alarm timer = new Alarm() ;
        timer.setTimer(minute);
    }

    public static void Functions(){
        System.out.println("Hello! 請選擇功能 1.設定鬧鐘 2.設定倒計時 3.收聽今天的日報 \n"+
                           "4.查詢課表 5.新增課表 6.刪除課表 7.查詢代辦事項 8.新增代辦事項 9.刪除代辦事項");
        choose = scn.nextLine();
        switch(choose.charAt(0)){
            case '1':
                String Time = scn.next();
                setAlarm(Time);
                break;

            case '2':
                System.out.println("請輸入分鐘數");
                int minute = scn.nextInt();
                if(minute<1)System.out.println("輸入錯誤喔");
                else setTimer(minute);
                break;

            case '3':
                broadcast.play();
                break;

            case '4':
                for(int i=0;i<7;i++){
                    try {
                        if(ClassTable.len(Integer.toString(i+1))==1){
                            System.out.printf("星期%d:無課程\n",i+1);
                        }else{
                            System.out.printf("星期%d:%s\n",i+1,ClassTable.read(Integer.toString(i+1)).substring(2));
                        }
                    } catch (Exception e) {
                        System.out.printf("星期%d:無課程\n",i+1);
                    }
                }
                break;
            case '5':

                while(true){
                    System.out.println("是星期幾的課呢?(星期天是7)");
                    choose = scn.nextLine();
                    if(choose.charAt(0)-48<1||choose.charAt(0)-48>7)System.out.println("輸入錯誤喔");
                    else break;
                }
                System.out.println("請輸入課程名稱，請不要輸入空白鍵及逗號:");
                tmpString = scn.nextLine();

                try {
                    ClassTable.addClass(Integer.toString(choose.charAt(0)-48), new String(ClassTable.Big5ToUTF8(tmpString),"UTF-8"));
                    System.out.println("新增成功!");
                } catch (Exception e) {
                    System.out.println("新增失敗!");
                }

                break;
            case '6':
            while(true){
                for(int i=0;i<7;i++){
                    try {
                        if(ClassTable.len(Integer.toString(i+1))==1){
                            System.out.printf("星期%d:無課程\n",i+1);
                        }else{
                            System.out.printf("星期%d:%s\n",i+1,ClassTable.read(Integer.toString(i+1)).substring(2));
                        }
                    } catch (Exception e) {
                        System.out.printf("星期%d:無課程\n",i+1);
                    }
                }
                System.out.println("\n要刪除星期幾的課呢?(星期天是7)");
                choose = scn.nextLine();
                if(choose.charAt(0)-48<1||choose.charAt(0)-48>7)System.out.println("輸入錯誤喔");
                else break;

            }
            System.out.println("請輸入要刪除的課程名稱:");
            tmpString = scn.nextLine();

            try {
                ClassTable.deleteClass(Integer.toString(choose.charAt(0)-48), new String(ClassTable.Big5ToUTF8(tmpString),"UTF-8"));
                System.out.println("新增成功!");
            } catch (Exception e) {
                System.out.println("新增失敗!");
            }
            case '7':
            try {
                System.out.println("所有代辦事項:");
                String[] list =ToDoList.Scan();
                for(int i=0;i<list.length;i++){
                    System.out.println(list[i]+": "+ToDoList.read(list[i]));
                }
                break;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("查詢失敗!");
                break;
            }
            case '8':
                try {
                    System.out.println("請輸入要新增的日期:(格式:yyyy-MM-dd)");
                    choose = scn.nextLine();
                    System.out.println("請輸入代辦事項，請不要輸入空白鍵及逗號:");
                    tmpString = scn.nextLine();
                    ToDoList.addTask(choose, tmpString);
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("新增失敗!");
                    break;
                }

            case '9':
                try {
                    String[] list =ToDoList.Scan();
                    for(int i=0;i<list.length;i++){
                        System.out.println(list[i]+": "+ToDoList.read(list[i]));
                    }
                    System.out.println("請輸入要刪除的代辦的日期:(格式:yyyy-MM-dd)");
                    choose = scn.nextLine();
                    System.out.println("請輸入要刪除的代辦事項，請不要輸入空白鍵及逗號:");
                    tmpString = scn.nextLine();
                    ToDoList.deleteTask(choose, tmpString);
                    break;
                } catch (Exception e) {
                    
                    e.printStackTrace();
                    System.out.println("刪除失敗!");
                    break;
                }
            default:
            System.out.println("輸入錯誤喔，請再試一次");
//            TextToSpeak.speak(langchain4j.ChatGPT("你是一個應用程式的語音助手，請根據以下的用戶輸入判斷用戶的需求，為用戶生成對應的程式操作指令:\n" + "操作指令說明與格式如下:\n" +"1. 設定鬧鐘: 1,{鬧鐘時間}\n" +"2. 設定倒計時: 2,{倒計時分鐘數}\n" +"3. 收聽今天的日報: 3\n\n" +"範例輸入與輸出:\n" +"範例輸入1: \"幫我設定三分鐘的計時器\"\n" +"範例輸出1: \"2,3\"\n\n" +"範例輸入2: \"幫我設定十點半的鬧鐘\"\n" +"範例輸出2: \"1,10:30\"\n\n" +"範例輸入3: \"我想聽日報\"\n" +"範例輸出3: \"3\"\n\n" +"- 用戶輸入: " + choose + "\n\n" +"請根據用戶輸入生成操作指令，輸出功能編號和所需的資料。"));

        }
    }
}


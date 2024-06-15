package com.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;


public class ClassTable {
    public static void main(String[] args) throws IOException {
        Scanner scn = new Scanner(System.in);
        try {
            
            writeClass("1", "1,國文,英文");
            System.out.println(read("1"));

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                // 处理中断异常，例如打印异常信息或进行其他处理
                e.printStackTrace();
            }
            addClass("1", "數學");
            System.out.println(read("1"));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                // 处理中断异常，例如打印异常信息或进行其他处理
                e.printStackTrace();
            }
            deleteClass("1", "國文");
            System.out.println(read("1"));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                // 处理中断异常，例如打印异常信息或进行其他处理
                e.printStackTrace();
            }
            writeClass("1", "");
            System.out.println(read("1"));

            addClass("1", new String(Big5ToUTF8(scn.nextLine()),"UTF-8"));
            System.out.println(read("1"));
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int len(String weekday) throws IOException {
        String filePath = System.getProperty("user.dir") + File.separator + "ClassTable" + File.separator + weekday + ".txt";
        try (FileReader reader = new FileReader(filePath)) {
            char[] data = new char[200];
            int len = reader.read(data);
            return len;
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
            return -1;
        }
    }
    public static void writeClass(String weekday,String content) throws IOException {
        String filePath = System.getProperty("user.dir") + File.separator + "ClassTable" + File.separator + weekday + ".txt";
        System.out.println("Writing to file: " + filePath);
        try (FileWriter writer = new FileWriter(filePath)) {
            if(len(weekday)<0&& content == ""){
                writer.write(weekday);
            }else{
                writer.write(content);
            }
        }
    }
    public static String read(String weekday) throws IOException {
        String filePath = System.getProperty("user.dir") + File.separator + "ClassTable" + File.separator + weekday + ".txt";
        try (FileReader reader = new FileReader(filePath)) {
            char[] data = new char[200];
            int len = reader.read(data);
            //if(len>-1)System.out.println(new String(data, 0, len));
            return new String(data, 0, len);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
            return null;
        }
    }
    
    public static void addClass(String weekday,String Class) throws IOException {
        if(len(weekday)<0){
            writeClass(weekday, weekday+","+Class);
        }else{
            writeClass(weekday, read(weekday)+","+Class);
        }
        
    }
    public static void deleteClass(String weekday,String Class) throws IOException {
        writeClass(weekday,read(weekday).replace(","+Class, ""));    
    }
    
    public static byte[] Big5ToUTF8(String input) throws UnsupportedEncodingException {
        // 將 Big5 字串轉換為字節數組（使用 Big5 編碼）
        byte[] big5Bytes = input.getBytes("Big5");

        // 將字節數組轉換為 UTF-8 字節數組
        return new String(big5Bytes, "Big5").getBytes("UTF-8");
    }
}

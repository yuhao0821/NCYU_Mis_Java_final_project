package com.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ToDoList {
    public static int len(String Date) throws IOException {
        String filePath = System.getProperty("user.dir") + File.separator + "ToDoList" + File.separator + Date ;
        try (FileReader reader = new FileReader(filePath)) {
            char[] data = new char[200];
            int len = reader.read(data);
            return len;
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
            return -1;
        }
    }
    public static void writeTaskList(String Date,String content) throws IOException {
        String filePath = System.getProperty("user.dir") + File.separator + "ToDoList" + File.separator + Date + ".txt";
        System.out.println("Writing to file: " + filePath);
        try (FileWriter writer = new FileWriter(filePath)) {
                writer.write(content);
        }
    }

    public static String read(String Date) throws IOException {
        String filePath = System.getProperty("user.dir") + File.separator + "ToDoList" + File.separator + Date;
        try (FileReader reader = new FileReader(filePath)) {
            char[] data = new char[200];
            int len = reader.read(data);
            if(len>-1)return new String(data, 0, len);
            else return "";
            
        } catch (FileNotFoundException e) {

            System.out.println("File not found: " + filePath);
            return "";
        }
    }
    public static String[] Scan() {
        File folder = new File(System.getProperty("user.dir") + File.separator + "ToDoList");

        if (folder.exists() && folder.isDirectory()) {
            // 列出文件夾中的所有文件
            String[] files = folder.list();

            return files;
        } else {
            // 文件夾不存在或不是一個文件夾
            return null;
        }
    }    
    public static void addTask(String Date,String content) throws IOException {
        String filePath = System.getProperty("user.dir") + File.separator + "ToDoList" + File.separator + Date + ".txt";
        
        File file = new File(filePath);

        // 檢查文件是否存在
        if (!file.exists()) {
            try {
                // 創建新文件
                if (file.createNewFile()) {
                    System.out.println("文件 " + filePath + " 創建成功。");
                } else {
                    System.out.println("文件 " + filePath + " 創建失敗。");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("文件 " + filePath + " 已存在。");
        }
        // 寫入文件
        writeTaskList(Date, read(Date+".txt")+","+content);
        System.out.println(Date+",  "+ read(Date+".txt")+","+content);

    }
    public static void deleteTask(String Date,String content) throws IOException {
        
        writeTaskList(Date,read(Date+".txt").replace(","+content, ""));
        String filePath = System.getProperty("user.dir") + File.separator + "ToDoList" + File.separator + Date + ".txt";
        File file = new File(filePath);
        
        if (file.exists() && file.length() <= 1) {
            // 文件存在且為空，刪除文件
            if (file.delete()) {
                System.out.println("空文件 " + filePath + " 已刪除。");
            } else {
                System.out.println("刪除空文件 " + filePath + " 失敗。");
            }
        } else {
            System.out.println("文件 " + filePath + " 不為空或不存在。");
        }
    
    }
    
}

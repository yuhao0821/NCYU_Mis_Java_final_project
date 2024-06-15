package damn;

import io.ikfly.constant.OutputFormat;
import io.ikfly.constant.VoiceEnum;
import io.ikfly.model.SSML;
import io.ikfly.service.TTSService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TextToSpeak {

    public static void speak(String text) {
        System.out.println(text);

        TTSService ts = new TTSService();
        ts.sendText(SSML.builder().outputFormat(OutputFormat.audio_24khz_48kbitrate_mono_mp3).synthesisText(text).outputFileName("音檔暫存").voice(VoiceEnum.zh_TW_HsiaoChenNeural).usePlayer(true) // 语音播放
                .build());

        ts.close();
        FileDelete("音檔暫存.mp3");
    }

    public static void FileDelete(String FileName) {
        // 獲取專案根目錄
        String projectRoot = System.getProperty("user.dir");

        // 建立檔案的絕對路徑
        Path filePath = Paths.get(projectRoot, FileName);
        System.out.println("Deleting: " + filePath);
        try {
            // 刪除檔案
            Files.deleteIfExists(filePath);
            //System.out.println("File deleted successfully: " + filePath);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.err.println("Failed to delete the file: " + filePath);
        }
    }
}
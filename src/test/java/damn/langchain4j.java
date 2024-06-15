package damn;

import dev.langchain4j.model.openai.OpenAiChatModel;

public class langchain4j {
    public static String ChatGPT(String prompt) {

        String apiKey = "demo";
        OpenAiChatModel model = OpenAiChatModel.withApiKey(apiKey);
        String answer = model.generate(prompt);
        //System.out.println(answer); 
        return(answer); 
    }
}

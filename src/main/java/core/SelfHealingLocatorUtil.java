package core;// SelfHealingLocatorUtil.java
import okhttp3.*;
import org.json.JSONObject;

public class SelfHealingLocatorUtil {
    private static final String OPENAI_API_KEY = "local";
    private static final String OPENAI_API_URL = "http://10.154.2.98:11434/v1/chat/completions";

    public static String getAlternativeLocator(String failedLocator, String pageHtml) throws Exception {
        OkHttpClient client = new OkHttpClient();

//        String prompt = "The following Selenium locator failed: " + failedLocator +
//                "\nHere is the HTML snippet:\n" + pageHtml +
//                "\nSuggest a new element selector to locate the same element.Respond with only the locator string. Example for the Locator string would be: //*[@id='id Attribute']";
        String prompt = "Below is a portion of an HTML page. Current locator"+failedLocator+" is failing, generate the updated locator using the following preferences in order:\n" +
                "1. ID\n" +
                "2. Name\n" +
                "3. Label-based XPath\n" +
                "4. Unique class or attribute\n" +
                "5. Relative XPath (short and readable)\n\n" +
                "HTML:\n" +
                pageHtml+"\n Your response Strictly follow the output instructions: ONLY LOCATOR STRING should be the output. NO Opening and Closing statement or explanation should be there.";

        JSONObject message = new JSONObject()
                .put("role", "System")
                .put("content", "You are a helpful assistant that generates alternative locators for Selenium tests based on failed locators and HTML snippets.")
                .put("role", "user")
                .put("content", prompt);

        JSONObject payload = new JSONObject()
                .put("model", "qwen3:8b")
                .put("messages", new org.json.JSONArray().put(message))
                .put("max_tokens", 2000);

        RequestBody body = RequestBody.create(
                payload.toString(),
                MediaType.parse("application/json")
        );

        Request request = new Request.Builder()
                .url(OPENAI_API_URL)
                .addHeader("Authorization", "Bearer " + OPENAI_API_KEY)
                .addHeader("Content-Type", "application/json")
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {

            String responseBody = response.body().string();
            System.out.println(responseBody);
//            String cleaned = responseBody.replaceAll("<think>[\\s\\S]*?</think>", "").trim();
            JSONObject json = new JSONObject(responseBody);
            String locator = json
                    .getJSONArray("choices")
                    .getJSONObject(0)
                    .getJSONObject("message")
                    .getString("content")
                    .trim();
            String cleaned = locator.replaceAll("<think>[\\s\\S]*?</think>", "").trim();

            System.out.println(cleaned);
            return cleaned;
        }
    }
}
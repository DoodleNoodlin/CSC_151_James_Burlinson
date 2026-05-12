//James Burlinson, this program runs a functional chatbot using a gemini api key, 5/6/26

package labs.example.geminiChatbot;

import java.net.URI;
import java.net.URISyntaxException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class GeminiChatbot {
    private static final String API_KEY = "AIzaSyCb4IXDJEDm5mJb97JOSfGQNDjSoYiUpFY";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Gemini: Hello! How can I help you today? (Type 'exit' to quit)");

        while (true) {
            System.out.print("You: ");
            String userInput = scanner.nextLine().trim();
            if (userInput.equalsIgnoreCase("exit")) {
                System.out.println("Gemini: Goodbye!");
                break;
            }
            if (userInput.isEmpty()) {
                continue;
            }
            try {
                String response = callGemini(userInput);
                String parsedResponse = parseGeminiResponse(response);
                System.out.println("Gemini: " + parsedResponse);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        scanner.close();
    }

    private static String callGemini(String prompt) throws IOException, URISyntaxException {
        String urlString = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=" + API_KEY;
        URI uri = new URI(urlString);
        URL url = uri.toURL();
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        String jsonInput = "{\"contents\":[{\"parts\":[{\"text\":\"" + escapeJson(prompt) + "\"}]}]}";

        try (OutputStream os = conn.getOutputStream()) {
            os.write(jsonInput.getBytes(StandardCharsets.UTF_8));
        }

        int responseCode = conn.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    response.append(line);
                }
                return response.toString();
            }
        } else {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getErrorStream(), StandardCharsets.UTF_8))) {
                StringBuilder error = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    error.append(line);
                }
                throw new IOException("HTTP error: " + responseCode + " - " + error.toString());
            }
        }
    }

    private static String parseGeminiResponse(String json) {
        // Manual parsing for the Gemini response JSON
            try {
            int textStart = json.indexOf("\"output\":\"");
            if (textStart >= 0) {
                textStart += 10;
            } else {
                textStart = json.indexOf("\"text\":\"");
                if (textStart >= 0) {
                    textStart += 8;
                }
            }
            if (textStart < 0) {
                return "No response text found.";
            }
            int textEnd = json.indexOf("\"", textStart);
            if (textEnd == -1) {
                return "Malformed response.";
            }
            String text = json.substring(textStart, textEnd);
            text = text.replace("\\n", "\n").replace("\\\"", "\"").replace("\\\\", "\\");
            return text;
        } catch (Exception e) {
            return "Error parsing response: " + e.getMessage();
        }
    }

    private static String escapeJson(String text) {
        return text.replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", "\\n").replace("\r", "\\r").replace("\t", "\\t");
    }
}
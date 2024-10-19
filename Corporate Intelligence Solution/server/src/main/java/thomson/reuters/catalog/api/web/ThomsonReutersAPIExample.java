package thomson.reuters.catalog.api.web;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class ThomsonReutersAPIExample  {

    private static final String API_URL = "https://api.thomsonreuters.com/v1/endpoint";  // Replace with the actual API URL
    private static final String API_KEY = "your-api-key-here";  // Replace with your API key

    private final RestTemplate restTemplate;

    public ThomsonReutersAPIExample(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public JsonObject getInvestmentData() {
        try {
            // Set headers
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + API_KEY);
            headers.set("Accept", "application/json");

            // Create an HttpEntity with the headers
            HttpEntity<String> entity = new HttpEntity<>(headers);

            // Make the GET request
            ResponseEntity<String> response = restTemplate.exchange(API_URL, HttpMethod.GET, entity, String.class);

            // Check for 200 OK response
            if (response.getStatusCode().is2xxSuccessful()) {
                String jsonResponse = response.getBody();
                // Parse the response using Gson
                JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
                return jsonObject;
            } else {
                throw new RuntimeException("Failed with status code: " + response.getStatusCode());
            }

        } catch (HttpClientErrorException e) {
            throw new RuntimeException("API request failed: " + e.getMessage());
        }
    }
}

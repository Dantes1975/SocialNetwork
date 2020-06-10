package eu.senla.socialnetwork.logger;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class LoggerProcessor {
    public static Object postHttpRequest(Object data, String url) {
        return postHttpRequest(data, url, null);
    }

    public static Object postHttpRequest(Object data, String httpUrl, Map<String, String> headersMetaData) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Accept-Charset", "utf-8");
        headers.add("Content-Type", "application/json");
        if (headersMetaData != null) {
            headersMetaData.entrySet().forEach(e -> {
                headers.add(e.getKey(), e.getValue());
            });
        }

        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<Object> request = new HttpEntity<>(data, headers);
        Object answer = restTemplate.postForObject(httpUrl, request, HttpStatus.class);
        return answer;
    }
}

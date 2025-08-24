package com.example.demo.service;

import com.example.demo.dto.BookDto;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

@Service
public class NaverApiService {

    @Value("${naver.api.client-id}")
    private String clientId;

    @Value("${naver.api.client-secret}")
    private String clientSecret;

    private final String NAVER_API_URL = "https://openapi.naver.com/v1/search/book.json";

    public List<BookDto> searchBooks(String query) throws JSONException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", clientId);
        headers.set("X-Naver-Client-Secret", clientSecret);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(NAVER_API_URL)
                .queryParam("query", query)
                .queryParam("display", 10); // 10개 결과

        ResponseEntity<String> response = restTemplate.exchange(
                uriBuilder.build().encode().toUri(),
                HttpMethod.GET,
                entity,
                String.class
        );

        return parseJsonResponse(response.getBody());
    }

    private List<BookDto> parseJsonResponse(String jsonResponse) throws JSONException {
        List<BookDto> bookList = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(jsonResponse);
        JSONArray items = jsonObject.getJSONArray("items");

        for (int i = 0; i < items.length(); i++) {
            JSONObject item = items.getJSONObject(i);
            BookDto bookDto = new BookDto();
            // HTML 태그 제거 및 필요한 정보 추출
            bookDto.setTitle(item.getString("title").replaceAll("<[^>]*>", ""));
            bookDto.setAuthor(item.getString("author").replaceAll("<[^>]*>", ""));
            bookDto.setPublisher(item.getString("publisher").replaceAll("<[^>]*>", ""));
            bookDto.setImage(item.getString("image"));
            bookDto.setIsbn(item.getString("isbn"));
            bookList.add(bookDto);
        }
        return bookList;
    }
}
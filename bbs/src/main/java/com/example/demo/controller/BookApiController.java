package com.example.demo.controller;

import com.example.demo.dto.BookDto;
import com.example.demo.service.NaverApiService;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookApiController {

    private final NaverApiService naverApiService;

    @GetMapping("/search")
    public List<BookDto> searchBooks(@RequestParam String query) throws JSONException {
        return naverApiService.searchBooks(query);
    }
}
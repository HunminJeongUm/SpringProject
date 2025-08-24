package com.example.demo.dto;

import lombok.Data;

@Data
public class BookDto {
    private String title;
    private String author;
    private String publisher;
    private String image; // 표지 이미지 URL
    private String isbn;  // 책 고유번호
}
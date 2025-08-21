package com.example.project0820;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Board {
    private Long id;
    private String author;
    private String title;
    private String content;
}
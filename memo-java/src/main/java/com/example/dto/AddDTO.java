package com.example.dto;

import lombok.Data;

@Data
public class AddDTO {
    private Long userId;

    private String title;

    private String description;

    private String content;
}

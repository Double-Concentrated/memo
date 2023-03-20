package com.example.dto;

import lombok.Data;

@Data
public class EditDTO {
    private Long memoId;

    private Long userId;

    private String title;

    private String description;

    private String content;
}

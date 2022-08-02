package com.example.weiboclone.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostRequestDto {
    private String contents;
    private MultipartFile data;
}

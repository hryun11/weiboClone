package com.example.weiboclone.dto.requestdto;

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

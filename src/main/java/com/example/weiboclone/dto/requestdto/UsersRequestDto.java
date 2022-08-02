package com.example.weiboclone.dto.requestdto;

import com.example.weiboclone.model.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UsersRequestDto {
    private String username;
    private String password;
    private String passwordCheck;
    private MultipartFile userProfileImage;

    public Users toEntity() {
        return new Users(username, password);
    }
}

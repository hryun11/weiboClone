package com.example.weiboclone.service;

import com.example.weiboclone.dto.requestdto.UsersRequestDto;
import com.example.weiboclone.model.Users;
import com.example.weiboclone.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Optional;
import java.util.regex.Pattern;

@Slf4j
@Transactional
@AllArgsConstructor
@Service
public class UsersService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final S3Uploader s3Uploader;

    public void join(UsersRequestDto requestDto) throws IOException {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();
        String passwordCheck = requestDto.getPasswordCheck();
        String pattern = "^[a-zA-Z0-9]*$";

        // 회원가입 조건
        if (username.length() < 3) {
            throw new IllegalArgumentException("닉네임을 3자 이상 입력하세요");
        } else if (!Pattern.matches(pattern, username)) {
            throw new IllegalArgumentException("알파벳 대소문자와 숫자로만 입력하세요");
        } else if (password.length() < 3) {
            throw new IllegalArgumentException("비밀번호를 3자 이상 입력하세요");
        } else if (password.contains(username)) {
            throw new IllegalArgumentException("비밀번호에 닉네임을 포함할 수 없습니다.");
        }

        // username 중복 확인
        checkDuplicateUsername(username);

        // 비밀번호, 비밀번호 재입력 확인
        checkPassword(password, passwordCheck);

        Users users = requestDto.toEntity();
        users.setPassword(bCryptPasswordEncoder.encode(requestDto.getPassword()));

        // 프로필 이미지 추가
        if (requestDto.getUserProfileImage() != null) {
            String profileUrl = s3Uploader.upload(requestDto.getUserProfileImage(), "profile");
            users.setUserProfileImage(profileUrl);
        }

        userRepository.save(users);
    }

    public void checkDuplicateUsername(String username) {
        Optional<Users> users = userRepository.findByUsername(username);
        if (users.isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 계정입니다.");
        }
    }

    private void checkPassword(String password, String passwordCheck) {
        if (!(passwordCheck.equals(password))) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }



}

package com.example.weiboclone.security;

import com.example.weiboclone.dto.PostRequestDto;
import com.example.weiboclone.dto.PostResponseDto;
import com.example.weiboclone.model.Posts;
import com.example.weiboclone.model.Users;
import com.example.weiboclone.repository.PostRepository;
import com.example.weiboclone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
@Component
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final S3Uploader s3Uploader;
    private final String POST_IMAGE_DIR = "static";

    // 게시글 작성
    @Transactional
    public void createPost(String username, PostRequestDto requestDto) throws IOException {
        Users users = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("없는 회원입니다."));

        String uploadImageUrl = s3Uploader.upload(requestDto.getData(), POST_IMAGE_DIR);

        Posts post = Posts.builder()
                .users(users)
                .content(requestDto.getContents())
                .image(uploadImageUrl)
                .build();

        postRepository.save(post);
    }


    // 게시글 상세 조회
    @Transactional(readOnly = true)
    public PostResponseDto findById(Long id) {
        Posts postEntity = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("글이 존재하지 않습니다"));

        return new PostResponseDto(postEntity);
    }
}
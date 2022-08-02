package com.example.weiboclone.controller;

import com.example.weiboclone.dto.PostsResponseDto;
import com.example.weiboclone.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RequiredArgsConstructor
@RestController
public class postsController {

    private final PostsService postsService;

    @GetMapping("/api/posts")
    public List<PostsResponseDto getposts(@RequestParam int page,
                                                       @RequestParam int size,
                                                       @RequestParam String sortby
                                                       ) {
        page -= page;
        return postsService.getposts(page, size, sortby);
    }
//    public List<PostsResponseDto> getposts(@PageableDefault(size = 10, direction = Sort.Direction.DESC)
//                                               Pageable pageable) {
//        return postsService.getposts(pageable);
//    }

}

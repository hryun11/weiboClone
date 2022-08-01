//package com.example.weiboclone.security;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class PostService {
//
//    private final MemberRepository memberRepository;
//    private final PostRepository postRepository;
//    private final ImgRepository imgRepository;
//    private final S3Service s3Service;
//
//    // 게시글 작성
//    @Transactional
//    public void uploadPost(PostRequestDto res, List<String> imgPaths) {
//        postBlankCheck(imgPaths);
//        System.out.println("로그인한 username : " + SecurityUtil.getCurrentUsername());
//
//        String username = SecurityUtil.getCurrentUsername();
//
//        Member member = memberRepository.findMemberByUsername(username).orElseThrow(
//                () -> new PrivateException(Code.NOT_FOUND_MEMBER)
//        );
//        String content = res.getContent();
//
//        Post post = new Post(content, member);
//        postRepository.save(post);
//
//        List<String> imgList = new ArrayList<>();
//        for (String imgUrl : imgPaths) {
//            Img img = new Img(imgUrl, post);
//            imgRepository.save(img);
//            imgList.add(img.getImgUrl());
//        }
//    }
//
//    private void postBlankCheck(List<String> imgPaths) {
//        if(imgPaths == null || imgPaths.isEmpty()){ //.isEmpty()도 되는지 확인해보기
//            throw new PrivateException(Code.WRONG_INPUT_IMAGE);
//        }
//    }
//}
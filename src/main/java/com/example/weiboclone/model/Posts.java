package com.example.weiboclone.model;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor (access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DynamicUpdate
@Builder
@Getter
@Setter
@Entity
public class Posts extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private Users users;

    @Builder.Default
    @OneToMany(mappedBy = "posts", fetch = FetchType.LAZY, cascade = CascadeType.ALL) //포스트 삭제시 댓글 삭제
    private List<Comment> comment = new ArrayList<>();

    @Column(nullable = true)
    private String image;

    @Column (nullable = true)
    private String content;

    @Column
    private Long commentcount;

    @Column
    private Long likescount;

    @Column
    private int likestate;


    public Posts(Long id, Users user, String image, String content, Long commentCount) {
        this.id = id;
        this.users = user;
        this.image = image;
        this.content = content;
        this.commentcount = commentCount;
    }


    public void minuslikecnt() {
        this.likescount -= 1;
    }
    public void likestate(int push) {
        this.likestate = push;    }
    public void pluslikecnt() {
        this.likescount += 1;
    }
}

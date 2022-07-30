package com.example.weiboclone.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@DynamicUpdate
@Data
@Entity
public class Posts extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "posts", fetch = FetchType.LAZY, cascade = CascadeType.ALL) //포스트 삭제시 댓글 삭제
    private List<Comment> comment = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private Users user;

    @Column
    private String image;

    @Column
    private String content;

    @Column
    private int commentCount;

    @Column
    private int likesCount;

    public Posts(Long id, Users user, String image, String content, int commentCount) {
        this.id = id;
        this.user = user;
        this.image = image;
        this.content = content;
        this.commentCount = commentCount;
    }

    public void addComment(Comment comment) {this.comment.add(comment);} //posts에 contents 내용을 넣어줌.
}

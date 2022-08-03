package com.example.weiboclone.model;

import com.example.weiboclone.dto.CommentsRequestsDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@NoArgsConstructor
@DynamicUpdate
@Getter
@Setter
@Entity
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private Users users;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "posts_id")
    private Posts posts;

    @Column
    private String contents;

    @Column
    private String username;

    @Column
    private String userprofileimage;

    @Column
    private Long postid;

        public Comment(CommentsRequestsDto requestsDto, Users users, Long postid) {
        this.contents = requestsDto.getContents();
        this.username = users.getUsername();
        this.userprofileimage = users.getUserProfileImage();
        this.postid = postid;
    }
}

package com.example.weiboclone.model;

import com.example.weiboclone.dto.CommentsRequestsDto;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    private LocalDateTime createdAt;

    public Comment(CommentsRequestsDto requestsDto, Posts posts) {
        this.contents = requestsDto.getContents();
        this.posts = posts;
    }
}

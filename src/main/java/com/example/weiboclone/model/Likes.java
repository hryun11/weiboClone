package com.example.weiboclone.model;

import com.example.weiboclone.dto.LIkeDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@NoArgsConstructor
@DynamicUpdate
@Data
@Entity
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "users_id")
//    private Users users;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "posts_id")
//    private Posts posts;

    @Column
    private Long userid;

    @Column
    private Long postid;


    public Likes(LIkeDto lIkeDto) {
        this.userid = lIkeDto.getUserid();
        this.postid = lIkeDto.getPostid();
    }
}

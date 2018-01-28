package com.myproject.forum.models;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "replies")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    private Long id;

    private String text;
    private Date date;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "topic_id")
    private Topic topic;

    public Reply(String text, Date date) {
        this.text = text;
        this.date = date;
    }

}

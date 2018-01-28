package com.myproject.forum.models;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "topic")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Topic {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(unique = true)
    private Long id;

    @NotNull
    private String name;
    @NotNull
    private Date date;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "category_name")
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "user_id")
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    private User user;

    @OneToMany(mappedBy = "topic" ,cascade = CascadeType.ALL)
    private Set<Reply> replies;

    public Topic(String name) {
        this.name = name;
    }

    public Topic(String name, Date date) {
        this.name = name;
        this.date = date;
    }
    public Topic(String name, Date date, Category category) {
        this.name = name;
        this.date = date;
        this.category = category;
    }
}

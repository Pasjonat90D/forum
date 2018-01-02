package com.myproject.forum.models;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "topic")
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

    public Topic() {
    }

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", category=" + category +
                '}';
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Reply> getReplies() {
        return replies;
    }

    public void setReplies(Set<Reply> replies) {
        this.replies = replies;
    }
}

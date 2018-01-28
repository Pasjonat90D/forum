package com.myproject.forum.models;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "category")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Category {

    @Id
    @Column(unique = true)
    private String name;
    private String description;

    @OneToMany(mappedBy = "category")
    @Cascade({org.hibernate.annotations.CascadeType.DELETE, org.hibernate.annotations.CascadeType.MERGE})
    private Set<Topic> topics;

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }

}

package com.farplayground.searchspecification.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import java.time.ZonedDateTime;

/**
 * @author farras
 * @since 0.0.1
 */

@Table(name = "profile")
@Entity
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Integer age;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    protected Profile() {
    }

    private Profile(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public static Profile createNewProfile(String name, Integer age){
        return new Profile(name, age);
    }
}
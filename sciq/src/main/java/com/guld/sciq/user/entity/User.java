package com.guld.sciq.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.guld.sciq.config.BaseEntity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_tb")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    @Column
    private Long id;
    
    @Column(nullable = false)
    private String email;
    
    @Column(nullable = false)
    @JsonIgnore
    private String password;
    
    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String nickName;
    
    @Column(nullable = true)
    private String schoolName;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserPrefer prefer;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    @Column(nullable = false)
    private int points = 0;

    @Column(nullable = false)
    private int level = 1;
}

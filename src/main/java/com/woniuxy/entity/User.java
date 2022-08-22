package com.woniuxy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    private String account;
    private String password;
    private String email;
    private String avatar;
    private int score;
    private String status;
    private String regtime;

}

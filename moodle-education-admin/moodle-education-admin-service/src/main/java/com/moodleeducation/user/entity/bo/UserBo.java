package com.moodleeducation.user.entity.bo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserBo {
    private String username;
    private String password;
    private String salt;
}

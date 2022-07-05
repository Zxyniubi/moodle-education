package com.moodleeducation.user.entity.bo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;



@Data
public class LoginBo implements Serializable {
    private static final long serialVersionUID = 1L;
    @NotNull(message = "用户名不能为空")
    private String username;
    @NotNull(message = "密码不能为空")
    private String password;
    @NotNull(message = "用户角色不能为空")
    private String role;

}

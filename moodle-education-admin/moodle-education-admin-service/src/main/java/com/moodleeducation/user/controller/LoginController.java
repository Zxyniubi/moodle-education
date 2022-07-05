package com.moodleeducation.user.controller;


import com.moodleeducation.commoncore.base.Result;
import com.moodleeducation.user.entity.bo.LoginBo;
import com.moodleeducation.user.service.UserCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/common")
public class LoginController {

    @Autowired
    private UserCommonService userCommonService;

    @PostMapping("/loginPassword")
    Result<String> loginPassword(@RequestBody LoginBo loginBo, BindingResult bindingResult){
        return userCommonService.loginPassword(loginBo,bindingResult);
    }
    @PostMapping("/logout")
    Result<String> logout(@RequestParam String username){
        return userCommonService.logout(username);
    }


}

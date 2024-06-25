package com.ohrray.controller;

import com.ohrray.domain.LoginDTO;
import com.ohrray.service.LoginService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
@NoArgsConstructor
public class LoginController {

    @Autowired
    private LoginService loginService;


    //로그인 페이지로 이동시켜준다.
    //getMapping 이 종료되면 mapping name 과 같은 html 찾아서 이동
    @GetMapping("/login")
    public void goLoginPage(){}

    //서비스에 넘겨줄것
    @PostMapping("/login")
    public void login(LoginDTO loginDTO){
        System.out.println("loginDTO     = " + loginDTO.getEmail());
        System.out.println("loginDTO.getPassword() = " + loginDTO.getPassword());
        loginService.login(loginDTO);
    }

    //회원가입페이지
    @PostMapping("/joinMember")
    public String joinMember(LoginDTO loginDTO){
        System.out.println("here is joinMember Controller");
        loginService.join(loginDTO);
        System.out.println("========================");
        System.out.println("result ??????");
        return "redirect:/member/login";
    }
}

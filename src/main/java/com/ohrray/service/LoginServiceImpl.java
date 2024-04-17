package com.ohrray.service;

import com.ohrray.entity.Member;
import com.ohrray.domain.LoginDTO;
import com.ohrray.repository.LoginRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@NoArgsConstructor
public class LoginServiceImpl implements LoginService{

    @Autowired
    private LoginRepository loginRepository;

    @Override
    public boolean validateMember(LoginDTO loginDTO) {
        System.out.println("여기로 넘어왔네요 아이디 비번 체크할거에요. ");
        System.out.println("loginDTO = " + loginDTO.getEmail());
        System.out.println("loginDTO.getPassword() = " + loginDTO.getPassword());
        Member member = Member.builder()
                .email(loginDTO.getEmail())
                .build();

        Optional<Member> validateResult = loginRepository.findById(member.getEmail());
        if(validateResult.isPresent())
            return true;
        else
            return false;
    }

    @Override
    public void login(LoginDTO loginDTO) {
        //서비스 로직은 여기서 쓰는거고
        //그거에 맞춰서 validate를 여기서 체크.
        System.out.println("here is login method");
        System.out.println("loginDTO = " + loginDTO.getEmail());
        System.out.println("===============================================service");
        if(validateMember(loginDTO)==true){
            System.out.println("success....!!");
        }else{
            System.out.println(".../.........back");
        }
    }

    @Override
    public void join(LoginDTO loginDTO) {
        System.out.println("here is service layout!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("==================================================s");
        System.out.println("loginDTO.getEmail() = " + loginDTO.getEmail());
        System.out.println("loginDTO.getPassword() = " + loginDTO.getPassword());
        System.out.println("======================================================e");
        Member member = Member.builder()
                .email(loginDTO.getEmail())
                .password(loginDTO.getPassword())
                .build();

        loginRepository.save(member);
    }

}

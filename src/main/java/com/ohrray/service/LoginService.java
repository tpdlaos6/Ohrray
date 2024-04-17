package com.ohrray.service;

import com.ohrray.domain.LoginDTO;

public interface LoginService {
    public boolean validateMember(LoginDTO loginDTO);
    public void login(LoginDTO loginDTO);
    public void join(LoginDTO loginDTO);
}

package com.greatlearning.employeemgmt.service;

import com.greatlearning.employeemgmt.model.User;

public interface AuthService {
	
    public String login(User user);

    public String register(User user);
}

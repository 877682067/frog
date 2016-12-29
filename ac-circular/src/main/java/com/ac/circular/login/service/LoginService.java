package com.ac.circular.login.service;

import com.ac.circular.login.entity.User;
import com.ac.common.MessageInfo;

public interface LoginService {

	MessageInfo validUserName(User user);

}

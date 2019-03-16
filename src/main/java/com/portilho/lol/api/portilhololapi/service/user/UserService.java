package com.portilho.lol.api.portilhololapi.service.user;

import com.portilho.lol.api.portilhololapi.model.user.UserModel;

import java.io.IOException;

public interface UserService
{
    String getUserByName(String username) throws IOException;

    UserModel getUserModelByName(String username);
}

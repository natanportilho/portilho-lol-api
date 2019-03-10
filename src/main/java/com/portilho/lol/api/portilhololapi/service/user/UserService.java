package com.portilho.lol.api.portilhololapi.service.user;

import java.io.IOException;

public interface UserService
{
    String getUserByName(String username) throws IOException;
}

package com.portilho.lol.api.portilhololapi.controller.user;

import com.portilho.lol.api.portilhololapi.exception.UserException;
import com.portilho.lol.api.portilhololapi.service.user.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
public class UserController
{
    @Resource
    private UserService userService;

    @RequestMapping("/userinfo")
    public String getUserInfo(@RequestParam String username){
        try
        {
            return userService.getUserByName(username);
        } catch (IOException e)
        {
            throw new UserException("User not found");
        }
    }
}

package com.portilho.lol.api.portilhololapi.controller.user;

import com.portilho.lol.api.portilhololapi.exception.UserException;
import com.portilho.lol.api.portilhololapi.facade.MatchesFacade;
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

    @Resource
    private MatchesFacade matchesFacade;

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

    @RequestMapping("/matchupsforuser")
    public String getAllMatchUpsForUser(@RequestParam String username){
//        This method should present all my match ups win rate.
//        1 - Check all my games this year
//        2 - get the champions I used for each game and check the enemy team champion in the same role
//        3 - Check who won.
//        4 - I should have a list which would have something like that: When I play Ezreal against Caitlyn my win rate is 64%.

        return matchesFacade.getAllMatchUpsForUser(username);
    }

}

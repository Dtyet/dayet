package cn.com.dayet.controller;

import cn.com.dayet.dto.AccesstTokenDTO;
import cn.com.dayet.dto.GIthupUser;
import cn.com.dayet.provider.GIthubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Dayet
 * @version 1.0
 * @date 2019/9/10 0:29
 */
@Controller
public class AuthorizeController {
    @Autowired
    private GIthubProvider gIthubProvider;
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code")String code,
                           @RequestParam(name = "state") String state){
        AccesstTokenDTO accesstTokenDTO = new AccesstTokenDTO();
        accesstTokenDTO.setClient_id("a4a8384dffdd71b1c8c1");
        accesstTokenDTO.setClient_secret("924875abac84b1d07093d663096f0bf1340e73ce");
        accesstTokenDTO.setCode(code);
        accesstTokenDTO.setRedirect_uri("http://localhost/callback");
        accesstTokenDTO.setState(state);
        String accessToken = gIthubProvider.getAccessToken(accesstTokenDTO);
        GIthupUser user = gIthubProvider.getuser(accessToken);
        System.out.println(user.getName());
        return "index";
    }
}

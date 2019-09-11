package cn.com.dayet.controller;

import cn.com.dayet.dto.AccesstTokenDTO;
import cn.com.dayet.dto.GIthupUser;
import cn.com.dayet.mapper.UserMapper;
import cn.com.dayet.provider.GIthubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Dayet
 * @version 1.0
 * @date 2019/9/10 0:29
 */
@Controller
public class AuthorizeController {
    //读取
    @Autowired
    private GIthubProvider gIthubProvider;
    private UserMapper userMapper;
    //读取配置中常量值
    @Value("${github.client.id}")
    private String Client_id;
    @Value("${github.client.secret}")
    private String setClient_secret;
    @Value("${github.redirect.url}")
    private String Redirect_uri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code")String code,
                           @RequestParam(name = "state") String state,
                            HttpServletRequest request){
        AccesstTokenDTO accesstTokenDTO = new AccesstTokenDTO();
        accesstTokenDTO.setClient_id(Client_id);
        accesstTokenDTO.setClient_secret(setClient_secret);
        accesstTokenDTO.setCode(code);
        accesstTokenDTO.setRedirect_uri(Redirect_uri);
        accesstTokenDTO.setState(state);
        String accessToken = gIthubProvider.getAccessToken(accesstTokenDTO);
        GIthupUser user = gIthubProvider.getuser(accessToken);
        if (user != null){
            //登录成功
            request.getSession().setAttribute("user",user);
            return "redirect:/";
        }else {
            //登录失败
            return "redirect:/";

        }
    }
}

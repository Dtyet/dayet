package cn.com.dayet.controller;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
/**
 * @author Dayet
 * @version 1.0
 * @date 2019/9/11 19:48
 */
@Controller
public class PubulishController {
    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }
}

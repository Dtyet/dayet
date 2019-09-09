package cn.com.dayet.provider;

import cn.com.dayet.dto.AccesstTokenDTO;
import cn.com.dayet.dto.GIthupUser;
import com.alibaba.fastjson.JSON;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author Dayet
 * @version 1.0
 * @date 2019/9/10 0:37
 */
@Component
public class GIthubProvider {
    public String getAccessToken(AccesstTokenDTO accesstTokenDTO){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType,JSON.toJSONString(accesstTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string=response.body().string();
            System.out.println(string);
            return  string;
        } catch (IOException e) {
            e.printStackTrace();
            }
        return null;
    }
    public GIthupUser getuser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                    .url("http://api.github.com/user?access_token="+accessToken)
                    .build();
        try (Response response = client.newCall(request).execute()) {
            String string= response.body().string();
            GIthupUser gIthupUser = JSON.parseObject(string, GIthupUser.class);
            return gIthupUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;


    }
}

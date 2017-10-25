package com.creat.outnet.component;

import com.creat.outnet.po.User;
import com.creat.outnet.utils.httputil.MyHttpClient;
import com.creat.outnet.utils.parsecode.ParseCode;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by whz on 2017/10/2.
 */
public class LoginRequest {

    private MyHttpClient client = new MyHttpClient();

    public String login(String userName, String userPassword) throws IOException, NoSuchFieldException, IllegalAccessException {
        String body = "";
        do{
            CloseableHttpResponse response1 = client.sendGet("http://jwkq.xupt.edu.cn:8080/Common/GetValidateCode");
            HttpEntity httpEntity1 = response1.getEntity();
            InputStream inputStream = httpEntity1.getContent();
            String code = ParseCode.getResultCode(inputStream);
            response1.close();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("UserName", userName);
            map.put("UserPassword", userPassword);
            map.put("ValiCode", code);
            map.put("json", true);
            CloseableHttpResponse response2 = client.sendPost("http://jwkq.xupt.edu.cn:8080/Account/Login", map, "utf-8",true);
            HttpEntity entity = response2.getEntity();
            body = EntityUtils.toString(entity, "utf-8");
        }while(body.contains("验证码输入错误"));
        return body;
    }
}

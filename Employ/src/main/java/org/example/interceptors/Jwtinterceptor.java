package org.example.interceptors;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;


/**
 * Created with IntelliJ IDEA.
 *
 * @Author: He Zhiyang
 * @Date: 2023/04/21/23:01
 * @Description:
 */
public class Jwtinterceptor implements HandlerInterceptor {
    @Resource
    org.example.jwt.JwtUtils jwtUtils;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取请求头令牌
        HashMap<String, Object> map = new HashMap<>();
        //       获取请求头的令牌
        String token = request.getHeader("token");
        try {
            DecodedJWT verify = jwtUtils.verify(token);
            map.put("state", true);
            map.put("msg", "请求成功");
            return true;
        } catch (SignatureVerificationException e) {
            e.printStackTrace();
            map.put("msg", "无效签名");
        } catch (TokenExpiredException e) {
            e.printStackTrace();
            map.put("msg", "token过期");
        } catch (AlgorithmMismatchException e) {
            e.printStackTrace();
            map.put("msg", "token算法不一致");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "无效签名");
        }
        map.put("state", false);
//        将map转为json jackjson
        String json = new ObjectMapper().writeValueAsString(map);
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().println(json);
        return false;
    }
}

package org.example.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: He Zhiyang
 * @Date: 2023/04/21/21:56
 * @Description:
 */
public class JwtUtils {
    /*生成token*/
    private static final String SING = "!Q@W3E4R%T^Y";

    public static String getToken(Map<String, String> map) {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE, 3);
        //创建jwtbuilder
        JWTCreator.Builder builder = JWT.create();
        map.forEach((k, v) -> {
            builder.withClaim(k, v);
        });
        String token = builder.withExpiresAt(instance.getTime())//过期时间
                .sign(Algorithm.HMAC256(SING));//sign

        return token;
    }

    /*验证*/
    public static DecodedJWT verify(String token) {
        return JWT.require(Algorithm.HMAC384(SING)).build().verify(token);
    }

//    /*获取信息*/
//    public static DecodedJWT getTokenInfo(String token) {
//      DecodedJWT verift=  JWT.require(Algorithm.HMAC384(SING)).build().verify(token);
//      return verift;
//    }
}

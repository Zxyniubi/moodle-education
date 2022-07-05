package com.moodleeducation.commoncore.tools;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Slf4j
public final class JWTUtils {

    private static final String TOKEN_SECRET="eyJhbGciOiJIUzI1NiJ9";
    private static final String ISSUER="ZXY";
    public static final String USERNO = "userNo";
    public static final Long DATE = 30 * 24 * 3600 * 1000L; // 1个月

    /**
     *管理员验证方法，加入了权限校验
     * @param userNo
     * @param date
     * @param permissionList
     * @return
     */
    public static String create(Integer userNo,Long date,String permissionList){
        try {
            return JWT.create().withIssuer(ISSUER)
                    .withClaim(USERNO,userNo.toString())
                    .withClaim("PERMISSION",permissionList).withExpiresAt(new Date(System.currentTimeMillis()+date))
                    .sign(Algorithm.HMAC256(TOKEN_SECRET));
        }catch (Exception e){
            log.error("JWT生成失败",e);
            return "" ;
        }
    }

    /**
     * 用户权限校验
     * @param userNo
     * @param date
     * @return
     */
    public static String create(Integer userNo,Long date){
        try {
            return JWT.create().withIssuer(ISSUER)
                    .withClaim(USERNO,userNo.toString())
                    .withExpiresAt(new Date(System.currentTimeMillis()+date))
                    .sign(Algorithm.HMAC256(TOKEN_SECRET));
        }catch (Exception e){
            log.error("JWT生成失败",e);
            return "" ;
        }
    }

    /**
     * 验证token
     * @param token
     * @return
     * @throws JWTVerificationException
     * @throws IllegalArgumentException
     * @throws UnsupportedEncodingException
     */
    public static DecodedJWT verify(String token) throws JWTVerificationException, IllegalArgumentException, UnsupportedEncodingException {
        return JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer(ISSUER).build().verify(token);
    }

    /**
     *
     * @param decodedJWT
     * @return
     */
    public static Integer getUserNo(DecodedJWT decodedJWT) {
        return Integer.valueOf(decodedJWT.getClaim(USERNO).asString());
    }


}

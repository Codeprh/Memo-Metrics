package noah.memo.framework.utils;

import io.jsonwebtoken.*;
import noah.memo.framework.controller.TokenUser;
import noah.memo.framework.log.Logger;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author chenyuhao
 * @date 2020-01-31 4:09 下午
 */
public class JwtUtil {

    private static final String SECRET_KEY = "memo_419@";

    private static final String SUBJECT = "memo";

    /**
     * 生成Jwt
     */
    public static String createJwt(long ttlMillis, TokenUser tokenUser) {
        //指定签名的时候使用的签名算法
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        // 获取当前时间戳
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        // 存入用户相关信息
        Map<String, Object> claims = new HashMap<>(4);
        claims.put("userId", tokenUser.getUserId());
        claims.put("loginName", tokenUser.getLoginName());

        JwtBuilder builder = Jwts.builder()
                .setClaims(claims)
                // 设置唯一性ID，避免Token重复。
                .setId(UUID.randomUUID().toString())
                // 签发时间
                .setIssuedAt(now)
                // 签发Jwt的主体
                .setSubject(SUBJECT)
                // 设置签名使用的签名算法和签名使用的秘钥
                .signWith(signatureAlgorithm, SECRET_KEY);
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            //设置过期时间
            builder.setExpiration(exp);
        }
        return builder.compact();
    }


    /**
     * Token的解密，转换成TokenUser
     */
    public static TokenUser parseJwt(String token) {
        //签名秘钥，和生成的签名的秘钥一模一样
        Jws<Claims> jws;
        try {
            jws = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
        } catch (Exception e) {
            Logger.error("Parse Jwt Error", e);
            return null;
        }
        Claims claims = jws.getBody();
        TokenUser user = new TokenUser();
        user.setUserId((Integer) claims.get("userId"));
        user.setLoginName((String) claims.get("loginName"));
        return user;
    }


    /**
     * 校验token中的签发人是不是memo
     */
    public static Boolean isVerify(String token) {
        //得到DefaultJwtParser
        Claims claims = Jwts.parser()
                //设置签名的秘钥
                .setSigningKey(SECRET_KEY)
                //设置需要解析的jwt
                .parseClaimsJws(token).getBody();

        return claims.getSubject().equals(SUBJECT);
    }


}

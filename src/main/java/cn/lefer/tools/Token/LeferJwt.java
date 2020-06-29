package cn.lefer.tools.Token;

import io.jsonwebtoken.*;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.util.Date;

/**
 * @author : lefer
 * @version : V1.0
 * @date :   2020/6/29
 * @Description : JWT工具类
 */
public class LeferJwt {
    public static String createToken(String subject, String userCode, String key, long ttlMillis) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        JwtBuilder builder = Jwts.builder()
                .setId(userCode)
                .setSubject(subject)   // 主题
                .setIssuer("lefer")     // 签发者
                .setIssuedAt(now)      // 签发时间
                .signWith(SignatureAlgorithm.HS256, new SecretKeySpec(DatatypeConverter.parseBase64Binary(key), SignatureAlgorithm.HS256.getJcaName())); // 签名算法以及密匙
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date expDate = new Date(expMillis);
            builder.setExpiration(expDate); // 过期时间
        }
        return builder.compact();
    }

    public static String refreshToken(String token,String key,long ttlMillis){
        return createToken(getSubjectFromToken(token,key),getUserFromToken(token,key),key,ttlMillis);
    }

    public static String getUserFromToken(String token, String key) {
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody().getId();
    }

    public static String getSubjectFromToken(String token, String key) {
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

    public static Date getExpirationDate(String token, String key){
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().getExpiration();
    }

    public static LeferJwtStatus isValid(String token, String key) {
        LeferJwtStatus status = new LeferJwtStatus();
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(token)
                    .getBody();
            status.setValid(true);
            status.setMessage(claims.toString());
        } catch (ExpiredJwtException e) {
            status.setMessage("Token已过期");
            status.setValid(false);
        } catch (SignatureException e) {
            status.setMessage("Token签名校验失败");
            status.setValid(false);
        } catch (Exception e) {
            status.setMessage("异常错误");
            status.setValid(false);
        }
        return status;
    }
}

package com.liveus.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Desc:
 * @author: Lenovo
 * @Time: 2019/11/12 9:42
 * @Copyright: © Liveus
 * @Warning: for fun
 */
public class JwtUtils {

    public static void main(String[] args) {
        Claims claims = verifyJwt(generateToken(1));
        System.out.println(claims.get("userId",Integer.class));
        System.out.println(claims.getExpiration()); //过期时间
        System.out.println(claims.getAudience());
        System.out.println(claims.getId()); //id
        System.out.println(claims.getSubject());// 主题
        System.out.println(claims.getIssuedAt()); //iat: jwt的签发时间
        System.out.println(claims.getIssuer());
        System.out.println(claims.getNotBefore());
    }

    // token 过期时间, 单位: 毫秒. 这个值表示 7 天
    private static final long TOKEN_EXPIRED_TIME = 7L * 24L * 60L * 60L * 1000L;
    // jwt 加密解密密钥
    private static final String JWT_SECRET = "MDk4ZjZiY2Q0NjIxZDM3M2NhZGU0ZTgzMjYyN2I0ZjY=";
    // jwt唯一标识
    private static final String JWT_ID = "liveus";
    // 开发者id
    private static final String OPEN_ID = "liveus";
    /**
    * @Desc:  创建jwt
    * @author: shenliqiang
    * @Time: 2019/11/12 15:37
    * @param claims,time
    * @return
    */

    /**
     * @Desc:  生成token
     * @author: shenliqiang
     * @Time: 2019/11/12 15:38
     * @param userId
     * @return
     */

    public static String generateToken(Integer userId) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("openId", OPEN_ID);
        return createJWT(map, TOKEN_EXPIRED_TIME);
    }

    public static String createJWT(Map<String, Object> claims, Long time) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256; //指定签名的时候使用的签名算法，也就是header那部分，jjwt已经将这部分内容封装好了。
        Date now = new Date(System.currentTimeMillis());
        SecretKey secretKey = generalKey();
        long nowMillis = System.currentTimeMillis();//生成JWT的时间
        //下面就是在为payload添加各种标准声明和私有声明了
        JwtBuilder builder = Jwts.builder() //这里其实就是new一个JwtBuilder，设置jwt的body
                .setClaims(claims)          // 如果有私有声明，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
                .setId(JWT_ID)               // 设置jti(JWT ID)：是JWT的唯一标识，根据业务需要，这个可以设置为一个不重复的值，主要用来作为一次性token,从而回避重放攻击。
                .setIssuedAt(now)           // iat: jwt的签发时间
                .signWith(signatureAlgorithm, secretKey);//设置签名使用的签名算法和签名使用的秘钥
        if (time >= 0) {
            long expMillis = nowMillis + time;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);     //设置过期时间
        }
        return builder.compact();
    }

    /**
    * @Desc:  验证jwt
    * @author: shenliqiang
    * @Time: 2019/11/12 15:38
    * @param token
    * @return  claims
    */

    public static Claims verifyJwt(String token) {
        //签名秘钥，和生成的签名的秘钥一模一样
        SecretKey key = generalKey();
        Claims claims;
        try {
            claims = Jwts.parser()  //得到DefaultJwtParser
                    .setSigningKey(key)         //设置签名的秘钥
                    .parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    /**
    * @Desc:  由字符串生成加密key
    * @author: shenliqiang
    * @Time: 2019/11/12 15:38
    * @param
    * @return
    */

    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.decodeBase64(JWT_SECRET);
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }
}

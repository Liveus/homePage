package com.liveus;

import io.jsonwebtoken.*;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;

/**
 * @Desc:
 * @author: Lenovo
 * @Time: 2019/11/11 17:24
 * @Copyright: © Liveus
 * @Warning: for fun
 */
public class JWTtest {
    public static void main(String[] args) {

        //密钥
        Key KEY = new SecretKeySpec("liveus".getBytes(), SignatureAlgorithm.HS512.getJcaName());

        //请求头
        Map<String, Object> stringObjectMap = new HashMap<>();
        stringObjectMap.put("type", "1");

        String payload = "{\"user_id\":\"1341137\", \"expire_time\":\"2018-01-01 0:00:00\"}";
        String compactJws = Jwts.builder().setHeader(stringObjectMap).setPayload(payload).signWith(SignatureAlgorithm.HS512, KEY).compact();

        System.out.println("jwt key:" + new String(KEY.getEncoded()));
        System.out.println("jwt payload:" + payload);
        System.out.println("jwt encoded:" + compactJws);


        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(KEY).parseClaimsJws(compactJws);
        JwsHeader header = claimsJws.getHeader();
        Claims body = claimsJws.getBody();

        System.out.println("jwt header:" + header);
        System.out.println("jwt body:" + body);
        System.out.println("jwt body user-id:" + body.get("user_id", String.class));
    }
}

package com.liveus.common.utils;

import io.jsonwebtoken.*;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Desc: token加密&解密
 * @author: Lenovo
 * @Time: 2019/11/12 9:20
 * @Copyright: © Liveus
 * @Warning: for fun
 */
public class TokenUtils {

    //sha512生成key
    private static Key KEY = new SecretKeySpec("liveus".getBytes(), SignatureAlgorithm.HS512.getJcaName());

    /**
    * @Desc:
    * @author: shenliqiang
    * @Time: 2019/11/12 9:23
    * @param userId
    * @return
    */

    public static String enode(String userId){
        // header
        Map<String, Object> stringObjectMap = new HashMap<>();
        stringObjectMap.put("type", "JWT");
        stringObjectMap.put("alg", "HS512");
        // 负荷
        String payload = "{\"user_id\":\"1341137\", \"expire_time\":\"2018-01-01 0:00:00\"}";
        String compactJws = Jwts.builder().setHeader(stringObjectMap).setPayload(payload).signWith(SignatureAlgorithm.HS512, KEY).compact();

        System.out.println("jwt key:" + new String(KEY.getEncoded()));
        System.out.println("jwt payload:" + payload);
        System.out.println("jwt encoded:" + compactJws);// jwt字符串
        return compactJws;
    }

    public static List<String> decode(String compactJws){
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(KEY).parseClaimsJws(compactJws);
        JwsHeader header = claimsJws.getHeader();
        Claims body = claimsJws.getBody();

        System.out.println("jwt header:" + header);
        System.out.println("jwt body:" + body);
        System.out.println("jwt body user-id:" + body.get("user_id", String.class));
        return null;
    }

}

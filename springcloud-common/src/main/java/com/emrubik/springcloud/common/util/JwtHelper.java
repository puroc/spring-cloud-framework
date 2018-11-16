package com.emrubik.springcloud.common.util;

import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.emrubik.springcloud.common.exception.JwtExpireException;
import com.emrubik.springcloud.common.exception.JwtParseException;
import com.emrubik.springcloud.common.exception.JwtSignException;
import com.emrubik.springcloud.domain.vo.JwtInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Data
@Component
public class JwtHelper {

    private static final String SECRET = "XX#$%()(#*!()!KL<><MQLMNQNQJQK sdfkjsdrow32234545fdf>?N<:{LWPW";

    private static final String EXP = "exp";

    private static final String PAYLOAD = "payload";

    @Value("${jwt.expire}")
    private String expire;

    //expire过期时间，单位：秒
    public String generateToken(JwtInfo jwtInfo) throws Exception {
        return sign(jwtInfo, Integer.parseInt(expire) * 1000);
    }

    public JwtInfo getInfoFromToken(String token) throws Exception {
        return unsign(token, JwtInfo.class);
    }

    //加密，传入一个对象和有效期
    private static <T> String sign(T object, long maxAge) throws JwtSignException {
        try {
            final JWTSigner signer = new JWTSigner(SECRET);
            final Map<String, Object> claims = new HashMap<String, Object>();
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(object);
            claims.put(PAYLOAD, jsonString);
            claims.put(EXP, System.currentTimeMillis() + maxAge);
            return signer.sign(claims);
        } catch (Throwable t) {
            throw new JwtSignException("jwt token加密失败", t);
        }
    }

    //解密，传入一个加密后的token字符串和解密后的类型
    private static <T> T unsign(String jwt, Class<T> classT) throws JwtExpireException, JwtParseException {
        final JWTVerifier verifier = new JWTVerifier(SECRET);
        try {
            final Map<String, Object> claims = verifier.verify(jwt);
            String json = null;
            if (claims.containsKey(EXP) && claims.containsKey(PAYLOAD)) {
                long exp = (Long) claims.get(EXP);
                long currentTimeMillis = System.currentTimeMillis();
                json = (String) claims.get(PAYLOAD);
//                if (exp > currentTimeMillis) {
//                    json = (String) claims.get(PAYLOAD);
//                } else {
//                    throw new JwtExpireException("jwt token过期");
//                }
            }
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json, classT);
//        } catch (JwtExpireException e) {
//            throw e;
        } catch (Throwable t) {
            throw new JwtParseException("jwt token解析失败", t);
        }
    }

}

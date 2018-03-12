package com.emrubik.springcloud.auth.client.jwt;

import com.emrubik.springcloud.auth.client.config.UserAuthConfig;
import com.emrubik.springcloud.auth.common.exception.UserTokenException;
import com.emrubik.springcloud.auth.common.util.jwt.IJWTInfo;
import com.emrubik.springcloud.auth.common.util.jwt.JWTHelper;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.security.SignatureException;

@Configuration
public class UserAuthUtil {
    @Autowired
    private UserAuthConfig userAuthConfig;
    public IJWTInfo getInfoFromToken(String token) throws Exception {
        try {
            return JWTHelper.getInfoFromToken(token, userAuthConfig.getPubKeyByte());
        }catch (ExpiredJwtException ex){
            throw new UserTokenException("User token expired!");
        }catch (SignatureException ex){
            throw new UserTokenException("User token signature error!");
        }catch (IllegalArgumentException ex){
            throw new UserTokenException("User token is null or empty!");
        }
    }
}

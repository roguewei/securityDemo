package com.winston.passwordencoder;

import com.winston.utils.MD5Util;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author Winston
 * @title: MyPasswordEncoder
 * @projectName winstonsecurity
 * @description: TODO
 * @date 2019/5/1223:42
 */
@Data
public class MyPasswordEncoder implements PasswordEncoder {

    private String salt = "qweasdzxcasd";

    @Override
    public String encode(CharSequence charSequence) {
        String str = "" + salt.charAt(2) + salt.charAt(4) + charSequence.toString() + salt.charAt(6) + salt.charAt(7);
        return MD5Util.md5(str);
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        String str = "" + salt.charAt(2) + salt.charAt(4) + charSequence.toString() + salt.charAt(6) + salt.charAt(7);
        return str.equals(s);
    }

}

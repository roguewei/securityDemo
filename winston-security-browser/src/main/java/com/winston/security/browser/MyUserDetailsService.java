package com.winston.security.browser;

import com.winston.mydemo.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName MyUserDetailsService
 * @Description
 * @Author Winston
 * @Date 2019/4/14 21:18
 * @Version 1.0
 **/
@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        System.out.println("登录用户名： "+ username);
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
//        return new org.springframework.security.core.userdetails.User(username, "123123",
//                authorities);
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("登录用户名： "+ username);
        System.out.println(passwordEncoder.encode("123123"));
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return new org.springframework.security.core.userdetails.User(username,
                passwordEncoder.encode("123123"),
                true, true, true, true,
                authorities);
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        System.out.println("登录用户名： "+ username);
//        User user = userDao.queryByUserName(username);
//        System.out.println("loadUserByUsername-----"+user.getUsername());
//        // AuthorityUtils.commaSeparatedStringToAuthorityList("admin")是测试生成权限的
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
//        return new org.springframework.security.core.userdetails.User("wei", user.getPassword(),
//                authorities);
//    }
}

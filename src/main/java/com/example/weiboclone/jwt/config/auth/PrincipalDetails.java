package com.example.weiboclone.jwt.config.auth;

import com.example.weiboclone.model.Users;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Data
public class PrincipalDetails implements UserDetails{

    private Users users;

    public PrincipalDetails(Users users) {
        this.users = users;
    }

    public Users getUsers() {
        return users;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//        users.getRoleList().forEach(r -> {
//            System.out.println("r: "+r);
//            authorities.add(()->{ return r;});
//        });
//        return authorities;
//    }




    @Override
    public String getPassword() {
        return users.getPassword();
    }

    @Override
    public String getUsername() {
        return users.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

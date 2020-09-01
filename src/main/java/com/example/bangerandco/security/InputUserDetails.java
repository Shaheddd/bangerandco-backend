package com.example.bangerandco.security;

import com.example.bangerandco.model.UserLogin;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;
import java.util.List;
import java.util.stream.Collectors;


@NoArgsConstructor
@Entity
@Data

public class InputUserDetails implements UserDetails {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String username;

    private String email;

    @JsonIgnore
    private String password;

    public InputUserDetails(int id, String username, String email, String password,
                            Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static InputUserDetails build(UserLogin userLogin) {
        List<GrantedAuthority> authorities = userLogin.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());

        return new InputUserDetails
                (
                userLogin.getId(),
                userLogin.getUsername(),
                userLogin.getEmail(),
                userLogin.getPassword(),
                authorities
        );
    }

    @Transient
    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if(o == null || getClass() != o.getClass())
            return false;
        InputUserDetails userDetails = (InputUserDetails) o;
        return Objects.equals(id, userDetails.id);
    }
}

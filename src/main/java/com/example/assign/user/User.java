package com.example.assign.user;

import com.example.assign.order.Order;
import com.example.assign.role.Role;
import com.example.assign.token.Token;
import com.example.assign.util.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Nationalized;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user")
public class User extends BaseEntity implements UserDetails {

    @Column(unique = true, nullable = false)
    private String username;
    @Column(length = 512)
    @Nationalized
    private String password;
    @Column
    private String email;
    @Column
    @Nationalized
    private String firstname;
    @Column
    @Nationalized
    private String lastname;
    @Column
    private Integer status;
    @Column(length = 512)
    @Nationalized
    private String address;
    @Column
    private String phone;
    @Column
    private String avatar;
    @Column
    @Temporal(TemporalType.DATE)
    private Date dob;
    @Column
    private String verify;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<Role> roles = new ArrayList<>();
    @OneToMany(mappedBy = "user")
    private List<Token> tokens = new ArrayList<>();
    @OneToMany(mappedBy = "user")
    private List<Order> orders = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getCode()))
                .toList();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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

package com.example.demo.domain.entity;

import com.example.demo.domain.entity.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.*;

@Data
@ToString(exclude = {"classes", "subscribes", "videos", "visits"})
@EqualsAndHashCode(exclude = {"classes", "subscribes", "videos", "visits"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usr")
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String fullname;

    @Column(nullable = false)
    @CreatedDate
    private Timestamp registrationDate;

    @Column(name = "role", nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "non_locked")
    private boolean nonLocked = true;

    @Column(name = "status")
    private String status;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<ClassEntity> classes;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<SubscribeEntity> subscribes;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<VideoEntity> videos;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<VisitEntity> visits;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<CommentEntity> comments;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Set.of(role);
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
        return nonLocked;
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


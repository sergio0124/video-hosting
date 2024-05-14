package com.example.demo.domain.entity;

import com.example.demo.domain.entity.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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

import java.sql.Timestamp;
import java.util.*;

@Data
@ToString(exclude = {"playlists", "permissions", "comments", "visits", "groups"})
@EqualsAndHashCode(exclude = {"playlists", "permissions", "comments", "visits", "groups"})
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

    private String imageUrl;
    
    @Column(unique = true)
    private String mail;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String fullname;

    @Column(nullable = false)
    @CreatedDate
    private Timestamp registrationDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean nonLocked = true;

    private boolean isActive = true;

    private String status;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<PlaylistEntity> playlists;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<PermissionEntity> permissions;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<VisitEntity> visits;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<CommentEntity> comments;

    @OneToMany(mappedBy="user", fetch = FetchType.LAZY)
    private List<GroupEntity> groups;

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
        return isActive;
    }
}


package com.example.demo.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Data
@ToString(exclude = {"permissions", "videos", "user"})
@EqualsAndHashCode(exclude = {"permissions", "videos", "user"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "playlist")
public class PlaylistEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @CreatedDate
    private Timestamp creationDate;

    private String description;
    
    private Boolean isPublic = false;

    @ManyToOne
    private UserEntity user;

    @OneToMany(mappedBy = "playlist", fetch = FetchType.LAZY)
    private List<PermissionEntity> permissions;

    @OneToMany(mappedBy = "playlist", fetch = FetchType.LAZY)
    private List<VideoEntity> videos;
}

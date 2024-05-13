package com.example.demo.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Data
@ToString(exclude = {"subscribes", "videos", "user"})
@EqualsAndHashCode(exclude = {"subscribes", "videos", "user"})
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
    
    private Boolean isPrivate = false;

    @ManyToOne
    private UserEntity user;

    @OneToMany(mappedBy = "vclass", fetch = FetchType.LAZY)
    private List<PermissionEntity> subscribes;

    @OneToMany(mappedBy = "vclass", fetch = FetchType.LAZY)
    private List<VideoEntity> videos;
}

package com.example.demo.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@ToString(exclude = {"user", "group", "playlist"})
@EqualsAndHashCode(exclude = {"user", "group", "playlist"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "permission")
public class PermissionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String description;

    @Column(nullable = false)
    @CreatedDate
    private Timestamp creationDate;

    private Timestamp inspirationDate;

    private Boolean isTemporary;

    private Boolean isAccepted = false;

    @ManyToOne
    private UserEntity user;

    @ManyToOne
    private GroupEntity group;

    @ManyToOne
    private PlaylistEntity playlist;
}

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
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@ToString(exclude = {"user", "vclass"})
@EqualsAndHashCode(exclude = {"user", "vclass"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "subscribe")
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

    @Column(name = "is_accepted")
    private Boolean isAccepted = false;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "class_id", referencedColumnName = "id", nullable = false)
    private PlaylistEntity vclass;
}

package com.example.demo.domain.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@ToString(exclude = { "user", "permissions" })
@EqualsAndHashCode(exclude = { "user", "permissions" })
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "group_table")
public class GroupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    @Column(nullable = false)
    @CreatedDate
    private Timestamp creationDate;

    private String description;

    @ManyToOne
    private UserEntity user;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PermissionEntity> permissions;

    @ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    @JoinTable(name = "group_user", joinColumns = { @JoinColumn(name = "group_id") }, inverseJoinColumns = {
	    @JoinColumn(name = "user_id") })
    private List<UserEntity> users = new ArrayList<>();
}

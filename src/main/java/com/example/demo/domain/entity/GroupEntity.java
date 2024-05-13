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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@ToString(exclude = {"video", "user"})
@EqualsAndHashCode(exclude = {"video", "user"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "group")
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
}

package com.example.demo.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@ToString(exclude = {"vclass", "user", "visits", "comments"})
@EqualsAndHashCode(exclude = {"vclass", "user", "visits", "comments"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "video")
public class VideoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String name;

    private String imageUrl;

    private String description;

    @Column(nullable = false)
    private String videoUrl;

    @CreationTimestamp
    private Timestamp creationTime;

    @ManyToOne
    private PlaylistEntity playlist;

    @OneToMany(mappedBy = "video", fetch = FetchType.EAGER)
    private List<TimeCodeEntity> timeCodes = new ArrayList<>();

    @OneToMany(mappedBy = "video", fetch = FetchType.EAGER)
    private List<VisitEntity> visits = new ArrayList<>();

    @OneToMany(mappedBy = "video", fetch = FetchType.EAGER)
    private List<CommentEntity> comments = new ArrayList<>();
}

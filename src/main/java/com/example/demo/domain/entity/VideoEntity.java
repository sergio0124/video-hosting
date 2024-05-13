package com.example.demo.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

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

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "video_name", nullable = false)
    private String videoName;

    @Column(name = "folder", nullable = false)
    private String folder;

    @CreationTimestamp
    @Column(name = "creation_time", nullable = false)
    private Timestamp creationTime;

    @ManyToOne
    @JoinColumn(name = "class_id", referencedColumnName = "id", nullable = false)
    private PlaylistEntity vclass;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private UserEntity user;

    @OneToMany(mappedBy = "video", fetch = FetchType.LAZY)
    private List<VisitEntity> visits;

    @OneToMany(mappedBy = "video", fetch = FetchType.LAZY)
    private List<CommentEntity> comments;
}

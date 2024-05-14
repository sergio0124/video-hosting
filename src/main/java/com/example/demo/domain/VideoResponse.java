package com.example.demo.domain;

import com.example.demo.domain.entity.CommentEntity;
import com.example.demo.domain.entity.PlaylistEntity;
import com.example.demo.domain.entity.TimeCodeEntity;
import com.example.demo.domain.entity.VisitEntity;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoResponse {

    private UUID id;

    private String name;

    private String imageUrl;

    private String description;

    private String videoUrl;

    private Date creationTime;

    private List<TimeCodeResponse> timeCodes;

    private List<CommentResponse> comments;
}

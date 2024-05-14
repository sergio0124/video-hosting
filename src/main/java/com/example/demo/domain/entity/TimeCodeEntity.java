package com.example.demo.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@Data
@ToString(exclude = {"video"})
@EqualsAndHashCode(exclude = {"video"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "time_code")
public class TimeCodeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String time;

    private String description;

    @ManyToOne
    private VideoEntity video;
}

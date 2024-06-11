package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PermissionUpdateRequest {

    private UUID id;

    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("inspiration_date")
    private Timestamp inspirationDate;

    private Boolean isTemporary;
}

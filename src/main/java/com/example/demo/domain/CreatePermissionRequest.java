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
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreatePermissionRequest {

    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("inspiration_date")
    private Timestamp inspirationDate;

    private Boolean isTemporary;

    @JsonProperty("user_id")
    private UUID userId;

    @JsonProperty("group_id")
    private UUID groupId;

    @JsonProperty("playlist_id")
    private UUID playlistId;
}

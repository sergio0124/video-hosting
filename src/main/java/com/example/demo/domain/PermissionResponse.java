package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PermissionResponse {

    @JsonProperty("creation_date")
    private String creationDate;

    @JsonProperty("inspire_date")
    private String inspireDate;

    private String username;

    @JsonProperty("user_group")
    private String userGroup;

    private String description;

    private Boolean isTemporary;
}

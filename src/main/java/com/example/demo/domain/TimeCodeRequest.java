package com.example.demo.domain;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TimeCodeRequest {

    private UUID id;

    @Pattern(regexp = "^\\d{0,2}:\\d{2}:[0-5]\\d$")
    private String time;

    private String description;

    private UUID videoId;
}

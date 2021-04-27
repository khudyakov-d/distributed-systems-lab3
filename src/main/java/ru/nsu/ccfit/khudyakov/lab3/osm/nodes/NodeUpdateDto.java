package ru.nsu.ccfit.khudyakov.lab3.osm.nodes;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class NodeUpdateDto {

    @NotNull
    private Long id;

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;

    private String owner;

    private Long uid;

    private Boolean visible;

    private Long version;

    private Long changeSet;

    private LocalDateTime timestamp;

}

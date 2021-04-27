package ru.nsu.ccfit.khudyakov.lab3.osm.nodes;

import com.sun.istack.NotNull;
import lombok.Data;
import ru.nsu.ccfit.khudyakov.lab3.osm.tags.TagDto;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class NodeDto {

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

    private List<TagDto> tags;

}

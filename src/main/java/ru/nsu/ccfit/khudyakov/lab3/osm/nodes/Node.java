package ru.nsu.ccfit.khudyakov.lab3.osm.nodes;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import ru.nsu.ccfit.khudyakov.lab3.osm.tags.Tag;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



@Data
@Entity
@EqualsAndHashCode(of = "id")
public class Node {

    @Id
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

    @ManyToMany(mappedBy = "nodes")
    @Cascade({CascadeType.PERSIST, CascadeType.MERGE})
    private List<Tag> tags = new ArrayList<>();

}

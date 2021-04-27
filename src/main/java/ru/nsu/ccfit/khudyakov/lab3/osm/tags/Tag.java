package ru.nsu.ccfit.khudyakov.lab3.osm.tags;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ru.nsu.ccfit.khudyakov.lab3.osm.nodes.Node;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@ToString(exclude = {"nodes"})
@EqualsAndHashCode(of = "id")
public class Tag {

    @EmbeddedId
    private TagId id;

    @ManyToMany
    private Set<Node> nodes = new HashSet<>();

}

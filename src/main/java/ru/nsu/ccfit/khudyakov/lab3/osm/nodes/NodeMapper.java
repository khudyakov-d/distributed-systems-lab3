package ru.nsu.ccfit.khudyakov.lab3.osm.nodes;

import org.mapstruct.Mapper;
import ru.nsu.ccfit.khudyakov.lab3.osm.tags.TagMapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = TagMapper.class)
public abstract class NodeMapper {

    public abstract Node map(NodeDto node);

    public abstract List<NodeDto> map(List<Node> nodes);

}

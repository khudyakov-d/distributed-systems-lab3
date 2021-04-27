package ru.nsu.ccfit.khudyakov.lab3.osm.nodes;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nsu.ccfit.khudyakov.lab3.osm.tags.TagMapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = TagMapper.class)
public abstract class NodeMapper {

    public abstract Node map(NodeDto node);

    @Mapping(target = "tags", ignore = true)
    public abstract Node map(NodeUpdateDto node);

    public abstract List<NodeDto> map(List<Node> nodes);

}

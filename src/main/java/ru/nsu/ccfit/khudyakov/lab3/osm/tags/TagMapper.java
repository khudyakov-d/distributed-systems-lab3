package ru.nsu.ccfit.khudyakov.lab3.osm.tags;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class TagMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "key", target = "id.key")
    @Mapping(source = "value", target = "id.value")
    @Mapping(target = "nodes", ignore = true)
    public abstract Tag map(TagDto tag);

    @Mapping(source = "id.key", target = "key")
    @Mapping(source = "id.value", target = "value")
    public abstract TagDto map(Tag tag);

}

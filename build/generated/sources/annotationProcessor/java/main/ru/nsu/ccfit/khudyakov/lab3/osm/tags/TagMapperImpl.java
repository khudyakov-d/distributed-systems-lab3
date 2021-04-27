package ru.nsu.ccfit.khudyakov.lab3.osm.tags;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-04-28T02:45:27+0700",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-6.8.3.jar, environment: Java 11.0.10 (Ubuntu)"
)
@Component
public class TagMapperImpl extends TagMapper {

    @Override
    public Tag map(TagDto tag) {
        if ( tag == null ) {
            return null;
        }

        Tag tag1 = new Tag();

        tag1.setId( tagDtoToTagId( tag ) );

        return tag1;
    }

    @Override
    public TagDto map(Tag tag) {
        if ( tag == null ) {
            return null;
        }

        TagDto tagDto = new TagDto();

        tagDto.setKey( tagIdKey( tag ) );
        tagDto.setValue( tagIdValue( tag ) );

        return tagDto;
    }

    protected TagId tagDtoToTagId(TagDto tagDto) {
        if ( tagDto == null ) {
            return null;
        }

        TagId tagId = new TagId();

        tagId.setKey( tagDto.getKey() );
        tagId.setValue( tagDto.getValue() );

        return tagId;
    }

    private String tagIdKey(Tag tag) {
        if ( tag == null ) {
            return null;
        }
        TagId id = tag.getId();
        if ( id == null ) {
            return null;
        }
        String key = id.getKey();
        if ( key == null ) {
            return null;
        }
        return key;
    }

    private String tagIdValue(Tag tag) {
        if ( tag == null ) {
            return null;
        }
        TagId id = tag.getId();
        if ( id == null ) {
            return null;
        }
        String value = id.getValue();
        if ( value == null ) {
            return null;
        }
        return value;
    }
}

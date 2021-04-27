package ru.nsu.ccfit.khudyakov.lab3.osm.nodes;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.nsu.ccfit.khudyakov.lab3.osm.tags.Tag;
import ru.nsu.ccfit.khudyakov.lab3.osm.tags.TagDto;
import ru.nsu.ccfit.khudyakov.lab3.osm.tags.TagMapper;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-04-28T02:45:26+0700",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-6.8.3.jar, environment: Java 11.0.10 (Ubuntu)"
)
@Component
public class NodeMapperImpl extends NodeMapper {

    @Autowired
    private TagMapper tagMapper;

    @Override
    public Node map(NodeDto node) {
        if ( node == null ) {
            return null;
        }

        Node node1 = new Node();

        node1.setId( node.getId() );
        node1.setLatitude( node.getLatitude() );
        node1.setLongitude( node.getLongitude() );
        node1.setOwner( node.getOwner() );
        node1.setUid( node.getUid() );
        node1.setVisible( node.getVisible() );
        node1.setVersion( node.getVersion() );
        node1.setChangeSet( node.getChangeSet() );
        node1.setTimestamp( node.getTimestamp() );
        node1.setTags( tagDtoListToTagList( node.getTags() ) );

        return node1;
    }

    @Override
    public Node map(NodeUpdateDto node) {
        if ( node == null ) {
            return null;
        }

        Node node1 = new Node();

        node1.setId( node.getId() );
        node1.setLatitude( node.getLatitude() );
        node1.setLongitude( node.getLongitude() );
        node1.setOwner( node.getOwner() );
        node1.setUid( node.getUid() );
        node1.setVisible( node.getVisible() );
        node1.setVersion( node.getVersion() );
        node1.setChangeSet( node.getChangeSet() );
        node1.setTimestamp( node.getTimestamp() );

        return node1;
    }

    @Override
    public List<NodeDto> map(List<Node> nodes) {
        if ( nodes == null ) {
            return null;
        }

        List<NodeDto> list = new ArrayList<NodeDto>( nodes.size() );
        for ( Node node : nodes ) {
            list.add( nodeToNodeDto( node ) );
        }

        return list;
    }

    protected List<Tag> tagDtoListToTagList(List<TagDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Tag> list1 = new ArrayList<Tag>( list.size() );
        for ( TagDto tagDto : list ) {
            list1.add( tagMapper.map( tagDto ) );
        }

        return list1;
    }

    protected List<TagDto> tagListToTagDtoList(List<Tag> list) {
        if ( list == null ) {
            return null;
        }

        List<TagDto> list1 = new ArrayList<TagDto>( list.size() );
        for ( Tag tag : list ) {
            list1.add( tagMapper.map( tag ) );
        }

        return list1;
    }

    protected NodeDto nodeToNodeDto(Node node) {
        if ( node == null ) {
            return null;
        }

        NodeDto nodeDto = new NodeDto();

        nodeDto.setId( node.getId() );
        nodeDto.setLatitude( node.getLatitude() );
        nodeDto.setLongitude( node.getLongitude() );
        nodeDto.setOwner( node.getOwner() );
        nodeDto.setUid( node.getUid() );
        nodeDto.setVisible( node.getVisible() );
        nodeDto.setVersion( node.getVersion() );
        nodeDto.setChangeSet( node.getChangeSet() );
        nodeDto.setTimestamp( node.getTimestamp() );
        nodeDto.setTags( tagListToTagDtoList( node.getTags() ) );

        return nodeDto;
    }
}

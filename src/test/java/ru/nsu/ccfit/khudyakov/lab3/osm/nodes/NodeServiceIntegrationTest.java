package ru.nsu.ccfit.khudyakov.lab3.osm.nodes;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.nsu.ccfit.khudyakov.lab3.osm.tags.Tag;
import ru.nsu.ccfit.khudyakov.lab3.osm.tags.TagId;
import ru.nsu.ccfit.khudyakov.lab3.osm.tags.TagRepository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
@ActiveProfiles("test")
class NodeServiceIntegrationTest {

    @Autowired
    private NodeService nodeService;

    @Autowired
    private NodeRepository nodeRepository;

    @Autowired
    private TagRepository tagRepository;

    @Test
    @Transactional
    void nodeService_saveNodeWithUnsavedChildTag_childSaved() {
        Node node = new Node();
        node.setId(1L);
        node.setLatitude(55.0416925);
        node.setLatitude(82.3040892);

        Tag tag1 = new Tag();
        tag1.setId(new TagId("addr:country", "RU"));

        node.getTags().add(tag1);

        nodeService.create(node);
        assertTrue(nodeRepository.existsById(node.getId()));
    }

    @Test
    @Transactional
    void nodeService_saveNodeWithUpdateAlreadySavedChildTag_childMerged() {
        Tag tag1 = new Tag();
        tag1.setId(new TagId("addr:country", "RU"));

        Node node1 = new Node();
        node1.setId(1L);
        node1.setLatitude(55.0416925);
        node1.setLatitude(82.3040892);
        node1.getTags().add(tag1);

        nodeService.create(node1);

        Tag tag2 = new Tag();
        tag2.setId(new TagId("addr:country", "RU"));

        Node node2 = new Node();
        node2.setId(2L);
        node2.setLatitude(55.0416925);
        node2.setLatitude(82.3040892);
        node2.getTags().add(tag2);
        nodeService.create(node2);

        tag1 = tagRepository.getTagWithNodes(tag1.getId());
        assertEquals(2, tag1.getNodes().size());
    }

    @Test
    @Transactional
    void nodeService_deleteNodeCheckTagForAnother_tagStillExist() {
        Tag tag1 = new Tag();
        tag1.setId(new TagId("addr:country", "RU"));

        Node node1 = new Node();
        node1.setId(1L);
        node1.setLatitude(55.0416925);
        node1.setLatitude(82.3040892);
        node1.getTags().add(tag1);

        nodeService.create(node1);

        Tag tag2 = new Tag();
        tag2.setId(new TagId("addr:country", "RU"));

        Tag tag3 = new Tag();
        tag3.setId(new TagId("lang", "RU"));

        Node node2 = new Node();
        node2.setId(2L);
        node2.setLatitude(55.0416925);
        node2.setLatitude(82.3040892);
        node2.getTags().add(tag2);
        node2.getTags().add(tag3);
        nodeService.create(node2);

        nodeService.delete(node1.getId());

        assertFalse(nodeRepository.existsById(node1.getId()));
        assertEquals(2, node2.getTags().size());
    }

}
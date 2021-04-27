package ru.nsu.ccfit.khudyakov.lab3.osm.nodes;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.nsu.ccfit.khudyakov.lab3.osm.exception.ErrorType;
import ru.nsu.ccfit.khudyakov.lab3.osm.exception.ServiceException;
import ru.nsu.ccfit.khudyakov.lab3.osm.tags.Tag;
import ru.nsu.ccfit.khudyakov.lab3.osm.tags.TagRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
class NodeService {

    public static final String NODE_NOT_FOUND = "Node with given id not found";

    public static final String NODE_ALREADY_EXIST = "Node with given id already exist";

    private final NodeRepository nodeRepository;

    private final TagRepository tagRepository;

    @Transactional
    public void create(Node node) {
        if (nodeRepository.findById(node.getId()).isPresent()) {
            throw new ServiceException(ErrorType.ALREADY_EXIST, List.of(NODE_ALREADY_EXIST));
        }

        List<Tag> tags = node.getTags();
        if (tags == null) {
            nodeRepository.save(node);
            return;
        }

        for (int i = 0, tagsSize = tags.size(); i < tagsSize; i++) {
            Tag tag = tags.get(i);
            Optional<Tag> storedTag = tagRepository.findById(tag.getId());

            if (storedTag.isPresent()) {
                tag = storedTag.get();
                tags.set(i, tag);
            }

            tag.getNodes().add(node);
        }

        nodeRepository.save(node);
    }

    @Transactional
    public void delete(Long nodeId) {
        Node node = nodeRepository.findById(nodeId)
                .orElseThrow(() -> new ServiceException(ErrorType.NOT_FOUND, List.of(NODE_NOT_FOUND)));

        List<Tag> tags = node.getTags();
        for (Tag tag : tags) {
            if (tag.getNodes().size() == 1) {
                tagRepository.delete(tag);
            } else {
                tag.getNodes().remove(node);
            }
        }

        nodeRepository.delete(node);
    }

    public void update(Node node) {
        Node targetNode = nodeRepository.findById(node.getId())
                .orElseThrow(() -> new ServiceException(ErrorType.NOT_FOUND, List.of(NODE_NOT_FOUND)));

        node.setTags(targetNode.getTags());

        nodeRepository.save(node);
    }

    public List<Node> findPage(Pageable pageable) {
        return new ArrayList<>(nodeRepository.findAll(pageable).getContent());
    }

    public List<Node> findNodesInRadius(double latitude, double longitude, double radius) {
        return nodeRepository.findAllInRadius(latitude, longitude, radius);
    }

}

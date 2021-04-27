package ru.nsu.ccfit.khudyakov.lab3.osm.nodes;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.ccfit.khudyakov.lab3.osm.response.ApiResponse;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/nodes")
@RequiredArgsConstructor
public class NodeController {

    public static final String DEFAULT_PAGE = "0";

    public static final String DEFAULT_PAGE_SIZE = "100";

    private final NodeService nodeService;

    private final NodeMapper nodeMapper;

    @GetMapping
    public ApiResponse<List<NodeDto>> getNodes(@RequestParam(defaultValue = DEFAULT_PAGE) int page,
                                               @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int size) {
        Pageable paging = PageRequest.of(page, size);
        List<Node> nodes = nodeService.findPage(paging);
        return ApiResponse.createOk(nodeMapper.map(nodes));
    }

    @GetMapping("/search")
    public ApiResponse<List<NodeDto>> getNodesInRadius(@Valid @NotNull @RequestParam Double latitude,
                                                       @Valid @NotNull @RequestParam Double longitude,
                                                       @Valid @NotNull @RequestParam Double radius) {
        List<Node> nodesInRadius = nodeService.findNodesInRadius(latitude, longitude, radius);
        return ApiResponse.createOk(nodeMapper.map(nodesInRadius));
    }

    @PostMapping
    public ApiResponse<Void> createNode(@RequestBody @Valid NodeDto node) {
        nodeService.create(nodeMapper.map(node));
        return ApiResponse.createOk(null);
    }

    @PutMapping
    public ApiResponse<Void> updateNode(@RequestBody @Valid NodeUpdateDto node) {
        nodeService.update(nodeMapper.map(node));
        return ApiResponse.createOk(null);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteNode(@PathVariable Long id) {
        nodeService.delete(id);
        return ApiResponse.createOk(null);
    }


}

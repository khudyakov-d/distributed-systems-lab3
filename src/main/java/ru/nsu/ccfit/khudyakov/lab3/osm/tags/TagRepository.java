package ru.nsu.ccfit.khudyakov.lab3.osm.tags;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag, TagId> {

    @Query("SELECT t FROM Tag t LEFT JOIN FETCH t.nodes")
    Tag getTagWithNodes(TagId id);

}

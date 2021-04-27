package ru.nsu.ccfit.khudyakov.lab3.osm.nodes;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
interface NodeRepository extends JpaRepository<Node, Long> {

    Page<Node> findAll(Pageable pageable);

    @Query("SELECT n FROM Node n WHERE FUNCTION('earth_distance',\n" +
            "FUNCTION('ll_to_earth',:latitude, :longitude),\n" +
            "FUNCTION('ll_to_earth',n.latitude,n.longitude)) < :radius")
    List<Node> findAllInRadius(double latitude, double longitude, double radius);

}

package naitei.group5.workingspacebooking.repository;

import naitei.group5.workingspacebooking.entity.Venue;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VenueRepository extends JpaRepository<Venue, Integer>, JpaSpecificationExecutor<Venue> {

    @Query("""
            SELECT v 
            FROM Venue v 
            JOIN FETCH v.owner 
            JOIN FETCH v.venueStyle 
            WHERE v.id = :id
            """)
    Optional<Venue> findByIdWithDetails(@Param("id") Integer id);

    @EntityGraph(attributePaths = {"venueStyle"})
    List<Venue> findByOwnerId(Integer ownerId);

    List<Venue> findByVerified(Boolean verified);
}

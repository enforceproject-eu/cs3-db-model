package org.n52.project.enforce.cs3.db.repository;

import java.util.Optional;

import org.n52.project.enforce.cs3.db.model.Cs3Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * <p>
 * Data repository.
 * </p>
 *
 * @author Benjamin Pross 
 * @since 1.0.0
 */
public interface Cs3DataRepository extends JpaRepository<Cs3Data, Integer> {
    
    /**
     * <p>
     * getGeoJson.
     * </p>
     * 
     * @return a {@link String} object
     */
    @Query("select st_cs3datatogeojson()")
    String getGeoJson();

    @Query("select d from Cs3Data as d where d.fictionary = :fictionaryId")
    Optional<Cs3Data> findByFictionaryId(@Param("fictionaryId") int fictionaryId);
}

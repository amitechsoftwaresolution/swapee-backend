package io.swapee.swapeebackend.repository;

import io.swapee.swapeebackend.model.Config;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigRepository extends JpaRepository<Config, Long> {

    @Query("SELECT c.infoValue FROM Config c WHERE c.infoKey = ?1")
    String findByKey(String key);
}

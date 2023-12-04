package io.swapee.swapeebackend.repository;

import io.swapee.swapeebackend.model.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, Long>{

    @Query("SELECT a FROM Advertisement a WHERE a.isActive = true")
    List<Advertisement> getAllActiveAdvertisement();
}

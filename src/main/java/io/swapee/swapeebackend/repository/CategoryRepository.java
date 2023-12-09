package io.swapee.swapeebackend.repository;

import io.swapee.swapeebackend.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
    @Query(value = "SELECT c.name FROM category c WHERE c.id = ?1", nativeQuery = true)
    String findNameById(Long categoryId);
}

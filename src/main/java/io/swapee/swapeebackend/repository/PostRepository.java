package io.swapee.swapeebackend.repository;

import io.swapee.swapeebackend.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = "SELECT * FROM post p WHERE p.is_active = true AND p.type_id = (" +
            "SELECT t.id FROM type t WHERE t.name = ?1)", nativeQuery = true)
    List<Post> findAllActivePostsByType(String type);

    List<Post> findAllByTitleContaining(String keyword);
}

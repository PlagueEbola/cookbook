package com.example.cookbook.repository;

import com.example.cookbook.model.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    @Modifying
    @Query(value = "UPDATE recipe SET is_deleted = 1 WHERE id = :id", nativeQuery = true)
    void deleteById(Long id);

    @Query(value = "SELECT * FROM Recipe where is_deleted = false", nativeQuery = true)
    Page<Recipe> findAll(Pageable pageable);
}

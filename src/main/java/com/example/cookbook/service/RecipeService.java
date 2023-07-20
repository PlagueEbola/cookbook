package com.example.cookbook.service;

import com.example.cookbook.model.Recipe;
import java.util.List;
import org.springframework.data.domain.PageRequest;

public interface RecipeService {
    List<Recipe> findAll(PageRequest pageRequest);

    Recipe save(Recipe recipe);

    Recipe update(Recipe recipe);

    Recipe findById(Long id);

    void deleteById(Long id);

    List<Recipe> findAllPreviousVersions(Long id);
}

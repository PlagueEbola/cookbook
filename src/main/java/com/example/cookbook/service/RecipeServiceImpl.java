package com.example.cookbook.service;

import com.example.cookbook.model.Recipe;
import com.example.cookbook.repository.RecipeRepository;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    @Override
    public List<Recipe> findAll(PageRequest pageRequest) {
        return sortedByAlphabet(recipeRepository.findAll(pageRequest).toList());
    }

    @Override
    public Recipe save(Recipe recipe) {
        if (recipe.getParentRecipe() == null) {
            return recipeRepository.save(recipe);
        }
        Long parentRecipeId = recipe.getParentRecipe().getId();
        Recipe patentRecipe = recipeRepository.findById(parentRecipeId).orElseThrow(() ->
                new RuntimeException("Can`t save recipe to DB. Parent recipe with id "
                        + parentRecipeId + " don`t exist."));
        patentRecipe.addChildRecipe(recipe);
        recipe.setParentRecipe(patentRecipe);
        return recipeRepository.save(recipe);
    }

    @Override
    public Recipe update(Recipe recipe) {
        if (recipeRepository.existsById(recipe.getId())) {
            return recipeRepository.save(recipe);
        }
        throw new RuntimeException("Recipe "
                + recipe.getName()
                + " cannot be updated. It does not exist.");
    }

    @Override
    public Recipe findById(Long id) {
        return recipeRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Can`t find recipe by id = " + id));
    }

    @Override
    public void deleteById(Long id) {
        recipeRepository.deleteById(id);
    }

    @Override
    public List<Recipe> findAllPreviousVersions(Long id) {
        List<Recipe> recipes = new ArrayList<>();
        Recipe parentRecipe = findById(id).getParentRecipe();
        while (parentRecipe != null) {
            recipes.add(parentRecipe);
            parentRecipe = parentRecipe.getParentRecipe();
            if (parentRecipe != null) {
                parentRecipe = findById(parentRecipe.getId());
            }

        }
        return sortedByAlphabet(recipes);
    }

    private List<Recipe> sortedByAlphabet(List<Recipe> recipes) {
        return recipes.stream()
                .sorted(Comparator.comparing(Recipe::getName))
                .collect(Collectors.toList());
    }
}

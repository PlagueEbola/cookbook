package com.example.cookbook.model.dto.mapper;

import com.example.cookbook.model.Recipe;
import com.example.cookbook.model.dto.request.RecipeRequestDto;
import com.example.cookbook.model.dto.response.RecipeResponseDto;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class RecipeMapper {
    public RecipeResponseDto toResponseDto(Recipe recipe) {
        RecipeResponseDto responseDto = new RecipeResponseDto();
        responseDto.setId(recipe.getId());
        responseDto.setName(recipe.getName());
        responseDto.setDescription(recipe.getDescription());
        if (recipe.getParentRecipe() != null) {
            responseDto.setParentRecipe(toResponseDto(recipe.getParentRecipe()));
        }

        if (responseDto.getChildRecipes() != null) {
            responseDto.setChildRecipes(recipe.getChildRecipes()
                    .stream()
                    .map(this::toResponseDto)
                    .collect(Collectors.toList()));
        }
        responseDto.setCreationTime(recipe.getCreationTime());
        return responseDto;
    }

    public Recipe toModel(RecipeRequestDto requestDto) {
        Recipe recipe = new Recipe();
        recipe.setName(requestDto.getName());
        recipe.setDescription(requestDto.getDescription());
        recipe.setCreationTime(LocalDateTime.now());
        recipe.setChildRecipes(Collections.emptyList());
        if (requestDto.getParentRecipeId() != null) {
            Recipe parentRecipe = new Recipe();
            parentRecipe.setId(requestDto.getParentRecipeId());
            recipe.setParentRecipe(parentRecipe);
        }
        return recipe;
    }
}

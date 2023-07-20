package com.example.cookbook.model.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public class RecipeResponseDto {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime creationTime;
    private RecipeResponseDto parentRecipe;
    private List<RecipeResponseDto> childRecipes;
}

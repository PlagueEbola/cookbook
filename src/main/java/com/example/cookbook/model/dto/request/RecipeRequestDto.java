package com.example.cookbook.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RecipeRequestDto {
    @NotBlank(message = "Name cannot be empty")
    @Size(min = 5)
    private String name;
    @NotBlank(message = "Description cannot be empty")
    private String description;
    @Positive(message = "The parent id must be positive")
    private Long parentRecipeId;
}

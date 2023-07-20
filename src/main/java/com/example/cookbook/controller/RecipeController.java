package com.example.cookbook.controller;

import com.example.cookbook.model.Recipe;
import com.example.cookbook.model.dto.mapper.RecipeMapper;
import com.example.cookbook.model.dto.request.RecipeRequestDto;
import com.example.cookbook.model.dto.response.RecipeResponseDto;
import com.example.cookbook.service.RecipeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recipes")
@AllArgsConstructor
public class RecipeController {
    private final RecipeService recipeService;
    private final RecipeMapper recipeMapper;

    @GetMapping
    @ApiOperation(value = "Get all recipes")
    public List<RecipeResponseDto> findAll(
            @RequestParam(defaultValue = "0")
            @ApiParam(value = "default value is 0") Integer page,
            @RequestParam(defaultValue = "5")
            @ApiParam(value = "default value is 5")Integer count) {
        PageRequest pageRequest = PageRequest.of(page, count);
        return recipeService.findAll(pageRequest)
                .stream()
                .map(recipeMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    @ApiOperation(value = "Create a new recipe")
    public RecipeResponseDto create(RecipeRequestDto requestDto) {
        return recipeMapper.toResponseDto(recipeService.save(recipeMapper.toModel(requestDto)));
    }

    @GetMapping("/{recipeId}")
    @ApiOperation(value = "Get recipe by id")
    public RecipeResponseDto findById(@PathVariable Long recipeId) {
        return recipeMapper.toResponseDto(recipeService.findById(recipeId));
    }

    @PutMapping("/{recipeId}")
    @ApiOperation(value = "Update recipe by id")
    public RecipeResponseDto update(RecipeRequestDto requestDto, @PathVariable Long recipeId) {
        Recipe recipe = recipeMapper.toModel(requestDto);
        recipe.setId(recipeId);
        return recipeMapper.toResponseDto(recipeService.update(recipe));
    }

    @DeleteMapping("/{recipeId}")
    @ApiOperation(value = "Soft delete recipe. ")
    public void delete(@PathVariable Long recipeId) {
        recipeService.deleteById(recipeId);
    }

    @GetMapping("/previous/{recipeId}")
    @ApiOperation(value = "Get all previous versions of the recipe by id")
    public List<RecipeResponseDto> findAllPreviousVersions(@PathVariable Long recipeId) {
        List<Recipe> allPreviousVersions = recipeService.findAllPreviousVersions(recipeId);
        return allPreviousVersions
                .stream()
                .map(recipeMapper::toResponseDto)
                .collect(Collectors.toList());
    }
}

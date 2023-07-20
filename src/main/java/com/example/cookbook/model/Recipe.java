package com.example.cookbook.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDateTime creationTime;
    private String description;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_recipe_id")
    private Recipe parentRecipe;
    @OneToMany(mappedBy = "parentRecipe", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<Recipe> childRecipes = new ArrayList<>();
    private boolean isDeleted = false;

    public void addChildRecipe(Recipe childRecipe) {
        childRecipes.add(childRecipe);
    }
}

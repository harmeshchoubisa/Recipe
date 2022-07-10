package com.recipe.mapper;

import com.recipe.domain.Recipe;
import com.recipe.entity.RecipeEntity;
import lombok.Data;

@Data
public class RecipeMapper {

    public static Recipe mapToRecipeDomain(RecipeEntity entity) {
        return Recipe.builder().recipeName(entity.getRecipeName())
                .instructions(entity.getInstructions())
                .numberOfServings(entity.getNumberOfServings())
                .vegetarian(entity.isVegetarian())
                .ingredients(entity.getIngredients()).build();
    }

    public static RecipeEntity mapToRecipeEntity(Recipe recipe) {
        return RecipeEntity.builder().recipeName(recipe.getRecipeName())
                .instructions(recipe.getInstructions())
                .numberOfServings(recipe.getNumberOfServings())
                .vegetarian(recipe.isVegetarian())
                .ingredients(recipe.getIngredients()).build();
    }
}

package com.recipe.filter;

import com.recipe.domain.Ingredient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeFilter {

    private Integer noOfServings;
    private Boolean vegetarian;
    private String instructions;
    private Ingredient includeIngredient;
    private Ingredient excludeIngredient;
}

package com.recipe.entity;

import com.recipe.domain.Ingredient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "RECIPE")
public class RecipeEntity {

    @Id
    private String recipeName;
    private String instructions;
    private Integer numberOfServings;
    private boolean vegetarian;
    @ElementCollection(targetClass = Ingredient.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private List<Ingredient> ingredients;
}

package com.recipe.controller;

import com.recipe.domain.Recipe;
import com.recipe.filter.RecipeFilter;
import com.recipe.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/recipe")
public class RecipeController {

    @Autowired
    private RecipeService service;

    @PostMapping
    public ResponseEntity<Recipe> addRecipe(@Valid @RequestBody Recipe recipe) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveRecipe(recipe));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recipe> updateRecipe(@Valid @RequestBody Recipe recipe, @PathVariable String id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(service.updateRecipe(recipe, id));
    }

    @DeleteMapping("/{id}")
    public void deleteRecipe(@PathVariable @Valid String id) {
        service.deleteRecipe(id);
    }

    @PostMapping("/addRecipes")
    public Recipe addRecipe(@Valid @RequestBody List<Recipe> recipes) {
         recipes.forEach(service::saveRecipe);
        return null;
    }

    @GetMapping("/searchByCriteria")
    public List<Recipe> searchByCriteria(@RequestBody RecipeFilter recipeFilter) {
        return service.searchByCriteria(recipeFilter);
    }
}

package com.recipe.service;

import com.recipe.domain.Recipe;
import com.recipe.entity.RecipeEntity;
import com.recipe.exception.RecipeNotFoundException;
import com.recipe.filter.RecipeFilter;
import com.recipe.mapper.RecipeMapper;
import com.recipe.repository.RecipeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.recipe.mapper.RecipeMapper.mapToRecipeDomain;
import static com.recipe.mapper.RecipeMapper.mapToRecipeEntity;

@Service
public class RecipeService {

    private Logger logger = LoggerFactory.getLogger(RecipeService.class);

    @Autowired
    private RecipeRepository repository;

    public Recipe saveRecipe(Recipe recipe) {
        RecipeEntity recipeEntity = mapToRecipeEntity(recipe);
        RecipeEntity entity = repository.save(recipeEntity);
        return mapToRecipeDomain(entity);
    }

    public List<Recipe> searchRecipes(Recipe recipe) {
        RecipeEntity recipeEntity = mapToRecipeEntity(recipe);
        List<RecipeEntity> entities = repository.findAll(Example.of(recipeEntity));
        return entities.stream().map(RecipeMapper::mapToRecipeDomain).collect(Collectors.toList());
    }

    public List<Recipe> searchByCriteria(final RecipeFilter recipeFilter) {

        Specification<Recipe> noOfServings =
                (root, query, criteriaBuilder) ->
                        criteriaBuilder.lessThanOrEqualTo(root.get("numberOfServings"), recipeFilter.getNoOfServings());

        Specification<Recipe> includesIngredient =
                (root, query, criteriaBuilder) ->
                        criteriaBuilder.like(root.get("ingredients").as(String.class), "%" + recipeFilter.getIncludeIngredient() + "%");

        Specification<Recipe> excludesIngredient =
                (root, query, criteriaBuilder) ->
                        criteriaBuilder.notLike(root.get("ingredients").as(String.class), "%" + recipeFilter.getExcludeIngredient() + "%");

        Specification<Recipe> instructions =
                (root, query, criteriaBuilder) ->
                        criteriaBuilder.like(root.get("instructions").as(String.class), "%" + recipeFilter.getInstructions() + "%");

//        Specification<Recipe> includesIngredient =
//                (root, query, criteriaBuilder) ->
//                        root.get("ingredients").in((recipeFilter.getIncludeIngredient()));
//
//        Specification<Recipe> excludesIngredient =
//                (root, query, criteriaBuilder) ->
//                        criteriaBuilder.not(root.get("ingredients").in((recipeFilter.getExcludeIngredient())));

        return repository.findAll(Specification.where(noOfServings).and(instructions).and(includesIngredient).and(excludesIngredient));
    }

    public Recipe updateRecipe(Recipe recipe, String id) {
        Optional<RecipeEntity> optionalRecipeEntity = repository.findById(id);
        if (optionalRecipeEntity.isEmpty())
            throw new RecipeNotFoundException("recipe is not found for recipe=" + id);

        RecipeEntity entity = mapToRecipeEntity(recipe);
        return mapToRecipeDomain(repository.save(entity));
    }

    public void deleteRecipe(String id) {
        Optional<RecipeEntity> optionalRecipeEntity = repository.findById(id);
        if (optionalRecipeEntity.isEmpty())
            throw new RecipeNotFoundException("recipe is not found for recipe " + id);
        repository.deleteById(id);
    }
}

package com.recipe.repository;

import com.recipe.domain.Recipe;
import com.recipe.entity.RecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RecipeRepository extends JpaRepository<RecipeEntity, String>, JpaSpecificationExecutor<Recipe> {
}

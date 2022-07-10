package com.recipe.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Recipe {

    @NotNull
    @NotBlank
    @Length(min = 2, max = 50)
    private String recipeName;
    @NotNull
    @NotBlank
    @Length(min = 2, max = 50)
    private String instructions;
    @Min(value = 1)
    @Max(value = 20)
    private Integer numberOfServings;
    private boolean vegetarian;
    @Valid
    @NotEmpty
    @Size(min = 1)
    private List<Ingredient> ingredients;
}

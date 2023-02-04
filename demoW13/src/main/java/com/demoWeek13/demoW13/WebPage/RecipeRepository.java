package com.demoWeek13.demoW13.WebPage;

import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class RecipeRepository {
	private List<Recipes> recipes = new ArrayList<>();

	public List<Recipes> getRecipes() {
		return recipes;
	}
	 
	 
}

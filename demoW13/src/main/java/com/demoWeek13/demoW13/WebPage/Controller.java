package com.demoWeek13.demoW13.WebPage;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	@Autowired
	private FileProcessor fileProcessor;
	
	@GetMapping("/gluten-free")
	public List<Recipes> getGlutenFree() {
		return fileProcessor.getAllRecipes().stream().filter(Recipes::getGlutenFree)
						.collect(Collectors.toList());
	}
	
	@GetMapping("/vegan")
	public List<Recipes> getVegan () {
		return fileProcessor.getAllRecipes()
						  .stream()
						  .filter(Recipes::getVegan)
						  .collect(Collectors.toList());
	}
	
	@GetMapping("/veganAndGluten-free")
	public List<Recipes> getVeganAndGlutenFree() {
		return fileProcessor.getAllRecipes().stream()
				.filter(recipe -> recipe.getVegan() && recipe.getGlutenFree())
				.collect(Collectors.toList());
	}
	
	@GetMapping("/vegetarian")
	public List<Recipes> getVetarian() {
		return fileProcessor.getAllRecipes()
							.stream()
							.filter(Recipes::getVegetarian)
							.collect(Collectors.toList());
	}
	
	@GetMapping("/all-recipes")
	public List<Recipes> getAllRecipes() {
		return fileProcessor.getAllRecipes();
	}
	
	

	
}

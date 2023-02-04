package com.demoWeek13.demoW13.WebPage;

import java.io.*;
import java.util.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileProcessor {
	@Autowired
	private RecipeRepository repo;
	
	private List<Recipes> ingest() {
		
		CSVFormat csvFormat = CSVFormat.DEFAULT.withDelimiter(',').withEscape('\\')
						.withHeader("Cooking Minutes", "Dairy Free", "Gluten Free", "Instructions", "Preparation Minutes", "Price Per Serving", 
								"Ready In Minutes", "Servings", "Spoonacular Score", "Title", "Vegan", "Vegetarian")
						.withSkipHeaderRecord()
						.withIgnoreSurroundingSpaces();
		
		try(Reader reader = new FileReader("recipes.txt"))
		{
			Iterable<CSVRecord> records = csvFormat.parse(reader);
			
			for(CSVRecord record : records) {
				Integer cookingMinutes = Integer.parseInt(record.get("Cooking Minutes"));
				Boolean dairyFree = Boolean.parseBoolean(record.get("Dairy Free"));
				Boolean glutenFree = Boolean.parseBoolean(record.get("Gluten Free"));
				String instructions = record.get("Instructions");
				Double prepMin = Double.parseDouble(record.get("Preparation Minutes"));
				Double price = Double.parseDouble(record.get("Price Per Serving"));
				Integer readyInMin = Integer.parseInt(record.get("Ready In Minutes"));
				Integer servings = Integer.parseInt(record.get("Servings"));
				Double score = Double.parseDouble(record.get("Spoonacular Score"));
				String title = record.get("Title");
				Boolean vegan = Boolean.parseBoolean(record.get("Vegan"));
				Boolean vegetarian = Boolean.parseBoolean(record.get("Vegetarian"));
				
				Recipes recipe = new Recipes();
				recipe.setCookingMinutes(cookingMinutes);
				recipe.setDairyFree(dairyFree);
				recipe.setGlutenFree(glutenFree);
				recipe.setInstructions(instructions);
				recipe.setPreparationMinutes(prepMin);
				recipe.setPricePerServing(price);
				recipe.setReadyInMinutes(readyInMin);
				recipe.setServings(servings);
				recipe.setSpoonacularScore(score);
				recipe.setTitle(title);
				recipe.setVegan(vegan);
				recipe.setVegetarian(vegetarian);
				
				repo.getRecipes().add(recipe);
			}
			
		} catch (Exception e) {

		}
		
		return repo.getRecipes();
	}
	
	public List<Recipes> getAllRecipes() {
		if(repo.getRecipes().size() == 0) {
			return ingest();
		}
		
		return repo.getRecipes();
	}

}

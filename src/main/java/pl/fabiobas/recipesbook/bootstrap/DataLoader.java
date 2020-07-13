package pl.fabiobas.recipesbook.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.fabiobas.recipesbook.model.*;
import pl.fabiobas.recipesbook.repositories.CategoryRepository;
import pl.fabiobas.recipesbook.repositories.RecipeRepository;
import pl.fabiobas.recipesbook.repositories.UnitOfMeasureRepository;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class DataLoader implements CommandLineRunner {

    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;

    public DataLoader(UnitOfMeasureRepository unitOfMeasureRepository, CategoryRepository categoryRepository, RecipeRepository recipeRepository) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Optional<UnitOfMeasure> teaspoonUomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
        if (teaspoonUomOptional.isEmpty()) {
            throw new RuntimeException("Expected UOM not found");
        }

        Optional<UnitOfMeasure> tablespoonUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");
        if (tablespoonUomOptional.isEmpty()) {
            throw new RuntimeException("Expected UOM not found");
        }

        Optional<UnitOfMeasure> cupUomOptional = unitOfMeasureRepository.findByDescription("Cup");
        if (cupUomOptional.isEmpty()) {
            throw new RuntimeException("Expected UOM not found");
        }

        Optional<UnitOfMeasure> pinchUomOptional = unitOfMeasureRepository.findByDescription("Pinch");
        if (pinchUomOptional.isEmpty()) {
            throw new RuntimeException("Expected UOM not found");
        }

        Optional<UnitOfMeasure> ounceUomOptional = unitOfMeasureRepository.findByDescription("Ounce");
        if (ounceUomOptional.isEmpty()) {
            throw new RuntimeException("Expected UOM not found");
        }

        Optional<UnitOfMeasure> dashUomOptional = unitOfMeasureRepository.findByDescription("Dash");
        if (dashUomOptional.isEmpty()) {
            throw new RuntimeException("Expected UOM not found");
        }

        Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");
        if (eachUomOptional.isEmpty()) {
            throw new RuntimeException("Expected UOM not found");
        }

        UnitOfMeasure teaspoonUom = teaspoonUomOptional.get();
        UnitOfMeasure tablespoonUom = tablespoonUomOptional.get();
        UnitOfMeasure cupUom = cupUomOptional.get();
        UnitOfMeasure pinchUom = pinchUomOptional.get();
        UnitOfMeasure ounceUom = ounceUomOptional.get();
        UnitOfMeasure dashUom = dashUomOptional.get();
        UnitOfMeasure eachUom = eachUomOptional.get();

        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");
        if (americanCategoryOptional.isEmpty()) {
            throw new RuntimeException("Expected category not found");
        }

        Optional<Category> italianCategoryOptional = categoryRepository.findByDescription("Italian");
        if (italianCategoryOptional.isEmpty()) {
            throw new RuntimeException("Expected category not found");
        }

        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");
        if (mexicanCategoryOptional.isEmpty()) {
            throw new RuntimeException("Expected category not found");
        }

        Optional<Category> fastFoodCategoryOptional = categoryRepository.findByDescription("Fast Food");
        if (fastFoodCategoryOptional.isEmpty()) {
            throw new RuntimeException("Expected category not found");
        }

        Category americanCategory = americanCategoryOptional.get();
        Category mexicanCategory = mexicanCategoryOptional.get();

        Recipe guacamole = new Recipe();
        guacamole.setDescription("Perfect Guacamole");
        guacamole.setPrepTime(10);
        guacamole.setCookTime(0);
        guacamole.setServings(4);
        guacamole.setSource("Simplyrecipes");
        guacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        guacamole.setDirections("1 Cut the avocado, remove flesh: Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\n" +
                "\n" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\n" +
                "\n" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\n" +
                "\n" +
                "4 Serve: Serve immediately, or if making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n");
        guacamole.setDifficulty(Difficulty.EASY);

        Notes note = new Notes();
        note.setRecipeNotes("Test note");
        guacamole.setNotes(note);

        guacamole.addIngredient(new Ingredient("ripe avocados", BigDecimal.valueOf(2), eachUom));
        guacamole.addIngredient(new Ingredient("salt", BigDecimal.valueOf(0.25), teaspoonUom));
        guacamole.addIngredient(new Ingredient("fresh lime juice or lemon juice", BigDecimal.valueOf(1), tablespoonUom));
        guacamole.addIngredient(new Ingredient("minced red onion or thinly sliced green onion", BigDecimal.valueOf(2), tablespoonUom));
        guacamole.addIngredient(new Ingredient("serrano chiles, stems and seeds removed, minced", BigDecimal.valueOf(2), eachUom));
        guacamole.addIngredient(new Ingredient("cilantro (leaves and tender stems), finely chopped", BigDecimal.valueOf(2), tablespoonUom));
        guacamole.addIngredient(new Ingredient("freshly grated black pepper", BigDecimal.valueOf(1), dashUom));
        guacamole.addIngredient(new Ingredient("ripe tomato, seeds and pulp removed, chopped", BigDecimal.valueOf(0.5), eachUom));

        guacamole.getCategories().add(mexicanCategory);
        guacamole.getCategories().add(americanCategory);
        recipeRepository.save(guacamole);

        Recipe tacos = new Recipe();
        tacos.setDescription("Spicy Grilled Chicken Tacos");
        tacos.setPrepTime(20);
        tacos.setCookTime(20);
        tacos.setServings(6);
        tacos.setSource("simplyrecipes.com");
        tacos.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        tacos.setDifficulty(Difficulty.MODERATE);
        tacos.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "\n" +
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.\n");
        Notes note2 = new Notes();
        note2.setRecipeNotes("Taco test note");
        tacos.setNotes(note2);

        tacos.addIngredient(new Ingredient("ancho chili powder", BigDecimal.valueOf(2), tablespoonUom));
        tacos.addIngredient(new Ingredient("dried oregano", BigDecimal.valueOf(1), teaspoonUom));
        tacos.addIngredient(new Ingredient("dried cumin", BigDecimal.valueOf(1), teaspoonUom));
        tacos.addIngredient(new Ingredient("sugar", BigDecimal.valueOf(1), teaspoonUom));
        tacos.addIngredient(new Ingredient("salt", BigDecimal.valueOf(0.5), teaspoonUom));
        tacos.addIngredient(new Ingredient("clove garlic, finely chopped", BigDecimal.valueOf(1), eachUom));
        tacos.addIngredient(new Ingredient("finely grated orange zest", BigDecimal.valueOf(1), tablespoonUom));
        tacos.addIngredient(new Ingredient("fresh-squeezed orange juice", BigDecimal.valueOf(3), tablespoonUom));
        tacos.addIngredient(new Ingredient("olive oil", BigDecimal.valueOf(2), tablespoonUom));
        tacos.addIngredient(new Ingredient("skinless, boneless chicken thighs (1 1/4 pounds)", BigDecimal.valueOf(6), eachUom));
        tacos.addIngredient(new Ingredient("small corn tortillas", BigDecimal.valueOf(8), eachUom));
        tacos.addIngredient(new Ingredient("packed baby arugula (3 ounces)", BigDecimal.valueOf(3), cupUom));
        tacos.addIngredient(new Ingredient("medium ripe avocados, sliced", BigDecimal.valueOf(2), eachUom));
        tacos.addIngredient(new Ingredient("radishes, thinly sliced", BigDecimal.valueOf(4), eachUom));
        tacos.addIngredient(new Ingredient("pint cherry tomatoes, halved", BigDecimal.valueOf(0.5), eachUom));
        tacos.addIngredient(new Ingredient("red onion, thinly sliced", BigDecimal.valueOf(0.25), eachUom));
        tacos.addIngredient(new Ingredient("Roughly chopped cilantro", BigDecimal.valueOf(1), eachUom));
        tacos.addIngredient(new Ingredient("sour cream thinned with 1/4 cup milk", BigDecimal.valueOf(0.5), cupUom));
        tacos.addIngredient(new Ingredient("lime, cut into wedges", BigDecimal.valueOf(1), eachUom));

        tacos.getCategories().add(mexicanCategory);
        tacos.getCategories().add(americanCategory);

        recipeRepository.save(tacos);

    }
}

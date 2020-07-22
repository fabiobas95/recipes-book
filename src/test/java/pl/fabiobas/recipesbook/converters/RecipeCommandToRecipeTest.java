package pl.fabiobas.recipesbook.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.fabiobas.recipesbook.commands.CategoryCommand;
import pl.fabiobas.recipesbook.commands.IngredientCommand;
import pl.fabiobas.recipesbook.commands.NotesCommand;
import pl.fabiobas.recipesbook.commands.RecipeCommand;
import pl.fabiobas.recipesbook.model.Difficulty;
import pl.fabiobas.recipesbook.model.Notes;
import pl.fabiobas.recipesbook.model.Recipe;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RecipeCommandToRecipeTest {

    private static final Long ID_VAL = 1L;
    private static final String DESCRIPTION = "description";
    private static final Integer PREP_TIME = 10;
    private static final Integer COOK_TIME = 20;
    private static final Integer SERVINGS = 5;
    private static final String SOURCE = "source";
    private static final String URL = "url";
    private static final String DIRECTIONS = "directions";
    private static final Difficulty DIFFICULTY = Difficulty.EASY;
    private static final Long INGRED_ID_1 = 3L;
    private static final Long INGRED_ID_2 = 4L;
    private static final Long CAT_ID_1 = 5L;
    private static final Long CAT_ID_2 = 6L;
    private static final Long NOTES_ID = 7L;

    RecipeCommandToRecipe converter;

    @BeforeEach
    void setUp() {
        converter = new RecipeCommandToRecipe(new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()),
                new NotesCommandToNotes(), new CategoryCommandToCategory());
    }

    @Test
    void testNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(converter.convert(new RecipeCommand()));
    }

    @Test
    void convert() {
        //given
        RecipeCommand command = new RecipeCommand();
        command.setId(ID_VAL);
        command.setDescription(DESCRIPTION);
        command.setPrepTime(PREP_TIME);
        command.setCookTime(COOK_TIME);
        command.setServings(SERVINGS);
        command.setSource(SOURCE);
        command.setUrl(URL);
        command.setDirections(DIRECTIONS);
        command.setDifficulty(DIFFICULTY);

        IngredientCommand ingredient1 = new IngredientCommand();
        ingredient1.setId(INGRED_ID_1);
        IngredientCommand ingredient2 = new IngredientCommand();
        ingredient2.setId(INGRED_ID_2);

        command.getIngredients().add(ingredient1);
        command.getIngredients().add(ingredient2);

        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(NOTES_ID);

        command.setNotes(notesCommand);

        CategoryCommand category1 = new CategoryCommand();
        category1.setId(CAT_ID_1);
        CategoryCommand category2 = new CategoryCommand();
        category2.setId(CAT_ID_2);

        command.getCategories().add(category1);
        command.getCategories().add(category2);

        //when
        Recipe recipe = converter.convert(command);
        //then
        assertNotNull(recipe);
        assertEquals(ID_VAL, recipe.getId());
        assertEquals(DESCRIPTION, recipe.getDescription());
        assertEquals(PREP_TIME, recipe.getPrepTime());
        assertEquals(COOK_TIME, recipe.getCookTime());
        assertEquals(SERVINGS, recipe.getServings());
        assertEquals(SOURCE, recipe.getSource());
        assertEquals(URL, recipe.getUrl());
        assertEquals(DIRECTIONS, recipe.getDirections());
        assertEquals(DIFFICULTY, recipe.getDifficulty());
        assertEquals(NOTES_ID, recipe.getNotes().getId());
        assertEquals(2, recipe.getIngredients().size());
        assertEquals(2, recipe.getCategories().size());
    }
}
package pl.fabiobas.recipesbook.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.fabiobas.recipesbook.commands.CategoryCommand;
import pl.fabiobas.recipesbook.commands.NotesCommand;
import pl.fabiobas.recipesbook.model.Category;
import pl.fabiobas.recipesbook.model.Notes;

import static org.junit.jupiter.api.Assertions.*;

class NotesToNotesCommandTest {

    private final static Long LONG_VALUE = 1L;
    private final static String DESCRIPTION = "description";

    NotesToNotesCommand converter;

    @BeforeEach
    void setUp() {
        converter = new NotesToNotesCommand();
    }

    @Test
    void testNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(converter.convert(new Notes()));
    }

    @Test
    void convert() {
        //given
        Notes notes = new Notes();
        notes.setId(LONG_VALUE);
        notes.setRecipeNotes(DESCRIPTION);

        //when
        NotesCommand notesCommand = converter.convert(notes);

        //then
        assertNotNull(notesCommand);
        assertEquals(LONG_VALUE, notesCommand.getId());
        assertEquals(DESCRIPTION, notesCommand.getRecipeNotes());
    }
}
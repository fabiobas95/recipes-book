package pl.fabiobas.recipesbook.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.fabiobas.recipesbook.commands.CategoryCommand;
import pl.fabiobas.recipesbook.commands.NotesCommand;
import pl.fabiobas.recipesbook.model.Category;
import pl.fabiobas.recipesbook.model.Notes;

import static org.junit.jupiter.api.Assertions.*;

class NotesCommandToNotesTest {

    private final static Long LONG_VALUE = 1L;
    private final static String DESCRIPTION = "description";

    NotesCommandToNotes converter;

    @BeforeEach
    void setUp() {
        converter = new NotesCommandToNotes();
    }

    @Test
    void testNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(converter.convert(new NotesCommand()));
    }

    @Test
    void convert() {
        //given
        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(LONG_VALUE);
        notesCommand.setRecipeNotes(DESCRIPTION);

        //when
        Notes notes = converter.convert(notesCommand);

        //then
        assertNotNull(notes);
        assertEquals(LONG_VALUE, notes.getId());
        assertEquals(DESCRIPTION, notes.getRecipeNotes());
    }
}
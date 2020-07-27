package pl.fabiobas.recipesbook.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.fabiobas.recipesbook.commands.RecipeCommand;
import pl.fabiobas.recipesbook.services.ImageService;
import pl.fabiobas.recipesbook.services.RecipeService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ImageControllerTest {

    @Mock
    ImageService imageService;

    @Mock
    RecipeService recipeService;

    MockMvc mockMvc;

    ImageController imageController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        imageController = new ImageController(imageService, recipeService);
        mockMvc = MockMvcBuilders.standaloneSetup(imageController).build();
    }

    @Test
    void getImageForm() throws Exception {
        //given
        RecipeCommand command = new RecipeCommand();
        command.setId(1L);

        when(recipeService.findCommandById(anyLong())).thenReturn(command);

        //when
        mockMvc.perform(get("/recipe/1/image"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("recipe"));

        //then
        verify(recipeService, times(1)).findCommandById(anyLong());
    }

    @Test
    void saveImage() throws Exception {
        MockMultipartFile multipartFile = new MockMultipartFile("imagefile", "testing.txt",
                "text/plain", "Fabio Bas".getBytes());

        mockMvc.perform(multipart("/recipe/1/image").file(multipartFile))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/recipe/1/show"));

        verify(imageService, times(1)).saveImageFile(anyLong(), any());
    }

    @Test
    void renderImageFromDB() throws Exception {

        //given
        RecipeCommand command = new RecipeCommand();
        command.setId(1L);

        String s = "fake image text";
        Byte[] boxed = new Byte[s.getBytes().length];

        int i = 0;

        for (byte b : s.getBytes()) {
            boxed[i++] = b;
        }

        command.setImage(boxed);

        when(recipeService.findCommandById(anyLong())).thenReturn(command);

        //when
        MockHttpServletResponse response = mockMvc.perform(get("/recipe/1/recipeimage"))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        byte[] responseBytes = response.getContentAsByteArray();

        assertEquals(s.getBytes().length,responseBytes.length);
    }
}
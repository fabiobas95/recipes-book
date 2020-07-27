package pl.fabiobas.recipesbook.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import pl.fabiobas.recipesbook.model.Recipe;
import pl.fabiobas.recipesbook.repositories.RecipeRepository;

import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService {

    private final RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }


    @Override
    @Transactional
    public void saveImageFile(Long recipeId, MultipartFile multipartFile) {
        try {
            Recipe recipe = recipeRepository.findById(recipeId).get();

            Byte[] bytes = new Byte[multipartFile.getBytes().length];

            int i = 0;

            for (byte b : multipartFile.getBytes()) {
                bytes[i++] = b;
            }

            recipe.setImage(bytes);

            recipeRepository.save(recipe);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

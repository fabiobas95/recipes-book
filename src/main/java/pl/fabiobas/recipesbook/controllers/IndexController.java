package pl.fabiobas.recipesbook.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.fabiobas.recipesbook.services.RecipeService;

@Controller
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"","/","/index"})
    String getIndexPage(Model model){

        model.addAttribute("recipes",recipeService.getRecipes());
        return "index";
    }
}

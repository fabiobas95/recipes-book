package pl.fabiobas.recipesbook.services;

import pl.fabiobas.recipesbook.commands.UnitOfMeasureCommand;

import java.util.Set;

public interface UnitOfMeasureService {
    Set<UnitOfMeasureCommand> listAllUoms();
}

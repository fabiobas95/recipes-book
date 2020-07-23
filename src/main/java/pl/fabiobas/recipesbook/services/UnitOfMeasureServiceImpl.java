package pl.fabiobas.recipesbook.services;

import org.springframework.stereotype.Service;
import pl.fabiobas.recipesbook.commands.UnitOfMeasureCommand;
import pl.fabiobas.recipesbook.converters.UnitOfMeasureToUnitOfMeasureCommand;
import pl.fabiobas.recipesbook.repositories.UnitOfMeasureRepository;

import java.util.HashSet;
import java.util.Set;

@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final UnitOfMeasureToUnitOfMeasureCommand converter;

    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository, UnitOfMeasureToUnitOfMeasureCommand converter) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.converter = converter;
    }

    @Override
    public Set<UnitOfMeasureCommand> listAllUoms() {
        Set<UnitOfMeasureCommand> unitOfMeasureCommandSet = new HashSet<>();
        unitOfMeasureRepository.findAll().
                forEach(uom ->unitOfMeasureCommandSet.add(converter.convert(uom)));
        return unitOfMeasureCommandSet;
    }
}

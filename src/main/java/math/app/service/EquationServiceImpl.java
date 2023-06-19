package math.app.service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import math.app.exception.InvalidEquationException;
import math.app.model.Equation;
import math.app.repository.EquationRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EquationServiceImpl implements EquationService {
    private final ValidationEquationService validationEquationService;
    private final RootCheckService checkService;
    private final EquationRepository repository;

    @Override
    public Equation save(String equationString) throws InvalidEquationException {
        if (validationEquationService.isValidEquation(equationString)) {
            Equation equation = new Equation();
            String normalizedString = equationString.replaceAll("\\s", "");
            equation.setEquationString(normalizedString);
            return repository.save(equation);
        } else {
            throw new InvalidEquationException("Invalid equation: " + equationString);
        }
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Equation updateWithRoot(String equationString, String x)
            throws InvalidEquationException {
        String normalizedString = equationString.replaceAll("\\s", "");
        if (!repository.existsByEquationString(normalizedString)) {
            throw new EntityNotFoundException("Equation not found: " + equationString);
        }
        if (checkService.checkValidRoot(normalizedString, x)) {
            Equation byEquationString = repository.findByEquationString(normalizedString);
            byEquationString.setRootEquation(x);
            repository.save(byEquationString);
            return byEquationString;
        } else {
            throw new InvalidEquationException("Invalid root: " + x);
        }
    }

    @Override
    public List<Equation> findAllByRootEquation(String root) {
        return repository.findAllByRootEquation(root);
    }

}

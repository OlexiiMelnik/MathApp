package math.app.service;

import java.util.List;
import math.app.exception.InvalidEquationException;
import math.app.model.Equation;

public interface EquationService {
    Equation save(String equation) throws InvalidEquationException;

    void deleteById(Long id);

    Equation updateWithRoot(String equation, String x) throws InvalidEquationException;

    List<Equation> findAllByRootEquation(String root);
}

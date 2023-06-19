package math.app.repository;

import java.util.List;
import math.app.model.Equation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquationRepository extends JpaRepository<Equation, Long> {
    Equation findByEquationString(String equation);

    boolean existsByEquationString(String equationString);

    List<Equation> findAllByRootEquation(String root);
}

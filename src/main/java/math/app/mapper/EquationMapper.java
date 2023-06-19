package math.app.mapper;

import math.app.model.Equation;
import math.app.model.dto.EquationRequestDto;
import math.app.model.dto.EquationResponseDto;
import org.springframework.stereotype.Component;

@Component
public class EquationMapper {
    public EquationResponseDto toDto(Equation equation) {
        EquationResponseDto responseDto = new EquationResponseDto();
        responseDto.setId(equation.getId());
        responseDto.setEquationString(equation.getEquationString());
        responseDto.setRootEquation(equation.getRootEquation());
        return responseDto;
    }

    public Equation toModel(EquationRequestDto requestDto) {
        Equation equation = new Equation();
        equation.setRootEquation(requestDto.getRootEquation());
        equation.setEquationString(requestDto.getEquationString());
        return equation;
    }
}

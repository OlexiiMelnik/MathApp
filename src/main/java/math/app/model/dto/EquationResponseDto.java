package math.app.model.dto;

import lombok.Data;

@Data
public class EquationResponseDto {
    private Long id;
    private String equationString;
    private String rootEquation;
}

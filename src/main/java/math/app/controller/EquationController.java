package math.app.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import math.app.exception.InvalidEquationException;
import math.app.mapper.EquationMapper;
import math.app.model.Equation;
import math.app.model.dto.EquationRequestDto;
import math.app.model.dto.EquationResponseDto;
import math.app.service.EquationService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/equation")
@RequiredArgsConstructor
@Tag(name = "Equation Controller",
        description = "an application that helps a math teacher manage equations")
@Log4j2
public class EquationController {
    private final EquationService equationService;
    private final EquationMapper mapper;

    @PostMapping
    @Operation(summary = "if the equation is correct, we save it in the db")
    public EquationResponseDto create(@RequestBody String equationString)
            throws InvalidEquationException {
        log.info("method create start" + LocalDateTime.now());
        return mapper.toDto(equationService.save(equationString));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete equation by Id")
    public void deleteById(@PathVariable Long id) {
        log.info("method deleteById start" + LocalDateTime.now());
        equationService.deleteById(id);
    }

    @PutMapping("/update-with-root")
    @Operation(summary = "if root correct we save root to DB")
    public EquationResponseDto updateWithRoot(
            @RequestBody EquationRequestDto requestDto)
            throws InvalidEquationException {
        log.info("method updateWithRoot start" + LocalDateTime.now());
        Equation equation = equationService.updateWithRoot(
                requestDto.getEquationString(), requestDto.getRootEquation());
        return mapper.toDto(equation);
    }

    @GetMapping("/allWithRoot")
    @Operation(summary = "find all Equation by root")
    public List<EquationResponseDto> findAllByRootEquation(
            @RequestParam("root") String root) {
        log.info("method findAllByRootEquation start" + LocalDateTime.now());
        return equationService.findAllByRootEquation(root).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}

package ru.nsu.ccfit.khudyakov.lab3.osm.exception;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.nsu.ccfit.khudyakov.lab3.osm.response.ApiResponse;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static ru.nsu.ccfit.khudyakov.lab3.osm.exception.ErrorType.*;

@ControllerAdvice
@Component
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        Map<String, String> errors = ex.getAllErrors().stream()
                .collect(Collectors.toMap(
                        ObjectError::getObjectName,
                        e -> StringUtils.defaultIfEmpty(e.getDefaultMessage(), "Ошибка валидации")
                ));

        ApiResponse<Void> apiResponse = ApiResponse.createError(VALIDATION_ERROR,
                List.of("Ошибка валидации"),
                errors);

        return ResponseEntity.ok(apiResponse);
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ApiResponse<Void>> processServiceException(ServiceException e) {
        return ResponseEntity.ok(ApiResponse.createError(e.getError(), e.getErrorMessages()));
    }

}

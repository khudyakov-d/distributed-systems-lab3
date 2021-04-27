package ru.nsu.ccfit.khudyakov.lab3.osm.exception;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.nsu.ccfit.khudyakov.lab3.osm.response.ApiResponse;

import java.util.List;
import java.util.stream.Collectors;

import static ru.nsu.ccfit.khudyakov.lab3.osm.exception.ErrorType.VALIDATION_ERROR;

@ControllerAdvice
@Component
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        ApiResponse<Void> apiResponse = getValidationsErrors(ex);

        return ResponseEntity.ok(apiResponse);
    }

    private ApiResponse<Void> getValidationsErrors(BindException ex) {
        List<ErrorMessageDto> errors = ex.getAllErrors().stream()
                .map(e -> new ErrorMessageDto(getErrorPlace(e), StringUtils.defaultIfEmpty(e.getDefaultMessage(), "Ошибка валидации")))
                .collect(Collectors.toList());

        return ApiResponse.createError(VALIDATION_ERROR,
                List.of("Ошибка валидации"),
                errors);
    }

    private String getErrorPlace(ObjectError objectError) {
        if (objectError instanceof FieldError) {
            FieldError fieldError = (FieldError) objectError;
            return fieldError.getField();
        } else {
            return objectError.getObjectName();
        }
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ApiResponse<Void>> processServiceException(ServiceException e) {
        return ResponseEntity.ok(ApiResponse.createError(e.getError(), e.getErrorMessages()));
    }

}

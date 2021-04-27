package ru.nsu.ccfit.khudyakov.lab3.osm.response;


import lombok.Data;
import ru.nsu.ccfit.khudyakov.lab3.osm.exception.ErrorType;

import java.util.List;
import java.util.Map;

import static ru.nsu.ccfit.khudyakov.lab3.osm.response.Status.ERROR;
import static ru.nsu.ccfit.khudyakov.lab3.osm.response.Status.OK;

@Data
public class ApiResponse<T> {

    private Status status;

    private T data;

    private ErrorType error;

    private List<String> errorMessages;

    private Map<String, String> validationErrors;

    public static <S> ApiResponse<S> createOk(S data) {
        ApiResponse<S> apiResponse = new ApiResponse<>();
        apiResponse.setStatus(OK);
        apiResponse.setData(data);
        return apiResponse;
    }

    public static <S> ApiResponse<S> createError(ErrorType error, List<String> errorMessages) {
        ApiResponse<S> apiResponse = new ApiResponse<>();
        apiResponse.setStatus(ERROR);
        apiResponse.setError(error);
        apiResponse.setErrorMessages(errorMessages);
        return apiResponse;
    }

    public static <S> ApiResponse<S> createError(ErrorType error,
                                                 List<String> errorMessages,
                                                 Map<String, String > validationErrors) {
        ApiResponse<S> apiResponse = createError(error, errorMessages);
        apiResponse.setValidationErrors(validationErrors);
        return apiResponse;
    }

}

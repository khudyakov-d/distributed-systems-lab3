package ru.nsu.ccfit.khudyakov.lab3.osm.exception;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class ServiceException extends RuntimeException {

    private final ErrorType error;

    private final List<String> errorMessages;

}

package ru.nsu.ccfit.khudyakov.lab3.osm.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessageDto {

    private String object;

    private String message;

}

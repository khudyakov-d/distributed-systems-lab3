package ru.nsu.ccfit.khudyakov.lab3.osm.tags;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class TagDto {

    @NotNull
    private String key;

    @NotNull
    private String value;

}

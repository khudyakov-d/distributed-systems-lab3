package ru.nsu.ccfit.khudyakov.lab3.osm.tags;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class TagId implements Serializable {

    private String key;

    private String value;

}

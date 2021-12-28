package ru.syntez.asyncapi.parser.entities.input;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Asyncapi input data
 * @author Skyhunter
 * @date 24.12.2021
 */
@Data
@JsonIgnoreProperties
public class AsyncapiInput {

    private String asyncapi; //api version '2.0.0'

}

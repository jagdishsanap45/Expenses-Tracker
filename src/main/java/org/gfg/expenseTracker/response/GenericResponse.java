package org.gfg.expenseTracker.response;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class GenericResponse<T> {

    private Integer statusCode;// 0--> sucessfull if 1 --> failure
    private String message;
    private Integer code;
    private T data; //data will vary according to response from different api
}

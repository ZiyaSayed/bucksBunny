package com.app.bucksbunny.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class APIPayload {

    String message;
    int httpCode;
    String httpStatus;
    boolean success;
    boolean exception;
    Object body;

}

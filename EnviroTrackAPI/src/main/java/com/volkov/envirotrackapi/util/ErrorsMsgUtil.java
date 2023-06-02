package com.volkov.envirotrackapi.util;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class ErrorsMsgUtil {

    public static void getBackErrors(BindingResult bindingResult) {
        var errorMsg = new StringBuilder();
        var errors = bindingResult.getFieldErrors();
        for (FieldError error : errors) {
            errorMsg.append(error.getField())
                    .append(" - ")
                    .append(error.getDefaultMessage() == null ? error.getCode() : error.getDefaultMessage())
                    .append("; ");
        }
        throw new SensorAndMeasurementException(errorMsg.toString());
    }
}

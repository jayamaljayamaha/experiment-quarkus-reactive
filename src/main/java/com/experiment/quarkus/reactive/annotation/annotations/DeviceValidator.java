package com.experiment.quarkus.reactive.annotation.annotations;

import com.experiment.quarkus.reactive.annotation.implementations.ValidateDevice;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;


import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ValidateDevice.class)
public @interface DeviceValidator {

    String message() default "Invalid device type";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}

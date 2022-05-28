package de.hsrm.mi.web.projekt.validierung;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.FIELD}) // Worauf ist Annotation anwendbar? 
@Retention(RetentionPolicy.RUNTIME) // zur Laufzeit vorhanden
// Verweis auf Validator-Implementierung 
@Constraint(validatedBy=BuntValidator.class)
@Documented
public @interface Bunt {
    String message() default "Farbe {value} ist nicht Bunt genug";
    Class<? extends Payload>[] payload() default { };
    Class<?>[] groups() default { };
}

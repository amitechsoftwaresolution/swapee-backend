package io.swapee.swapeebackend.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Minoltan Issack on 12/24/2022
 */
// Enables the annotation to be intercepted at runtime
@Retention(RetentionPolicy.RUNTIME)
// Restricts this annotation to only be used with methods
@Target(ElementType.METHOD)
public @interface ToLog {
}

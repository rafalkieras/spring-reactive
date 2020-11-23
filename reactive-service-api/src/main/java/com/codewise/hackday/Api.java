package com.codewise.hackday;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PACKAGE, ElementType.TYPE})
@Retention(RetentionPolicy.CLASS)
@Value.Style(
        typeAbstract = "*",
        typeImmutable = "*Resource",
        get = {"is*", "get*"},
        init = "with*",
        unset = "without*",
        from = "copy",
        visibility = Value.Style.ImplementationVisibility.PUBLIC,
        builderVisibility = Value.Style.BuilderVisibility.PUBLIC,
        jdkOnly = true,
        defaults = @Value.Immutable(copy = false),
        depluralize = true,
        allParameters = true,
        validationMethod = Value.Style.ValidationMethod.NONE,
        defaultAsDefault = true
)
@JsonSerialize
@JsonDeserialize
public @interface Api {

}

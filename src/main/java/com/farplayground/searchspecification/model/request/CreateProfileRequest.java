package com.farplayground.searchspecification.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * @author farras
 * @since 0.0.1
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateProfileRequest {

    @JsonProperty
    @NotNull
    @NotEmpty
    @JsonDeserialize(using = StringDeserializer.class)
    String name;

    @JsonProperty
    @NotNull
    @Positive
    Integer age;
}

package com.experiment.quarkus.reactive.dto;

import com.experiment.quarkus.reactive.annotation.annotations.DeviceValidator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.Date;

@Data
public class ImageData {
    @NotBlank(message = "Image name should not be null or empty")
    @Schema(example = "cute dog", type = SchemaType.STRING)
    private String name;
    @Digits(integer = 10, fraction = 0)
    @Schema(example = "120", type = SchemaType.INTEGER)
    private Double width;
    @Digits(integer = 10, fraction = 0)
    @Schema(example = "120", type = SchemaType.INTEGER)
    private Double height;
    @JsonProperty(value = "number_of_pixels")
    @Digits(integer = 10, fraction = 0)
    @Schema(example = "14400", type = SchemaType.INTEGER)
    private Integer numberOfPixels;
    @Schema(example = "jpg", type = SchemaType.STRING)
    private String format;
    @Schema(example = "https://images.examples.com/cute_dog_123", type = SchemaType.STRING)
    private String url;
    @JsonProperty(value = "created_date")
    @Schema(example = "2017-07-21T17:32:28Z", format = "date-time")
    private Date createdDate;
    @JsonProperty(value = "last_modified_date")
    @Schema(example = "2018-07-21T17:32:28Z", format = "date-time")
    private Date lastModifiedDate;
    @Digits(integer = 10, fraction = 0)
    @Schema(example = "1320000", type = SchemaType.INTEGER)
    private Integer size;
    @JsonProperty(value = "captured_by")
    @Schema(example = "sandun", type = SchemaType.STRING)
    private String capturedBy;
    @DeviceValidator
    @Schema(example = "DSLR", type = SchemaType.STRING)
    private String device;
}

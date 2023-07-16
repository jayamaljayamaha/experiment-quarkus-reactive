package com.experiment.quarkus.reactive.dto;

import com.experiment.quarkus.reactive.common.InvalidImage;
import com.experiment.quarkus.reactive.common.ReturnImage;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Data
@Builder
public class ImageResponse implements Serializable {
    private int successImages;
    private int failedImages;
    @Builder.Default
    private List<ReturnImage> images = new ArrayList<>();
    @Builder.Default
    private List<InvalidImage> invalidImages = new ArrayList<>();

}

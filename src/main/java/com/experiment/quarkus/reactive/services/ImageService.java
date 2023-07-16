package com.experiment.quarkus.reactive.services;

import com.experiment.quarkus.reactive.common.Device;
import com.experiment.quarkus.reactive.common.InvalidImage;
import com.experiment.quarkus.reactive.common.ReturnImage;
import com.experiment.quarkus.reactive.dto.ImageData;
import com.experiment.quarkus.reactive.dto.ImageRequest;
import com.experiment.quarkus.reactive.dto.ImageResponse;
import com.experiment.quarkus.reactive.entity.Image;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.hibernate.reactive.mutiny.Mutiny;

import java.util.List;
import java.util.Set;

@ApplicationScoped
public class ImageService {

    @Inject
    Mutiny.SessionFactory sessionFactory;

    @Inject
    Validator validator;

    @Transactional
    public Multi<ReturnImage> saveImages(ImageRequest imageRequest) {
        ImageResponse response = ImageResponse.builder().build();
        List<Multi<ReturnImage>> returnImages = imageRequest.getImages().stream()
                .filter(imageData -> this.validateImageData(imageData, response, imageRequest.getImages().indexOf(imageData)))
                .map(this::createImageEntity)
                .map(image -> sessionFactory.withTransaction(session -> session.persist(image).onItem()
                        .transform(result -> ReturnImage.builder()
                                .id(image.getId().toString())
                                .url(image.getUrl())
                                .name(image.getName())
                                .isSuccess(true)
                                .build())
                        .onFailure().recoverWithItem(ReturnImage.builder()
                                .url(image.getUrl())
                                .name(image.getName())
                                .isSuccess(false)
                                .build())))
                .map(Uni::toMulti).toList();
        return Multi.createBy().merging().streams(returnImages);
    }

    private boolean validateImageData(ImageData imageData, ImageResponse imageResponse, int index) {
        Set<ConstraintViolation<ImageData>> violations = validator.validate(imageData);
        if (!violations.isEmpty()) {
            imageResponse.setFailedImages(imageResponse.getFailedImages() + 1);
            imageResponse.getInvalidImages().addAll(violations.stream()
                    .map(violation -> InvalidImage.builder().property(violation.getPropertyPath().toString())
                            .imageIndex(index).error(violation.getMessage()).build()).toList());
        }
        return violations.isEmpty();
    }

    private Image createImageEntity(ImageData imageData) {
        return Image.builder()
                .name(imageData.getName())
                .url(imageData.getUrl())
                .width(imageData.getWidth())
                .height(imageData.getHeight())
                .numberOfPixels(imageData.getNumberOfPixels())
                .format(imageData.getFormat())
                .createdDate(imageData.getCreatedDate())
                .lastModifiedDate(imageData.getLastModifiedDate())
                .size(imageData.getSize())
                .capturedBy(imageData.getCapturedBy())
                .device(Device.valueOf(imageData.getDevice()))
                .build();
    }
}

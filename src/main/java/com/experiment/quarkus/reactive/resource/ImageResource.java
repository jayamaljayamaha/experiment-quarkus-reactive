package com.experiment.quarkus.reactive.resource;

import com.experiment.quarkus.reactive.common.ReturnImage;
import com.experiment.quarkus.reactive.dto.ImageRequest;
import com.experiment.quarkus.reactive.services.ImageService;
import io.smallrye.mutiny.Multi;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/images")
public class ImageResource {

    @Inject
    ImageService imageService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Multi<ReturnImage> saveImages(ImageRequest request){
        return imageService.saveImages(request);
//        return Response.status(Response.Status.CREATED).entity(response).build();
    }
}

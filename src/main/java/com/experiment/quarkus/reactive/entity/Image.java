package com.experiment.quarkus.reactive.entity;

import com.experiment.quarkus.reactive.common.Device;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "image")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private Double width;
    private Double height;
    @Column(name = "number_of_pixels")
    private Integer numberOfPixels;
    private String format;
    private String url;
    @Column(name = "created_date")
    private Date createdDate;
    @Column(name = "last_modified_date")
    private Date lastModifiedDate;
    private Integer size;
    @Column(name = "captured_by")
    private String capturedBy;
    @Enumerated(EnumType.STRING)
    private Device device;
}

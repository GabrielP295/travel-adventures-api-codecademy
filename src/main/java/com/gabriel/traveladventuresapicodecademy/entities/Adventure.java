package com.gabriel.traveladventuresapicodecademy.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "ADVENTURES")
public class Adventure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Pattern(regexp = "\\d{2}/\\d{2}/\\d{4}", message = "date must be in MM/dd/yyyy format")
    @Column(name = "DATE")
    private String date;

    @NotBlank
    @Column(name = "COUNTRY")
    private String country;

    @NotBlank
    @Column(name = "CITY")
    private String city;

    @Column(name = "STATE")
    private String state;

    @NotNull
    @Min(value = 0, message = "numPhotos must be 0 or greater")
    @Column(name = "NUM_PHOTOS")
    private Long numPhotos;

    @NotNull
    @Column(name = "BLOG_COMPLETED")
    private Boolean blogCompleted;

    public Integer getId() {
        return this.id;
    }

    public String getDate() {
        return this.date;
    }

    public String getCountry() {
        return this.country;
    }

    public String getCity() {
        return this.city;
    }

    public String getState() {
        return this.state;
    }

    public Long getNumPhotos() {
        return this.numPhotos;
    }

    public Boolean getBlogCompleted() {
        return this.blogCompleted;
    }

    public void setBlogCompleted(Boolean blogCompleted) {
        this.blogCompleted = blogCompleted;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setNumPhotos(Long numPhotos) {
        this.numPhotos = numPhotos;
    }
}

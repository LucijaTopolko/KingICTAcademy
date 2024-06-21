package com.academy.kingictacademy.product.entity;

import java.util.Objects;

public class ProductDTO {
    private String thumbnail;
    private String title;
    private double price;
    private String description;

    public ProductDTO(String image, String title, double price, String description) {
        this.thumbnail = image;
        this.title = title;
        this.price = price;
        if (description != null && description.length() > 100) {
            this.description = description.substring(0, 100) + "...";
        } else {
            this.description = description;
        }    }

    public ProductDTO() {
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description != null && description.length() > 100) {
            this.description = description.substring(0, 100) + "...";
        } else {
            this.description = description;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDTO that = (ProductDTO) o;
        return Double.compare(price, that.price) == 0 && Objects.equals(thumbnail, that.thumbnail) && Objects.equals(title, that.title) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(thumbnail, title, price, description);
    }
}
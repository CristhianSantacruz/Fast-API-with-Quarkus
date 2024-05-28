package org.product;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Entity
public class ProductEntity extends PanacheEntity {

    @NotBlank
    public String name;
    @NotBlank
    public String description;
    @NotNull
    public BigDecimal price;
    public int stock;
}

package org.academiadecodigo.tailormoons.placeholder.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.HashMap;
import java.util.Map;

public class IngredientDto {

    private Integer id;

    @NotNull(message = "Name of the ingredient is mandatory")
    @NotBlank(message = "Name of the ingredient is mandatory")
    @Size(min = 3, max = 64)
    private String name;

    @NotNull(message = "Quantity is mandatory")
    @NotBlank(message = "Quantity is mandatory")
    @Size(max = 8)
    private Integer quantity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "IngredientDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}

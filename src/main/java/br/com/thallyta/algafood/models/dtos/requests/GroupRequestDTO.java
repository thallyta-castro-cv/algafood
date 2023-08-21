package br.com.thallyta.algafood.models.dtos.requests;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class GroupRequestDTO {

    @NotBlank
    private String name;
}
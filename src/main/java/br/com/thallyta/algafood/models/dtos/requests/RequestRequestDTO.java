package br.com.thallyta.algafood.models.dtos.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class RequestRequestDTO {

    @Valid
    @NotNull
    private RestaurantIdRequestDTO restaurant;

    @Valid
    @NotNull
    private AddressRequestDTO address;

    @Valid
    @NotNull
    private FormPaymentIdRequestDTO formPayment;

    @Valid
    @Size(min = 1)
    @NotNull(message = "O item do pedido deve ser informado.")
    private List<RequestItemRequestDTO> items;
}

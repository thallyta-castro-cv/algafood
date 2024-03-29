package br.com.thallyta.algafood.models.dtos.responses;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
public class RequestResponseDTO {

    private String code;
    private BigDecimal subtotal;
    private BigDecimal shippingFee;
    private BigDecimal totalValue;
    private String requestStatus;
    private OffsetDateTime createdDate;
    private OffsetDateTime confirmationDate;
    private OffsetDateTime deliveryDate;
    private OffsetDateTime cancellationDate;
    private String addressCep;
    private String addressStreet;
    private String addressNumber;
    private String addressComplement;
    private String addressNeighborhood;
    private Long addressCityId;
    private String addressCityName;
    private Long addressCityStateId;
    private String addressCityStateName;
    private Long restaurantId;
    private String restaurantName;
    private UserResponseDTO client;
    private FormPaymentResponseDTO formPayment;
    private List<RequestItemResponseDTO> items;
}

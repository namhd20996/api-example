package com.example.assign.order;

import com.example.assign.product.ProductDTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class OrderAddRequest {
    @NotBlank
    @Pattern(
            message = "regex username not valid",
            regexp = "^[a-zA-Z\\s]+$"
    )
    private String fullName;
    @Pattern(
            message = "regex phone not valid",
            regexp = "^(0?)(3[2-9]|5[6|8]|7[0|6-9]|8[0-6|]|9[0-4|6-9])[0-9]{7}$"
    )
    private String phoneNumber;
    @NotBlank
    private String address;
    @Email(
            message = "regex email",
            regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                    + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$"
    )
    private String email;

    @NotNull
    private List<ProductDTO> products;
}

package com.example.assign.order;

import com.example.assign.product.ProductDTO;
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
    @Pattern(
            message = "regex address not valid",
            regexp = "^[a-zA-Z\\s]+$"
    )
    private String address;
    @NotBlank(message = "Email not blank")
    @Pattern(
            message = "Regex email",
            regexp = "^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]+?\\.[a-zA-Z]{2,3}$"
    )
    private String email;
    @NotNull
    private List<ProductDTO> products;
}

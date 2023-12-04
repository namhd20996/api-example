package com.example.assign.product;

import com.example.assign.util.BaseResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
public class ProductResponse extends BaseResponse<ProductDTO> {
}

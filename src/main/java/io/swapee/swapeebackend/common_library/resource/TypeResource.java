package io.swapee.swapeebackend.common_library.resource;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TypeResource {
    private String name;
    private BigDecimal amount;
}

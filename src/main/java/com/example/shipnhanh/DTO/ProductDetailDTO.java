package com.example.shipnhanh.DTO;


import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ProductDetailDTO {
    private Long Id;
    private String name;
    private String nameMachanse;
    private String image;
    private BigDecimal price1;
    private BigDecimal price2;
    private Integer status;
    private Long merchantsId;
    private Long productsId;
    private Double longitude;
    private Double latitude;
    private Integer countSeach;

    public ProductDetailDTO(String name, String nameMachanse, String image, BigDecimal price1, BigDecimal price2, Integer status) {
        this.name = name;
        this.nameMachanse = nameMachanse;
        this.image = image;
        this.price1 = price1;
        this.price2 = price2;
        this.status = status;
    }
    public ProductDetailDTO(Long Id, String name, String nameMachanse, String image, BigDecimal price1, BigDecimal price2, Integer status ,Integer countSeach) {
        this.Id= Id;
        this.name = name;
        this.nameMachanse = nameMachanse;
        this.image = image;
        this.price1 = price1;
        this.price2 = price2;
        this.status = status;
        this.countSeach = countSeach;
    }

    public ProductDetailDTO(Long Id, String name, String nameMachanse, String image,
        BigDecimal price1, BigDecimal price2, Integer status, Long merchantsId,Long productsId) {
        this.Id= Id;
        this.name = name;
        this.nameMachanse = nameMachanse;
        this.image = image;
        this.price1 = price1;
        this.price2 = price2;
        this.status = status;
        this.merchantsId = merchantsId;
        this.productsId = productsId;
    }
}

package com.vku.dtos;





import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {


    private Long Id;
	
	private Long productId;
	
    private int quantity;

    private double intoMoney;
    
}

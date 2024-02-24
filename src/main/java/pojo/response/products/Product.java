package pojo.response.products;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product{
	private String name;
	private boolean inStock;
	private int id;
	private String category;
}
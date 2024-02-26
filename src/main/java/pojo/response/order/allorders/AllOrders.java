package pojo.response.order.allorders;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pojo.response.order.get_a_single_order.Item;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AllOrders{
	private String id;
	private List<Item> items;
	private String customerName;
	private String created;
	private String comment;
}
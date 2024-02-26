package pojo.response.order.get_a_single_order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetASingleOrderRes{
	private String id;
	private List<Item> items;
	private String customerName;
	private String createdBy;
	private String created;
	private long timestamp;
	private boolean processed;
	private String comment;
}
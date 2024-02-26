package pojo.response.order.get_a_single_order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    private int id;
    private int productId;
    private int quantity;
}

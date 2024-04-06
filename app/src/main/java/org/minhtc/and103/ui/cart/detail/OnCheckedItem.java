package org.minhtc.and103.ui.cart.detail;

import org.minhtc.and103.data.dto.OrderDetail;
import org.minhtc.and103.data.model.CartItem;
import java.util.List;
public interface OnCheckedItem {
    void onCheckedItem(List<OrderDetail> selectedCartItems, Double totalAmount);
    void onUncheckedItem(List<OrderDetail> selectedCartItems, Double totalAmount);
}

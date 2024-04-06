package org.minhtc.and103.ui.cart;

import org.minhtc.and103.data.model.Cart;
import org.minhtc.and103.data.model.Product;

public interface OnCartClickListener {

    void onCartClick(Cart cart);
    void onDeleteCart(Cart cart);
}

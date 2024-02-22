package org.zerock.mallapi.service;

import org.zerock.mallapi.dto.CartItemDTO;
import org.zerock.mallapi.dto.CartItemListDTO;

import java.util.List;

public interface CartService {
    public List<CartItemListDTO> addOrModify(CartItemDTO cartItemDTO);
    public List<CartItemListDTO> getCartItems(String email);
    public List<CartItemListDTO> remove(Long cino);
}

package com.example.book_storemanagement.service.cart;

import com.example.book_storemanagement.model.dto.CartDTO;

import com.example.book_storemanagement.model.dto.TotalPriceDTO;
import com.example.book_storemanagement.model.entity.Cart;
import com.example.book_storemanagement.repository.ICartRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService implements ICartService {
    @Autowired
    private ICartRepository iCartRepository;

    @Override
    public void createCart(Long accountId, Long bookId, Cart cart) {
        //iCartRepository.createCart(accountId, bookId, quantity,cart);
        iCartRepository.createCart(accountId, bookId,cart);
    }

    @Override
    public List<CartDTO> findAllCart(Long accountId) {
        return iCartRepository.findAllCart(accountId);
    }

    @Override
    public Optional<TotalPriceDTO> getTotal(Long accountId) {
        return iCartRepository.getTotal(accountId);
    }

    @Override
    public void removeBookToCart(Long id) {
        iCartRepository.deleteById(id);
    }


}

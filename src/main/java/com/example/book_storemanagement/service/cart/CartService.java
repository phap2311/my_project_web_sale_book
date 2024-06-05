package com.example.book_storemanagement.service.cart;

import com.example.book_storemanagement.model.dto.CartDTO;

import com.example.book_storemanagement.model.dto.TotalPriceDTO;
import com.example.book_storemanagement.model.entity.Bill;
import com.example.book_storemanagement.model.entity.Cart;
import com.example.book_storemanagement.repository.IBillRepository;
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

    @Autowired
    private IBillRepository iBillRepository;

    @Override
    public void createCart(Long accountId, Long bookId, Cart cart) {
        Cart existingCart = iCartRepository.findByAccountIdAndBookId(accountId, bookId);
        if (existingCart != null) {
            existingCart.setQuantity(existingCart.getQuantity() + cart.getQuantity());
            iCartRepository.save(existingCart);

        } else {
            iCartRepository.createCart(accountId, bookId, cart);
        }

    }

    @Override
    public void updateCartWithBill(Long accountId, String billCode) {
        List<Cart> carts = iCartRepository.findByAccountId(accountId);
        //gọi lại bill vừa tao
        // Optional<Bill> bill = iBillRepository.findByCodeBill(code);
       // billCode = generateFiveDigitInteger();

       Bill bill = iBillRepository.findByCode(billCode);
       Cart cart1;
        for (Cart cart : carts) {
            cart.setBill(bill);
            cart1 = cart;
            iCartRepository.save(cart1);
        }
    }

//    public String generateFiveDigitInteger() {
//        int randomNumber = (int) (Math.random() * 90000) + 10000;
//        return String.valueOf(randomNumber);
//    }



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

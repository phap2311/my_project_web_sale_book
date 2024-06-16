package com.example.book_storemanagement.service.cart;

import com.example.book_storemanagement.model.dto.CartDTO;

import com.example.book_storemanagement.model.dto.TotalPriceDTO;
import com.example.book_storemanagement.model.entity.Bill;
import com.example.book_storemanagement.model.entity.Books;
import com.example.book_storemanagement.model.entity.Cart;
import com.example.book_storemanagement.repository.IBillRepository;
import com.example.book_storemanagement.repository.IBookRepository;
import com.example.book_storemanagement.repository.ICartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService implements ICartService {
    @Autowired
    private ICartRepository iCartRepository;

    @Autowired
    private IBillRepository iBillRepository;

    @Autowired
    private IBookRepository iBookRepository;


    //    @Override
//    public void createCart(Long accountId, Long bookId, Cart cart) {
//
//        int availableQuantity = books.getQuantity();
//        int requestedQuantity = cart.getQuantity();
//
//
//        Cart existingCart = iCartRepository.findByAccountIdAndBookId(accountId, bookId);
//        if (existingCart != null) {
//            existingCart.setQuantity(existingCart.getQuantity() + cart.getQuantity());
//            iCartRepository.save(existingCart);
//
//        } else {
//            iCartRepository.createCart(accountId, bookId, cart);
//        }
//
//    }
    @Override
    public void createCart(Long accountId, Long bookId, Cart cart) {

        Optional<Books> books = iBookRepository.findById(bookId);
        int availableQuantity = books.get().getQuantity();
        int requestedQuantity = cart.getQuantity();

        Cart existingCart = iCartRepository.findByAccountIdAndBookId(accountId, bookId);
        if (existingCart != null) {
            requestedQuantity += existingCart.getQuantity();
        }

        if (requestedQuantity > availableQuantity) {
            throw new RuntimeException("Not enough books in stock");
        }

        if (existingCart != null) {
            existingCart.setQuantity(requestedQuantity);
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

    @Override
    public void removeAllBookCart(Long accountId) {
        iCartRepository.removeAllBookCart(accountId);
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
    public Optional<CartDTO> getAllCartByBook(Long accountId, Long bookId) {
        return iCartRepository.getAllCartByBook(accountId, bookId);
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

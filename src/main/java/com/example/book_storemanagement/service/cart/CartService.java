package com.example.book_storemanagement.service.cart;

import com.example.book_storemanagement.model.dto.CartDTO;
import com.example.book_storemanagement.model.dto.CartDTOI1;

import com.example.book_storemanagement.model.dto.TotalPriceDTO;
import com.example.book_storemanagement.model.entity.Bill;
import com.example.book_storemanagement.model.entity.Books;
import com.example.book_storemanagement.model.entity.Cart;
import com.example.book_storemanagement.repository.IBillRepository;
import com.example.book_storemanagement.repository.IBookRepository;
import com.example.book_storemanagement.repository.ICartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public List<CartDTOI1> getAllCartByBill(Long accountId, Long billId) {
        return iCartRepository.getAllCartByBill(accountId,billId);
    }


//    public List<CartDTOI1> removeBookFromCart(Long accountId, Long bookId) {
//        List<CartDTOI1> cart = findAllCart(accountId);
//        return cart.stream()
//                .filter(cartItem -> !cartItem.getBooks_id().equals(bookId))
//                .collect(Collectors.toList());
//    }
//    public String generateFiveDigitInteger() {
//        int randomNumber = (int) (Math.random() * 90000) + 10000;
//        return String.valueOf(randomNumber);
//    }

//    public List<CartDTO> removeAllBooksFromCart(Long accountId) {
//        // Fetch the cart items for the account
//        List<CartDTO> cart = findAllCart(accountId);
//
//        // Clear the cart by returning an empty list
//        cart.clear();
//
//        // Return the updated (empty) cart
//        return cart;
//    }
    @Override
    public List<CartDTOI1> findAllCart(Long accountId) {
        return iCartRepository.findAllCart(accountId);
    }
//    @Override
//    public List<CartDTO> findAllCart(Long accountId) {
//        return iCartRepository.findAllCart(accountId);
//    }
    @Override
    public Optional<CartDTOI1> getAllCartByBook(Long accountId, Long bookId) {
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

package com.example.book_storemanagement.service.seller;

import com.example.book_storemanagement.model.dto.SellerDetail;
import com.example.book_storemanagement.model.entity.Books;
import com.example.book_storemanagement.repository.ISellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SellerService implements ISellerService {
    @Autowired
    private ISellerRepository iSellerRepository;
    @Override
    public List<SellerDetail> getAllSeller() {
        return iSellerRepository.getAllSeller();
    }

    @Override
    public Optional<SellerDetail> getSellerDetail(Long id) {
        return iSellerRepository.getSellerDetail(id);
    }


}

package com.example.book_storemanagement.service.seller;

import com.example.book_storemanagement.model.dto.SellerDetail;
import com.example.book_storemanagement.model.entity.Books;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ISellerService {
    List<SellerDetail> getAllSeller();
    Optional<SellerDetail> getSellerDetail(Long id);



}

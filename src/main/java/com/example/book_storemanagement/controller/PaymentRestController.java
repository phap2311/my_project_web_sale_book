package com.example.book_storemanagement.controller;


import com.example.book_storemanagement.config.payment.PaymentConfig;
import com.example.book_storemanagement.model.dto.PaymentDTO;
import com.example.book_storemanagement.model.dto.PaymentInfoStorage;
import com.example.book_storemanagement.model.entity.Account;
import com.example.book_storemanagement.model.entity.Bill;
import com.example.book_storemanagement.service.account.IAccountService;
import com.example.book_storemanagement.service.bill.IBillService;
import com.example.book_storemanagement.service.book.IBookService;
import com.example.book_storemanagement.service.cart.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.*;
@RestController
@CrossOrigin("*")
@RequestMapping("/auth")
public class PaymentRestController {
    @Autowired
    private IAccountService iAccountService;

    @Autowired
    private IBillService iBillService;
    @Autowired
    private IBookService iBookService;
    @Autowired
    private ICartService iCartService;

    private final PaymentInfoStorage paymentInfoStorage;

    @Autowired
    public PaymentRestController(PaymentInfoStorage paymentInfoStorage) {
        this.paymentInfoStorage = paymentInfoStorage;
    }

    @GetMapping("/createPay")
    private ResponseEntity<?> payment(@RequestParam(value = "price") Long price,
                                    // @RequestBody Bill bill,
                                      @RequestParam(value = "id") Long id) throws UnsupportedEncodingException {
      // paymentInfoStorage.setBill(bill);
        long amount = price * 100;
        String orderType = "other";
        String bankCode = "NCB";
        String vnp_TxnRef = PaymentConfig.getRandomNumber(8);
        String vnp_IpAddr = "10.10.8.48";
        String vnp_ReturnUrl = "http://localhost:3000/paymentCart/" + id;
//        String vnp_ReturnUrl = "http://localhost:8080/auth/payment_info/" + id;
        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", PaymentConfig.vnp_Version);
        vnp_Params.put("vnp_Command", PaymentConfig.vnp_Command);
        vnp_Params.put("vnp_TmnCode", PaymentConfig.vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", "VND");
        vnp_Params.put("vnp_BankCode", bankCode);
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", "Thanh toan don ha ng:" + vnp_TxnRef);
        vnp_Params.put("vnp_OrderType", orderType);

        vnp_Params.put("vnp_Locale", "vn");
//        vnp_Params.put("vnp_ReturnUrl", PaymentConfig.vnp_ReturnUrl);
        vnp_Params.put("vnp_ReturnUrl", vnp_ReturnUrl);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        cld.add(Calendar.MINUTE, 5);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = PaymentConfig.hmacSHA512(PaymentConfig.secretKey, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = PaymentConfig.vnp_PayUrl + "?" + queryUrl;
        PaymentDTO paymentResDTO = new PaymentDTO();
        paymentResDTO.setStatus("OK");
        paymentResDTO.setMessage("Success");
        paymentResDTO.setUrl(paymentUrl);
        System.out.println(paymentUrl);
        return new ResponseEntity<>(paymentUrl, HttpStatus.OK);
    }

    @GetMapping("/payment_info/{id}")
    public ResponseEntity<?> getPaymentInfo(@PathVariable Long id,
                                            @RequestParam(value = "status") String status ,
                                            @RequestParam(value = "vnp_Amount", required = false) String amount,
                                            @RequestParam(value = "vnp_BankCode", required = false) String bankCode,
                                            @RequestParam(value = "vnp_OrderInfo", required = false) String order,
                                            @RequestParam(value = "vnp_ResponseCode", required = false) String responseCode,
                                            Principal principal) {
        Bill bill = paymentInfoStorage.getBill();
        Long billId = iBillService.getLastInsertedId();
        Optional<Account> account = this.iAccountService.findById(id);
        if (status.equals("00")) {
            System.out.println(account.get().getId() + " da thanh toan thanh cong");
            this.iBillService.update(id, billId);
            this.iBookService.updateQuantityBook(id);
            this.iCartService.getAllCartByBill(id,billId);
           // this.iCartService.removeAllBookCart(id);
            return new ResponseEntity<>("success",HttpStatus.OK);
        } else {
            System.out.println(account.get().getName() + " da huy thanh toan");
            return new ResponseEntity<>("error",HttpStatus.OK);
        }
    }
}

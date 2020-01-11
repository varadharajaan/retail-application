package com.retail.store.controller;

import javax.validation.Valid;

import com.retail.store.domain.bill.Item;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.retail.store.domain.DiscountCalculation;
import com.retail.store.domain.bill.Bill;
import com.retail.store.domain.user.User;
import com.retail.store.service.discount.DiscountService;

import java.util.List;
import java.util.Map;

@RequestMapping("discounts/v1")
@RestController
public class DiscountController {

    private final DiscountService discountService;

    @Autowired
    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @ApiOperation(value = "find discount values")
    @ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid Request"),
        @ApiResponse(code = 404, message = "Given user id not found"),
        @ApiResponse(code = 500, message = "Server Error"), })
    @PostMapping("/")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Map<Double,Double>> createDiscount(@Valid @RequestBody DiscountCalculation discCalc) {
        User user = discCalc.getUser();
        return new ResponseEntity<>(discountService.discountCalculation(user, discCalc.getBill()), HttpStatus.ACCEPTED);
    }
}

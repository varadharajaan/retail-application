package com.retail.store.service.discount;

import com.retail.store.domain.bill.Bill;
import com.retail.store.domain.bill.Item;
import com.retail.store.domain.user.User;

import java.util.List;
import java.util.Map;

public interface DiscountService {

    Map<Double,Double> discountCalculation(User user, Bill bill);
}

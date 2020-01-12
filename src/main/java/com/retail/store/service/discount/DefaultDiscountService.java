package com.retail.store.service.discount;

import com.retail.store.domain.bill.Item;
import com.retail.store.domain.discount.DiscountRange;
import com.retail.store.repository.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.retail.store.domain.bill.Bill;

import com.retail.store.domain.user.User;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class DefaultDiscountService implements DiscountService {

    @Autowired
    DiscountEngine de;

    @Autowired
    DiscountRepository discountRepository;


    @Override
    public Map<Double, Double> discountCalculation(User user, Bill bill) {


        Map<Double,Double> discountMap= new LinkedHashMap<>();

        bill.getItems().forEach(item ->
        {
            double amount;

           // double groceryAmount =   0.00;   //de.calculateTotal(bill.getItems(), ItemType.GROCERY);
            //double totalAmount = de.calculateTotal(bill.getItems(), null);
            //double nonGroceryAmount = totalAmount - groceryAmount;
            double userDiscount = de.getUserSpecificDiscount(user, item);
            //double billsDiscount = de.calculateBillsDiscount(totalAmount, 100, 0);

            amount = calculateDiscountForUser(item.getPrice(), user);

            discountMap.put(item.getPrice(),amount );

        });

        return discountMap;
    }

    private double calculateDiscountForUser(Double price, User user) {



        double discountedPrice = 0;

        double tempValue ;



        List<DiscountRange> discountsList = discountRepository.findAll();


        for (DiscountRange ds:discountsList) {
            tempValue = price-ds.getEnd();
            if(tempValue>0) {
                discountedPrice = discountedPrice + de.calculateDiscount(ds.getEnd(), ds.getDiscount());
                price=tempValue;
            }
            else
            {
                discountedPrice = discountedPrice + de.calculateDiscount(price, ds.getDiscount());
                break;
            }

        }
        return discountedPrice;
    }
}

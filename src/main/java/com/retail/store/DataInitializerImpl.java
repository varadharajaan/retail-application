package com.retail.store;

import com.retail.store.domain.discount.DiscountRange;
import com.retail.store.domain.user.UserType;
import com.retail.store.repository.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Varadharajan
 */
@Component
@Transactional
public class DataInitializerImpl {

    @Autowired
    DiscountRepository discountRepository;
    public void initData() {

        DiscountRange discountRange1 = new DiscountRange();

        discountRange1.setUser(UserType.REGULAR);
        discountRange1.setStart(0);
        discountRange1.setEnd(5000);
        discountRange1.setDiscount(0);

        DiscountRange discountRange2 = new DiscountRange();

        discountRange2.setUser(UserType.REGULAR);
        discountRange2.setStart(5001);
        discountRange2.setEnd(10000);
        discountRange2.setDiscount(0.1);

        DiscountRange discountRange3 = new DiscountRange();

        discountRange3.setUser(UserType.REGULAR);
        discountRange3.setStart(10001);
        discountRange3.setEnd(Integer.MAX_VALUE);
        discountRange3.setDiscount(0.2);

        discountRepository.save(discountRange1);
        discountRepository.save(discountRange2);
        discountRepository.save(discountRange3);

        DiscountRange discountRange5 = new DiscountRange();

        discountRange5.setUser(UserType.PREMIUM);
        discountRange5.setStart(0);
        discountRange5.setEnd(4000);
        discountRange5.setDiscount(0.1);

        DiscountRange discountRange6 = new DiscountRange();

        discountRange6.setUser(UserType.PREMIUM);
        discountRange6.setStart(4001);
        discountRange6.setEnd(8000);
        discountRange6.setDiscount(0.15);

        DiscountRange discountRange7 = new DiscountRange();

        discountRange7.setUser(UserType.PREMIUM);
        discountRange7.setStart(8001);
        discountRange7.setEnd(12000);
        discountRange7.setDiscount(0.2);

        DiscountRange discountRange8 = new DiscountRange();

        discountRange8.setUser(UserType.PREMIUM);
        discountRange8.setStart(12001);
        discountRange8.setEnd(Integer.MAX_VALUE);
        discountRange8.setDiscount(0.3);

        discountRepository.save(discountRange5);
        discountRepository.save(discountRange6);
        discountRepository.save(discountRange7);
        discountRepository.save(discountRange8);

    }


}

package com.retail.store.service.discount;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import com.retail.store.domain.bill.Bill;
import com.retail.store.domain.bill.Item;
import com.retail.store.domain.bill.ItemType;
import com.retail.store.domain.discount.DiscountRange;
import com.retail.store.domain.user.User;
import com.retail.store.domain.user.UserType;
import com.retail.store.repository.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DiscountEngine {


    @Autowired
    DiscountRepository discountRepository;
    /**
     * Calculate the total of all items of a specific <code>type</code>. Should
     * the type to check be <code>null</code> then it will calculate the total
     * of all item types.
     *
     * @param items
     *            - the {@link List} of items
     * @param type
     *            - the item type or null for all items
     * @return total of all items of a particular {@link ItemType}
     */
    public final double calculateTotal(List<Item> items, ItemType type) {
        if(items == null) {
            throw new NullPointerException("items cannot be null");
        }

        double total = 0.0;
        for(Item item : items) {
            if(type == null) {
                total = total + item.getPrice();
            }
            else {
                if(type.equals(item.getType())) {
                    total = total + item.getPrice();
                }
            }
        }

        return total;
    }

    /**
     * Performs a simple discount calculation of the <code>amount</code>
     *
     * @param amount
     *            - the amount to discount from
     * @param discount
     *            - the percentage to discount
     * @return the discounted amount
     */
    public final double calculateDiscount(double amount, double discount) {
        if(discount > 1.0) {
            throw new IllegalArgumentException("discount cannot be more than 1.0");
        }

        double x = amount * discount;
        return amount - x;
    }

    /**
     * Given a {@link User} it will calculate what their specific discount is
     * based on the retail store's policy.
     *
     * @param user
     *            - the user
     * @return the specific discount based on {@link UserType}
     */
    public final double getUserSpecificDiscount(User user, Item item) throws NullPointerException {
        if(user == null) {
            throw new NullPointerException("user cannot be null");
        }

        UserType type = user.getType();
        double discount = 0;

        switch(type) {
            case REGULAR: {
                if(isCustomerSince(user.getJoinDate(), 0)) {

                     DiscountRange discountRangeBy = discountRepository.findDiscountRangeBy(UserType.REGULAR, item.getPrice());
                    if(null!=discountRangeBy)
                    discount = discountRangeBy.getDiscount();
                }
                break;
            }

            case PREMIUM: {
                if(isCustomerSince(user.getJoinDate(), 0)) {
                     DiscountRange discountRangeBy = discountRepository.findDiscountRangeBy(UserType.PREMIUM, item.getPrice());
                    if(discountRangeBy != null)
                    discount = discountRangeBy.getDiscount();
                }
                break;
            }
            default: {
                break;
            }
        }

        return discount;
    }

    /**
     * Returns true if the join date is greater than or equal to the number of
     * years to check.
     *
     * @param joinDate
     *            - the date that the customer joined
     * @param years
     *            - the number of years to check
     * @return true if the customer has joined <code>years</code> ago
     */
    public final boolean isCustomerSince(LocalDate joinDate, long years) {
        LocalDate now = LocalDate.now();
        Period period = Period.between(joinDate, now);
        int diff = period.getYears();
        return diff >= years;
    }

    /**
     * For every <code>amount</code> it will discount
     * <code>discountAmount</code> from the total.
     *
     * @param totalAmount
     *            - the total amount to check
     * @param amount
     *            - the amount to count
     * @param discountAmount
     *            - the amount to discount per <code>amount</code>
     * @return the total discount amount
     */
    public final double calculateBillsDiscount(double totalAmount, double amount, double discountAmount) {
        int count = (int)(totalAmount / amount);
        return discountAmount * count;
    }
}

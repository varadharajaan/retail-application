package com.retail.store.domain.discount;

import com.retail.store.domain.BaseEntity;
import com.retail.store.domain.user.UserType;

/**
 * @Author Varadharajan on 11/01/20 07:17
 * @Projectname store
 */
import javax.persistence.*;

@Entity
@Table(name = "discountrange")
public class DiscountRange  extends BaseEntity {

    @Column(name="user")
    @Enumerated(EnumType.STRING)
    UserType user;
    @Column(length = 10)
    double start;
    @Column(length = 10)
    double end;
    @Column(length = 10)
    double discount;

    public DiscountRange(){

    }
    public DiscountRange(UserType user, double start, double end, double discount) {
        this.user = user;
        this.start = start;
        this.end = end;
        this.discount = discount;
    }

    public UserType getUser() {
        return user;
    }

    public void setUser(UserType user) {
        this.user = user;
    }

    public double getStart() {
        return start;
    }

    public void setStart(double start) {
        this.start = start;
    }

    public double getEnd() {
        return end;
    }

    public void setEnd(double end) {
        this.end = end;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double disount) {
        this.discount = disount;
    }
}

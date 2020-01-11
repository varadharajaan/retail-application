package com.retail.store.repository;

import com.retail.store.domain.discount.DiscountRange;
import com.retail.store.domain.user.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface DiscountRepository extends JpaRepository<DiscountRange, UUID> {

    @Query("SELECT u FROM DiscountRange u WHERE (u.user in :userType AND (u.start <= : bill AND u.end >= :bill))")
    DiscountRange findDiscountRangeBy(@Param("userType")UserType userType, @Param("bill")double bill);
}

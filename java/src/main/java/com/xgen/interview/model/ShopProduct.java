package com.xgen.interview.model;

import java.math.BigDecimal;
import java.util.Objects;

public class ShopProduct {
    public ShopProduct(String itemType) {
        this.itemType = itemType;
    }

    public String itemType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShopProduct that = (ShopProduct) o;
        return itemType.equals(that.itemType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemType);
    }
}

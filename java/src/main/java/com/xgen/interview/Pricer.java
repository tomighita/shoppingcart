package com.xgen.interview;

import com.xgen.interview.model.ShopProduct;

import java.math.BigDecimal;
import java.util.HashMap;


/**
 * A stub implementation - for this exercise, you may disregard that this is incomplete.
 */
public class Pricer {
    HashMap<ShopProduct, BigDecimal> pricingDatabase = new HashMap<>(); // stub
    HashMap<String, ShopProduct> itemTypeToShopProductConverter = new HashMap<>();

    public Pricer() {
        addProductsToDatabase(new ShopProduct("apple"), new BigDecimal("1.0"));
        addProductsToDatabase(new ShopProduct("banana"), new BigDecimal("2.0"));
    }

    BigDecimal priceIntToBigDecimal(int priceInCents) {
        return new BigDecimal("0." + priceInCents);
    }

    int priceBigDecimalToInt(BigDecimal price) {
        return (int)(price.floatValue() * 100);
    }

    @Deprecated
    public void addProductToDatabase(String itemType, int priceInCents) {
        ShopProduct product = new ShopProduct(itemType);
        BigDecimal price = new BigDecimal(priceInCents);
        addProductsToDatabase(product, price);
    }

    // TODO: replace with proper Exception handling
    public void addProductsToDatabase(ShopProduct product, BigDecimal price) {
        if (pricingDatabase.containsKey(product))
            throw new RuntimeException("This product is already in the DB!");
        pricingDatabase.put(product, price);
        itemTypeToShopProductConverter.put(product.itemType, product);
    }

    @Deprecated
    public Integer getPrice(String itemType) {
        ShopProduct item = findProduct(itemType);
        return priceBigDecimalToInt(getPrice(item));
    }

    public BigDecimal getPrice(ShopProduct product) {
        return getPrice(product, 1);
    }

    public BigDecimal getPrice(ShopProduct product, int quantity) {
        return pricingDatabase.getOrDefault(product, BigDecimal.ZERO).multiply(BigDecimal.valueOf(quantity));
    }

    public ShopProduct findProduct(String itemType) {
        if (!itemTypeToShopProductConverter.containsKey(itemType))
            itemTypeToShopProductConverter.put(itemType, new ShopProduct(itemType));
        return itemTypeToShopProductConverter.get(itemType);
    }

}

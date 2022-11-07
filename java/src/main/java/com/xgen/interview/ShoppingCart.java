package com.xgen.interview;

import com.xgen.interview.model.ShopProduct;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.*;


/**
 * This is the current implementation of ShoppingCart.
 * Please write a replacement
 */
public class ShoppingCart implements IShoppingCart {
    class ShoppingCartAddition {
        public ShoppingCartAddition(int quantity, long timestamp) {
            this.quantity = quantity;
            this.itemNumber = timestamp;
        }

        int quantity;
        long itemNumber;
    }
    HashMap<ShopProduct, ShoppingCartAddition> contents = new HashMap<>();
    Pricer pricer;
    long itemNumber = 0L;
    ReceiptFormatter receiptFormatter = new ReceiptFormatter();


    public ShoppingCart(Pricer pricer) {
        this.pricer = pricer;
    }

    public void addItem(String itemType, int number) {
        itemNumber++;
        ShopProduct product = pricer.findProduct(itemType);
        ShoppingCartAddition oldValue = contents.getOrDefault(product, new ShoppingCartAddition(0, 0));
        oldValue.quantity = oldValue.quantity + number;
        oldValue.itemNumber = itemNumber;
        contents.put(product, oldValue);
    }

    public void printReceipt() {
        BigDecimal subTotal = BigDecimal.ZERO;
        subTotal = this.contents.entrySet().stream()
                .sorted((a,b) -> (int) (a.getValue().itemNumber  - b.getValue().itemNumber))
                .map(x -> {
                    BigDecimal price = pricer.getPrice(x.getKey(),x.getValue().quantity);
                    System.out.println(receiptFormatter.formatItem(x.getKey(),price,x.getValue().quantity));
                    return price;
                }).reduce(BigDecimal.ZERO, (total, element) -> total.add(element));
        String totalString = String.format("€%.2f", subTotal.floatValue());
        System.out.println("Total - " + totalString);
    }
}

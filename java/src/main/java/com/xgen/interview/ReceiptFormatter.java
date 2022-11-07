package com.xgen.interview;

import com.xgen.interview.model.ShopProduct;

import java.math.BigDecimal;

public class ReceiptFormatter {
    public ReceiptFormatter() {}

    enum Column {
        PRICE, QUANTITY, ITEM
    }

    public Column[] columns = {Column.ITEM, Column.QUANTITY, Column.PRICE};

    public String formatItem(ShopProduct product, BigDecimal price, int quantity) {
        String priceString = String.format("€%.2f", price.floatValue());
        String output = "";
        for (int i = 0; i < columns.length; i++) {
            if (i > 0)
                output += " - ";
            Column col = columns[i];
            switch (col) {
                case ITEM:
                    output += product.itemType;
                    break;
                case QUANTITY:
                    output += quantity;
                    break;
                case PRICE:
                    output += priceString;
                    break;
            }
        }
        return output;
    }
}

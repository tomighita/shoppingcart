package com.xgen.interview;

import com.xgen.interview.Pricer;
import com.xgen.interview.ShoppingCart;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;


public class ShoppingCartTest {

    @Test
    public void canAddAnItem() {
        ShoppingCart sc = new ShoppingCart(new Pricer());

        sc.addItem("apple", 1);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();
        assertEquals(String.format("apple - 1 - €1.00%nTotal - €1.00%n"), myOut.toString());
    }

    @Test
    public void canAddMoreThanOneItem() {
        ShoppingCart sc = new ShoppingCart(new Pricer());

        sc.addItem("apple", 2);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();
        assertEquals(String.format("apple - 2 - €2.00%nTotal - €2.00%n"), myOut.toString());
    }

    @Test
    public void canAddDifferentItems() {
        ShoppingCart sc = new ShoppingCart(new Pricer());

        sc.addItem("apple", 2);
        sc.addItem("banana", 1);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();

        String result = myOut.toString();

        if (result.startsWith("apple")) {
            assertEquals(String.format("apple - 2 - €2.00%nbanana - 1 - €2.00%nTotal - €4.00%n"), result);
        } else {
            assertEquals(String.format("banana - 1 - €2.00%napple - 2 - €2.00%nTotal - €4.00%n"), result);
        }
    }

    @Test
    public void doesntExplodeOnMysteryItem() {
        ShoppingCart sc = new ShoppingCart(new Pricer());

        sc.addItem("crisps", 2);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();
        assertEquals(String.format("crisps - 2 - €0.00%nTotal - €0.00%n"), myOut.toString());
    }

    @Test
    public void itemsAreInProperOrder() {
        ShoppingCart sc = new ShoppingCart(new Pricer());

        sc.addItem("crisps", 2);
        sc.addItem("apple", 1);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();
        assertEquals(String.format("crisps - 2 - €0.00%napple - 1 - €1.00%nTotal - €1.00%n"), myOut.toString());
    }

    @Test
    public void itemsAreInProperOrderAfterMultipleAdds() {
        ShoppingCart sc = new ShoppingCart(new Pricer());

        sc.addItem("crisps", 2);
        sc.addItem("apple", 1);
        sc.addItem("crisps", 2);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();
        assertEquals(String.format("apple - 1 - €1.00%ncrisps - 4 - €0.00%nTotal - €1.00%n"), myOut.toString());
    }

    @Test
    public void itemsColumnsChangedOrder() {
        ShoppingCart sc = new ShoppingCart(new Pricer());
        sc.receiptFormatter.columns = new ReceiptFormatter.Column[]{ReceiptFormatter.Column.PRICE, ReceiptFormatter.Column.ITEM, ReceiptFormatter.Column.QUANTITY};

        sc.addItem("crisps", 4);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();
        assertEquals(String.format("€0.00 - crisps - 4%nTotal - €0.00%n"), myOut.toString());
    }

    @Test
    public void testMoreThan3ColumnsOnFormatter() {
        ShoppingCart sc = new ShoppingCart(new Pricer());
        sc.receiptFormatter.columns = new ReceiptFormatter.Column[]{ReceiptFormatter.Column.PRICE, ReceiptFormatter.Column.ITEM, ReceiptFormatter.Column.QUANTITY,  ReceiptFormatter.Column.QUANTITY,  ReceiptFormatter.Column.QUANTITY,  ReceiptFormatter.Column.QUANTITY};

        sc.addItem("crisps", 4);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();
        assertEquals(String.format("€0.00 - crisps - 4 - 4 - 4 - 4%nTotal - €0.00%n"), myOut.toString());
    }
}



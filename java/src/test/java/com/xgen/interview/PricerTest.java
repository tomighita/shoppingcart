package com.xgen.interview;

import com.xgen.interview.model.ShopProduct;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class PricerTest extends TestCase {
    Pricer pricer = new Pricer();

    @Before
    public void setPricer() {
        this.pricer.addProductsToDatabase(new ShopProduct("chicken breast"), new BigDecimal("5.2"));
        this.pricer.addProductsToDatabase(new ShopProduct("burger (frozen)"), new BigDecimal("7.4"));
        this.pricer.addProductsToDatabase(new ShopProduct("fries"), new BigDecimal("2.0"));
    }

    @Test
    public void testAddProductToDatabase() {
        Pricer pricer = this.pricer;
        assertEquals(2,pricer.pricingDatabase.keySet().size());
        pricer.addProductsToDatabase(new ShopProduct("orange"), new BigDecimal("2.55555"));
        assertEquals(3,pricer.pricingDatabase.keySet().size());
    }

    public void testGetPrice() {
        Pricer pricer = this.pricer;
        assertEquals(2,pricer.pricingDatabase.keySet().size());
        pricer.addProductsToDatabase(new ShopProduct("orange"), new BigDecimal("2.55555"));
        assertEquals(new BigDecimal("2.55555"),pricer.getPrice(new ShopProduct("orange")));
    }

    public void testGetPriceDeprecated() {
        Pricer pricer = this.pricer;
        assertEquals(2,pricer.pricingDatabase.keySet().size());
        pricer.addProductsToDatabase(new ShopProduct("orange"), new BigDecimal("2.55555"));
        assertEquals(255, pricer.getPrice("orange").intValue());
    }

    public void testGetPriceMultipleObjects() {
        Pricer pricer = this.pricer;
        assertEquals(2,pricer.pricingDatabase.keySet().size());
        ShopProduct orange = new ShopProduct("orange");
        pricer.addProductsToDatabase(orange, new BigDecimal("2.0"));
        assertEquals(BigDecimal.valueOf(4.0), pricer.getPrice(orange, 2));
    }

    public void testFindProduct() {
        Pricer pricer = this.pricer;
        assertEquals(2,pricer.pricingDatabase.keySet().size());
        ShopProduct orange = new ShopProduct("orange");
        pricer.addProductsToDatabase(orange, BigDecimal.valueOf(2.0));
        assertEquals(orange, pricer.findProduct("orange"));
    }

    public void testProductToString() {
        Pricer pricer = this.pricer;
        assertEquals(2,pricer.pricingDatabase.keySet().size());
        ShopProduct orange = new ShopProduct("orange");
        pricer.addProductsToDatabase(orange, BigDecimal.valueOf(2.0));
        ShopProduct chocolate= new ShopProduct("chocolate");
        assertEquals(chocolate, pricer.findProduct("chocolate"));
    }
}
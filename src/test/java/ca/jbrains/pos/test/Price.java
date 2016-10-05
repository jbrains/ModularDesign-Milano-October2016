package ca.jbrains.pos.test;

public class Price {
    public static Price cents(int priceInCents) {
        return new Price();
    }
    public String toString() {
        return "a Price";
    }
}

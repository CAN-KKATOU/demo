package org.example.enums;

import java.util.Random;

public class RoShamBo1 {
    static final int SIZE = 20;
    private static Random rand = new Random(47);

    public static Item newItem() {
        return switch (rand.nextInt(3)) {
            case 0 -> new Scissors();
            case 1 -> new Paper();
            case 2 -> new Rock();
            default -> throw new IllegalStateException("Unexpected value: " + rand.nextInt(3));
        };
    }

    public static void match(Item a, Item b) {
        System.out.println(
                a + " vs. " + b + ": " + a.compete(b)
        );
    }

    public static void main(String[] args) {
        for (int i = 0; i < SIZE; i++)
            match(newItem(), newItem());
    }
}

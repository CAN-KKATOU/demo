package org.example.generics.Coffee;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class CoffeeSupplier implements Supplier<Coffee>, Iterable<Coffee> {
    private final Class<?>[] types = {Latte.class, Mocha.class, Cappuccino.class, Americano.class, Breve.class};
    private static final Random rand = new Random(47);
    private int size = 0;

    public CoffeeSupplier() {
    }

    public CoffeeSupplier(int sz) {
        size = sz;
    }

    @Override
    public Coffee get() {
        try {
            return (Coffee) types[rand.nextInt(types.length)].getConstructor().newInstance();
        } catch (InstantiationException |
                 NoSuchMethodException |
                 InvocationTargetException |
                 IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Iterator<Coffee> iterator() {
        return new Iterator<Coffee>() {
            int count = size;

            @Override
            public boolean hasNext() {
                return count > 0;
            }

            @Override
            public Coffee next() {
                count--;
                return CoffeeSupplier.this.get();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public static void main(String[] args) {
        Stream.generate(new CoffeeSupplier())
                .limit(5)
                .forEach(System.out::println);
    }
}

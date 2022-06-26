package org.example.generics;

import java.util.Iterator;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Fibonacci implements Supplier<Integer>, Iterable<Integer> {
    private int count = 0;
    private int n;

    private int fib(int num) {
        if (num < 2)
            return 1;
        return fib(num - 2) + fib(num - 1);
    }

    public Fibonacci() {
    }

    public Fibonacci(int num) {
        n = num;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {

            @Override
            public boolean hasNext() {
                return n > 0;
            }

            @Override
            public Integer next() {
                n--;
                return Fibonacci.this.get();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public Integer get() {
        return fib(count++);
    }

    public static void main(String[] args) {
        // Stream.generate(new Fibonacci()).limit(18).map(n -> n + " ").forEach(System.out::print);
        for (int i : new Fibonacci(18))
            System.out.print(i + " ");
    }
}

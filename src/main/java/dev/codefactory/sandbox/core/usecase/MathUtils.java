package dev.codefactory.sandbox.core.usecase;

public class MathUtils {
    public static double average(int a, int b) {
        return a + b / 2;
    }

    public static void main(String[] args) {
        System.out.println(average(2,1));
    }
}


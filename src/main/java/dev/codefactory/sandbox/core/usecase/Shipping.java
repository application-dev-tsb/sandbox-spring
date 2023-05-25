package dev.codefactory.sandbox.core.usecase;

public class Shipping {
    public static int minimalNumberOfPackages(int items, int availableLargePackages, int availableSmallPackages) {
        int remainingItems = items;

        int remainingLargePackages = availableLargePackages;

        //exhaust all large
        while (remainingItems>=5 && remainingLargePackages>0) {
            remainingLargePackages--;
            remainingItems -= 5;
        }

        if (availableSmallPackages < remainingItems) return -1;

        return (availableLargePackages-remainingLargePackages) + remainingItems;
    }

    public static void main(String[] args) {
        System.out.println(minimalNumberOfPackages(16, 2, 10));
    }
}

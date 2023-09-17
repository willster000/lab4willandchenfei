import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class BinPacking {

    static class Bin {
        int capacity;
        int currentSize;
        List<Integer> items;  // List to store items in the bin

        public Bin(int capacity) {
            this.capacity = capacity;
            this.currentSize = 0;
            this.items = new LinkedList<>();  // Initialize the items list
        }

        public boolean canFit(int itemSize) {
            return currentSize + itemSize <= capacity;
        }

        public void addItem(int itemSize) {
            currentSize += itemSize;
            items.add(itemSize);  // Add the item to the bin's item list
        }

        public int getRemainingSpace() {
            return capacity - currentSize;
        }
    }

    public static void bestFitDecreasing(int binCapacity, int[] items) {
        Arrays.sort(items);
        List<Bin> bins = new LinkedList<>();

        for (int i = 0; i < 3; i++) {
            bins.add(new Bin(binCapacity));
        }

        List<Integer> availableItems = new LinkedList<>();
        for (int i = items.length - 1; i >= 0; i--) {
            availableItems.add(items[i]);
        }

        for (Bin bin : bins) {
            int i = 0;
            while (i < availableItems.size()) {
                int item = availableItems.get(i);
                if (bin.canFit(item)) {
                    bin.addItem(item);
                    availableItems.remove(i);
                } else if (item > bin.getRemainingSpace()) {
                    break; 
                } else {
                    i++; 
                }
            }
        }

        int totalUnusedSpace = 0;
        for (Bin bin : bins) {
            System.out.print(bin.items + ", ");  // Print items in the bin
            totalUnusedSpace += bin.getRemainingSpace();
        }
        System.out.print("   " + availableItems + "   " + totalUnusedSpace);  // Print unpacked items and total unused space
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
    
        System.out.println("Please enter the bin capacity, the number of items and the items themselves in turn:");
        String input = s.nextLine();
     
        String[] splitInput = input.split(" ");
        
        if (splitInput.length < 2) {
            System.out.println("Insufficient input provided.");
            return;
        }
    
        int binCapacity = Integer.parseInt(splitInput[0]);
        int numberOfItems = Integer.parseInt(splitInput[1]);
    
        if (splitInput.length != 2 + numberOfItems) {
            System.out.println("Incorrect number of items provided.");
            return;
        }
    
        int[] items = new int[numberOfItems];
    
        for (int i = 0; i < numberOfItems; i++) {
            items[i] = Integer.parseInt(splitInput[i + 2]);
        }
    
        bestFitDecreasing(binCapacity, items);
    }
    

}


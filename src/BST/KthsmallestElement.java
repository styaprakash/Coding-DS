package BST;


import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class KthsmallestElement {
    public static void main(String[] args) {
            TreeMap<Integer, Integer> treeMap = new TreeMap<>();

            // Adding elements to the TreeMap
            treeMap.put(5, 50);
            treeMap.put(3, 30);
            treeMap.put(8, 80);
            treeMap.put(2, 20);
            treeMap.put(4, 40);
            treeMap.put(7, 70);
            treeMap.put(10, 100);

            // Example usage of findKsmallest
            int k = 3;
            int result = findKsmallest(treeMap, k);

            if (result != -1) {
                System.out.println("The " + k + "-th smallest key is: " + result);
            } else {
                System.out.println("Invalid input or k is out of range.");
            }
        }

        static int findKsmallest(TreeMap<Integer, Integer> treeMap, int k) {
            if (treeMap.isEmpty() || k > treeMap.size() || k <= 0) {
                return -1;  // Invalid input or k is out of range
            }

            // Create an in-order iterator for the TreeMap's key set
            Iterator<Integer> iterator = treeMap.keySet().iterator();

            // Traverse the iterator until the k-th smallest element
            int count = 0;
            while (iterator.hasNext()) {
                int key = iterator.next();
                count++;
                if (count == k) {
                    return key;
                }
            }

            // If K is greater than the no. of elements in the TreeMap
            return -1;
    }
}


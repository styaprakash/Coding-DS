package BST;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class PairWithGivenSum {
    
    static Node insertion(Node root,int x){
        if (root==null) return new Node(x);
        if (x< root.key)
            root.left=insertion(root.left,x);
        else if (x> root.key) {
            root.right=insertion(root.right,x);
        }
        return root;
    }
    static void inOrderTraversal1(Node root) {
//        System.out.println("inOrderTraversal called");
        if (root != null) {
            inOrderTraversal1(root.left);
            System.out.print(root.key + " ");
            inOrderTraversal1(root.right);
        }
    }
    static boolean findPair(Node root, int sum) {
        HashSet<Integer> set = new HashSet<>();
        return findPairUtil(root, sum, set);
    }
    static boolean findPairUtil(Node root, int sum, HashSet<Integer> set) {
        if (root == null) return false;
        if (findPairUtil(root.left, sum, set))
            return true;
        if (set.contains(sum - root.key))
            return true;
        else
            set.add(root.key);
        return findPairUtil(root.right, sum, set);
    }


    //Using two pointer approach
    static void inOrderTraversal2(Node root, ArrayList<Integer> arr){
        if (root!=null) {
            inOrderTraversal2(root.left,arr);
            arr.add(root.key);
            inOrderTraversal2(root.right,arr);
        }
    }
    static boolean isPairSum(ArrayList<Integer> arr,int sum){
        int left=0;
        int right= arr.size()-1;
        while (left<right){
            int currSum= arr.get(left)+ arr.get(right);
            if (currSum==sum)
                return true;
            else if (currSum<sum) {
                left++;
            }
            else
                right--;
        }
        return false;
    }


    public static void main(String[] args) {
        Node root=null;
        root=insertion(root,4);
        root = insertion(root, 8);
        root = insertion(root, 9);
        root = insertion(root, 10);
        root = insertion(root, 11);
        root = insertion(root, 20);
        root = insertion(root, 25);
        root = insertion(root, 30);
//        System.out.println("Traversal of the tree in the InOrder fashion: ");
//        inOrderTraversal1(root);
//        System.out.println();
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the element sum you wanna find: ");
        int sumToFind = sc.nextInt();
        //Using Method 1: By hashing approach
//        boolean hasPair = findPair(root, sumToFind);
//        System.out.println("\nDoes the BST have a pair with sum " + sumToFind + "? " + hasPair);

        //Using Method 2: By Two pointer approach
        ArrayList<Integer> list=new ArrayList<>();
        inOrderTraversal2(root,list);
        boolean hasPair2=isPairSum(list,sumToFind);

        System.out.println("InOrder Traversal:");
        for (int value : list) {
            System.out.print(value + " ");
        }
        System.out.println("\nDoes the BST have a pair with sum " + sumToFind + "? " + hasPair2);
    }
}

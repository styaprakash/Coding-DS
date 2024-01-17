package BST;

import java.util.ArrayList;
import java.util.Collections;

public class FixBST {
    static void storeinArr(Node root, ArrayList<Integer> arr){
        if (root==null) return;
        storeinArr(root.left,arr);
        arr.add(root.key);
        storeinArr(root.right,arr);
    }
    static void correctBSTUtil(Node root, ArrayList<Integer> arr, int[] index){
        if (root==null) return;
        correctBSTUtil(root.left,arr,index);
        root.key=arr.get(index[0]);
        index[0]++;
        correctBSTUtil(root.right,arr,index);
    }
    public static void correctBST(Node root)
    {
        // ArrayList to store the inorder traversal of
        // given tree
        ArrayList<Integer> array = new ArrayList<Integer>();
        storeinArr(root, array);
        Collections.sort(array);
        int[] index = { 0 };
        correctBSTUtil(root, array, index);
    }
    public static void printInorder(Node root)
    {
        if (root == null)
            return;
        printInorder(root.left);
        System.out.print(root.key + " ");
        printInorder(root.right);
    }
    public static void main(String[] args)
    {
        /*   6
            / \
           10  2
          / \ / \
         1  3 7 12

        10 and 2 are swapped */
        Node root = new Node(6);
        root.left = new Node(10);
        root.right = new Node(2);
        root.left.left = new Node(1);
        root.left.right = new Node(3);
        root.right.right = new Node(12);
        root.right.left = new Node(7);

        // Inorder traversal of the Original Tree
        System.out.println(
                "Inorder Traversal of the original tree");
        printInorder(root);

        correctBST(root);

        // inorder traversal of the fixed tree
        System.out.println(
                "\nInorder Traversal of the fixed tree");
        printInorder(root);
    }
}


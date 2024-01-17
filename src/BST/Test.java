package BST;

import java.util.*;

public class Test {
    static Node insert(Node root, int x) {
        if (root == null)
            return new Node(x);
        if (root.key > x) {
            root.left = insert(root.left, x);
            root.lcount++;
        } else if (root.key < x)
            root.right = insert(root.right, x);
        return root;
    }

    //Traversal
    static void inOrderTraversal(Node root) {
        if (root != null) {
            inOrderTraversal(root.left);
            System.out.print(root.key + " ");
            inOrderTraversal(root.right);
        }
    }

    //searching in BST
    static boolean search(Node root, int x) {
        if (root == null)
            return false;
        else if (root.key == x) {
            return true;
        } else if (root.key < x)
            return search(root.right, x);
        else
            return search(root.left, x);
    }

    //deletion in BST
    static Node delNode(Node root, int x) {
        if (root == null) return null;
        if (root.key > x)
            root.left = delNode(root.left, x);
        else if (root.key < x)
            root.right = delNode(root.right, x);
        else {
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;
            else {
                Node succ = getSucc(root);
                root.key = succ.key;
                root.right = delNode(root.right, succ.key);
            }
//            return root;
        }
        return root;
    }

    static Node getSucc(Node root) {
        Node curr = root.right;
        while (curr != null && curr.left != null) {
            curr = curr.left;
        }
        return curr;
    }

    //Floor in BSt
    static Node floor(Node root, int x) {
        Node res = null;
        while (root != null) {
            if (root.key == x)
                return root;
            else if (x < root.key)
                return root = root.left;
            else {
                res = root;
                root = root.right;
            }
        }
        return res;
    }

    //Ceil in BST
    static Node ceil(Node root, int x) {
        Node res = null;
        while (root != null) {
            if (root.key == x) return root;
            else if (root.key < x)
                root = root.right;
            else {
                res = root;
                root = root.left;
            }
        }
        return res;
    }

    //Elements between K1 and K2 in a BST
    static void printBetween(Node root, int k1, int k2) {
        if (root == null) return;
        if (root.key >= k1 && root.key <= k2)
            System.out.print(root.key + " ");
        if (root.key > k1)
            printBetween(root.left, k1, k2);
        if (root.key <= k2)
            printBetween(root.right, k1, k2);
    }

    //Check BST
    static boolean isBST(Node root) {
        if (root == null) return true;
        int leftMax = max(root.left);
        if (leftMax >= root.key)
            return false;
        int rightMin = min(root.right);
        if (rightMin < root.key)
            return false;
        boolean isLeftBST = isBST(root.left);
        boolean isRightBST = isBST(root.right);
        return isLeftBST && isRightBST;
    }

    static int min(Node root) {
        if (root == null)
            return Integer.MAX_VALUE;
        int leftMin = min(root.left);
        int rightMin = min(root.right);
        return Math.min(root.key, Math.min(leftMin, rightMin));
    }

    static int max(Node root) {
        if (root == null)
            return Integer.MIN_VALUE;
        int largestLeft = max(root.left);
        int largestRight = max(root.right);
        return Math.max(root.key, Math.max(largestRight, largestLeft));
    }

    //Effective checkBST
    public static class IsBSTRETURN {
        int min, max;
        boolean isBST;

        public IsBSTRETURN(int min, int max, boolean isBST) {
            this.min = min;
            this.max = max;
            this.isBST = isBST;
        }
    }

    static IsBSTRETURN isBST2(Node root) {
        if (root == null) {
            IsBSTRETURN ans = new IsBSTRETURN(Integer.MAX_VALUE, Integer.MIN_VALUE, true);
            return ans;
        }
        IsBSTRETURN leftAns = isBST2(root.left);
        IsBSTRETURN rightAns = isBST2(root.right);
        int min = Math.min(root.key, Math.min(leftAns.min, rightAns.min));
        int max = Math.max(root.key, Math.max(leftAns.max, rightAns.max));
        boolean isBst = true;
        if (leftAns.max >= root.key) isBst = false;
        if (rightAns.min < root.key) isBst = false;
        if (!leftAns.isBST) isBst = false;
        if (!rightAns.isBST) isBst = false;
        IsBSTRETURN ans = new IsBSTRETURN(min, max, isBst);
        return ans;
    }

    //    Effective BST check using Inorder traversal
    static int prev = Integer.MIN_VALUE;

    static boolean checkBST(Node root) {
        if (root == null) return true;
        if (checkBST(root.left) == false) return false;
        if (root.key <= prev)
            return false;
        prev = root.key;
        return checkBST(root.right);
    }

    //Ceiling on the life side of an array
    static void leftCeiling(int arr[]) {
        System.out.println(-1);
        for (int i = 1; i < arr.length; i++) {
            int diff = Integer.MAX_VALUE;
            for (int j = 0; j < i; j++) {
                if (arr[j] >= arr[i])
                    diff = Math.min(diff, arr[j] - arr[i]);
            }
            if (diff == Integer.MAX_VALUE)
                System.out.println(-1);
            else
                System.out.println(arr[i] + diff);
        }
    }

    //Ceiling on left side in very efficient manner
    static void findCeiling(int[] arr) {
        TreeSet<Integer> s = new TreeSet<>();
        System.out.println("Ceiling on left side: ");
        for (int i = 0; i < arr.length; i++) {
            Integer ceiling = s.ceiling(arr[i]);
            //If no ceiling found, print -1
            if (ceiling == null)
                System.out.print("-1 ");
            else
                System.out.print(ceiling + " ");
            //Add the current element to the TreeSet
            s.add(arr[i]);
        }
    }

    //Kth smallest element naive way
    static int findKsmallest(TreeMap<Integer, Integer> treeMap, int k) {
        Iterator<Map.Entry<Integer, Integer>> iterator = treeMap.entrySet().iterator();
        int count = 0;
        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> entry = iterator.next();
            count++;
            if (count == k)
                return entry.getKey();
        }
        //If K is greater than the no. of elements in the TreeMap
        return -1;
    }

    //kth smallest Naive approach 2
    private static int count;

    static int findKthSmallest(Node root, int k) {
        count = 0;
        return inOrderTraversalForKSmallest(root, k);
    }

    private static int inOrderTraversalForKSmallest(Node root, int k) {
        if (root == null) return -1;
        int leftRes = inOrderTraversalForKSmallest(root.left, k);
        if (leftRes != -1)
            return leftRes;
        count++;
        if (count == k) {
            System.out.println("Kth Largest Element: " + root.key); // Print the k-th largest element
            return root.key;
        }
        return inOrderTraversalForKSmallest(root.right, k);
    }

    static int KthSmallestEfficient(Node root, int k) {
        if (root == null)
            return -1;
        int count = root.lcount + 1;
        if (k == count)
            return root.key;//current node is k smallest
        else if (k < count) {
            return KthSmallestEfficient(root.left, k);
        } else
            return KthSmallestEfficient(root.right, k - count);
    }

    //Vertical sum in a Binary tree
    static void vSumR(Node root, int hd, TreeMap<Integer, Integer> mp) {
        if (root == null) return;
        vSumR(root.left, hd - 1, mp);
        int pSum = (mp.get(hd) == null ? 0 : mp.get(hd));
        mp.put(hd, pSum + root.key);
        vSumR(root.right, hd + 1, mp);
    }

    static void vSum(Node root) {
        TreeMap<Integer, Integer> mp = new TreeMap<Integer, Integer>();
        vSumR(root, 0, mp);
        for (Map.Entry<Integer, Integer> entry : mp.entrySet()) {
            System.out.println("Vertical Sum at horizontal distance " + entry.getKey() + ": " + entry.getValue());
        }
    }

    //Vertical Traversal of Binary Tree
    static class Pair {
        Node node;
        int hd;

        Pair(Node n, int h) {
            node = n;
            hd = h;
        }
    }

    static void verticalOrder(Node root) {
        Queue<Pair> q = new LinkedList<Pair>();
        Map<Integer, ArrayList<Integer>> mp = new TreeMap<>();
        q.add(new Pair(root, 0));
        while (!q.isEmpty()) {
            Pair p = q.poll();
            Node curr = p.node;
            int hd = p.hd;
            if (mp.containsKey(hd))
                mp.get(hd).add(curr.key);
            else {
                ArrayList<Integer> al = new ArrayList<Integer>();
                al.add(curr.key);
                mp.put(hd, al);
            }
            if (curr.left != null)
                q.add(new Pair(curr.left, hd - 1));
            if (curr.right != null)
                q.add(new Pair(curr.right, hd + 1));
        }
        //print mp contains line by line
        for (Map.Entry<Integer, ArrayList<Integer>> p : mp.entrySet()) {
            ArrayList<Integer> al = p.getValue();
            for (int x : al)
                System.out.print(x + " ");
            System.out.println();
        }
    }

    //Top view of binary tree
    static void printTopView(Node root) {
        if (root == null)
            return;
        System.out.println("The top view of this BST is: ");
        class QueueNode{
            Node node; int hd;
            public QueueNode(Node node,int hd){
                this.node=node;
                this.hd=hd;
            }
        }
        Queue<QueueNode> q = new LinkedList<>();
        TreeMap<Integer, Integer> map = new TreeMap<>();
        q.add(new QueueNode(root,0));
        while (!q.isEmpty()) {
            QueueNode qNode = q.poll();
            Node curr=qNode.node;
            int hd = qNode.hd;
            if (!map.containsKey(hd))
                map.put(hd, curr.key);
            if (curr.left != null)
                q.add(new QueueNode(curr.left,hd-1));
            if (curr.right != null)
                q.add(new QueueNode(curr.right,hd+1));
        }

        for (int value : map.values())
            System.out.print(value + " ");
    }

    //Bottom view of binary tree
    static void printBottomView(Node root){
        if (root==null) return;
        System.out.println("Bottom view of binary tree: ");
        class QueueNode1 {
            Node node;
            int hd; // horizontal distance

            public QueueNode1(Node node, int hd) {
                this.node = node;
                this.hd = hd;
            }
        }
        Queue<QueueNode1> q=new LinkedList<>();
        TreeMap<Integer,Integer> mp=new TreeMap<>();
        q.add(new QueueNode1(root,0));
        while (!q.isEmpty()){
            QueueNode1 qnode=q.poll();
            Node curr=qnode.node;
            int hd=qnode.hd;
            mp.put(hd,curr.key);
            if (curr.left!=null)
                q.add(new QueueNode1(curr.left,hd-1));
            if (curr.right!=null)
                q.add(new QueueNode1(curr.right,hd-1));
        }
        for (int value1:mp.values()){
            System.out.print(value1+" ");
        }
    }
    public static void main(String[] args) {
        Node root = null;
        root = insert(root, 8);
        root = insert(root, 3);
        root = insert(root, 10);
        root = insert(root, 1);
        root = insert(root, 6);
        root = insert(root, 9);
        root = insert(root, 12);
        root = insert(root, 69);
        System.out.println();
        inOrderTraversal(root);
        System.out.println();
        System.out.println("the element we are looking for is:"+ search(root,10));
        root=delNode(root,10);
        System.out.println("The elements after deletion: ");
        inOrderTraversal(root);
        System.out.println();
        System.out.println("the element we are looking for is: "+ search(root,10));
        System.out.println();
        int floorValue = 2;
        Node floorNode = floor(root, floorValue);
        System.out.println("Floor value of " + floorValue + " is: " + floorNode.key);
        int ceilvalue=7;
        Node ceilnode=ceil(root,ceilvalue);
        System.out.println("Ceil value of "+ceilvalue+" is: "+ ceilnode.key);
        System.out.println("The nodes in between which are present in the tree are");
        printBetween(root,1,10);
        System.out.println();
        System.out.println("Is the tree is actually a BST: "+ isBST(root));
        IsBSTRETURN result = isBST2(root);
        if (result.isBST) {
            System.out.println("The given tree is a Binary Search Tree.");
        } else {
            System.out.println("The given tree is not a Binary Search Tree.");
        }
        //checking BST by inorder traversal(as this the best way)
        System.out.println("Is the Tree is BST: "+checkBST(root));
        int[] arr={30,20,10};
        System.out.println("Ceiling on the left side in an array: ");
        leftCeiling(arr);
        findCeiling(arr);
        System.out.println();
        inOrderTraversal(root);
        System.out.println();
        Scanner s=new Scanner(System.in);
        System.out.println("Enter the Kth smallest value to wanna find: ");
        int l=s.nextInt();
//        findKthSmallest(root,k);
        int smallest = KthSmallestEfficient(root, l);
        if (smallest != -1) {
            System.out.println(l + "nd/st/th smallest element is " + smallest);
        } else {
            System.out.println("Invalid value of k");
        }
        //Vertical sum
        vSum(root);
        //Vertical Order Traversal
        Test t = new Test();
        System.out.println("The vertical traversal of the BST is: ");
        Test.verticalOrder(root);
        printTopView(root);
        System.out.println();
        printBottomView(root);
    }
}
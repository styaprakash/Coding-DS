package AVLTree;

public class avltree {
    Node root;
    int height(Node root){
        if (root==null)
            return 0;
        return root.height;
    }
    int max(int a,int b) {
        return (a > b) ? a : b;
    }
    int getBalance(Node root){
        if (root==null)
            return 0;
        return  height(root.left)-height(root.right);
    }
    Node rightRotate(Node y){
        Node x=y.left;
        Node T2=x.right;
        x.right=y;
        y.left=T2;
        y.height=max(height(y.right),height(y.left))+1;
        x.height=max(height(x.left),height(x.right))+1;
        return x;
    }
    Node leftRotate(Node x){
        Node y=x.right;
        Node T2=y.left;
        y.left=x;
        x.right=T2;
        x.height=max(height(x.left),height(x.right))+1;
        y.height=max(height(y.left),height(y.right))+1;
        return y;
    }
    //Insertion
    Node insert(Node root,int key){
        if (root==null)
            return new Node(key);
        if(key<root.key)
            root.left=insert(root.right,key);
        else if (key> root.key)
            root.right=insert(root.right,key);
        else
            return root; //Duplicate keys are not allowed!
        root.height=1+max(height(root.left),height(root.right));
        int balance=getBalance(root);
        //LL case
        if (balance>1 && key<root.left.key)
            return rightRotate(root);
        //RR case
        if (balance<-1 && key<root.right.key){
            return leftRotate(root);
        }
        //LR case
        if (balance>1 && key>root.left.key){
            root.left=leftRotate(root.left);
            return rightRotate(root);
        }
        //RL case
        if (balance<-1 && key<root.right.key){
            root.right=rightRotate(root.right);
            return leftRotate(root);
        }
        return root;
    }
    //Traversal
    static void preOrder(Node root){
        if (root!=null){
            System.out.println(root.key+" ");
            preOrder(root.left);
            preOrder(root.right);
        }
    }
    //Deletion
    Node delete(Node root, int key){
        //perform standard BST delete
        if (root==null)
            return root;
        if (key< root.key)
            root.left=delete(root.left,key);
        else if (key> root.key)
            root.right=delete(root.right,key);
        else {
            if ((root.left==null)||(root.right==null)){
                Node temp=null;
                if (temp==root.left)
                    temp=root.right;
                else
                    temp=root.left;
                //No child case
                if (temp==null){
                    temp=root;
                    root=null;
                }
                else {
                    root=temp; //copy the contents of non-empty child
                }
            }
            else {
                Node temp=minValueNode(root.right);
                root.key= temp.key;
                root.right=delete(root.right, temp.key);
            }
        }
        if (root==null)
            return root;
        root.height=1+max(height(root.left),height(root.right));
        int balance=getBalance(root);
        //LL case
        if (balance>1 && getBalance(root.left)>=0)
            return rightRotate(root);
        //LR case
        if (balance>1 && getBalance(root.right)<0) {
            root.left=leftRotate(root.left);
            return rightRotate(root);
        }
        //RR case
        if (balance<-1 && getBalance(root.right)<=0)
            return leftRotate(root);
        //RL case
        if (balance<-1 && getBalance(root.right)>0){
            root.right=rightRotate(root.right);
            return leftRotate(root);
        }
        return root;
    }
    Node minValueNode(Node root){
        Node curr=root;
        while (curr.left!=null)
            curr=curr.left;
        return curr;
    }

    public static void main(String[] args) {
        avltree tree=new avltree();
        tree.root=tree.insert(tree.root,10);
        tree.root=tree.insert(tree.root,20);
        tree.root=tree.insert(tree.root,30);
        tree.root=tree.insert(tree.root,40);
        tree.root=tree.insert(tree.root,50);
        tree.root=tree.insert(tree.root,69);
        System.out.println("PreOrder traversal of the tree: ");
        tree.preOrder(tree.root);
        int keyDel=30;
        tree.root=tree.delete(tree.root,keyDel);
        System.out.println("\n\nAVL Tree after deletion "+keyDel+": ");
        tree.preOrder(tree.root);
    }
}

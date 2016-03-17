/**
 *
 * @author Mengchuan Lin
 */
public class BinarySearchTree {
    private Node root;

    @SuppressWarnings("LeakingThisInConstructor")
    public BinarySearchTree(int ary[]) {
        for (int i = 0; i < ary.length; i++) {
            Node newNode = new Node(ary[i]);
            treeInsert(this, newNode);
            if (i == 0)
                this.root = newNode;
        }
    }

    //inner class Node for BinarySearchTree
    private class Node {
        private final int key;
        private Node parent, left, right;

        public Node(int key) { this.key = key; }
    }

    private void treeInsert(BinarySearchTree tree, Node z) {
        Node y = null;
        Node x = tree.root;
        if (z == null)
            return;
        while (x != null) {
            y = x; //maintain previous location of x in y
            //find empty location to insert node z based on key value
            if (z.key < x.key)
                x = x.left;
            else
                x = x.right;
        }
        z.parent = y; //since y is x.parent, set it as z.parent
        //if y is null, tree is empty, set z as the new root
        if (y == null)
            tree.root = z;
        else if (z.key < y.key)
            y.left = z;
        else
            y.right = z;
    }

    private void inorderTreeWalk(Node x) {
        if (x != null) {
            inorderTreeWalk(x.left);
            System.out.print(x.key + " ");
            inorderTreeWalk(x.right);
        }
    }

    private Node treeMinimum(Node x) {
        //keep walking down the left branch until minimum is found
        while (x.left != null) {
            x = x.left;
        }
        return x;
    }

    private Node treeSuccessor(Node x) {
        /* if x has a right child, its successor is at the leftmost node of its
        right child */
        if (x.right != null)
            return treeMinimum(x.right);
        /* if not, travel up the tree until x ends up as the left child of any
        node, i.e., x < x.parent */
        Node y = x.parent;
        while (y != null && x == y.right) {
            x = y;
            y = y.parent;
        }
        return y;
    }

    private Node iterativeSearch(Node x, int k) {
        while (x != null && k != x.key)
            x = k < x.key ? x.left : x.right;
        return x;
    }

    public static void main(String[] args) {
        //initialize array and build tree
        int ary[] = { 15, 6, 18, 3, 7, 17, 20, 2, 4, 12, 8 };
        BinarySearchTree bst = new BinarySearchTree(ary);

        //test tree operations and print output
        System.out.print("Inorder tree walk: ");
        bst.inorderTreeWalk(bst.root);

        Node newRoot = bst.iterativeSearch(bst.root, 18);
        Node min = bst.treeMinimum(newRoot);
        System.out.println("\nMinimum from node.key = " + newRoot.key +
                           " is " + min.key);

        System.out.println("Successor to the root node is "
                           + bst.treeSuccessor(bst.root).key);

        Node target = bst.iterativeSearch(bst.root, 12);
        System.out.println("Successor to node with key = " + target.key
                           + " is " + bst.treeSuccessor(target).key);
    }
}

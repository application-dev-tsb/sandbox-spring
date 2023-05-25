package dev.codefactory.sandbox.core.usecase;

class Node {
    private Node leftChild, rightChild;

    public Node(Node leftChild, Node rightChild) {
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public Node getLeftChild() {
        return this.leftChild;
    }

    public Node getRightChild() {
        return this.rightChild;
    }

    public int height() {
        if (rightChild==null && leftChild==null) return 0;

        if (leftChild!=null && rightChild!=null) {
            return 1 + Math.max(rightChild.height(), leftChild.height());
        }

        if (leftChild!=null) return 1 + leftChild.height();

        return 1 + rightChild.height();
    }

    public static void main(String[] args) {
        Node leaf1 = new Node(null, null);
        Node leaf2 = new Node(null, null);
        Node node = new Node(leaf1, null);
        Node root = new Node(node, leaf2);

        System.out.println(root.height());
    }
}

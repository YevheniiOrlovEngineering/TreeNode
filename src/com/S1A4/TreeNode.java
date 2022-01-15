package com.S1A4;

import java.util.*;

public class TreeNode<T> {

    T data;
    TreeNode<T> parent;

    TreeNode<T> left;
    TreeNode<T> right;

    List<TreeNode<T>> children;


    public TreeNode(T data) {
        this.data = data;
        this.left = null;
        this.right = null;
        this.children = new ArrayList<>();
        this.parent = null;
    }

    public TreeNode<T> getParent() {
        return this.parent;
    }

    public TreeNode<T> getChild(int index) {
        return this.children.get(index);
    }

    public T getData() {
        return this.data;
    }

    public List<TreeNode<T>> getChildren() {
        return this.children;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setChildren(List<TreeNode<T>> children) {
        this.children = children;
    }

    public void setChild(int index, TreeNode<T> child) {
        this.children.remove(index);
        this.children.add(index, child);
    }

    public void setParent(TreeNode<T> parent) {
        this.parent = parent;
    }

    public void setLeft(TreeNode<T> leftChild) {
        this.left = leftChild;
    }

    public void setRight(TreeNode<T> rightChild) {
        this.right = rightChild;
    }

    public void addChild(T child, boolean isBinary, boolean isLeft) {

        TreeNode<T> childNode = new TreeNode<T>(child);
        childNode.parent = this;
        this.children.add(childNode);

        if (isBinary) {
            if (isLeft) {
                this.left = childNode;
            }
            else {
                this.right = childNode;
            }
        }
    }

    public void addChildren(List<T> childrenList, boolean isBinary) {
        if (isBinary) {
            this.addChild(childrenList.get(0), true, true);
            this.addChild(childrenList.get(1), true, false);
        }
        else {
            for (T value: childrenList) {
                this.addChild(value, false, false);
            }
        }
    }

    public void printData() {
        System.out.println(this.data);
    }

    public void printChildData(int index) {
        System.out.println(this.children.get(index).data);
    }

    public void printChildrenData() {
        for (TreeNode<T> child: this.children) {
            System.out.println(child.data);
        }
    }


    public static <T> void depthFirstTraversal_Preorder(TreeNode<T> root) {
        if (root != null) {
            System.out.println(root.data);
            depthFirstTraversal_Preorder(root.left);
            depthFirstTraversal_Preorder(root.right);
        }
    }

    public static <T> void depthFirstTraversal_Inorder(TreeNode<T> root) {
        if (root != null) {
            depthFirstTraversal_Inorder(root.left);
            System.out.println(root.data);
            depthFirstTraversal_Inorder(root.right);
        }
    }

    public static <T> void depthFirstTraversal_Postorder(TreeNode<T> root) {
        if (root != null) {
            depthFirstTraversal_Postorder(root.left);
            depthFirstTraversal_Postorder(root.right);
            System.out.println(root.data);
        }
    }

    public static <T>List<T> breadthFirstTraversal(TreeNode<T> root) {
        if (root == null) { return List.of(); }

        Queue<TreeNode<T>> queue = new ArrayDeque<>();
        List<T> traversedNodes = new ArrayList<>();

        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode<T> curNode = queue.remove();
            traversedNodes.add(curNode.data);

            if (curNode.left != null) { queue.add(curNode.left); }
            if (curNode.right != null) { queue.add(curNode.right); }
        }

        return traversedNodes;
    }



    public static void main(String[] args) {
        TreeNode<String> root = new TreeNode<String>("F");

        root.addChildren(Arrays.asList("B", "G"), true);
        root.left.addChildren(Arrays.asList("A", "D"), true);
        root.left.right.addChildren(Arrays.asList("C", "E"), true);
        root.right.addChild("I", true, true);
        root.right.left.addChild("H", true, true);

        System.out.println("PreOrder");
        TreeNode.depthFirstTraversal_Preorder(root);
        System.out.println();

        System.out.println("InOrder");
        TreeNode.depthFirstTraversal_Inorder(root);
        System.out.println();

        System.out.println("PostOrder");
        TreeNode.depthFirstTraversal_Postorder(root);
        System.out.println();

        System.out.println("LevelOrder");
        TreeNode.breadthFirstTraversal(root).forEach(System.out::println);

    }
}

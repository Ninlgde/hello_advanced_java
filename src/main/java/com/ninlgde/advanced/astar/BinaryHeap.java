package com.ninlgde.advanced.astar;

/**
 * @author ninlgde
 * @date 2022/9/23 12:07
 */
public class BinaryHeap<T extends BinaryHeap.Node> {
    public int size;
    private Node[] nodes;
    private final boolean isMaxHeap;

    public BinaryHeap() {
        this(16, false);
    }

    public BinaryHeap(int capacity, boolean isMaxHeap) {
        this.size = 0;
        this.isMaxHeap = isMaxHeap;
        this.nodes = new Node[capacity];
    }

    public T add(T node) {
        if (this.size == this.nodes.length) {
            Node[] newNodes = new Node[this.size << 1];
            System.arraycopy(this.nodes, 0, newNodes, 0, this.size);
            this.nodes = newNodes;
        }

        node.index = this.size;
        this.nodes[this.size] = node;
        this.up(this.size++);
        return node;
    }

    @SuppressWarnings("unchecked")
    public T pop() {
        Node[] nodes = this.nodes;
        Node popped = nodes[0];
        nodes[0] = nodes[--this.size];
        nodes[this.size] = null;
        if (this.size > 0) {
            this.down(0);
        }

        return (T) popped;
    }

    public void clear() {
        this.size = 0;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public void setValue(T node, int value) {
        float oldValue = node.value;
        node.value = (float) value;
        if ((float) value < oldValue ^ this.isMaxHeap) {
            this.up(node.index);
        } else {
            this.down(node.index);
        }

    }

    private void up(int index) {
        Node[] nodes = this.nodes;
        Node node = nodes[index];

        int parentIndex;
        for (float value = node.value; index > 0; index = parentIndex) {
            parentIndex = index - 1 >> 1;
            Node parent = nodes[parentIndex];
            if (value < parent.value == this.isMaxHeap) {
                break;
            }

            nodes[index] = parent;
            parent.index = index;
        }

        nodes[index] = node;
        node.index = index;
    }

    private void down(int index) {
        Node[] nodes = this.nodes;
        int size = this.size;
        Node node = nodes[index];
        float value = node.value;

        while (true) {
            int leftIndex = 1 + (index << 1);
            if (leftIndex >= size) {
                break;
            }

            int rightIndex = leftIndex + 1;
            Node leftNode = nodes[leftIndex];
            float leftValue = leftNode.value;
            Node rightNode;
            float rightValue;
            if (rightIndex >= size) {
                rightNode = null;
                rightValue = (float) (this.isMaxHeap ? Integer.MIN_VALUE : Integer.MAX_VALUE);
            } else {
                rightNode = nodes[rightIndex];
                rightValue = rightNode.value;
            }

            if (leftValue < rightValue ^ this.isMaxHeap) {
                if (leftValue == value || leftValue > value ^ this.isMaxHeap) {
                    break;
                }

                nodes[index] = leftNode;
                leftNode.index = index;
                index = leftIndex;
            } else {
                if (rightValue == value || rightValue > value ^ this.isMaxHeap) {
                    break;
                }

                nodes[index] = rightNode;
                rightNode.index = index;
                index = rightIndex;
            }
        }

        nodes[index] = node;
        node.index = index;
    }

    public String toString() {
        if (this.size == 0) {
            return "[]";
        } else {
            Object[] nodes = this.nodes;
            StringBuilder buffer = new StringBuilder(32);
            buffer.append('[');
            buffer.append(nodes[0]);

            for (int i = 1; i < this.size; ++i) {
                buffer.append(", ");
                buffer.append(nodes[i]);
            }

            buffer.append(']');
            return buffer.toString();
        }
    }

    public static class Node {
        float value;
        int index;

        public Node(float value) {
            this.value = value;
        }

        public float getValue() {
            return this.value;
        }
    }
}

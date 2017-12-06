package cn.longzzai.interview.linkedlist;

/**
 * @author longcho
 * 2017-11-12-10:30
 */
public class Node {
    private final int value;
    private Node next;

    public Node(int value) {
        this.value = value;
        this.next = null;
    }

    public int getValue() {
        return value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
    public static void printLinkedList(Node node){
        while (node != null){
        System.out.print(node.value);
        System.out.print(" ");
        node = node.next;
        }
        System.out.println();

    }
}

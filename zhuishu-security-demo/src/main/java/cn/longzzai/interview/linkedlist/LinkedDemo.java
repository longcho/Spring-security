package cn.longzzai.interview.linkedlist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author longcho
 * 2017-11-12-10:33
 */
public class LinkedDemo {
    public Node createLinkedList(List<Integer> data) {
        if (data.isEmpty()) {
            return null;
        }
        Node node = new Node(data.get(0));
        node.setNext(createLinkedList(data.subList(1, data.size())));
        return node;
    }

    public Node reverceLinkedList(Node head) {
        if (head == null){
            return null;
        }
        if (head.getNext() == null){
            return head;
        }
        Node reverceNode = reverceLinkedList(head.getNext());
        head.getNext().setNext(head);
        head.setNext(null);
        return reverceNode;
    }

    public Node reverceLinkedListByLoop(Node head){
        Node newHead = null;
        Node currentHead = head;
        while (currentHead != null){
            //
            Node next = currentHead.getNext();
            currentHead.setNext(newHead);
            newHead = currentHead;
            currentHead = next;
        }
        return newHead;
    }

    public void printCombinations(ArrayList<Integer> prefix , List<Integer> data , int n){
        //n=0 output
        if (n == 0){
                System.out.println(prefix.toString());
                return;
        }
        if (data.isEmpty() || n < 0){
            return;
        }
        prefix.add(data.get(0));
        printCombinations(prefix , data.subList(1 , data.size()) , n -1);
        prefix.remove(prefix.size() -1);
        printCombinations(prefix , data.subList(1 , data.size()) , n );
    }

    public int BinarySearchOnArrs(int[] data , int key){
        if (data.length ==0 || data == null || data[0] > key || data[data.length - 1] < key){
            return -1;
        }
        int a = 0;
        int b = data.length - 1;
        while ((b - a) > 1){
            int midel =  a + (b - a) / 2;
            if (key > data[midel]) {
                a = midel;
            }else{
                b = midel;
            }
        }
        if (data[a] != key){
            a = b;
        }
        int index = data[a] == key ? a : -1 ;
        return index;
    }

    public static void main(String[] args) {
        LinkedDemo demo = new LinkedDemo();
        //Node.printLinkedList(demo.reverceLinkedListByLoop(demo.createLinkedList(new ArrayList<Integer>())));
        //Node.printLinkedList(demo.reverceLinkedListByLoop(demo.createLinkedList(Arrays.asList(1))));
        //Node.printLinkedList(demo.reverceLinkedListByLoop(demo.createLinkedList(Arrays.asList(1, 2 , 3, 4, 5))));
        ////demo.printCombinations(new ArrayList<>() , Arrays.asList(1 ,2 , 3 , 4) , 2);
        //System.out.println("=================");
        //demo.printCombinations(new ArrayList<>() , Arrays.asList(1 ,2 , 3 , 4) , 1);
        //System.out.println("=================");
        //demo.printCombinations(new ArrayList<>() , Arrays.asList(1 ,2 , 3 , 4) , 0);
        //System.out.println("=================");
        //demo.printCombinations(new ArrayList<>() , Arrays.asList(1 ,2 , 3 , 4 ,5 ,6 ,7) , 4);
        //System.out.println("=================");
        //demo.printCombinations(new ArrayList<>() , Arrays.asList(1) , 2);
        //System.out.println("=================");
        //demo.printCombinations(new ArrayList<>() , Arrays.asList(1) , 1);
        //System.out.println("=================");
        //demo.printCombinations(new ArrayList<>() , Arrays.asList() , 0);
        //System.out.println("=================");
        System.out.println(demo.BinarySearchOnArrs(new int[]{1, 3, 4, 5,5, 6, 7, 8, 9, 10, 19}, 5));
        System.out.println(demo.BinarySearchOnArrs(new int[]{1, 3, 4, 5,5,5, 6, 7, 8, 9, 10, 19}, 5));
        System.out.println(demo.BinarySearchOnArrs(new int[]{1, 3, 4, 5, 6, 7, 8, 9, 10, 19}, 20));
        System.out.println(demo.BinarySearchOnArrs(new int[]{1, 3, 4, 5, 6, 7, 8, 9, 10, 19}, -20));
        System.out.println(demo.BinarySearchOnArrs(new int[]{1, 3, 4, 5, 6, 7, 8, 9, 10, 19}, 1));
        System.out.println(demo.BinarySearchOnArrs(new int[]{1, 3, 4, 5, 6, 7, 8, 9, 10, 19}, 12));
        System.out.println(demo.BinarySearchOnArrs(new int[]{1, 3, 4, 5, 6, 7, 8, 9, 10, 19}, 19));
        System.out.println(demo.BinarySearchOnArrs(new int[]{19}, 19));
        System.out.println(demo.BinarySearchOnArrs(new int[]{1}, 19));
        System.out.println("=================");
    }
}

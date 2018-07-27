package com.wangzai.Node;


/**
 * 给定一个pivot,将单向列表以pivot分割为左边小,中间相等,右边大的形式
 *
 * @author wangzai
 * @date 2018/5/15 下午9:20
 */
public class NodePartition {

    public static Node partition(Node head, int pivot) {


        //记录三块区域的节点
        Node lessHead = null;
        Node lessTail = null;
        Node equalHead = null;
        Node equalTail = null;
        Node moreHead = null;
        Node moreTail = null;



        Node next = null;

        while (head != null) {
            next = head.next;
            head.next = null;

            if (head.val < pivot) {
                if (lessHead == null) {
                    lessHead = head;
                    lessTail = head;
                } else {
                    lessTail.next = head;
                    lessTail = head;
                }
            }
            if (head.val == pivot) {
                if (equalHead == null) {
                    equalHead = head;
                    equalTail = head;
                } else {
                    equalTail.next = head;
                    equalTail = head;
                }
            }
            if (head.val > pivot) {
                if (moreHead == null) {
                    moreHead = head;
                    moreTail = head;
                } else {
                    moreTail.next = head;
                    moreTail = head;
                }
            }
            head = next;

        }

        if (lessTail != null) {
            lessTail.next = equalHead;
            equalTail = equalTail == null ? lessTail : equalTail;
        }
        if (equalTail != null) {
            equalTail.next = moreHead;
        }


        return lessHead != null ? lessHead : equalHead != null ? equalHead : moreHead;

    }

    public static void main(String[] args) {
        Node n1 = new Node(1, null);
        Node n2 = new Node(2, null);
        Node n3 = new Node(3, null);
        Node n4 = new Node(2, null);
        Node n5 = new Node(1, null);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        Node n = partition(n1, 2);
        System.out.println(n);

    }


}

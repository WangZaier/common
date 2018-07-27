package com.wangzai.Node;

/**
 * 回文串的验证
 * <p>
 * 给出链表头部,验证改链表是否是一个回文串
 *
 * @author wangzai
 * @date 2018/5/15 上午6:59
 */
public class PalindromeValidate {


    public static boolean validate(Node head) {

        //慢指针
        Node slow = head;
        //快指针
        Node fast = head;

        //慢指针每次走一步,快指针一次走两步,直到快指针走到底时next为null,停止
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        //-这个时候,slow指向中点-//
        fast = slow.next;//将链表后半部分引用给fast
        slow.next = null;//slow将next断开,那么slow就作为中点了,(此时head的next也是断开了)


        //设置一个新的Node来将fast,也就是链表后半部分逆序存储
        Node node = null;

        //后半区逆序过程
        while (fast != null) {
            //流程图:
            //1. slow:3    fast:2->1 node:1
            //2. slow:3    fast:2->3 node:1
            //3. slow:2->3 fast:2->3 node:1
            //4. slow:2->3 fast:1    node:1
            //也就是每次把fast的next设置为slow.实现倒转

            node = fast.next;//现将后半区的"next"存储起来
            fast.next = slow;//再将后半区的"next"指向前一个节点
            slow = fast;//更新slow,将它更新为原来的next的前一个节点,方便下次继续逆序指向
            fast = node;//最后lastHalf暂存区(还没有逆序的区域)还给fast进行下一次倒序操作
        }

        //最后效果:
        //   1->2->3 -> null
        //         ↑
        //         2 <- 1


        //经过上面的步骤,后半区已经反转了
        node = slow;//node存储一下已经反转的后半区
        fast = head;//获取前半段数据

        boolean result = true;//结果

        //开始验证数据,直接跑就ok.此时已经反转
        while (slow != null && fast != null) {
            if (slow.val != fast.val) {
                result = false;
                break;
            }
            //各自下一个
            slow = slow.next;
            fast = fast.next;
        }

        //最后,将后半区调整回原来的样子,现在slow和fast都为空
        slow = node.next;//将后半区赋的"next"给slow
        node.next = null;//将后半区的next断开

        //后半部分再次逆序
        while (slow != null) {
            //1. fast:3 slow:2->3 node:1
            //2. fast:3 slow:2->1 node:1
            //3. fast:3 slow:2->1 node:2->1
            //4. fast:3 slow:3    node:2->3
            fast = slow.next; //将后半区的"next"暂存到fast中
            slow.next = node;//后半区的next指向前面的数据
            node = slow;//更新node,让下一次指向next的时候指向自己(已经逆序的数据)
            slow = fast;//后半区剩余数据的逆序
        }

        return result;


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

        System.out.println(validate(n1));

    }

}

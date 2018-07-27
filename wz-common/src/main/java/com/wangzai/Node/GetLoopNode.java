package com.wangzai.Node;

/**
 * 两条链表(有环/无环)的交点情况
 *
 * @author wangzai
 * @date 2018/5/17 上午2:41
 */
public class GetLoopNode {

    /**
     * 两个链表都无环
     * 两个链表一个有环一个无环.不存在
     * 两个链表都有环
     */
    public static Node get(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }

        //获取入环节点
        Node loop1 = getLoopNode(head1);
        Node loop2 = getLoopNode(head2);

        //两个无环链表相交情况
        if (loop1 == null && loop2 == null) {
            return noLoop(head1, head2);
        }

        //两个链表都有环的相交情况
        if (loop1 != null && loop2 != null) {
            return hasLoopNode(head1, loop1, head2, loop2);
        }

        //一个链表有环,一个链表无环,无法形成相交
        return null;

    }

    //判断链表是否有环,并返回入口节点
    private static Node getLoopNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }

        //设置快慢指针
        //若快指针指向出现null则无环
        //而在有环的情况下，两个指针会在环里绕圈，最终指向同一个地址
        Node slow = head.next;
        Node fast = head.next.next;

        while (slow != fast) {
            //出现了null,则无环
            if (fast.next == null || fast.next.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }

        //让两个指针其中一个从链表头 head 出发，一次走一步
        //让另一个指针从相遇点出发，也一次走一步，相遇点就是环的入口

        fast = head;
        while (slow != fast) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }


    //当两条链链表都无环的情况,返回相交节点
    private static Node noLoop(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }

        //获取引用
        Node node1 = head1;
        Node node2 = head2;

        //获取到两个链表长度
        int head1Length = 0;
        int head2Length = 0;

        //获取第一条链表长度
        while (node1 != null) {
            head1Length++;
            node1 = node1.next;
        }
        //获取第二条链表长度
        while (node2 != null) {
            head2Length++;
            node2 = node2.next;
        }

        int differ = head1Length - head2Length;

        //将node1设置为比较长的链表
        node1 = differ > 0 ? head1 : head2;
        //那么node2就是比较短的链表
        node2 = node1 == head1 ? head2 : head1;

        //取差值的绝对值
        differ = Math.abs(differ);

        //根据差值遍历比较长的链表node1
        //使得node1和node2没有差值.统一起跑线
        //这时两者同时起步,必然同时碰上"交点"
        while (differ != 0) {
            node1 = node1.next;
            differ--;
        }

        //此时起步相同.一人一步知道相遇
        while (node1 != node2) {
            node1 = node1.next;
            node2 = node2.next;
        }

        return node1;
    }

    //有环情况
    public static Node hasLoopNode(Node node1, Node loop1, Node node2, Node loop2) {

        //如果两个链表同时进环
        if (loop1 == loop2) {
            //将两个链表的loop切断,进行两个无环链表进行相交点获取
            loop1.next = null;
            loop2.next = null;
            return noLoop(node1, node2);
        } else {
            //如果loop1和loop2不相等.则入环节点不一致
            Node node = loop1.next;
            //从loop1出发.回到loop1.绕一圈.如果碰到了loop2,直接返回loop1即可(交点取其一)
            while (node != loop1) {
                if (node == loop2) {
                    return loop1;
                }
                node = node.next;
            }
            //没碰到返回null
            return null;

        }
    }

    public static void main(String[] args) {

        /**
         * 两条链表无环相交情况
         */
        //第一条链表
        Node n1 = new Node(1, null);
        Node n2 = new Node(2, null);
        Node n3 = new Node(3, null);
        Node n4 = new Node(4, null);
        Node n5 = new Node(5, null);

        //第二条链表
        Node n6 = new Node(6, null);
        Node n7 = new Node(7, null);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;

        n6.next = n7;
        n7.next = n4;

        Node node = get(n1, n6);
        System.out.println(node.val);

        /**
         * 两条链表都有环相交情况===入环点相同
         */
        Node n8 = new Node(8, null);
        Node n9 = new Node(9, null);
        Node n10 = new Node(10, null);
        Node n11 = new Node(11, null);
        Node n12 = new Node(12, null);

        //第二条链表
        Node n13 = new Node(13, null);
        Node n14 = new Node(14, null);
        Node n15 = new Node(15, null);

        ///相同入环点
        n8.next = n9;
        n9.next = n10;
        n10.next = n11;
        n11.next = n12;
        n12.next = n10;//进入n10,形成闭环

        n13.next = n14;
        n14.next = n15;
        n15.next = n10;//进入n10,形成闭环

        Node node1 = get(n8, n13);
        System.out.println(node1.val);

        /**
         * 两条链表都有环相交情况===入环点不同
         */
        Node n16 = new Node(16, null);
        Node n17 = new Node(17, null);
        Node n18 = new Node(18, null);
        Node n19 = new Node(19, null);
        Node n20 = new Node(20, null);

        //第二条链表
        Node n21 = new Node(21, null);
        Node n22 = new Node(22, null);
        Node n23 = new Node(23, null);

        n16.next = n17;
        n17.next = n18;
        n18.next = n19;
        n19.next = n20;
        n20.next = n18;//进入n18形成闭环

        n21.next = n22;
        n22.next = n23;
        n23.next = n19;//进入n19形成闭环

        Node node2 = get(n16, n23);
        System.out.println(node2.val);

    }

}

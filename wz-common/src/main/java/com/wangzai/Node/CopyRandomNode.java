package com.wangzai.Node;

import java.util.HashMap;
import java.util.Map;

/**
 * 对Node进行一个深度拷贝
 *
 * @author wangzai
 * @date 2018/5/16 上午12:42
 */
public class CopyRandomNode {

    public static RandomNode hashCopy(RandomNode head) {
        if (head == null) {
            return head;
        }

        //结构映射
        Map<RandomNode, RandomNode> nodeMapping = new HashMap<>();

        RandomNode node = head;

        //建立映射关系
        while (node != null) {
            nodeMapping.put(node, new RandomNode(node.val));
            node = node.next;
        }


        node = head;
        //拷贝指针
        while (node != null) {
            nodeMapping.get(node).next = nodeMapping.get(node.next);
            nodeMapping.get(node).random = nodeMapping.get(node.random);
            node = node.next;
        }

        return nodeMapping.get(head);
    }


    public static RandomNode nodeCopy(RandomNode head) {
        if (head == null) {
            return head;
        }

        RandomNode randomNode = head;

        RandomNode next;

        RandomNode random;

        RandomNode help;
        //将新的拷贝节点添加到原有链表
        while (randomNode != null) {
            next = randomNode.next;//存储next
            RandomNode node = new RandomNode(randomNode.val);//创建node
            node.next = next;//把next放到node后面
            randomNode.next = node;//将 node->next 放回去

            //下一个
            randomNode = randomNode.next.next;
        }


        //random指针拷贝
        randomNode = head;
        while (randomNode != null) {
            random = randomNode.random;//获取当前节点的random指针
            randomNode.next.random = random.next;//设置拷贝节点的random为random下一个(必然是拷贝节点)
            randomNode = randomNode.next.next;//下两个
        }

        //链表分离
        RandomNode result = head.next;//result是以head.next为开头的
        randomNode = head;
        while (randomNode != null) {
            //1 -> c1 -> 2 -> c2 -> 3 -> c3 -> 4 -> c4
            next = randomNode.next.next;//判断当前节点后面是否存在非拷贝节点
            help = randomNode.next;//获取拷贝节点
            randomNode.next = next;//当前节点的"next"设置为下一个非拷贝节点
            help.next = next != null ? next.next : null;//如果"next"为空说明当前节点后面不存在非拷贝节点设置为null,反之将拷贝节点的"next"设置为下一个拷贝节点
            randomNode = next;//下一个
        }

        return result;

    }

    public static void main(String[] args) {
        RandomNode node1 = new RandomNode(1);
        RandomNode node2 = new RandomNode(2);
        RandomNode node3 = new RandomNode(3);
        RandomNode node4 = new RandomNode(4);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        node1.random = node3;
        node2.random = node4;
        node3.random = node1;
        node4.random = node2;

        RandomNode randomNode = hashCopy(node1);

        RandomNode randomNode1 = nodeCopy(node1);


    }


}

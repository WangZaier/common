package com.wangzai.tree;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * 二叉树的序列化和反序列化
 *
 * @author wangzai
 * @date 2018/5/19 上午2:58
 */
public class BinartTreeSerialize {

    /**
     * 前序
     */
    //前序序列化
    public static String serialize(TreeNode head) {
        if (head == null) {
            return "#_";
        }

        String result = head.val + "_";

        result += serialize(head.left);
        result += serialize(head.right);

        return result;


    }

    //前序序列化的反序列化
    public static TreeNode reSerialize(String data) {
        String[] vals = data.split("_");

        //放进一个队列
        Queue<String> queue = new LinkedList<>();

        for (String val : vals) {
            queue.offer(val);
        }


        return reConPreOrder(queue);
    }

    private static TreeNode reConPreOrder(Queue<String> queue) {
        String value = queue.poll();
        //如果节点为空.返回null
        if (Objects.equals(value, "#")) {
            return null;
        }

        //创建父节点
        TreeNode head = new TreeNode(value);
        //左节点就是,queue下一个
        head.left = reConPreOrder(queue);
        //右节点就是,queue左边遍历完了的下一个
        head.right = reConPreOrder(queue);

        return head;
    }


    /**
     * 按层
     */
    //按层序列化
    public static String serializeByLevel(TreeNode head) {
        if (head == null) {
            return "#_";
        }

        String result = head.val + "_";

        //创建队列
        Queue<TreeNode> queue = new LinkedList<>();
        //放入头节点
        queue.offer(head);

        //队列先进先出.所以先放left
        //就是父节点永远比子节点先进去.
        //每次遍历完一层之后queue里就是另一层
        while (!queue.isEmpty()) {
            //取出队列先进的那个node
            head = queue.poll();
            //左节点不为空,放进队列
            if (head.left != null) {
                result += head.left.val + "_";
                queue.offer(head.left);
            } else {
                result += "#_";
            }
            //右节点不为空,放入队列
            if (head.right != null) {
                result += head.right.val + "_";
                queue.offer(head.right);
            } else {
                result += "#_";
            }
        }

        return result;
    }

    //按层反序列化
    public static TreeNode reSerializeByLevel(String data) {
        String[] values = data.split("_");

        int index = 0;
        //创建头节点
        TreeNode head = generatorNodeByString(values[index++]);

        //创建队列
        Queue<TreeNode> queue = new LinkedList<>();

        //添加头节点
        if (head != null) {
            queue.offer(head);
        }

        //设定Node
        TreeNode node = null;

        //循环创建树
        while (!queue.isEmpty()) {
            //节点弹出
            node = queue.poll();

            node.left = generatorNodeByString(values[index++]);
            node.right = generatorNodeByString(values[index++]);

            //左节点不为空,放进队列
            if (node.left != null) {
                queue.offer(node.left);
            }
            //右节点不为空,放进队列
            if (node.right != null) {
                queue.offer(node.right);
            }
        }

        return head;
    }

    private static TreeNode generatorNodeByString(String val) {
        if (Objects.equals(val, "#")) {
            return null;
        }
        return new TreeNode(val);
    }


    public static void main(String[] args) {
        TreeNode n1 = new TreeNode("1");
        TreeNode n2 = new TreeNode("2");
        TreeNode n3 = new TreeNode("3");
        TreeNode n4 = new TreeNode("4");
        TreeNode n5 = new TreeNode("5");
        TreeNode n6 = new TreeNode("6");
        TreeNode n7 = new TreeNode("7");


        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n3.left = n6;
        n3.right = n7;

//        String serialize = serialize(n1);
//        System.out.println(serialize);
//
//
//        Node reSerialize = reSerialize(serialize);
//        String serialize1 = serialize(reSerialize);
//        System.out.println(serialize1);
        String serialize = serializeByLevel(n1);
        System.out.println(serialize);


        TreeNode reSerialize = reSerializeByLevel(serialize);
        String serialize1 = serializeByLevel(reSerialize);
        System.out.println(serialize1);


    }

}

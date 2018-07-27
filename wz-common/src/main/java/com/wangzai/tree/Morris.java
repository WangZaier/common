package com.wangzai.tree;

/**
 * mirrors遍历
 *
 * @author wangzai
 * @date 2018/5/22 上午4:36
 */
public class Morris {

    public static void morris(TreeNode head) {
        if (head == null) {
            return;
        }

        TreeNode cur = head;
        TreeNode mostRight = null;

        while (cur != null) {
            mostRight = cur.left;
            //当左子树不为空的情况下
            if (mostRight != null) {
                //当前节点不为空,或没有指回当前节点.说明这不是最右的节点
                while (mostRight.right != null && mostRight.right != cur) {
                    //不停找左孩子的右节点
                    mostRight = mostRight.right;
                }

                //如果左子树的最右节点为空的情况下.则指向当前节点,并cur向左子树移动
                if (mostRight == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    //否则说明左子树最右节点已经指向了当前节点,让他指回null
                    mostRight.right = null;
                    //然后cur指向右子树
                }
            }
            //如果左子树为空,cur直接指向右子树
            cur = cur.right;
            System.out.println(cur.val + " ");
        }
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

        morris(n1);
    }

}

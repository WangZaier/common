package com.wangzai.tree;

/**
 * 平衡二叉树验证
 *
 * @author wangzai
 * @date 2018/5/24 上午3:03
 */
public class BalanceBinaryTreeNodeIsValid {

    public static boolean vaild(TreeNode head) {
        if (head == null) {
            return true;
        }

        return process(head).is;

    }


    public static ReturnData process(TreeNode head) {
        if (head == null) {
            return new ReturnData(true, 0);
        }

        //获取左树高度,以及平衡性
        ReturnData left = process(head.left);
        //left不平衡直接return
        if (!left.is) {
            return new ReturnData(false, 0);
        }

        //获取右树高度
        ReturnData right = process(head.right);

        //如果左右高度差超过1.验证失败
        if (Math.abs(right.h - left.h) > 1) {
            return new ReturnData(false, 0);
        }

        //返回成功
        return new ReturnData(true, Math.max(left.h, right.h) + 1);
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

        System.out.println(vaild(n1));

    }

    public static class ReturnData {
        public boolean is;
        public int h;

        public ReturnData(boolean is, int h) {
            this.is = is;
            this.h = h;
        }
    }
}

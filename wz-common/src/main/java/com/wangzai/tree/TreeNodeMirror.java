package com.wangzai.tree;

/**
 * 二叉树镜像
 *
 * @author wangzai
 * @date 2018/5/22 上午5:22
 */
public class TreeNodeMirror {


    public void Mirror(TreeNode root) {
        if(root==null){
            return;
        }
        //调用递归函数
        swap(root);
    }

    private void swap(TreeNode node){

        //定义help
        TreeNode help ;
        //交换节点操作
        help = node.left;
        node.left = node.right;
        node.right = help;

        //左子树交换
        if(node.left!=null){
            swap(node.left);
        }
        //右子树交换
        if(node.right!=null){
            swap(node.right);
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

        TreeNodeMirror treeNodeMirror = new TreeNodeMirror();
        treeNodeMirror.Mirror(n1);

        String serialize = BinartTreeSerialize.serializeByLevel(n1);
        System.out.println(serialize);


    }

}

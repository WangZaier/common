package com.wangzai.tree;

/**
 * 前缀树
 *
 * @author wangzai
 * @date 2018/5/18 上午7:48
 */
public class PrefixTree {

    private PrefixNode root;

    //新建头节点
    public PrefixTree() {
        root = new PrefixNode();
    }

    //添加造作
    public void insert(String word) {
        if (word == null) {
            return;
        }

        char[] chars = word.toCharArray();
        PrefixNode node = root;//获取头节点
        int index = 0;


        for (char aChar : chars) {
            //当前ASCII码减去a得到 0～25
            index = aChar - 'a';
            //检查node节点的nexts的index下标如果为空,我们放入新的
            if (node.nexts[index] == null) {
                node.nexts[index] = new PrefixNode();
            }
            //如果当前节点存在了.那么看下一个
            node = node.nexts[index];
            node.pass++;//沿途经过的节点
        }
        node.end++;//标识有一个字符是以这个结尾
    }

    //查询
    public int search(String word) {
        if (word == null) {
            return 0;
        }

        char[] chars = word.toCharArray();
        PrefixNode node = root;//获取头节点
        int index = 0;

        for (char aChar : chars) {
            index = aChar - 'a';
            if (node.nexts[index] == null) {
                return 0;
            }
            node = node.nexts[index];//下一个
        }
        return node.end;//上面遍历走到底了.我们返回该word添加进来的次数
    }

    //删除
    public void delete(String word) {
        //如果返回不等于0说明该word出现过
        if (search(word) != 0) {
            char[] chars = word.toCharArray();
            PrefixNode node = root;
            int index = 0;
            for (char aChar : chars) {
                index = aChar - 'a';
                //如果说当前节点--pass后为0,说明该节点字符串没有别的字符串共享.删就完事儿了.
                //如果说不为0,--pass之后不必对该节点设置null,因为别的串要用
                if (--node.nexts[index].pass == 0) {
                    node.nexts[index] = null;
                    return;
                }
                node = node.nexts[index];
            }
            node.end--;//该单词--
        }
    }

    //统计前缀为xxx的的个数
    public int prefixNumber(String prefix) {
        if (prefix == null) {
            return 0;
        }
        char[] chars = prefix.toCharArray();
        PrefixNode node = root;
        int index = 0;
        for (char aChar : chars) {
            index = aChar - 'a';
            if (node.nexts[index] == null) {
                return 0;
            }
            node = node.nexts[index];
        }
        //跑完以后获取pass
        return node.pass;
    }

    private class PrefixNode {
        public int pass;//pass代表当前节点的字符串被共享多少次
        public int end;
        public PrefixNode[] nexts;

        public PrefixNode() {
            pass = 0;
            end = 0;
            nexts = new PrefixNode[26];
        }
    }

    public static void main(String[] args) {
        PrefixTree prefixTree = new PrefixTree();

        prefixTree.insert("wangzai");

        int wangzai = prefixTree.search("wangzai");
        System.out.println(wangzai);

        int wang = prefixTree.prefixNumber("wang");
        System.out.println(wang);

        prefixTree.delete("wangzai");
        int search = prefixTree.search("wangzai");
        System.out.println(search);
    }

}

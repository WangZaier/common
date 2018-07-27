package com.wangzai.Node;

import com.wangzai.tree.TreeNode;

import java.util.*;

public class Test {

    public static void main(String[] args) {

        Node n1 = new Node(1, null);
        Node n2 = new Node(1, null);
        Node n3 = new Node(2, null);
        Node n4 = new Node(3, null);
        Node n5 = new Node(3, null);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;

        Node node = deleteDuplicates(n1);

        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }

    public static Node deleteDuplicates(Node head) {

        Node start = head;

        while (start != null && start.next != null) {
            //如果当前数和下一个数相等.则向后遍历直到不一样
            if (start.val == start.next.val) {
                int val = start.val;
                Node help = start;
//                while(help!=null){
                while (true) {
                    if(help==null){
                        start.next = help;
                        break;
                    }
                    if (help.val == val) {
                        help = help.next;
                    } else {
                        start.next = help;
                        break;
                    }
                }
            } else {
                start = start.next;
            }
        }
        return head;
    }


    public static int[] plusOne(int[] digits) {

        int n = digits.length;
        for (int i = n - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }

            digits[i] = 0;
        }

        int c = 1;
        int[] newNumber = new int[n + 1];
        newNumber[0] = 1;
        return newNumber;
    }


    /**
     * 61
     */
    public static Node rotateRight(Node head, int k) {
        if (head == null) {
            return null;
        }
        Node start = head;
        Node end = head;
        int length = 1;//默认从1节点开始
        //计算链表长度
        while (end.next != null) {
            end = end.next;
            length++;
        }
        //闭环
        end.next = start;

        //让它模上length(等于说是减去多余的圈数),在用长度减它
        int breaknode = length - (k % length);
        for (int i = 1; i < breaknode; i++) {
            start = start.next;
        }

        //获取头节点
        Node node = start.next;
        //尾节点截断
        start.next = null;

        return node;
    }


//    public static void permutation(char[] buf, int start, int end) {
//        if (start == end) {// 当只要求对数组中一个字母进行全排列时，只要就按该数组输出即可
//            for (int i = 0; i <= end; i++) {
//                System.out.print(buf[i]);
//            }
//            System.out.println();
//        } else {// 多个字母全排列
//            for (int i = start; i <= end; i++) {
//                char temp = buf[start];// 交换数组第一个元素与后续的元素
//                buf[start] = buf[i];
//                buf[i] = temp;
//                permutation(buf, start + 1, end);// 后续元素递归全排列
//                temp = buf[start];// 将交换后的数组还原
//                buf[start] = buf[i];
//                buf[i] = temp;
//            }
//        }
//    }


    /**
     *
     */
    public static int lengthOfLastWord(String s) {
        int length = s.length() - 1;
        int j = 0;
        boolean flag = false;

        for (int i = length; i >= 0; i--) {
            if (s.charAt(i) != ' ') {
                j++;
                flag = true;
            } else if (s.charAt(i) == ' ' && flag) {
                return j;
            }
        }
        return j;
    }


    public static int maxSubArray(int[] A) {
        int n = A.length;
        int[] dp = new int[n];//dp[i] means the maximum subarray ending with A[i];
        dp[0] = A[0];
        int max = dp[0];

        for (int i = 1; i < n; i++) {
            //settings dp[i] values is A[i] + last dp values , if lastdp less than 0 , skip last d p
            dp[i] = A[i] + (dp[i - 1] > 0 ? dp[i - 1] : 0);

            max = Math.max(max, dp[i]);
        }

        return max;
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            String key = "";
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            for (char aChar : chars) {
                key += aChar;
            }
            if (map.get(key) == null) {
                List<String> list = new ArrayList<>();
                list.add(str);
                map.put(key, list);
            } else {
                map.get(key).add(str);
            }
        }

        return new ArrayList<>(map.values());
    }

    /* Clockwise Rotate */
    public static void rotate(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return;
        int rows = matrix.length;
        int cols = matrix[0].length;
        for (int first = 0, last = rows - 1; first < last; first++, last--) {
            int[] tmp = matrix[first];
            matrix[first] = matrix[last];
            matrix[last] = tmp;
        }
        for (int i = 0; i < rows; i++) {
            for (int j = i + 1; j < cols; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
    }

    /**
     *
     */
    public static List<List<Integer>> permute(int[] nums) {
        Set<List<Integer>> res = new HashSet<>();
        allSort(nums, 0, nums.length - 1, res);

        List<List<Integer>> result = new ArrayList<>();

        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (List<Integer> re : res) {
                    result.add(re);
                }
            }
        }).start();

        return result;
    }

    static void allSort(int[] array, int begin, int end, Set<List<Integer>> res) {
        //打印数组的内容
        if (begin == end) {
            List<Integer> str = new ArrayList<>();
            for (int s : array) {
                str.add(s);
            }
            res.add(str);
            return;
        }
        //把子数组的第一个元素依次和第二个、第三个元素交换位置
        for (int i = begin; i <= end; i++) {
            swap(array, begin, i);
            allSort(array, begin + 1, end, res);
            //交换回来
            swap(array, begin, i);
        }
    }

    static void swap(int[] array, int a, int b) {
        int tem = array[a];
        array[a] = array[b];
        array[b] = tem;
    }

    /**
     *
     */
    public static int jump(int[] A) {
        int sc = 0;
        //跳跃距离
        int e = 0;
        //最大跳跃距离
        int max = 0;
        for (int i = 0; i < A.length - 1; i++) {
            max = Math.max(max, i + A[i]);
            if (i == e) {
                sc++;
                e = max;
            }
        }
        return sc;
    }

    public static boolean isMatch(String str, String pattern) {
        int s = 0, p = 0, match = 0, starIdx = -1;
        while (s < str.length()) {
            // 指针同时推进
            if (p < pattern.length() && (pattern.charAt(p) == '?' || str.charAt(s) == pattern.charAt(p))) {
                s++;
                p++;
            }
            // *发现，仅前进模式指针
            else if (p < pattern.length() && pattern.charAt(p) == '*') {
                starIdx = p;
                match = s;
                p++;
            }
            // 最后的模式指针是*，前进的字符串指针
            else if (starIdx != -1) {
                p = starIdx + 1;
                match++;
                s = match;
            }
            //当前模式指针不是星，最后的指针指针不是*
            ///字符不匹配
            else {
                return false;
            }
        }

        // 图案中剩余字符的检查
        while (p < pattern.length() && pattern.charAt(p) == '*')
            p++;

        return p == pattern.length();
    }

    public static boolean isValidSudoku(char[][] board) {

        for (int i = 0; i < 9; i++) {
            //行
            boolean[] help = new boolean[9];
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c != '.') {
                    //该位置为true的情况.直接返回false
                    if (help[c - '1']) {
                        System.out.println("row:" + i + ",coloum:" + j);
                        return false;
                    } else {
                        //改为true
                        help[c - '1'] = true;
                    }
                }
            }

            //列
            boolean[] help1 = new boolean[9];
            for (int j = 0; j < 9; j++) {
                char c = board[j][i];
                if (c != '.') {
                    if (help1[c - '1']) {
                        System.out.println("row:" + i + ",coloum:" + j);
                        return false;
                    } else {
                        //改为true
                        help1[c - '1'] = true;
                    }
                }
            }
        }

        //块
        //行列3等分
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boolean[] help = new boolean[9];
                //行
                for (int row = i * 3; row < (i + 1) * 3; row++) {
                    for (int coloum = j * 3; coloum < (j + 1) * 3; coloum++) {
                        char c = board[row][coloum];
                        //为.跳过
                        if (c != '.') {
                            if (help[c - '1']) {
                                return false;
                            } else {
                                //改为true
                                help[c - '1'] = true;
                            }
                        }
                    }
                }
            }
            System.out.println("\n");

        }

        return true;
    }

    private static boolean validate(char[][] arr, int x, int y) {
        boolean[] help = new boolean[9];
        for (int i = x * 3; i < (x + 1) * 3; i++) {
            for (int j = y * 3; j < (y + 1) * 3; j++) {
                char c = arr[i][j];
                //为.跳过
                if (c != '.') {
                    if (help[c - '1']) {
                        return false;
                    } else {
                        //改为true
                        help[c - '1'] = true;
                    }
                }
            }
        }

        return true;
    }

    public static int searchInsert(int[] nums, int target) {

        for (int i = 0; i < nums.length; i++) {
            if (target > nums[i]) {
                if ((i + 1) >= nums.length) {
                    return i + 1;
                }
                if (target > nums[i + 1]) {
                    continue;
                }
                if (target < nums[i + 1]) {
                    return i + 1;
                }

            } else {
                return i < 0 ? 0 : i;
            }

        }

        return -1;
    }

    public static List<Interval> merge(List<Interval> intervals) {

        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval a, Interval b) {
                return a.start - b.start;
            }
        });

        List<Interval> res = new ArrayList<>();

        for (Interval interval : intervals) {
            int resSize = res.size() - 1;
            if (res.isEmpty()) {
                res.add(interval);
            }
            //提前验证 head1->end1 head2->end2中 end1>end2 直接跳过
            else if (res.get(resSize).end > interval.end) {
                continue;
            } else if (res.get(resSize).end >= interval.start) {
                res.get(resSize).end = interval.end;
            } else {
                res.add(interval);
            }
        }
        ;

        return res;
    }

    public static class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }


}

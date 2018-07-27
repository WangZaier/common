package com.wangzai.matrix;

/**
 * 在M*N的有序矩阵中寻找一个K
 * 时间复杂度为O(M+N),空间复杂度为O(1)
 *
 * @author wangzai
 * @date 2018/5/15 上午4:49
 */
public class FindNumbersInOrderMatrix {

    public static boolean find(int[][] matrix, int value) {

        //定义动点坐标
        int row1 = 0;
        int col1 = matrix[0].length - 1;

        //定义行
        int row2 = matrix.length - 1;
        //定义列
        int col2 = matrix[0].length - 1;


        boolean find = false;

        //从右向左走
        while (row1 <= row2 && col1 >= 0) {
            //如果找到了
            if (matrix[row1][col1] == value) {
                return true;
            } else if (matrix[row1][col1] > value) {
                //如果当前坐标比value大,向左移动
                col1--;
            } else if (matrix[row1][col1] < value) {
                //如果当前坐标比value小,向下移动
                row1++;
            }
        }

        return find;
    }


    public static void main(String[] args) {
        int[][] matrix = {
                {1,  2,  3,  4 },
                {2,  4,  6,  8 },
                {10, 12, 14, 16},
                {11, 13, 17, 19}
        };

        boolean b = find(matrix, 11);
        System.out.println(b);
    }
}

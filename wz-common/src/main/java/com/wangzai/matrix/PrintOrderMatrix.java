package com.wangzai.matrix;

/**
 * 缩圈输出矩阵
 *
 * @author wangzai
 * @date 2018/5/15 上午2:30
 */
public class PrintOrderMatrix {


    //四点确定一矩形
    public static void print(int[][] matrix) {

        //动点1的列标
        int row1 = 0;
        //动地1的行标
        int col1 = 0;
        //动点2的列标
        int row2 = matrix.length - 1;
        //动点2的行标
        int col2 = matrix[0].length - 1;

        //循环调用
        while (row1 <= row2 && col1 <= col2) {
            //每次循环,左边界++,上边界++,右边界--,下边界-- 全部往内圈缩
            pringEdge(matrix, row1++, col1++, row2--, col2--);
        }
    }


    //给出边界,打印边界
    public static void pringEdge(int[][] matrix, int row1, int col1, int row2, int col2) {


        if (row1 == row2) {//如果只有一行/只剩下一行.直接for打印
            for (int i = col1; i <= col2; i++) {
                System.out.println(matrix[row1][i] + " ");
            }
        } else if (col1 == col2) {//如果只有一列/只剩下一列.直接for打印
            for (int i = row1; i <= row2; i++) {
                System.out.println(matrix[i][col1] + " ");
            }
        } else {//其他则属于正常情况
            //引用动点1列标和行标
            int curC = col1;
            int curR = row1;

            //curC开始从row1行从左向右遍历,触碰到col2结束
            while (curC != col2) {
                System.out.println(matrix[row1][curC] + " ");
                curC++;
            }

            //curR在col1列从上到下遍历,触碰到row2结束
            while (curR != row2) {
                System.out.println(matrix[curR][col2] + " ");
                curR++;
            }

            /**
             * 通过上面的遍历,列标和行标都走到了末尾.也就是等于row2和col2
             */

            //curC开始从row2行从右边向左遍历,直到碰到原先的col1结束
            while (curC != col1) {
                System.out.println(matrix[row2][curC] + " ");
                curC--;
            }

            //curR开始从col1列从下到上遍历.直到碰到了row1结束
            while (curR != row1) {
                System.out.println(matrix[curR][col1] + " ");
                curR--;
            }
        }
    }

    public static void main(String[] args) {
//        int[][] arr = {{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {11, 12, 13, 14, 15}};
        int[][] arr = {{1, 2, 3}, {4,5,6}, {7,8,9}};
        print(arr);
    }
}

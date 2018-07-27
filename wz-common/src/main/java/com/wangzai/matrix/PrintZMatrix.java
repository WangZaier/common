package com.wangzai.matrix;

/**
 * 以Z形输出矩阵
 *
 * @author wangzai
 * @date 2018/5/15 上午2:30
 */
public class PrintZMatrix {

    //横向节点的下标代表列
    //纵向节点的下标代表行
    public static void print(int[][] matrix) {
        //动点1的列标
        int row1 = 0;
        //动点1的行标
        int col1 = 0;
        //动点2的列标
        int row2 = 0;
        //动点2的行标
        int col2 = 0;

        //行的边界
        int endR = matrix.length - 1;
        //列的边界
        int endC = matrix[0].length - 1;

        boolean fromUp = false;


        //当走到重点停止.
        while (row1 != endR + 1) {
            //打印方法
            printLevel(matrix, row1, col1, row2, col2, fromUp);

            //当动点1的行标走到底.行标下移(+1),如果没走到底,行标不动
            row1 = col1 == endC ? row1 + 1 : row1;
            //当动点1的行标走到底,列标不动,如果没走到底,那么右移(+1)
            col1 = col1 == endC ? col1 : col1 + 1;
            //当动点2的行标走到底.列标右移(+1),如果没走到底,列标不动
            col2 = row2 == endR ? col2 + 1 : col2;
            //当动点2的行标走到底.行标不动.如果没走到底,列标下移(+1)
            row2 = row2 == endR ? row2 : row2 + 1;

            //输出顺序反转
            fromUp = !fromUp;
        }
    }

    //输出方法
    private static void printLevel(int[][] matrix, int row1, int col1, int row2, int col2, boolean fromUp) {

        //正向输出和反向输出
        if (fromUp) {
            while (row1 <= row2) {
                System.out.println(matrix[row1++][col1--] + " ");
            }
        } else {
            while (row2 != row1 - 1) {
                System.out.println(matrix[row2--][col2++] + " ");
            }
        }
    }


    public static void main(String[] args) {
        int[][] arr = {
                {1,  2,  3,  4,  5},
                {6,  7,  8,  9,  10},
                {11, 12, 13, 14, 15},
                {16, 17, 18, 19, 20}
        };
        print(arr);
    }

}

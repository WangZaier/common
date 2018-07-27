package com.wangzai.array;

/**
 * 在一个数组中寻找缺失的最小正整数
 *
 * @author wangzai
 * @date 2018/5/20 下午11:59
 */
public class SmallestNumberMissInArray {

    public static int missNum(int[] arr) {

        int L = 0;
        int R = arr.length;

        while (L < R) {
            //遇到当前值为L+1,L向右扩大1,并下一个
            if (arr[L] == L + 1) {
                L++;
            }
            //当前值小于等于L.值无用
            //当前值比可达到的最大值还大,无效值
            //当前值和前一个值相重.情况变差
            else if (arr[L] <= L || arr[L] > R || arr[arr[L] - 1] == arr[L]) {
                //右区往左缩.原本能达到的最好情况变差了.
                arr[L] = arr[--R];
            } else {
                swap(arr, L, arr[L] - 1);
            }
        }
        return L + 1;
    }

    private static void swap(int[] arr, int i, int j) {

        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;

    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        System.out.println(missNum(arr));
    }
}

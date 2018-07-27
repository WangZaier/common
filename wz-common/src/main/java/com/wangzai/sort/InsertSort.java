package com.wangzai.sort;

/**
 * 插入排序
 *
 * @author wangzai
 * @date 2018/5/6 下午2:08
 */
public class InsertSort {


    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
        }
    }

    //数据交换
    private static void swap(int[] arr, int j, int i) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }


    public static void main(String[] args) {
        int[] arr = {6, 3, 2, 4};
        InsertSort.sort(arr);
        for (int i : arr) {
            System.out.println(i);
        }
    }

}

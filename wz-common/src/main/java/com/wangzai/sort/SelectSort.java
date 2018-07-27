package com.wangzai.sort;

/**
 * 选择排序
 *
 * @author wangzai
 * @date 2018/5/12 上午1:26
 */
public class SelectSort {


    //原理就是先0 - i 中找一个最小值(在遍历的时候每有更小的就把它放到0索引上)
    //然后 1- i 中找一个最小值(在遍历的时候每有更小的就把它放到1索引上)
    //然后 2- i 中找一个最小值(在遍历的时候每有更小的就把它放到2索引上)
    //。。。。。同理


    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            //内层循环j=i+1,外层循环控制着循环次数。即每趟中a[i]这个值就是本趟的最小值。i位置上是最小值
            for (int j = i + 1; j < arr.length; j++) {
                //若是这个a[j]比a[i]更小,则吧a[j]给换到前面去,也就是a[i]的位置
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }


}

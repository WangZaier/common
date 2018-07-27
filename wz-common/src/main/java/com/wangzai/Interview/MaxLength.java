package com.wangzai.Interview;

import java.util.HashMap;
import java.util.Map;

/**
 * 获取一个数组中累加和为x的数的最长连续长度
 *
 * @author wangzai
 * @date 2018/5/23 上午9:58
 */
public class MaxLength {

    public static int maxLength(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        //存储没有重复的出现的累加和和他的下标
        Map<Integer, Integer> map = new HashMap<>();

        //放入-1未知的时候累加和为0
        map.put(0, -1);

        int len = 0;//最长长度
        int sum = 0;//当前累加和

        for (int i = 0; i < arr.length; i++) {
            //sum与当前值累加
            sum += arr[i];

            //一旦map中存在 当前累加和-预期值
            if (map.containsKey(sum - k)) {
                //如果出现过,那么说明那个位置到当前位置的和就是k了 (sum - (sum-k) = k)
                len = Math.max(i - map.get(sum - k), len);
            }
            //如果map中不存在当前累加和,放入map
            //如果map中存在的话是不需要在此放入当前累加和的()
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }

        return len;
    }


    public static void main(String[] args) {
        int[] arr = {7, 3, 2, 1, 1, 7, -6, -1, 7};
        int length = maxLength(arr, 7);
        System.out.println(length);


    }


}
package com.wangzai.sort;

/**
 * 求一个数组排序后相邻数的最大值
 *
 * @author wangzai
 * @date 2018/5/11 下午11:27
 */
public class MaxGap {

    public static int max(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }

        //数组长度
        int len = nums.length;

        //初始最小值
        int min = Integer.MAX_VALUE;
        //初始最大值
        int max = Integer.MIN_VALUE;

        //遍历得最大最小值
        for (int num : nums) {
            //获取最小值
            min = Math.min(min, num);
            //获取最大值
            max = Math.max(max, num);
        }

        /**
         * 桶的数量为len+1是为了保证
         */
        //判断每个桶中是否有值
        boolean[] hasNum = new boolean[len + 1];
        //记录每个桶中的最大值
        int[] maxs = new int[len + 1];
        //记录每个桶中的最小值
        int[] mins = new int[len + 1];

        int bid = 0;
        //遍历
        for (int num : nums) {
            //获取当前数在桶中的位置
            bid = bucket(num, len, max, min);
            //跟桶原来数据比较,如果更小,替换掉它
            mins[bid] = hasNum[bid] ? Math.min(mins[bid], num) : num;
            //跟桶原来数据比较,如果更大,替换掉它
            maxs[bid] = hasNum[bid] ? Math.max(maxs[bid], num) : num;
            //将记录值改为true
            hasNum[bid] = true;
        }

        //定义结果
        int result = 0;
        //这是记录"上一个桶"的最大值
        int lastMax = maxs[0];
        //下标记录值
        int i = 1;
        for (; i < len; i++) {
            //如果当前桶不为空
            if (hasNum[i]) {
                //只有当前最小与上一个最大有可能会出现最大值.
                //为什么是最小而不是最大.因为如果是最大,那么可能存在同一个桶存储至少两个以上值(max,min),这种情况必然不是最大
                result = Math.max(mins[i] - lastMax, result);
                //然后将这个桶中的max赋予lastMax,以便下一次便利使用
                lastMax = maxs[i];
            }

        }


        return result;
    }


    /**
     * 数据需要进入的桶
     *
     * @param num 当前数
     * @param len 桶大小
     * @param max 最大值
     * @param min 最小值
     * @return 返回改数据需要进入桶的标识
     */
    private static int bucket(int num, int len, int max, int min) {

        /**
         * 例: 0-99,9个数
         * 0,13,17,22,33,55,64,77,89,99
         * |0~9|10~19|20~29|30~39|40~49|50~59|60~69|70~79|80~89|90~99|
         */
        //(num - min)是为了获取获取num在整个数组范围内的相对位置
        //(max - min)是为了获取整个数组的范围
        //(num - min)/(max - min)可以获取到num在整个范围内的相对位置,乘以10即可获取他的桶位置
        return (num - min) * len / (max - min);

    }

    public static void main(String[] args) {
        int[] arr = {0, 13, 17, 22, 33, 54, 55, 65, 77, 89, 99};
        System.out.println(max(arr));
    }

}

package com.wangzai.Interview;

import java.util.Stack;

/**
 * 烽火台问题
 *
 * @author wangzai
 * @date 2018/5/22 上午3:11
 */
public class BeaconTowerProblem {

    public static long communications(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }

        int size = arr.length;

        //寻找最大值
        int maxIndex = 0;
        for (int i = 0; i < size; i++) {
            maxIndex = arr[maxIndex] < arr[i] ? i : maxIndex;
        }

        //最大值
        int value = arr[maxIndex];
        //最大值的下一个数
        int index = nextIndex(size, maxIndex);
        //结果
        long result = 0L;

        //新建一个栈
        Stack<Pair> stack = new Stack<>();
        //把最大值扔进去,这是开始遍历的位置
        stack.push(new Pair(value));

        //从maxIndex下一个下标开始.如果等于的话.说明一圈转完
        while (index != maxIndex) {
            //获取当前value
            value = arr[maxIndex];
            //当前值大于栈中数据
            while (!stack.isEmpty() && stack.peek().value < value) {
                //获取times,将其弹出
                int times = stack.pop().times;
                //结算山峰对
                result += getInternalSum(times) + 2 * times;
            }
            //如果等于栈中数据
            if (!stack.isEmpty() && stack.peek().value == value) {
                stack.peek().times++;
            } else {
                //如果大于,放入栈
                stack.push(new Pair(value));
            }
            //下一个index
            index = nextIndex(size, index);
        }

        //单独结算
        while (!stack.isEmpty()) {
            int times = stack.pop().times;
            //每次弹出必存在Ck²
            result += getInternalSum(times);
            if (!stack.isEmpty()) {
                result += times;
                if (stack.size() > 1) {
                    result += times;
                } else {
                    result += stack.peek().times > 1 ? times : 0;
                }
            }
        }

        return result;
    }


    private static long getInternalSum(int times) {
        //如果times为1,那就是0个烽火台对,否则就是Ck²
        return times == 1L ? 0L : (long) times * (long) (times - 1) / 2L;
    }

    private static int nextIndex(int size, int maxindex) {
        return maxindex < (size - 1) ? maxindex + 1 : 0;
    }

    public static void main(String[] args) {
        int[] arr = {4, 4, 4, 3, 5, 6};
        System.out.println(communications(arr));
    }

    private static class Pair {
        int value;
        int times;

        public Pair(int value) {
            this.value = value;
            this.times = 1;
        }
    }
}

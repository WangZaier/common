package com.wangzai.util;

/**
 * String转为int
 *
 * @author wangzai
 * @date 2018/5/20 上午2:17
 */
public class StringToInteger {


    public static int convert(String str) {
        if (str == null || str.equals("")) {
            return 0;
        }

        char[] chars = str.toCharArray();

        //有效性验证
        if (!isValid(chars)) {
            return 0;
        }

        //判断正负.复数false,正数true
        boolean positive = chars[0] == '-' ? false : true;
        //因为负数的最小值绝对值比正数的绝对值大1
        //所有负数都能转为正数,一般做转化先搞成负数
        int minq = Integer.MIN_VALUE / 10;
        int minr = Integer.MIN_VALUE % 10;
        int res = 0;//结果
        int cur = 0;//当前活得数字

        //正数从0,负数从1开始
        for (int i = positive ? 0 : 1; i < chars.length; i++) {
            //对当前数字取负值
            cur = '0' - chars[i];
            //1.如果原结果比最小值除以10还小.必然溢出
            //2.如果原结果跟最小值除以10一样
            //2.则判断当前数和最小值的模.如果当前值比模小,必溢出
            if ((res < minq) || (res == minq && cur < minr)) {
                return 0;
            }
            //原结果乘以10,加当前值
            res = res * 10 + cur;
        }

        //如果当前数是正数,达到最小值.则溢出
        if (positive && res == Integer.MIN_VALUE) {
            return 0;
        }

        //正数取反
        return positive ? -res : res;
    }

    //验证有效性
    private static boolean isValid(char[] chars) {

        //如果开头不是'-',且第一个字符不处于0~9 之间,无效
        if (chars[0] != '-' && (chars[0] < '0' || chars[0] > '9')) {
            return false;
        }
        //如果首字母是'-',且长度只有1或下一个字符是0,无效
        if (chars[0] == '-' && (chars.length == 1 || chars[1] == '0')) {
            return false;
        }
        //如果首字母为0,并且长度大于1,无效
        if (chars[0] == '0' && chars.length > 0) {
            return false;
        }
        //上面对0下标判断了,从1开始,非0~9则异常
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] < '0' || chars[0] > '9') {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        String str = "-2147483648";
        System.out.println(convert(str));
    }

}

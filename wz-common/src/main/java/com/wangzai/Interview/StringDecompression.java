package com.wangzai.Interview;

import java.io.File;

/**
 * 字符串减压
 *
 * @author wangzai
 * @date 2018/5/21 下午4:31
 */
public class StringDecompression {

    public static String unZip(String str) {
        if (str == null) {
            return "";
        }

        char[] chars = str.toCharArray();


        return process(chars, 0).str;

    }

    public static ReturnData process(char[] chs, int index) {
        //结果
        StringBuilder result = new StringBuilder();
        //括号内需要重复的次数
        int times = 0;

        //越界,碰到"}"停止
        while (index < chs.length && chs[index] != '}') {
            //碰到"{"调用递归过程
            if (chs[index] == '{') {
                ReturnData process = process(chs, index + 1);
                result.append(getTimesString(times, process.str));
                times = 0;
                index = process.end;
            } else {
                //读取到重复次数,设置给time
                if (chs[index] >= '0' && chs[index] <= '9') {
                    times = times * 10 + chs[index] - '0';
                }
                //读取到正常数据,设置给result
                if (chs[index] >= 'a' && chs[index] <= 'z') {
                    result.append(String.valueOf(chs[index]));
                }
                //索引向后增加
                index++;
            }
        }

        return new ReturnData(result.toString(), index);

    }

    private static String getTimesString(int times, String str) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < times; i++) {
            result.append(str);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(unZip("c2{ab}se"));
    }

    private static class ReturnData {
        String str;
        int end;

        public ReturnData(String str, int end) {
            this.str = str;
            this.end = end;
        }
    }

}

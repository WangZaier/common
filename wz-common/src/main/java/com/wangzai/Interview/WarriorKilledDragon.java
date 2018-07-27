package com.wangzai.Interview;

import java.util.Arrays;

/**
 * 勇士杀龙
 * <p>
 * 某个王国有恶龙若干.能力不均.
 * 只有能力大于等于恶龙的勇士才能杀死他
 * 勇士的佣金为他的能力值
 * 请设计最省钱的方式
 * 勇士可多次雇佣
 *
 * @author wangzai
 * @date 2018/5/21 上午5:11
 */
public class WarriorKilledDragon {


    //单杀
    public static int kill(int[] warrior, int[] dragon) {
        //对勇士能力排序
        Arrays.sort(warrior);

        //需要付的佣金
        int result = 0;

        for (int i = 0; i < dragon.length; i++) {
            //左边界
            int left = 0;
            //右边界
            int right = warrior.length - 1;
            //最符合的勇士
            int suitable = warrior[warrior.length - 1];
            while (left <= right) {
                //中点
                int mid = left + (right - left) / 2;
                //如果龙战斗力小于战士.则将right设置为中分-1
                if (dragon[i] < warrior[mid]) {
                    //如果这个勇士比合适的勇士更合适(战斗力更低,佣金更低),替换
                    if (warrior[mid] <= suitable) {
                        suitable = warrior[mid];
                    }
                    right = mid - 1;
                } else if (dragon[i] > warrior[mid]) {
                    //勇士战斗力小于龙.left设置为中分+1
                    left = mid + 1;
                } else {
                    //找到了最合适的勇士.停止while
                    suitable = warrior[mid];
                    break;
                }
            }

            //循环完毕.佣金增加
            result += suitable;
        }

        return result;
    }


    public static void main(String[] args) {
        int[] warrior = {1, 2, 7, 10, 14, 20};
        int[] dragon = {6, 10, 13, 19};

        System.out.println(kill(warrior, dragon));
    }

}

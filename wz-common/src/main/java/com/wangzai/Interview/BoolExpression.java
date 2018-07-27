package com.wangzai.Interview;

/**
 * @author wangzai
 * @date 2018/5/21 下午6:07
 */
public class BoolExpression {

    public boolean isVaild(char[] exp) {
        //获取长度的1位.为偶数的情况下返回false
        if ((exp.length & 1) == 0) {
            return false;
        }

        //判断偶数位是否为0,1
        for (int i = 0; i < exp.length; i = i + 2) {
            if ((exp[i] != '0') && (exp[i] != '1')) {
                return false;
            }
        }

        for (int i = 1; i < exp.length; i = i + 2) {
            if ((exp[i] != '|') && (exp[i] != '^') && (exp[i] != '&')) {
                return false;
            }
        }

        return true;
    }





}

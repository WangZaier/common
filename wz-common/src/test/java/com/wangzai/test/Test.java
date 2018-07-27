package com.wangzai.test;


public class Test {

    public static void main(String[] args) {
        String s = "the sky is blue";
        String s1 = new Test().reverseWords(s);
        System.out.println(s1);
        new String("");
    }

    public boolean isPalindrome(String s) {
        s = s.replaceAll("[^0-9a-zA-Z]", "");

        char[] chars = s.toCharArray();
        int length = chars.length;

        if (length <= 1) {
            return true;
        }

        int start = 0;
        int end = length - 1;
        while (start < end) {
            if (Character.toUpperCase(chars[start]) != Character.toUpperCase(chars[end])) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    public String reverseWords(String s) {
        String[] strs = s.split(" ");
        int length = strs.length - 1;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = length; i >= 0; i--) {
            stringBuilder.append(strs[i] + " ");

        }

        return stringBuilder.toString();

    }
}

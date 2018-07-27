package com.wangzai.sort;

/**
 * 快速排序
 *
 * @author wangzai
 * @date 2018/5/12 上午1:26
 */
public class QuickSort {

    public static void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);

    }

    public static void sort(int[] arr, int left, int right) {
        if (left < right) {
            //在数组中随机选择一个数作为划分值,然后我们把这个划分值换到数组尾部
            swap(arr, left + (int) (Math.random() * (right - left + 1)), right);

            //进行一个分区
            int[] p = partition(arr, left, right);
            //对less分区进行排序
            sort(arr, left, p[0] - 1);
            //对more分区进行排序
            sort(arr, p[1] + 1, right);
        }
    }

    public static int[] partition(int arr[], int left, int right) {

        //less区
        int less = left - 1;
        //more区
        int more = right;

        //如果left自下标和more区域碰到了一起
        //当然这里为什么不用less<more呢 , 因为less和left不是同步的, less只是代表less区域,不代表left下标的遍历.例如相等就不去划分less区域而是直接left++
        while (left < more) {

            //我们这里arr[right]就是一个定值.为数组最右边值,作为参考值
            //如果当这个left过来了
            //对比过后,如果left也就是当前值比参考值小,那么我们将less区域后面的一个值和当前left进行位置交换,(如果那个值是刚好等于参考值,就会把它移到那个"相等区域"这就是为什么不把相等的数划分到less中)
            //之后++less也表示less区域往右把刚才换过来的数添加到less区域,然后left++表示继续进行
            if (arr[left] < arr[right]) {
                //进行交换
                swap(arr, ++less, left++);

            } else if (arr[left] > arr[right]) {
                //对比后如果说这个值比参考值更大,则将他与参考值的左边一个数进行交换,也就是将值移到more区域,--more也就代表more区域增加一个数据 下标往前靠,left就代表所需要的哪个数据下表
                //不left++是因为left这个数跟more区域前一个数交换之后,那个数在下一个循环还要判断的
                swap(arr, --more, left);
            } else {
                //如果说其他情况."等于" 直接跳过,并left++,表示下标继续往后走,但是less区域不变这个"等于"就是等于区域
                left++;
            }
        }

        //left和more撞上了.将more最左边的值与参考值交换位置,形成 less|参考值|more 区域
        swap(arr, more, right);

        //返回与参考值相等区域的左边界和右边界
        return new int[]{less + 1, more};
    }


    //数据交换
    private static void swap(int[] arr, int j, int i) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] ints = {3, 4, 5, 3, 5, 1, 6, 4};

        sort(ints);

        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }

}

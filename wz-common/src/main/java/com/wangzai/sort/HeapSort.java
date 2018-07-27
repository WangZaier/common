package com.wangzai.sort;


/**
 * 堆排序
 *
 * @author wangzai
 * @date 2018/5/12 上午1:26
 */
public class HeapSort {


    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }


        //便利数组
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }

        int heapSize = arr.length;//初始的时候为arr的长度
        while (heapSize > 0) {
            //头节点.和尾节点交换,并让size-1
            swap(arr, 0, --heapSize);
            //进行完成,也就是生成大根堆
            heapify(arr, 0, heapSize);
        }

    }


    //堆生成的插入过程(从下至上) .可以认为把大的换到上面去
    //每次插入一个数跟他的父亲节点比较大小.
    //如果子节点比父亲节点大那么交换.交换完以后.子节点下边变换成了父节点那个位置,然后继续(index-1)/2向上跟父节点比.直到比父节点小位置.stop
    public static void heapInsert(int[] arr, int index) {
        //如果当前节点比父节点大
        while (arr[index] > arr[(index - 1) / 2]) {
            //则进行交换位置
            swap(arr, index, (index - 1) / 2);
            //交换后把子节点下标改为父节点的index,然后以这个节点继续向上比较.直到比父节点小
            index = (index - 1) / 2;

        }
    }


    //堆的头尾交换(从上至下) """其实也就是调整交换后的头节点的位置"""
    //当然现在的堆本来已经是父节点必然比子节点大(除了头节点不一定)
    //,给出结尾的size,我们就可以将他们进行交换.
    public static void heapify(int[] arr, int index, int size) {
        //获取当前index的左孩子节点
        int left = index * 2 + 1;


        //形成堆.条件是:遍历时不能超过size边界,因为size边界定下了就不再去动了(最大)
        while (left < size) {
            //首先保证右孩子是否存在(是否越界),然后比较左孩子和右孩子大小
            int largest = left + 1 < size &&
                    arr[left + 1] > arr[left]
                    ? left + 1 : left;

            //让当前节点(父节点)跟左孩子和右孩子中大的一个进行比对
            largest = arr[largest] > arr[index] ? largest : index;

            //如果最大的就是index,那大家就当无事发生
            if (largest == index) {
                break;
            }

            //否则进行交换,larget和index交换
            swap(arr, index, largest);

            //把index变成刚才左右孩子比较出来更大的位置(虽然此时该位置已经和父节点交换数据了)
            //然后以larget位置数据作为父节点往下看,能不能换
            index = largest;

            //左节点修改
            left = index * 2 + 1;

            //接下来继续去遍历.以largest为父节点.跟孩子节点互动
        }

    }


    public static void swap(int[] arr, int i, int j) {
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

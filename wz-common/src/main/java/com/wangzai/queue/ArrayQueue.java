package com.wangzai.queue;

public class ArrayQueue<T> {


    T[] arr;//数据
    int size;//当前存在的数据大小
    int start;//读取
    int end;//写入

    public ArrayQueue(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("init size cloud not less 0");
        }
        this.arr = (T[]) new Object[size];
        this.size = 0;
        this.start = 0;
        this.end = 0;
    }

    public static void main(String[] args) {
        ArrayQueue<Student> stack = new ArrayQueue<>(5);

        stack.push(new Student("wz", 12));
        stack.push(new Student("wz", 13));
        stack.push(new Student("wz", 14));

        System.out.println(stack.poll());
        System.out.println(stack.peek());

        stack.push(new Student("wz", 15));

        System.out.println(stack.poll());
        System.out.println(stack.poll());
        System.out.println(stack.poll());
    }

    public T peek() {
        if (size == 0) {
            return null;
        }
        return arr[start];
    }

    public void push(T data) {
        if (size == arr.length) {
            throw new ArrayIndexOutOfBoundsException("queue is full");
        }
        size++;//存在的数据+1
        arr[end] = data;//赋予数值,
        end = end == arr.length - 1 ? 0 : end + 1;//判断end是否已经到了数组边界,到了的话从头开始,否则下标+1
    }

    public T poll() {
        if (size == 0) {
            throw new ArrayIndexOutOfBoundsException("queue is empty");
        }
        size--;
        int tmp = start;
        start = start == arr.length - 1 ? 0 : start + 1;
        return arr[tmp];
    }


}


package com.wangzai.queue;

/**
 * @author wangzai
 * @date 2018/5/12 下午5:10
 */
public class ArrayStack<T> {

    T[] arr;
    int index;

    //初始化大小
    public ArrayStack(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("init size cloud not less 0");
        }
        this.arr = (T[]) new Object[size];
        this.index = 0;
    }

    public T peek() {
        if (index == 0) {
            return null;
        }
        return arr[index - 1];
    }

    public void push(T data) {
        if (index == arr.length) {
            throw new IllegalArgumentException("stack full");
        }
        //数据加入
        arr[index++] = data;
    }

    public T pop() {
        if (index == 0) {
            throw new ArrayIndexOutOfBoundsException("stack is empty");
        }
        //设置为null,防止对象游离
        arr[index] = null;
        //数据拿出
        return arr[--index];
    }

    public static void main(String[] args) {
        ArrayStack<Student> stack = new ArrayStack<>(5);

        stack.push(new Student("wz",12));
        stack.push(new Student("wz",13));
        stack.push(new Student("wz",14));

        System.out.println(stack.pop());
        System.out.println(stack.peek());

        stack.push(new Student("wz",15));

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }


}


class Student{
    String name;
    Integer age;

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
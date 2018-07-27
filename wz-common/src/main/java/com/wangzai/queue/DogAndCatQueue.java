package com.wangzai.queue;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * 猫狗队列问题
 *
 * @author wangzai
 * @date 2018/5/12 下午11:05
 */
public class DogAndCatQueue {

    //猫队列
    Queue<PetQueue> catQueue;
    //狗队列
    Queue<PetQueue> dogQueue;
    //标记
    Integer index;

    public DogAndCatQueue() {
        catQueue = new LinkedList<>();
        dogQueue = new LinkedList<>();
        index = 0;
    }

    //进队列
    public void push(Pet pet) {
        //如果是狗,进入狗队列
        if (Objects.equals("dog", pet.getType())) {
            dogQueue.add(new PetQueue(pet, index++));
        }else if (Objects.equals("cat", pet.getType())) {
            catQueue.add(new PetQueue(pet, index++));
        }else {
            throw new RuntimeException("not cat or dog");
        }

    }


    //如果需要弹出狗
    public Dog pollDog() {
        if (!dogQueue.isEmpty()) {
            return (Dog) dogQueue.poll().getPet();
        } else {
            throw new RuntimeException("dog is empty");
        }
    }

    //如果需要弹出猫
    public Cat pollCat() {
        if (!catQueue.isEmpty()) {
            return (Cat) catQueue.poll().getPet();
        } else {
            throw new RuntimeException("dog is empty");
        }
    }

    public Pet pollAll() {
        //两者都不为空:比较猫队列最前和狗队列最前index谁更小
        if (!catQueue.isEmpty() && !dogQueue.isEmpty()) {
            if (catQueue.peek().getIndex() < dogQueue.peek().getIndex()) {
                return catQueue.poll().getPet();
            } else {
                return dogQueue.poll().getPet();
            }
        } else if (!dogQueue.isEmpty()) { //上面条件下来两者必有一空.狗不为空直接返回
            return dogQueue.poll().getPet();
        } else if (!catQueue.isEmpty()) {//上面条件下来两者必有一空.猫不为空直接返回
            return catQueue.poll().getPet();
        } else {
            throw new RuntimeException("dog and cat is empty");
        }
    }


    public static void main(String[] args) {
        DogAndCatQueue queue = new DogAndCatQueue();

        queue.push(new Dog());
        queue.push(new Cat());
        queue.push(new Dog());
        queue.push(new Cat());
        queue.push(new Dog());
        queue.push(new Cat());

        System.out.println(queue.pollAll().getType());
        System.out.println(queue.pollAll().getType());
        System.out.println(queue.pollAll().getType());
        System.out.println(queue.pollAll().getType());
        System.out.println(queue.pollAll().getType());
        System.out.println(queue.pollAll().getType());


    }

}

//自定义类用来标记宠物进入队列的索引
class PetQueue {
    private Pet pet;
    private Integer index;

    public PetQueue(Pet pet, Integer index) {
        this.pet = pet;
        this.index = index;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getPetType() {
        return pet.getType();
    }
}

//宠物以及猫狗定义
class Pet {
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

class Dog extends Pet {
    public Dog() {
        setType("dog");
    }
}

class Cat extends Pet {
    public Cat() {
        setType("cat");
    }
}

package com.wangzai.map;

import java.util.Objects;

public class WZHashMap<K, V> implements WZMap<K, V> {


    //链表长度阀值
    static final int TREEIFY_THRESHOLD = 8;
    //初始大小
    private static int DEFAULT_INITIAL_CAPACITY = 16;
    //负载因子
    private static double DEFAULT_LOAD_FACTOR = 0.75f;
    //数据链
    private Entry<K, V>[] table = null;

    //记录使用的节点数
    private int useSize = 0;

    //这个用来记录所有的数据量
    private int size = 0;


    public WZHashMap() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public WZHashMap(int initialCapacity, double defaultLoadFactor) {
        this.DEFAULT_INITIAL_CAPACITY = initialCapacity;
        this.DEFAULT_LOAD_FACTOR = defaultLoadFactor;
        this.table = new Entry[DEFAULT_INITIAL_CAPACITY];
    }

    //获取hash
    static int hash(Object key) {
        int hash;
        return key == null ? 0 : (hash = key.hashCode()) ^ hash >>> 16;
    }

    //获取数据
    @Override
    public V get(K k) {
        int index = getIndex(k, table.length);
        if (table[index] == null) {
            throw new NullPointerException();
        }

        //获取value
        V value = getValueInEntry(table[index], k);
        if (value == null) {
            throw new NullPointerException();
        }
        return value;
    }

    //通过key在连表中寻找对象
    private V getValueInEntry(Entry<K, V> entry, K k) {
        if (Objects.equals(k, entry.getKey())) {
            //验证key是否相等
            return entry.getValue();
        } else if (entry.next != null) {
            //下一节点不为空则继续调用验证下一个key
            return getValueInEntry(entry.next, k);
        }
        //当自己本身key与相应key无法匹配.有没有下一个节点.则返回null
        return null;
    }

    //添加数据
    @Override
    public V put(K key, V value) {
        //如果使用大小达到了数据大小*负载因子,则进行扩容
        if (useSize > DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR) {
            resize();
        }
        //如果链表长度达到阀值,进行扩容
        if (size != 0 && useSize != 0) {
            //我们之前说过.所有的数据会几乎均匀分布在散列表上
            // 我们就用size,也就是总量除以使用的节点数.如果平均链表长度过8则resize
            if (size / useSize >= 8) {
                resize();
            }
        }
        //获取需要存储的下标
        int index = getIndex(key, table.length);

        Entry<K, V> entry = table[index];
        if (entry == null) {
            //如果等于null直接存储
            table[index] = new Entry<>(key, value, null);
            useSize++;
            size++;
        } else {
            //entry不为空
            table[index] = new Entry<>(key, value, entry);
            size++;
        }

        return table[index].getValue();
    }

    //通过key和数组大小确定该数据应该存放在哪里
    private int getIndex(K key, int length) {
        int m = length - 1;
        int index = hash(key) & m;
        return index;
    }

    //获取size
    @Override
    public int size() {
        return size;
    }


    //扩容方法
    private void resize() {
        //新数组
        Entry<K, V>[] newTable = new Entry[2 * DEFAULT_INITIAL_CAPACITY];

        useSize = 0;
        //将原有数据拷贝至新数据
        transfer(newTable);
        //更新引用
        table = newTable;
        //数据默认容量扩大
        DEFAULT_INITIAL_CAPACITY *= 2;
    }

    //数组拷贝
    private void transfer(Entry<K, V>[] newTable) {

        //引用原来数组
        Entry[] src = table;
        //获取新数组长度
        int newCapacity = newTable.length;
        //遍历原来数组
        for (int j = 0; j < src.length; j++) {
            //取得原来Entry数组元素
            Entry<K, V> entry = src[j];
            //如果这个元素不为空
            if (entry != null) {
                //释放原来Entry数组的对象引用
                src[j] = null;
                do {
                    //获取当前元素的next
                    Entry<K, V> next = entry.next;
                    //以当前元素key与新数组大小确定下标
                    int index = getIndex(entry.getKey(), newCapacity);
                    //实现链表结构，新加入的放在链头，之前的的数据放在链尾,把entry放进去
                    entry.next = newTable[index];
                    if (newTable[index] == null) {
                        useSize++;
                    }
                    //然后把这个链表(entry)重新放到newtable的i位置
                    newTable[index] = entry;
                    //访问下一个Entry链上的元素
                    entry = next;
                } while (entry != null);
            }
        }

    }


    public class Entry<K, V> implements WZMap.Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;

        public Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }
    }
}

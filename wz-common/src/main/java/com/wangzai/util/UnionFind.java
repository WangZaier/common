package com.wangzai.util;

import java.util.HashMap;
import java.util.List;

/**
 * 并查集
 *
 * @author wangzai
 * @date 2018/5/18 上午6:20
 */
public class UnionFind {


    private class UnionFindSet {
        HashMap<Data, Data> fatherMap;
        HashMap<Data, Integer> sizeMap;

        public UnionFindSet(List<Data> nodes) {
            fatherMap = new HashMap<>();
            sizeMap = new HashMap<>();
            makeSets(nodes);
        }

        private void makeSets(List<Data> nodes) {
            fatherMap.clear();
            sizeMap.clear();
            for (Data node : nodes) {
                fatherMap.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        private Data findDaibiao(Data node) {
            Data father = fatherMap.get(node);
            if (father != node) {
                father = findDaibiao(father);
            }
            fatherMap.put(node, father);
            return father;
        }

        public boolean isSameSet(Data a, Data b) {
            return findDaibiao(a) == findDaibiao(b);
        }

        public void union(Data a, Data b) {
            if (a == null || b == null) {
                return;
            }

            Data aHead = findDaibiao(a);
            Data bHead = findDaibiao(b);

            if (aHead != bHead) {
                int aSetSize = sizeMap.get(aHead);
                int bSetSize = sizeMap.get(bHead);

                if (aSetSize <= bSetSize) {
                    fatherMap.put(aHead, bHead);
                    sizeMap.put(bHead, aSetSize + bSetSize);
                } else {
                    fatherMap.put(bHead, aHead);
                    sizeMap.put(aHead, aSetSize + bSetSize);
                }

            }
        }


    }


    private class Data {
    }


}

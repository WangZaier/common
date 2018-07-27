package com.wangzai.lambda;


import java.util.function.Consumer;
import java.util.function.Function;

@FunctionalInterface
public interface LambdaDemo {
    int s(int a);

    default int k() {
        return 1;
    }
}

class StaticFunc {
    public static void staticFunc(Integer data) {
        System.out.println(data);
    }
}

class NoStaticFunc {
    public String func(Integer data) {
        return "data:" + data;
    }
}

class test {
    public static void main(String[] args) {
        LambdaDemo lambdaDemo = (i) -> {
            return 1;
        };

        int k = lambdaDemo.k();
        System.out.println(k);

        int s = lambdaDemo.s(1);
        System.out.println(s);


        Consumer<Integer> staticFunc = StaticFunc::staticFunc;
        staticFunc.accept(2);

        NoStaticFunc noStaticFunc = new NoStaticFunc();
        Function<Integer, String> function = noStaticFunc::func;
        String apply = function.apply(1);
        System.out.println(apply);

    }
}

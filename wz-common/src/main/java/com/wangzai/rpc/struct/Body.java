package com.wangzai.rpc.struct;


import java.io.Serializable;

/**
 * 用于传递的调用信息
 *
 * @author wangzai
 * @date 2018/6/20 下午5:30
 */
public class Body implements Serializable {

    //全限定类名
    private String className;

    //调用的方法
    private String methodName;

    //方法参数类型列表
    private Class<?>[] parameterTypes;

    //参数列表
    private Object[] paramValues;

    public Body() {
    }

    public Body(String className, String methodName, Class<?>[] parameterTypes, Object[] paramValues) {
        this.className = className;
        this.methodName = methodName;
        this.parameterTypes = parameterTypes;
        this.paramValues = paramValues;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public Object[] getParamValues() {
        return paramValues;
    }

    public void setParamValues(Object[] paramValues) {
        this.paramValues = paramValues;
    }
}

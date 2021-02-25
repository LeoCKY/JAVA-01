package com.l9.q1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class PeopleHandler implements InvocationHandler {

    private Object delegate;

    public Object bind(Object delegate) {
        this.delegate = delegate;
        return Proxy.newProxyInstance(
                delegate.getClass().getClassLoader(),
                delegate.getClass().getInterfaces(),
                this);
    }


    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("method start... " + method.getName() + "\t" + args[0]);
        Object obj = method.invoke(delegate, args);
        System.out.println("method ends... " + method.getName() + "\t" + obj.toString());
        return obj;
    }

}

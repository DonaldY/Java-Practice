package com.donaldy.jvm.metaspace;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

/**
 * @author donald
 * @date 2021/04/28
 */
public class CarDemo {

    static class Car {
        public void run() {
            System.out.println("车子启动，运行...");
        }
    }

    public static void main(String[] args) {
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(Car.class);
            enhancer.setUseCache(false);
            enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> {
                if (method.getName().equals("run")) {
                    System.out.println("车子启动之前，安全检查中...");
                    return methodProxy.invokeSuper(o, objects);
                }
                return methodProxy.invokeSuper(o, objects);
            });

            Car car = (Car) enhancer.create();
            car.run();
        }
    }
}

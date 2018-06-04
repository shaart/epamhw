package com.epam;

import java.lang.reflect.Method;
import org.springframework.beans.factory.support.MethodReplacer;

public class E {

    public void hello() {
        System.out.println("Default method at E");
    }
}

class Replacecer implements MethodReplacer {

    @Override
    public Object reimplement(Object o, Method method, Object[] objects) {
        System.out.println("Replaced method at E");

        return o;
    }
}
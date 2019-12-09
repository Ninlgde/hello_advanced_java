package com.ninlgde.jvm.clazz;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;

import static java.lang.invoke.MethodHandles.lookup;

public class MethodHandleTest2 {
    class GrandFather {
        void thinking() {
            System.out.println("I am a grandfather");
        }
    }

    class Father extends GrandFather {
        void thinking() {
            System.out.println("I am father");
        }
    }

    class Son extends Father {
        void thinking() {
            try {
                MethodType mt = MethodType.methodType(void.class);
                MethodHandle mh = lookup().findSpecial(GrandFather.class, "thinking", mt, getClass());
                mh.invoke(this);
            } catch (Throwable e) {

            }
        }
    }

    public static void main(String[] args) {
        (new MethodHandleTest2().new Son()).thinking();
    }
}

package com.ninlgde.advanced.annotation;

import java.lang.annotation.Annotation;
import java.util.Arrays;

@DocumentA
class A2 {
}

@DocumentB
public class DocumentDemo2 extends A2 {
    public static void main(String[] args) {
        Class<?> clazz = DocumentDemo2.class;

        DocumentA documentA = clazz.getAnnotation(DocumentA.class);
        System.out.println("A:" + documentA);

        Annotation[] annotations = clazz.getAnnotations();
        System.out.println("annotations:" + Arrays.toString(annotations));

        Annotation[] annotations2 = clazz.getDeclaredAnnotations();
        System.out.println("annotations2:" + Arrays.toString(annotations2));

        boolean b = clazz.isAnnotationPresent(DocumentA.class);
        System.out.println("b:" + b);
    }
}

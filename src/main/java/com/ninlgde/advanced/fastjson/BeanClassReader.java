package com.ninlgde.advanced.fastjson;

import org.objectweb.asm.*;

import java.io.IOException;
import java.io.InputStream;

import static org.objectweb.asm.Opcodes.ASM5;

public class BeanClassReader {
    public static void main(String[] args) throws IOException {
        Class clazz = ReflectTest.Result.class;
//        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(clazz.getName().replace('.', '/'));
        ClassReader cr = new ClassReader(clazz.getName());
        ClassWriter cw = new ClassWriter(0);
        ClassVisitor cv = new ClassVisitor(ASM5, cw) {
            @Override
            public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
                System.out.println("filed: " + name);
                FieldVisitor fv = super.visitField(access, name, descriptor, signature, value);

                AnnotationVisitor av = fv.visitAnnotation(descriptor, true);

                return fv;
            }

            @Override
            public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
                System.out.println("method: " + name);
                MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions);
                AnnotationVisitor av = mv.visitAnnotationDefault();
                return mv;
            }
        };
        cr.accept(cv, ClassReader.SKIP_CODE | ClassReader.SKIP_DEBUG);
    }
}

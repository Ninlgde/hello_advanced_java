package com.ninlgde.advanced.fastjson.asm;

import org.objectweb.asm.*;

import java.io.IOException;
import java.io.InputStream;

import static org.objectweb.asm.Opcodes.ASM5;

public class ASMReadFieldMethod {
    public static void main(String[] args) throws IOException {
//        ClassLoader cl = MyMain.class.getClassLoader();
//        InputStream is = cl.getResourceAsStream(MyMain.class.getName());

        byte[] bytes = ClassFileUtils.readFile("./MyMain.class");

        ClassReader cr = new ClassReader(bytes);
        ClassWriter cw = new ClassWriter(0);
        ClassVisitor cv = new ClassVisitor(ASM5, cw) {
            @Override
            public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
                System.out.println("filed: " + name);
                FieldVisitor fv = super.visitField(access, name, descriptor, signature, value);

//                fv.visitAnnotation()
                return fv;
            }

            @Override
            public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
                System.out.println("method: " + name);
                return super.visitMethod(access, name, descriptor, signature, exceptions);
            }
        };
        cr.accept(cv, ClassReader.SKIP_CODE | ClassReader.SKIP_DEBUG);
    }
}

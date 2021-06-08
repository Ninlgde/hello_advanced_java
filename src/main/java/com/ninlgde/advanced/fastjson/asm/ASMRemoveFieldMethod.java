package com.ninlgde.advanced.fastjson.asm;

import org.objectweb.asm.*;

import java.io.IOException;
import java.io.InputStream;

import static org.objectweb.asm.Opcodes.ASM7;

public class ASMRemoveFieldMethod {
    public static void main(String[] args) throws IOException {
        byte[] bytes = ClassFileUtils.readFile("./MyMain3.class");

        ClassReader cr = new ClassReader(bytes);
        ClassWriter cw = new ClassWriter(0);
        ClassVisitor cv = new ClassVisitor(ASM7, cw) {
            @Override
            public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
                super.visit(version, access, "MyMain4", signature, superName, interfaces); // 更改类名否则无法运行
            }
            @Override
            public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
                if ("b".equals(name)) {
                    return null;
                }
                return super.visitField(access, name, desc, signature, value);
            }

            @Override
            public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
                if ("xyz".equals(name)) {
                    return null;
                }
                return super.visitMethod(access, name, desc, signature, exceptions);
            }
        };
        cr.accept(cv, ClassReader.SKIP_CODE | ClassReader.SKIP_DEBUG);
        byte[] bytesModified = cw.toByteArray();
        ClassFileUtils.writeFile("./MyMain4.class", bytesModified);
    }
}

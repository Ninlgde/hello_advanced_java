package com.ninlgde.advanced.fastjson.asm;

import org.objectweb.asm.*;

import java.io.IOException;

import static org.objectweb.asm.Opcodes.ASM7;

public class ASMAddField {
    public static void main(String[] args) throws IOException {
        byte[] bytes = ClassFileUtils.readFile("./MyMain.class");

        ClassReader cr = new ClassReader(bytes);
        ClassWriter cw = new ClassWriter(0);
        ClassVisitor cv = new ClassVisitor(ASM7, cw) {
            @Override
            public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
                super.visit(version, access, "MyMain2", signature, superName, interfaces); // 更改类名否则无法运行
            }
            @Override
            public void visitEnd() {
                super.visitEnd();
                FieldVisitor fv = cv.visitField(Opcodes.ACC_PUBLIC, "xyz", "Ljava/lang/String;", null, null);
                if (fv != null) fv.visitEnd();
            }
        };
        cr.accept(cv, ClassReader.SKIP_CODE | ClassReader.SKIP_DEBUG);
        byte[] bytesModified = cw.toByteArray();
        ClassFileUtils.writeFile("./MyMain2.class", bytesModified);
    }
}

package com.ninlgde.advanced.fastjson.asm;

import org.objectweb.asm.*;

import java.io.IOException;

import static org.objectweb.asm.Opcodes.ASM7;

public class ASMModifyMethod {
    public static void main(String[] args) throws IOException {
        byte[] bytes = ClassFileUtils.readFile("./MyMain.class");

        ClassReader cr = new ClassReader(bytes);
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        ClassVisitor cv = new ClassVisitor(ASM7, cw) {

            @Override
            public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {

                if ("test01".equals(name)) {
                    // 删除 foo 方法
                    return null;
                }
                return super.visitMethod(access, name, desc, signature, exceptions);
            }

            @Override
            public void visitEnd() {
                // 新增 foo 方法
                MethodVisitor mv = cv.visitMethod(Opcodes.ACC_PUBLIC, "test01", "(I)I", null, null);

                mv.visitCode();
                mv.visitVarInsn(Opcodes.ILOAD, 1);
                mv.visitIntInsn(Opcodes.BIPUSH, 100);
                mv.visitInsn(Opcodes.IADD);
                mv.visitInsn(Opcodes.IRETURN);
                // 触发计算
                mv.visitMaxs(0, 0);
                mv.visitEnd();
            }
        };

        cr.accept(cv, 0);
        byte[] bytesModified = cw.toByteArray();
        ClassFileUtils.writeFile("./MyMain.class", bytesModified);
    }
}

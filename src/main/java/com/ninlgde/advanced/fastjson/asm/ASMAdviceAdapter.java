package com.ninlgde.advanced.fastjson.asm;

import org.objectweb.asm.commons.AdviceAdapter;
import org.objectweb.asm.*;

import java.io.IOException;

public class ASMAdviceAdapter {
    public static void main(String[] args) throws IOException {
        byte[] bytes = ClassFileUtils.readFile("./MyMain.class");

        ClassReader cr = new ClassReader(bytes);
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        ClassVisitor cv = new ClassVisitor(Opcodes.ASM7, cw) {
            @Override
            public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
                MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions);
                if (!"foo".equals(name)) return mv;
                return new AdviceAdapter(Opcodes.ASM7, mv, access, name, descriptor) {
                    @Override
                    protected void onMethodEnter() {
                        // 新增 System.out.println("enter " +  name);
                        super.onMethodEnter();
                        mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                        mv.visitLdcInsn("enter " + name);
                        mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
                    }

                    @Override
                    protected void onMethodExit(int opcode) {
                        // 新增 System.out.println("[normal,err] exit " +  name);
                        super.onMethodExit(opcode);
                        mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                        if (opcode == Opcodes.ATHROW) {
                            mv.visitLdcInsn("err exit " + name);
                        } else {
                            mv.visitLdcInsn("normal exit " + name);
                        }
                        mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
                    }
                };
            }
        };

        cr.accept(cv, 0);
        byte[] bytesModified = cw.toByteArray();
        ClassFileUtils.writeFile("./MyMain.class", bytesModified);
    }
}

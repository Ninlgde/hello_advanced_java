package com.ninlgde.advanced.fastjson.asm;

import org.objectweb.asm.*;
import org.objectweb.asm.commons.AdviceAdapter;

import java.io.IOException;

public class ASMAddTryCatch {
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
                    Label startLabel = new Label();

                    @Override
                    protected void onMethodEnter() {
                        super.onMethodEnter();
                        mv.visitLabel(startLabel);

                        mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                        mv.visitLdcInsn("enter " + name);
                        mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
                    }

                    @Override
                    public void visitMaxs(int maxStack, int maxLocals) {
                        // 生成异常表
                        Label endLabel = new Label();
                        mv.visitTryCatchBlock(startLabel, endLabel, endLabel, null);
                        mv.visitLabel(endLabel);

                        // 生成异常处理代码块
                        finallyBlock(ATHROW);
                        mv.visitInsn(ATHROW);
                        super.visitMaxs(maxStack, maxLocals);
                    }

                    private void finallyBlock(int athrow) {
                        mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                        if (athrow == Opcodes.ATHROW) {
                            mv.visitLdcInsn("err exit " + name);
                        } else {
                            mv.visitLdcInsn("normal exit " + name);
                        }
                        mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
                    }


                    @Override
                    protected void onMethodExit(int opcode) {
                        super.onMethodExit(opcode);
                        // 处理正常返回的场景
                        if (opcode != ATHROW) finallyBlock(opcode);
                    }
                };
            }
        };

        cr.accept(cv, 0);
        byte[] bytesModified = cw.toByteArray();
        ClassFileUtils.writeFile("./MyMain.class", bytesModified);
    }
}

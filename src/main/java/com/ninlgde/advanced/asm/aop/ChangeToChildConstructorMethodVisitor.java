package com.ninlgde.advanced.asm.aop;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class ChangeToChildConstructorMethodVisitor extends MethodVisitor {

    private String superClassName;

    public ChangeToChildConstructorMethodVisitor(int api, MethodVisitor methodVisitor, String superClassName) {
        super(api, methodVisitor);
        this.superClassName = superClassName;
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name,
                                String desc, boolean isInterfaces) {
        // 调用父类的构造函数时
        if (opcode == Opcodes.INVOKESPECIAL && name.equals("<init>")) {
            owner = superClassName;
        }
        super.visitMethodInsn(opcode, owner, name, desc, isInterfaces);// 改写父类为 superClassName
    }
}

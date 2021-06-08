package com.ninlgde.advanced.asm.aop;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class AddSecurityCheckMethodVisitor extends MethodVisitor {

    public AddSecurityCheckMethodVisitor(int api, MethodVisitor methodVisitor) {
        super(api, methodVisitor);
    }

    @Override
    public void visitCode() {
        visitMethodInsn(Opcodes.INVOKESTATIC, SecurityChecker.class.getName().replace(".", "/"),
                "checkSecurity", "()V", false);
    }
}

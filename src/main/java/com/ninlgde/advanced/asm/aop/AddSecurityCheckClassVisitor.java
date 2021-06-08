package com.ninlgde.advanced.asm.aop;

import org.objectweb.asm.*;

public class AddSecurityCheckClassVisitor extends ClassVisitor {

    private String enhancedSuperName = null;
    private int api;

    public AddSecurityCheckClassVisitor(int api, ClassVisitor classVisitor) {
        super(api, classVisitor);
        this.api = api;
    }

    @Override
    public void visit(final int version, final int access, final String name,
                      final String signature, final String superName,
                      final String[] interfaces) {
        String enhancedName = name + "$EnhancedByASM";  // 改变类命名
        enhancedSuperName = name; // 改变父类，这里是”Account”
        super.visit(version, access, enhancedName, signature,
                enhancedSuperName, interfaces);
    }

    @Override
    public MethodVisitor visitMethod(final int access, final String name,
                                     final String desc, final String signature, final String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
        MethodVisitor wrappedMv = mv;
        if (mv != null) {
            if (name.equals("operation")) {
                wrappedMv = new AddSecurityCheckMethodVisitor(this.api, mv);
            } else if (name.equals("<init>")) {
                wrappedMv = new ChangeToChildConstructorMethodVisitor(this.api, mv,
                        enhancedSuperName);
            }
        }
        return wrappedMv;
    }
}

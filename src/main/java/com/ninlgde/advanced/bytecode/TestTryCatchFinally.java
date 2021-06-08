package com.ninlgde.advanced.bytecode;

import java.io.IOException;

public class TestTryCatchFinally {
    private void tryItOut1() throws IOException {

    }

    private void handleException(IOException e) {

    }

    private void handleFinally() {

    }

    private void process() {
        try {
            tryItOut1();
        } catch (IOException e) {
            handleException(e);
        } finally {
            handleFinally();
        }
    }

    public static int func1() {
        try {
            return 0;
        } catch (Exception e){
            return 1;
        } finally {
            return 2;
        }
    }

    public static int func2() {
        try {
            int a = 1 / 0;
            return 0;
        } catch (Exception e) {
            return 1;
        } finally {
            return 2;
        }
    }

    public static int func3() {
        try {
            int a = 1 / 0;
            return 0;
        } catch (Exception e) {
            int b = 1 / 0;
        } finally {
            return 2;
        }
    }

    public static void main(String[] args) {
        new TestTryCatchFinally().process();
    }
}

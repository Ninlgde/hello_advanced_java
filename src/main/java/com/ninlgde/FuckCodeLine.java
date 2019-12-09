package com.ninlgde;

public class FuckCodeLine {
    private int _a = 0;

    public FuckCodeLine() {
    }

    public FuckCodeLine(int _a) {
        this._a = _a;
    }

    public int get_a() {
        return _a;
    }

    public void set_a(int _a) {
        this._a = _a;
    }

    private FuckCodeLine a() {
        this._a += 1;
        return this;
    }

    private FuckCodeLine b() {
        this._a += 1;
        return this;
    }

    private FuckCodeLine c() {
        this._a += 1;
        return this;
    }

    private FuckCodeLine d(int delta) {
        this._a += delta;
        return this;
    }

    public static void main(String[] args) {
        // style 0
        System.out.println((new FuckCodeLine(3).a().b().c().d(5).get_a()));

        // style 1
        FuckCodeLine a = new FuckCodeLine(3);
        int b = a.a().b().c().d(5).get_a();
        System.out.println(b);

        // style 2
        FuckCodeLine c = null;
        c = new FuckCodeLine();
        c.set_a(3);
        c.a();
        c.b();
        c.c();
        c.d(5);
        int d;
        d = c.get_a();
        System.out.println(d);
    }
}

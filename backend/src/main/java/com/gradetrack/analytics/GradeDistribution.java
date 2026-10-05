package com.gradetrack.analytics;

public class GradeDistribution {

    private long aPlus;
    private long a;
    private long b;
    private long c;
    private long d;
    private long fail;

    public GradeDistribution() {
    }

    public GradeDistribution(
            long aPlus,
            long a,
            long b,
            long c,
            long d,
            long fail) {

        this.aPlus = aPlus;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.fail = fail;
    }

    public long getAPlus() {
        return aPlus;
    }

    public void setAPlus(long aPlus) {
        this.aPlus = aPlus;
    }

    public long getA() {
        return a;
    }

    public void setA(long a) {
        this.a = a;
    }

    public long getB() {
        return b;
    }

    public void setB(long b) {
        this.b = b;
    }

    public long getC() {
        return c;
    }

    public void setC(long c) {
        this.c = c;
    }

    public long getD() {
        return d;
    }

    public void setD(long d) {
        this.d = d;
    }

    public long getFail() {
        return fail;
    }

    public void setFail(long fail) {
        this.fail = fail;
    }
}
package io.can.unittestingdemo.project.enums;

public enum Term {
    FALL(9), SPRING(2), SUMMER(6);

    private int startMonth;

    Term(int startMonth) {
        this.startMonth = startMonth;
    }

    public int getStartMonth() {
        return startMonth;
    }
}

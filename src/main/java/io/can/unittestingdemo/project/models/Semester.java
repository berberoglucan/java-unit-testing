package io.can.unittestingdemo.project.models;

import io.can.unittestingdemo.project.enums.Term;

import java.time.LocalDate;
import java.util.Objects;

import static io.can.unittestingdemo.project.enums.Term.*;

public class Semester {

    private int year;
    private Term term;

    public Semester(LocalDate localDate) {
        this.year = localDate.getYear();
        this.term = term(localDate.getMonthValue());
    }

    public Semester() {
        LocalDate now = LocalDate.now();
        this.year = now.getYear();
        this.term = term(now.getMonthValue());
    }

    private Term term(int monthValue) {

        if (monthValue >= FALL.getStartMonth() || monthValue < SPRING.getStartMonth()) {
            return FALL;
        } else if (monthValue >= SPRING.getStartMonth() && monthValue < SUMMER.getStartMonth()) {
            return SPRING;
        }

        return SUMMER;
    }

    public boolean isActive() {
        return this.equals(new Semester());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Semester semester = (Semester) o;
        return year == semester.getYear() &&
                term == semester.getTerm();
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, term);
    }

    @Override
    public String toString() {
        return "Semester{" +
                "year=" + year +
                ", term=" + term +
                '}';
    }

    public int getYear() {
        return year;
    }

    public Term getTerm() {
        return term;
    }

}

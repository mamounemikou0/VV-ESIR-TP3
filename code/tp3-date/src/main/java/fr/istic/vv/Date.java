package fr.istic.vv;

import java.util.Objects;

public class Date implements Comparable<Date> {
    private int day;
    private int month;
    private int year;

    // Tableau pour les jours dans chaque mois
    private static final int[] DAYS_IN_MONTH = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

    // Constructeur qui valide la date et lève une exception en cas d'invalidité
    public Date(int day, int month, int year) {
        if (!isValidDate(day, month, year)) {
            throw new IllegalArgumentException("Invalid date");
        }
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public static boolean isValidDate(int day, int month, int year) {
        if (year < 1) return false;  // Restriction aux années positives 
        if (month < 1 || month > 12) return false;  // Mois valide
        if (day < 1 || day > daysInMonth(month, year)) return false;  // Jour valide dans le mois
        return true;
    }

    // Méthode qui détermine si une année est bissextile
    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    // Méthode pour obtenir la date du jour suivant
    public Date nextDate() {
        int newDay = day + 1;
        int newMonth = month;
        int newYear = year;

        if (newDay > daysInMonth(month, year)) {
            newDay = 1;
            newMonth++;
        }
        if (newMonth > 12) {
            newMonth = 1;
            newYear++;
        }

        return new Date(newDay, newMonth, newYear);
    }

    // Méthode pour obtenir la date du jour précédent
    public Date previousDate() {
        int newDay = day - 1;
        int newMonth = month;
        int newYear = year;

        if (newDay < 1) {
            newMonth--;
            if (newMonth < 1) {
                newMonth = 12;
                newYear--;
            }
            newDay = daysInMonth(newMonth, newYear);
        }

        return new Date(newDay, newMonth, newYear);
    }

    public int compareTo(Date other) {
        if (other == null) {
            throw new NullPointerException("Other date is null");
        }
        int yearComparison = Integer.compare(this.year, other.year);
        if (yearComparison != 0) return yearComparison;

        int monthComparison = Integer.compare(this.month, other.month);
        if (monthComparison != 0) return monthComparison;

        return Integer.compare(this.day, other.day);
    }

    private static int daysInMonth(int month, int year) {
        if (month == 2 && isLeapYear(year)) {
            return 29;
        }
        return DAYS_IN_MONTH[month - 1];
    }

    @Override
    public String toString() {
        return String.format("%02d/%02d/%04d", day, month, year);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Date)) return false;
        Date other = (Date) obj;
        return this.day == other.day && this.month == other.month && this.year == other.year;
    }

    @Override
    public int hashCode() {
        return Objects.hash(day, month, year);
    }
    }

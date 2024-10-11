# Test the Date class

Implement a class `Date` with the interface shown below:

```java
class Date implements Comparable<Date> {

    public Date(int day, int month, int year) { ... }

    public static boolean isValidDate(int day, int month, int year) { ... }

    public static boolean isLeapYear(int year) { ... }

    public Date nextDate() { ... }

    public Date previousDate { ... }

    public int compareTo(Date other) { ... }

}
```

The constructor throws an exception if the three given integers do not form a valid date.

`isValidDate` returns `true` if the three integers form a valid year, otherwise `false`.

`isLeapYear` says if the given integer is a leap year.

`nextDate` returns a new `Date` instance representing the date of the following day.

`previousDate` returns a new `Date` instance representing the date of the previous day.

`compareTo` follows the `Comparable` convention:

* `date.compareTo(other)` returns a positive integer if `date` is posterior to `other`
* `date.compareTo(other)` returns a negative integer if `date` is anterior to `other`
* `date.compareTo(other)` returns `0` if `date` and `other` represent the same date.
* the method throws a `NullPointerException` if `other` is `null` 

Design and implement a test suite for this `Date` class.
You may use the test cases discussed in classes as a starting point. 
Also, feel free to add any extra method you may need to the `Date` class.


Use the following steps to design the test suite:

1. With the help of *Input Space Partitioning* design a set of initial test inputs for each method. Write below the characteristics and blocks you identified for each method. Specify which characteristics are common to more than one method.
2. Evaluate the statement coverage of the test cases designed in the previous step. If needed, add new test cases to increase the coverage. Describe below what you did in this step.
3. If you have in your code any predicate that uses more than two boolean operators check if the test cases written to far satisfy *Base Choice Coverage*. If needed add new test cases. Describe below how you evaluated the logic coverage and the new test cases you added.
4. Use PIT to evaluate the test suite you have so far. Describe below the mutation score and the live mutants. Add new test cases or refactor the existing ones to achieve a high mutation score.

Use the project in [tp3-date](../code/tp3-date) to complete this exercise.

# Answer
Identify Characteristics and Blocks
## 1. 
### Constructor & isValidDate (int day, int month, int year)

#### Characteristics: 

    day: Day of the month (1–31 depending on the month and leap year)
    month: Month of the year (1–12)
    year: Year (positive integers, checking for leap years)

#### Blocks:
**Valid blocks:**   

    Valid day for each month and leap year (e.g., Feb 29 only valid in leap years).
    Valid month (1–12).
    Positive integer year.   
    
**Invalid blocks:**

    Invalid day for the month (e.g., day > 28 for February in a non-leap year).
    Invalid month (outside 1–12).
    Negative or zero year.
    
### isLeapYear (int year)
#### Characteristics:   
    
    year: Year to check for leap year status.

#### Blocks:
    
**Valid blocks:**
    Divisible by 4 but not by 100 (leap year).
    Divisible by 400 (leap year).
    Not divisible by 4 (non-leap year).
    Divisible by 100 but not by 400 (non-leap year).

### nextDate and previousDate

##### Characteristics:

**Transitions between:**

    End of month to next month.
    End of year to next year.
    Leap year February to March.
    December 31 to January 1 (next year).
    Beginning of month to previous month.
    Beginning of year to previous year.
    Leap year March 1 to February 29.   
    
#### Blocks:

    Regular day transitions within the month.
    Month-to-month transitions.
    Year transitions.
    Leap year transitions.
    

### compareTo (Date other)

#### Characteristics: 

    other: Date object to compare.   
    
#### Blocks:   

**Valid blocks:**

    Dates with the same year, month, day (should return 0).
    Dates where the current object is earlier (should return negative).
    Dates where the current object is later (should return positive).
    
**Invalid blocks:**

    null as other (should throw NullPointerException).

## 2. Evaluate the statement coverage.


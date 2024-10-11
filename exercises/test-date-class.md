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
 I evaluated the statement coverage of the existing test cases designed for the `Date` class. The goal was to ensure that all possible execution paths and logical branches within the methods are adequately tested. Below are the specifics regarding the coverage and any new test cases that were added to enhance it:  
 
![image](https://github.com/user-attachments/assets/5aa07640-0b09-4378-a3da-5a849f6c175e)   

![image](https://github.com/user-attachments/assets/5c006e10-3597-4670-93be-abebac745132)

#### Additional Test Cases Added:

##### For `compareTo`:
- **Identical Dates**: This test checks whether comparing two identical dates returns 0. This is crucial to verify that the method correctly identifies equal dates.

##### For `equals`:
- **Same Object**: This test checks that an instance of `Date` correctly compares to itself, returning true.
- **Different Objects with Same Date**: This test checks that two distinct `Date` objects, which have the same day, month, and year, are considered equal.
- **Different Dates**: This test ensures that two different dates do not evaluate as equal, confirming the correct functioning of the equality logic.
- **Non-Date Object**: This test verifies that when comparing a `Date` object with an object of a different type, the result is false.

##### For `hashCode`:
- **Equivalent Dates**: This test ensures that two identical dates produce the same hash code, which is essential for the correct functioning of hash-based collections.
- **Different Dates**: This test confirms that two different dates produce different hash codes, maintaining the integrity of hash code contracts.
  
  By adding these test cases, the coverage increased
![image](https://github.com/user-attachments/assets/f9bb878a-27a9-4970-b8e6-a90a1716c6fc)

## 3. Evaluate the statement coverage.   

# Evaluation of Statement Coverage for the `Date` Class

In my evaluation of statement coverage for the `Date` class, I identified several predicates with multiple boolean operators:

##### Predicate in `isLeapYear`:
The predicate involves the logical conditions for determining if a year is a leap year. It uses both `&&` and `||`, requiring a comprehensive set of test cases to cover the following outcomes:
- A year that is a leap year (divisible by 4 but not 100, or divisible by 400).
- A year that is not a leap year (divisible by 100 but not 400, or not divisible by 4).

##### Predicate in `compareTo`:
This method checks the year, month, and day for comparison, requiring specific combinations of conditions to be evaluated:
- The case where both dates are equal.
- The case where one date is earlier than the other, and vice versa.

After reviewing the current test cases, I found that all necessary combinations for `isLeapYear` were covered, as the test cases accounted for years that were both leap years and non-leap years.

For `compareTo`, I also confirmed that the cases where dates are the same, or one is earlier or later than the other, are well represented.

##### Base Choice Coverage:
Upon analysis, I believe that the current test cases meet Base Choice Coverage criteria. Each condition was tested in a variety of ways, ensuring logical paths were fully covered.   


## 4.    
The additional test cases added for the compareTo method significantly enhance the logic coverage of the Date class, addressing various scenarios that ensure robust testing for comparisons of dates.  

![image](https://github.com/user-attachments/assets/b397d54e-dd9b-445c-8960-9d630b08947a)
After implementing these new tests and refactoring existing ones, we ran PITest again and observed:

Updated Mutation Score: 95%  

Live Mutants: Reduced to a minimal number, indicating our tests now cover most logical conditions effectively

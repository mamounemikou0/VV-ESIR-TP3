package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {

    // Test pour la méthode isValidDate
    @Test
    public void testIsValidDate() {
        // Valid leap year dates
        assertTrue(Date.isValidDate(29, 2, 2020));  // Feb 29 valid in a leap year
        // Invalid leap year dates
        assertFalse(Date.isValidDate(29, 2, 2021));  // Not a leap year, Feb 29 invalid
        // valid days for specific months
        assertTrue(Date.isValidDate(31, 1, 2020));  // January 31 is valid
        // Invalid days for specific months
        assertFalse(Date.isValidDate(31, 4, 2020));  // April don't have 31 days
        // Invalid month
        assertFalse(Date.isValidDate(15, 13, 2020));  // Invalid month 13
        assertFalse(Date.isValidDate(15, 10, -1));  // Invalid year -1
    }

    // Test pour la méthode isLeapYear
    @Test
    public void testIsLeapYear() {
        assertTrue(Date.isLeapYear(2020));  // 2020 is a leap year
        assertTrue(Date.isLeapYear(2000));  // 2000 is a leap year 
        assertFalse(Date.isLeapYear(1900));  // 1900 is not a leap year 
        assertFalse(Date.isLeapYear(2019));  // 2019 is not a leap year
    }

    // Test pour la méthode nextDate
    @Test
    public void testNextDate() {
        Date date1 = new Date(1, 1, 2020);
        assertEquals(new Date(2, 1, 2020), date1.nextDate());  // January 1 to January 2

        Date date2 = new Date(31, 1, 2020);
        assertEquals(new Date(1, 2, 2020), date2.nextDate());  // January 31 to February 1

        Date date3 = new Date(31, 12, 2020);
        assertEquals(new Date(1, 1, 2021), date3.nextDate());  // December 31 to January 1

        Date date4 = new Date(29, 2, 2020);
        assertEquals(new Date(1, 3, 2020), date4.nextDate());  // Leap year, Feb 29 to March 1
    }

    // Test pour la méthode previousDate
    @Test
    public void testPreviousDate() {
        Date date1 = new Date(2, 1, 2020);
        assertEquals(new Date(1, 1, 2020), date1.previousDate());  // January 2 to January 1

        Date date2 = new Date(1, 2, 2020);
        assertEquals(new Date(31, 1, 2020), date2.previousDate());  // February 1 to January 31

        Date date3 = new Date(1, 1, 2021);
        assertEquals(new Date(31, 12, 2020), date3.previousDate());  // January 1 to December 31

        Date date4 = new Date(1, 3, 2020);
        assertEquals(new Date(29, 2, 2020), date4.previousDate());  // Leap year, March 1 to Feb 29
    }

    // 2
    // Test pour la méthode compareTo
    @Test
    public void testCompareTo() {
        Date date1 = new Date(1, 1, 2020);
        Date date2 = new Date(1, 1, 2020);
        Date date3 = new Date(2, 1, 2020);
        Date date4 = new Date(1, 1, 2019);
        Date date5 = new Date(1, 2, 2020);
        Date date6 = new Date(1, 3, 2020);

        assertEquals(0, date1.compareTo(date2));  // Same date
        assertTrue(date1.compareTo(date3) < 0);   // date1 is earlier than date3
        assertTrue(date3.compareTo(date1) > 0);   // date3 is later than date1
        assertTrue(date1.compareTo(date4) > 0);   // date1 is later than date4
        assertThrows(NullPointerException.class, () -> date1.compareTo(null)); // Null comparison

        // Comparer les mois
        assertTrue(date1.compareTo(date5) < 0);  // January < February
        assertTrue(date5.compareTo(date1) > 0);  // February > January
        assertTrue(date5.compareTo(date6) < 0);  // February < March
    }

    // Test pour la méthode equals
    @Test
    public void testEquals() {
        Date date1 = new Date(15, 8, 2021);
        assertTrue(date1.equals(date1));  // Même objet

        Date date2 = new Date(15, 8, 2021);
        assertTrue(date1.equals(date2));  // Différents objets mais mêmes valeurs

        Date date3 = new Date(16, 8, 2021);
        assertFalse(date1.equals(date3));  // Dates différentes

        assertFalse(date1.equals("not a date"));  // Non-Date
    }

    // Test pour la méthode hashCode
    @Test
    public void testHashCode() {
        Date date1 = new Date(15, 8, 2021);
        Date date2 = new Date(15, 8, 2021);
        assertEquals(date1.hashCode(), date2.hashCode());  // HashCodes identiques

        Date date3 = new Date(16, 8, 2021);
        assertNotEquals(date1.hashCode(), date3.hashCode());  // HashCodes différents
    }

    // 3
    @Test
    public void testToString() {
        Date date1 = new Date(15, 5, 2021);
        assertEquals("15/05/2021", date1.toString());  // Check formatted string 

        Date date2 = new Date(1, 1, 2021);
        assertEquals("01/01/2021", date2.toString());  // Check formatted string 

        Date date3 = new Date(31, 12, 2021);
        assertEquals("31/12/2021", date3.toString());  

        Date date4 = new Date(29, 2, 2020);
        assertEquals("29/02/2020", date4.toString());  // Check formatted string for February 29 in a leap year

        Date date5 = new Date(1, 2, 2021);
        assertEquals("01/02/2021", date5.toString());  // Check formatted string for single-digit day and month
    }
}


# Detecting test smells with PMD

In folder [`pmd-documentation`](../pmd-documentation) you will find the documentation of a selection of PMD rules designed to catch test smells.
Identify which of the test smells discussed in classes are implemented by these rules.

Use one of the rules to detect a test smell in one of the following projects:

- [Apache Commons Collections](https://github.com/apache/commons-collections)
- [Apache Commons CLI](https://github.com/apache/commons-cli)
- [Apache Commons Math](https://github.com/apache/commons-math)
- [Apache Commons Lang](https://github.com/apache/commons-lang)

Discuss the test smell you found with the help of PMD and propose here an improvement.
Include the improved test code in this file.

# Answer
**Project used:** https://github.com/apache/commons-cli.git   

**Class used:** ConverterTests.java   

**Rule:** JUnitAssertionsShouldIncludeMessage   

**Detected test smell:**   
```java
@Test
public void dateTests() throws Exception {
…
        assertEquals(expected, Converter.DATE.apply(formatted));
…
```

JUnit assertions should include an informative message - i.e., use **the three-argument version of assertEquals()**, not the two-argument version.


**Improved code:**
```java
assertEquals("Date conversion failed", expected, Converter.DATE.apply(formatted));
```




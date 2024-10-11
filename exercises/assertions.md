# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer
1. The assertion fails due to floating-point imprecision. Computers represent floating-point numbers in binary, which cannot always exactly represent decimal values. For example, the result of 3 * 0.4 is not exactly 1.2, but rather something like 1.2000000000000002 due to the way floating-point arithmetic works. When you compare 3 * 0.4 == 1.2, the equality check fails because these two numbers are not precisely the same in memory.

To handle this issue, it is recommended to use a tolerance value (epsilon) when comparing floating-point numbers. Instead of checking for exact equality, you can check if the absolute difference between the two numbers is smaller than a small threshold (epsilon).

2. difference between assertEquals and assertSame is: 
- assertEquals compares the values of two objects. It checks whether the contents of the objects are logically equal, meaning it uses the equals() method of the objects.
- assertSame checks whether two references point to the same object in memory. It ensures that both references are identical, not just equal in value.

They produce different results when two distinct objects have the same value but are different objects in memory.

**Scenarios where assertEquals and assertSame produce the same result:**
```java
@Test
public void testSameObject() {
    String a = "mamoune";
    String b = a;  // Both variables reference the same object

    // Both assertions pass,they are the same object and they have the same value
    assertEquals(a, b); 
    assertSame(a, b);
}
 ```

**Scenarios where assertEquals and assertSame don't produce the same result:**

```java
@Test
public void testDifferentObjectsSameValue() {
    String a = new String("mmaoune");
    String b = new String("mamoune");
    // This passes because the values are the same 
    assertEquals(a, b); 

    // This fails because a and b are different objects in memory
    assertSame(a, b);
}
```
3. the other uses for fail method:

**Use case: Incomplete implementation placeholder:**

You can use fail() to mark methods that have not been fully implemented yet during development. This serves as a reminder that this part of the code needs to be completed or revisited.
```java
	@Test
public void testFeature() {
    //  this feature is not yet implemented
    fail("its not complete");
}
```

this clearly indicates that the test is incomplete, and when running the test suite, it helps developers identify areas that need more work   

**Use case: Defensive programming**   

fail() can also be used in complex algorithms where certain branches of logic should be unreachable. If the program unexpectedly reaches that point, it indicates a serious bug, and fail() is invoked to signal the failure.
like we use in switch case   

```java
public void processRequest(String requestType) {
    switch(requestType) {
        case "Add":
            break;
        case "Modify":
            break;
        default:
            fail("Unexpected" + requestType);
    }
}
```
4. In JUnit 4, the way to specify an expected exception was through the `@Test(expected = Exception.class)` annotation. While this approach worked, it had several limitations:

- **Global Exception Handling**: The `@Test(expected)` annotation applied to the entire test method, meaning any exception thrown anywhere in the method would be caught. This could lead to confusion, especially if the exception was thrown from a part of the code that was not the focus of the test.
but in JUnit 5, these limitations were addressed with the `assertThrows()` method.

#### Advantages of Using `assertThrows()` in JUnit 5:

- **Precise Control Over Exception Handling**: 
  - `assertThrows()` allows you to specify the exact line of code that is expected to throw the exception. This makes tests more readable and easier to understand.

- **Localized Testing**: 
  - The ability to use `assertThrows()` directly where the exception is expected improves clarity. This means you can isolate the part of the code that is meant to throw an exception, ensuring that your test focuses on the specific behavior you want to verify.

the transition to using assertThrows() in JUnit 5 provides a more powerful, flexible, and readable approach to exception testing compared to the @Test(expected) annotation in JUnit 4.



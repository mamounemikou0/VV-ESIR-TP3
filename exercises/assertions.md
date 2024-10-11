# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer
1.The assertion fails due to floating-point imprecision. Computers represent floating-point numbers in binary, which cannot always exactly represent decimal values. For example, the result of 3 * 0.4 is not exactly 1.2, but rather something like 1.2000000000000002 due to the way floating-point arithmetic works. When you compare 3 * 0.4 == 1.2, the equality check fails because these two numbers are not precisely the same in memory.

To handle this issue, it is recommended to use a tolerance value (epsilon) when comparing floating-point numbers. Instead of checking for exact equality, you can check if the absolute difference between the two numbers is smaller than a small threshold (epsilon).

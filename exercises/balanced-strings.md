# Balanced strings

A string containing grouping symbols `{}[]()` is said to be balanced if every open symbol `{[(` has a matching closed symbol `)]}` and the substrings before, after and between each pair of symbols is also balanced. The empty string is considered as balanced.

For example: `{[][]}({})` is balanced, while `][`, `([)]`, `{`, `{(}{}` are not.

Implement the following method:

```java
public static boolean isBalanced(String str) {
    ...
}
```

`isBalanced` returns `true` if `str` is balanced according to the rules explained above. Otherwise, it returns `false`.

Use the coverage criteria studied in classes as follows:

1. Use input space partitioning to design an initial set of inputs. Explain below the characteristics and partition blocks you identified.
2. Evaluate the statement coverage of the test cases designed in the previous step. If needed, add new test cases to increase the coverage. Describe below what you did in this step.
3. If you have in your code any predicate that uses more than two boolean operators, check if the test cases written so far satisfy *Base Choice Coverage*. If needed, add new test cases. Describe below how you evaluated the logic coverage and the new test cases you added.
4. Use PIT to evaluate the test suite you have so far. Describe below the mutation score and the live mutants. Add new test cases or refactor the existing ones to achieve a high mutation score.

Write below the actions you took on each step and the results you obtained.
Use the project in [tp3-balanced-strings](../code/tp3-balanced-strings) to complete this exercise.

## Answer
## 1.
   ### Characteristics and Partition Blocks

#### Empty String
- **Input:** `""`
- **Expected Output:** `true`

#### Single Character (Invalid)
- **Inputs:** `"("`, `"["`, `"}"`
- **Expected Output:** `false`

#### Correctly Nested Balanced Strings
- **Inputs:** `"{}"`, `"[]"`, `"()"`, `"{[]}"`, `"{[()]}"`, `"{[][]}({})"`
- **Expected Output:** `true`

#### Incorrectly Nested or Unmatched Symbols
- **Inputs:** `"}{"`, `"[(])"`, `"{{"`, `"{(}]"`, `"([)]"`
- **Expected Output:** `false`

#### Random Strings with Symbols and Letters
- **Inputs:** `"a(b)c"`, `"foo{bar}"`, `"["`
- **Expected Output:** Based on balancing only symbols, e.g., `"a(b)c"` should return `true` if parentheses match.

## 2.

#### The Stack is Used (Push and Pop Operations)
- The stack is utilized to push opening symbols (e.g., `{`, `[`, `(`) and pop them when corresponding closing symbols (e.g., `}`, `]`, `)`) are encountered.

#### The Stack is Empty at the Beginning and End
- At the start, the stack is empty.
- If the string is correctly nested, the stack should be empty when all characters are processed, indicating balanced symbols.

#### Invalid Symbols Cause Early Returns (False)
- If an unmatched closing symbol or an incorrect nesting is found, the function should return `false` immediately without continuing further.

#### Correct Nesting Causes the Final Result to be True
- If all symbols are correctly nested and the stack is empty at the end, the function returns `true`.
- 
  after evaluating the statements , we found that tests coveres all the code, so we didn't add other tests 
![image](https://github.com/user-attachments/assets/228ffc08-37e2-42da-8610-f43347938284)


## 3. 
### Testing Requirements

#### Opening Symbols (Predicate 1):
- **True Case:** Input contains at least one opening symbol.
- **False Case:** Input contains no opening symbols.

#### Closing Symbols (Predicate 2):
- **True Case:** Input contains at least one closing symbol.
- **False Case:** Input contains no closing symbols.

#### Stack Empty Check (Predicate 3):
- **True Case:** The stack is empty when a closing symbol is encountered.
- **False Case:** The stack is not empty when a closing symbol is encountered.

#### Matching Pairs Check (Predicate 4):
- **True Case:** The opening and closing symbols match.
- **False Case:** The opening and closing symbols do not match.
  
 based on the test cases we have already provided ,they satisfid the BCC.   
 
 ## 4.    
 
 After using PIT (Pitest) to evaluate the test suite, we achieved a 100% mutation coverage. This means that all mutations introduced by PIT were either killed by the existing test suite or no live mutants remained.   
 
![image](https://github.com/user-attachments/assets/da051301-1adb-464c-a553-855b91df084e)







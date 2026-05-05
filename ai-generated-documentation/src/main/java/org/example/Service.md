![Capgemini Logo](https://www.capgemini.com/wp-content/themes/capgemini2020/assets/images/logo.svg)

### Make it real.

---
## Service.java Documentation

**1. Overview:**

This Java code defines a `Service` class that provides utility methods for basic mathematical operations and conditional logic. It includes a method to check if a given integer is even and another method to perform complex conditional checks based on the signs of three input integers. 

**2. Package/module name:**

org.example

**3. Class/file name:**

Service.java

**4. Detailed Documentation:**

   - **Class `Service`**:
     - **Description:** This class encapsulates utility methods for checking even numbers and performing conditional logic based on integer signs. 
     - **Method `isEven(int input)`**:
       - **Description:** Determines if a given integer is even.
       - **Parameters:**
         - `input`: The integer to be checked.
       - **Return Values:** A boolean value (`true` if the input is even, `false` otherwise).
       - **Important Logic:** Uses the modulo operator (%) to check if the remainder of dividing the input by 2 is zero. If the remainder is zero, the number is even and the method returns `true`; otherwise, it returns `false`.
     - **Method `highComplexityMethod(int a, int b, int c)`**:
       - **Description:** Performs a series of nested conditional checks based on the signs of three input integers (`a`, `b`, and `c`). It prints messages to the console indicating the sign of each integer.
       - **Parameters:**
         - `a`: The first integer.
         - `b`: The second integer.
         - `c`: The third integer.
       - **Return Values:** This method does not return any value (void).
       - **Important Logic:** 
         - Uses a series of `if` and `else if` statements to check the sign of `a`.
         - For each possible sign of `a`, it further checks the signs of `b` and `c` using nested `if` and `else if` statements.
         - Prints messages to the console indicating the signs of `a`, `b`, and `c` based on the conditions met.

**5. Pseudo Code:**


```
// Class: Service

// Method: isEven(input)
  1. Calculate the remainder when 'input' is divided by 2 using the modulo operator (%).
  2. If the remainder is equal to 0, return true (indicating 'input' is even).
  3. Otherwise, return false (indicating 'input' is odd).

// Method: highComplexityMethod(a, b, c)
  1. Check the sign of 'a':
    - If 'a' is positive:
      - Check the sign of 'b':
        - If 'b' is positive:
          - Check the sign of 'c':
            - If 'c' is positive: Print "a, b, and c are positive".
            - Otherwise (c is non-positive): Print "a and b are positive, but c is non-positive".
        - Otherwise (b is non-positive):
          - Check the sign of 'c':
            - If 'c' is positive: Print "a is positive, but b and c are non-positive".
            - Otherwise (c is non-positive): Print "a, b, and c are non-positive".
    - If 'a' is non-positive:
      - Check the sign of 'b':
        - If 'b' is positive:
          - Check the sign of 'c':
            - If 'c' is positive: Print "a is non-positive, but b and c are positive".
            - Otherwise (c is non-positive): Print "a is non-positive, but b is positive, and c is non-positive".
        - Otherwise (b is non-positive):
          - Check the sign of 'c':
            - If 'c' is positive: Print "a and b are non-positive, but c is positive".
            - Otherwise (c is non-positive): Print "a, b, and c are non-positive".



```

**Dependencies and Libraries:**


* This code does not rely on any external libraries. It uses standard Java built-in functionalities. 





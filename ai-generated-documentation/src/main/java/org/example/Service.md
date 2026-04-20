![Capgemini Logo](https://www.capgemini.com/wp-content/themes/capgemini2020/assets/images/logo.svg)

### Make it real.

---
## Service.java Documentation

**1. Overview:**

This Java code defines a `Service` class that provides two methods: `isEven()` and `highComplexityMethod()`. The `isEven()` method determines if an integer is even, while `highComplexityMethod()` analyzes three integers (`a`, `b`, and `c`) and prints various messages based on their positive or non-positive values.

**2. Package/module name:**

org.example

**3. Class/file name:**

Service.java

**4. Detailed Documentation:**

   - **Class `Service`**:
     - **Description:** This class encapsulates utility methods for checking even numbers and analyzing integer relationships. 
     - **Method `isEven(int input)`**:
       - **Description:** Checks if a given integer is even.
       - **Parameters:**
         - `input`: The integer to be checked.
       - **Return Values:** A boolean value (`true` if the input is even, `false` otherwise).
       - **Important Logic:** Uses the modulo operator (`%`) to determine if the remainder of dividing the `input` by 2 is zero. If it is, the number is even and `true` is returned; otherwise, `false` is returned.

     - **Method `highComplexityMethod(int a, int b, int c)`**:
       - **Description:** Analyzes three integers (`a`, `b`, and `c`) and prints messages based on their positive or non-positive values. 
       - **Parameters:**
         - `a`: The first integer.
         - `b`: The second integer.
         - `c`: The third integer.
       - **Return Values:** None (void method).
       - **Important Logic:** Uses nested if-else statements to evaluate the signs of `a`, `b`, and `c` and prints messages accordingly. It covers all possible combinations of positive and non-positive values for the three integers.

**5. Pseudo Code:**



```
// Class: Service

// Method: isEven(input)
  1. Calculate the remainder when 'input' is divided by 2 using the modulo operator (%).
  2. If the remainder is equal to 0, return true (the number is even).
  3. Otherwise, return false (the number is odd).

// Method: highComplexityMethod(a, b, c)
  1. Check the value of 'a':
    - If 'a' is 0:
      - Check the value of 'b':
        - If 'b' is positive:
          - Check the value of 'c':
            - If 'c' is positive, print "c is positive".
            - Otherwise, print "c is non-positive".
        - Otherwise (if 'b' is non-positive):
          - Check the value of 'c':
            - If 'c' is positive, print "c is positive".
            - Otherwise, print "c is non-positive".
    - If 'a' is less than 0:
      - Check the value of 'b':
        - If 'b' is positive:
          - Check the value of 'c':
            - If 'c' is positive, print "c is positive".
            - Otherwise, print "c is non-positive".
        - Otherwise (if 'b' is non-positive):
          - Check the value of 'c':
            - If 'c' is positive, print "c is positive".
            - Otherwise, print "c is non-positive".
    - Otherwise (if 'a' is non-positive):
      - Check the value of 'b':
        - If 'b' is positive:
          - Check the value of 'c':
            - If 'c' is positive, print "c is positive".
            - Otherwise, print "c is non-positive".
        - Otherwise (if 'b' is non-positive):
          - Check the value of 'c':
            - If 'c' is positive, print "c is positive".
            - Otherwise, print "c is non-positive".



**Dependencies and Libraries:**

* This code does not rely on any external libraries. It uses standard Java built-in functionalities. 



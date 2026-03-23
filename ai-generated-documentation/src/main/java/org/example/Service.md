![Capgemini Logo](https://www.capgemini.com/wp-content/themes/capgemini2020/assets/images/logo.svg)

### Make it real.

---
## Service.java Documentation and Pseudocode

**1. Overview:**

This Java code defines a `Service` class that provides two methods: `isEven` and `highComplexityMethod`. The `isEven` method determines if an integer is even, while `highComplexityMethod` performs a series of conditional checks and prints messages based on the values of three input integers.

**2. Package/module name:**

org.example

**3. Class/file name:**

Service.java

**4. Detailed Documentation:**

**Class: Service**

- **Method:** `isEven(int input)`
    - **Description:** Checks if a given integer is even.
    - **Parameters:**
        - `input`: An integer value to be checked.
    - **Return Values:**
        - `true`: If the `input` is even.
        - `false`: If the `input` is odd.
    - **Important Logic:**
        - Uses the modulo operator (`%`) to check if the remainder of the division of `input` by 2 is equal to 0. If it is, the number is even, and `true` is returned; otherwise, `false` is returned.

- **Method:** `highComplexityMethod(int a, int b, int c)`
    - **Description:** Prints messages based on the signs of three input integers.
    - **Parameters:**
        - `a`: The first integer.
        - `b`: The second integer.
        - `c`: The third integer.
    - **Return Values:**
        - None.
    - **Important Logic:**
        - Uses nested `if` statements to check the signs of `a`, `b`, and `c`.
        - Prints messages indicating whether each integer is positive or non-positive.
        - The messages are structured to provide a clear indication of the signs of all three integers.
        - No explicit error handling is present.

**5. Pseudo Code:**

```
// Class: Service

// Method: isEven(input)
  1. Calculate the remainder when input is divided by 2 (input % 2).
  2. If the remainder is 0, return true (input is even).
  3. Otherwise, return false (input is odd).

// Method: highComplexityMethod(a, b, c)
  1. Check the sign of a:
    - If a is 0:
      - Check the sign of b:
        - If b is positive:
          - Check the sign of c:
            - If c is positive: Print "a is positive, b is positive, c is positive".
            - If c is non-positive: Print "a is positive, b is positive, c is non-positive".
        - If b is non-positive:
          - Check the sign of c:
            - If c is positive: Print "a is positive, b is non-positive, c is positive".
            - If c is non-positive: Print "a is positive, b is non-positive, c is non-positive".
    - If a is negative:
      - Check the sign of b:
        - If b is positive:
          - Check the sign of c:
            - If c is positive: Print "a is non-positive, b is positive, c is positive".
            - If c is non-positive: Print "a is non-positive, b is positive, c is non-positive".
        - If b is non-positive:
          - Check the sign of c:
            - If c is positive: Print "a is non-positive, b is non-positive, c is positive".
            - If c is non-positive: Print "a is non-positive, b is non-positive, c is non-positive".
    - If a is non-positive:
      - Check the sign of b:
        - If b is positive:
          - Check the sign of c:
            - If c is positive: Print "a is non-positive, b is positive, c is positive".
            - If c is non-positive: Print "a is non-positive, b is positive, c is non-positive".
        - If b is non-positive:
          - Check the sign of c:
            - If c is positive: Print "a is non-positive, b is non-positive, c is positive".
            - If c is non-positive: Print "a is non-positive, b is non-positive, c is non-positive".



**Dependencies and Libraries:**

- The code doesn't rely on any external libraries.




![Capgemini Logo](https://www.capgemini.com/wp-content/themes/capgemini2020/assets/images/logo.svg)

### Make it real.

---
## Service.java Documentation

**1. Overview:**

This Java code defines a `Service` class that provides two methods: `isEven` and `highComplexityMethod`. The `isEven` method determines if an integer is even, while `highComplexityMethod` performs a series of conditional checks based on the signs of three input integers.

**2. Package/module name:**

org.example

**3. Class/file name:**

Service.java

**4. Detailed Documentation:**

**- Class `Service`:**

   - **Description:** This class encapsulates utility methods for checking even numbers and performing conditional logic based on integer signs.

   - **Method `isEven(int input)`:**

     - **Description:** Checks if the provided integer is even.
     - **Parameters:**
       - `input`: An integer to be checked.
     - **Return Values:** `true` if the input is even, `false` otherwise.
     - **Important Logic:** Uses the modulo operator (`%`) to check if the remainder of the division of `input` by 2 is 0. If the remainder is 0, the number is even.

   - **Method `highComplexityMethod(int a, int b, int c)`:**

     - **Description:** Performs a series of nested conditional checks based on the signs of three integers (`a`, `b`, and `c`). It prints messages to the console indicating the signs of the integers.
     - **Parameters:**
       - `a`: The first integer.
       - `b`: The second integer.
       - `c`: The third integer.
     - **Return Values:** None.
     - **Important Logic:**
       - The method first checks the sign of `a`.
       - Based on the sign of `a`, it further checks the signs of `b` and `c` using nested `if` statements.
       - For each combination of signs, it prints a message to the console indicating the signs of `a`, `b`, and `c`.

**5. Pseudo Code:**

```
// Class: Service

// Method: isEven(input)
  1. Calculate the remainder when 'input' is divided by 2.
  2. If the remainder is 0:
    - Return true.
  3. Else:
    - Return false.

// Method: highComplexityMethod(a, b, c)
  1. Check the sign of 'a':
    - If 'a' is positive:
      - Check the sign of 'b':
        - If 'b' is positive:
          - Check the sign of 'c':
            - If 'c' is positive:
              - Print "a is positive, b is positive, c is positive"
            - Else:
              - Print "a is positive, b is positive, c is non-positive"
        - Else:
          - Check the sign of 'c':
            - If 'c' is positive:
              - Print "a is positive, b is non-positive, c is positive"
            - Else:
              - Print "a is positive, b is non-positive, c is non-positive"
    - If 'a' is non-positive:
      - Check the sign of 'b':
        - If 'b' is positive:
          - Check the sign of 'c':
            - If 'c' is positive:
              - Print "a is non-positive, b is positive, c is positive"
            - Else:
              - Print "a is non-positive, b is positive, c is non-positive"
        - Else:
          - Check the sign of 'c':
            - If 'c' is positive:
              - Print "a is non-positive, b is non-positive, c is positive"
            - Else:
              - Print "a is non-positive, b is non-positive, c is non-positive"



```

**Dependencies and Libraries:**

- The code does not rely on any external libraries.




![Capgemini Logo](https://www.capgemini.com/wp-content/themes/capgemini2020/assets/images/logo.svg)

### Make it real.

---
## General Documentation for example package 

This documentation provides a comprehensive overview of the `example` package, detailing its structure, functionalities, and usage guidelines. The package consists of two core components: `Client` and `Service`.  The `Client` class interacts with the `Service` to generate greetings based on user input. It validates the input name, checks if its length is even using the `isEven()` method provided by the `Service` interface, and returns a greeting in uppercase if the length is even, otherwise, it returns the greeting in lowercase. The `Service` class encapsulates utility methods for checking even numbers and performing conditional logic based on integer signs. It includes a method to determine if a given integer is even and another method to perform complex conditional checks based on the signs of three input integers.

## Table of Contents
- [Client.md](Client.md) 
  - **Description:** This file details the functionality of the `Client` class, explaining how it interacts with the `Service` interface to generate greetings based on user input and validate the input name. It also includes a pseudocode representation of the `greeting()` method for clarity.
- [Service.md](Service.md) 
  - **Description:** This file describes the functionality of the `Service` class, outlining its methods for checking even numbers (`isEven()`) and performing complex conditional checks based on integer signs (`highComplexityMethod()`). It also provides a pseudocode representation of both methods for better understanding.




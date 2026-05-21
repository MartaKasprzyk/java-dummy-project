![Capgemini Logo](https://www.capgemini.com/wp-content/themes/capgemini2020/assets/images/logo.svg)

### Make it real.

---
## Client.java Documentation

**1. Overview:**

This Java code defines a `Client` class that interacts with a `Service` object to generate greetings. The client receives a name as input, checks if it's valid, determines if the length of the name is even, and then generates a greeting accordingly (uppercase if even, lowercase otherwise). 

**2. Package/module name:**

org.example

**3. Class/file name:**

Client.java

**4. Detailed Documentation:**

   - **Class `Client`**:
     - **Description:**  Represents a client that interacts with a service to generate greetings based on a provided name.
     - **Fields:**
       - `service`: A private field of type `Service` representing the service object used for generating even/odd checks.

     - **Constructor `Client(Service service)`**:
       - **Description:** Initializes a new `Client` instance with the given `service` object.
       - **Parameters:**
         - `service`: A `Service` object responsible for checking if a number is even.
       - **Return Values:** None

     - **Method `greeting(String name)`**:
       - **Description:** Generates a greeting message based on the provided name. 
       - **Parameters:**
         - `name`: A String representing the name to be used in the greeting.
       - **Return Values:**
         - A String containing the generated greeting message (uppercase if the name length is even, lowercase otherwise).
       - **Important Logic:**
         - Checks if the `name` parameter is null or empty. If so, throws an `IllegalArgumentException`.
         - Calls the `isEven()` method of the `service` object to determine if the length of the `name` is even.
         - Formats a greeting message using string interpolation ("Hello, %s").
         - Returns the formatted greeting in uppercase if the name length is even, otherwise returns it in lowercase.

**5. Pseudo Code:**


```
// Class: Client

// Method: greeting(name)
  1. Check if 'name' is null or empty:
    - If true, throw an "IllegalArgumentException" with message "'name' must not be null or empty".
  2. Call the 'isEven()' method of the 'service' object, passing the length of 'name' as input. 
  3. Format a greeting message using string interpolation: "Hello, %s", replacing '%s' with 'name'.
  4. If 'isEven' returns true (length of 'name' is even):
    - Convert the formatted greeting to uppercase and return it.
  5. Otherwise (length of 'name' is odd):
    - Return the formatted greeting as is. 



```

**Dependencies and Libraries:**


* **Service Interface:** The code assumes the existence of a `Service` interface with an `isEven()` method. This interface likely defines the contract for checking if a number is even.  No specific library dependency is mentioned in the provided code.





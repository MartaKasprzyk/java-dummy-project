![Capgemini Logo](https://www.capgemini.com/wp-content/themes/capgemini2020/assets/images/logo.svg)

### Make it real.

---
## Dokumentacja kodu Service.java

**1. Opis ogólny:**

Kod Java w pliku `Service.java` definiuje klas? `Service`, która zawiera dwie metody: `isEven()` i `highComplexityMethod()`. Metoda `isEven()` sprawdza, czy podany argument jest liczb? parzyst?, zwracaj?c warto?? logiczn? (true lub false). Metoda `highComplexityMethod()` wykonuje z?o?on? logik? porówna?, która analizuje warto?ci trzech zmiennych ca?kowitych (`a`, `b`, `c`) i wy?wietla komunikaty tekstowe w konsoli.

**2. Nazwa pakietu/modu?u:**

`org.example`

**3. Nazwa klasy/pliku:**

`Service`

**4. Szczegó?owa dokumentacja:**

   - **Metoda `isEven(int input)`:**
     - **Opis:** Sprawdza, czy podany argument (`input`) jest liczb? parzyst?.
     - **Parametry:**
       - `input`: Liczba ca?kowita, której parzysto?? ma by? sprawdzona.
     - **Zwracane warto?ci:**
       - `boolean`: Warto?? logiczna (true lub false), wskazuj?ca czy liczba jest parzysta.
     - **Wa?na logika:**
       - Wykorzystuje operator modulo (`%`) do sprawdzenia, czy reszta z dzielenia liczby przez 2 jest równa 0. Je?eli tak, to liczba jest parzysta i zwracany jest `true`, w przeciwnym razie `false`.

   - **Metoda `highComplexityMethod(int a, int b, int c)`:**
     - **Opis:** Wykonuje z?o?on? logik? porówna?, która analizuje warto?ci trzech zmiennych ca?kowitych (`a`, `b`, `c`) i wy?wietla komunikaty tekstowe w konsoli.
     - **Parametry:**
       - `a`: Liczba ca?kowita.
       - `b`: Liczba ca?kowita.
       - `c`: Liczba ca?kowita.
     - **Zwracane warto?ci:**
       - Nie zwraca ?adnej warto?ci.
     - **Wa?na logika:**
       - Sprawdza warto?? zmiennej `a` i w zale?no?ci od jej znaku (dodatnia, ujemna lub zero) wykonuje ró?ne sekwencje warunków (`if`, `else if`, `else`).
       - Wewn?trz ka?dej sekwencji warunków porównuje warto?ci zmiennych `b` i `c` i wy?wietla odpowiednie komunikaty tekstowe w konsoli.

**5. Pseudokod:**


```
// Klasa: Service

// Metoda: isEven(int input)
  1. Oblicz reszt? z dzielenia 'input' przez 2.
  2. Je?eli reszta jest równa 0, zwró? true, w przeciwnym razie zwró? false.

// Metoda: highComplexityMethod(int a, int b, int c)
  1. Sprawd? warto?? zmiennej 'a':
    - Je?eli 'a' jest dodatnie:
      - Sprawd? warto?? zmiennej 'b':
        - Je?eli 'b' jest dodatnie:
          - Sprawd? warto?? zmiennej 'c':
            - Je?eli 'c' jest dodatnie, wy?wietl komunikat "a is positive", "b is positive" i "c is positive".
            - W przeciwnym razie, wy?wietl komunikat "a is positive", "b is positive" i "c is non-positive".
        - Je?eli 'b' nie jest dodatnie:
          - Sprawd? warto?? zmiennej 'c':
            - Je?eli 'c' jest dodatnie, wy?wietl komunikat "a is positive", "b is non-positive" i "c is positive".
            - W przeciwnym razie, wy?wietl komunikat "a is positive", "b is non-positive" i "c is non-positive".
    - Je?eli 'a' jest ujemne:
      - Sprawd? warto?? zmiennej 'b':
        - Je?eli 'b' jest dodatnie:
          - Sprawd? warto?? zmiennej 'c':
            - Je?eli 'c' jest dodatnie, wy?wietl komunikat "a is non-positive", "b is positive" i "c is positive".
            - W przeciwnym razie, wy?wietl komunikat "a is non-positive", "b is positive" i "c is non-positive".
        - Je?eli 'b' nie jest dodatnie:
          - Sprawd? warto?? zmiennej 'c':
            - Je?eli 'c' jest dodatnie, wy?wietl komunikat "a is non-positive", "b is non-positive" i "c is positive".
            - W przeciwnym razie, wy?wietl komunikat "a is non-positive", "b is non-positive" i "c is non-positive".
    - Je?eli 'a' jest równe 0:
      - Sprawd? warto?? zmiennej 'b':
        - Je?eli 'b' jest dodatnie:
          - Sprawd? warto?? zmiennej 'c':
            - Je?eli 'c' jest dodatnie, wy?wietl komunikat "a is positive", "b is positive" i "c is positive".
            - W przeciwnym razie, wy?wietl komunikat "a is positive", "b is positive" i "c is non-positive".
        - Je?eli 'b' nie jest dodatnie:
          - Sprawd? warto?? zmiennej 'c':
            - Je?eli 'c' jest dodatnie, wy?wietl komunikat "a is positive", "b is non-positive" i "c is positive".
            - W przeciwnym razie, wy?wietl komunikat "a is positive", "b is non-positive" i "c is non-positive".



```

**ZaleÅ¼noÅ›ci:**

Kod ten nie posiada widocznych zaleÅ¼noÅ›ci od zewn?trznych bibliotek.



![Capgemini Logo](https://www.capgemini.com/wp-content/themes/capgemini2020/assets/images/logo.svg)

### Make it real.

---
## Dokumentacja kodu Service.java

**1. Overview (Opis ogÃ³lny)**

Klasa `Service` zawiera dwie metody: `isEven()` i `highComplexityMethod()`. Metoda `isEven()` sprawdza, czy podany numer jest liczbÄ? parzystÄ…, zwracaj?c `true`, je?li tak, a `false` w przeciwnym razie. Metoda `highComplexityMethod()` wykonuje z?o?on? logikÄ™ porównawcz? na trzech liczbach ca?kowitych (`a`, `b`, `c`), wypisuj?c informacje o ich znakach (dodatnich lub ujemnych) do konsoli.

**2. Package/module name (Nazwa pakietu/moduÅ‚u)**

org.example

**3. Class/file name (Nazwa klasy/pliku)**

Service.java

**4. Detailed Documentation (SzczegÃ³Å‚owa dokumentacja)**

* **Metoda `isEven(int input)`**
    *   **Opis:** Sprawdza, czy podany numer (`input`) jest liczbÄ? parzystÄ… zwracaj?c `true`, je?li tak, a `false` w przeciwnym razie.
    *   **Parametry:**
        *   `input`: Liczba ca?kowita, której parzysto?? ma by? sprawdzona.
    *   **Return Values:**  `boolean` - `true`, je?li `input` jest liczbÄ? parzystÄ…, `false` w przeciwnym razie.

* **Metoda `highComplexityMethod(int a, int b, int c)`**
    *   **Opis:** Wykonuje z?o?on? logikÄ™ porównawcz? na trzech liczbach ca?kowitych (`a`, `b`, `c`), wypisuj?c informacje o ich znakach (dodatnich lub ujemnych) do konsoli.
    *   **Parametry:**
        *   `a`: Pierwsza liczba ca?kowita.
        *   `b`: Druga liczba ca?kowita.
        *   `c`: Trzecia liczba ca?kowita.
    *   **Return Values:**  Brak warto?ci zwracanej.

**5. Pseudo Code (Pseudokod)**


```
# Klasa: Service

# Metoda: isEven(input)
  1. Oblicz resztÄ™ z dzielenia input przez 2.
  2. Je?li reszta jest równa 0, zwró? true.
  3. W przeciwnym razie, zwró? false.

# Metoda: highComplexityMethod(a, b, c)
  1. Sprawd? warto?? a:
    - Je?li a jest dodatnie:
      - Sprawd? warto?? b:
        - Je?li b jest dodatnie:
          - Sprawd? warto?? c:
            - Je?li c jest dodatnie, wypisz "a, b i c s? dodatnie".
            - W przeciwnym razie, wypisz "c jest nieujemne".
        - W przeciwnym razie, wypisz "b jest nieujemne".
    - Je?li a jest ujemne:
      - Sprawd? warto?? b:
        - Je?li b jest dodatnie:
          - Sprawd? warto?? c:
            - Je?li c jest dodatnie, wypisz "a jest ujemne, b i c s? dodatnie".
            - W przeciwnym razie, wypisz "c jest nieujemne".
        - W przeciwnym razie, wypisz "b jest nieujemne".
    - W przeciwnym razie, wypisz "a jest nieujemne".



```





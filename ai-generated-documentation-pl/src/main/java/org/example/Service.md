![Capgemini Logo](https://www.capgemini.com/wp-content/themes/capgemini2020/assets/images/logo.svg)

### Make it real.

---
## Dokumentacja Service.java

![Logo Capgemini](https://www.capgemini.com/wp-content/themes/capgemini2020/assets/images/logo.svg)

### Zrób to realnie.

---

**1. Podsumowanie:**

Ten kod Java definiuje klasę `Service`, która dostarcza pomocniczych metod do podstawowych operacji numerycznych. Zawiera metodę sprawdzania, czy podany licznik jest parzysty, oraz metodę (`highComplexityMethod`), która wykonuje serię warunkowych sprawdzeń na podstawie znaków trzech liczb całkowitych.

**2. Nazwa pakietu/modułu:**

org.example

**3. Nazwa klasy/pliku:**

Service.java

**4. Dokumentacja szczegółowa:**

   - **Klasa `Service`**:
     - **Opis:** Klasa pomocnicza zawierająca metody do wykonywania prostych sprawdzów numerycznych i operacji logicznych warunkowych.

     - **Metody:**
       - **Metoda `isEven(int input)`**:
         - **Opis:** Określa, czy podana liczba całkowita jest parzysta.
         - **Parametry:**
           - `input`: Liczba całkowita do sprawdzenia parzystości.
         - **Wartości zwracane:**
           - Wartość logiczna (`true` jeśli `input` jest parzysty, `false` w przeciwnym razie).
         - **Ważna logika:** 
           - Korzysta z operatora modulo (`%`) do sprawdzenia, czy reszta z dzielenia `input` przez 2 jest równa 0. Jeśli tak, liczba jest parzysta i metoda zwraca `true`; w przeciwnym razie zwraca `false`.

       - **Metoda `highComplexityMethod(int a, int b, int c)`**:
         - **Opis:** Wykonuje serię warunkowych sprawdzeń zagnieżdżonych na podstawie znaków trzech liczb całkowitych (`a`, `b` i `c`). Wypisuje wiadomości do konsoli informujące o znaku każdej liczby.
         - **Parametry:**
           - `a`: Liczba całkowita.
           - `b`: Liczba całkowita.
           - `c`: Liczba całkowita.
         - **Wartości zwracane:** Brak (metoda void).
         - **Ważna logika:** 
           - Korzysta z serii instrukcji `if` i `else if`, aby sprawdzić znak `a`.
           - Dla każdego możliwego znaku `a`, dalej sprawdza znaki `b` i `c` za pomocą zagnieżdżonych instrukcji `if` i `else if`.
           - Wypisuje wiadomości do konsoli informujące o znaku każdej liczby na podstawie spełnionych warunków.

**5. Pseudo kod:**



```
// Klasa: Service

// Metoda: isEven(input)
  1. Oblicz resztę z dzielenia 'input' przez 2 za pomocą operatora modulo (%).
  2. Jeśli reszta jest równa 0, zwróć true (wskazując, że 'input' jest parzysty).
  3. W przeciwnym razie, zwróć false (wskazując, że 'input' jest nieparzysty).

// Metoda: highComplexityMethod(a, b, c)
  1. Sprawdź znak 'a':
    - Jeśli 'a' jest dodatni:
      - Sprawdź znak 'b':
        - Jeśli 'b' jest dodatni:
          - Sprawdź znak 'c':
            - Jeśli 'c' jest dodatni, wypisz "a jest dodatni, b jest dodatni, c jest dodatni".
            - W przeciwnym razie (c jest nieujemny), wypisz "a jest dodatni, b jest dodatni, c jest nieujemny".
        - W przeciwnym razie (b jest nieujemny):
          - Sprawdź znak 'c':
            - Jeśli 'c' jest dodatni, wypisz "a jest dodatni, b jest nieujemny, c jest dodatni".
            - W przeciwnym razie (c jest nieujemny), wypisz "a jest dodatni, b jest nieujemny, c jest nieujemny".
    - Jeśli 'a' jest nieujemny:
      - Sprawdź znak 'b':
        - Jeśli 'b' jest dodatni:
          - Sprawdź znak 'c':
            - Jeśli 'c' jest dodatni, wypisz "a jest nieujemny, b jest dodatni, c jest dodatni".
            - W przeciwnym razie (c jest nieujemny), wypisz "a jest nieujemny, b jest dodatni, c jest nieujemny".
        - W przeciwnym razie (b jest nieujemny):
          - Sprawdź znak 'c':
            - Jeśli 'c' jest dodatni, wypisz "a jest nieujemny, b jest nieujemny, c jest dodatni".
            - W przeciwnym razie (c jest nieujemny), wypisz "a jest nieujemny, b jest nieujemny, c jest nieujemny".



```

**Zależności i biblioteki:**


* **Standardowa biblioteka Java:** Ten kod korzysta ze standardowej biblioteki Java do podstawowych operacji takich jak modulo (`%`) i drukowanie na konsoli. Nie używa jawnie żadnych zewnętrznych bibliotek.





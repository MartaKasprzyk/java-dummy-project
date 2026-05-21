![Capgemini Logo](https://www.capgemini.com/wp-content/themes/capgemini2020/assets/images/logo.svg)

### Make it real.

---
## Dokumentacja Client.java

![Logo Capgemini](https://www.capgemini.com/wp-content/themes/capgemini2020/assets/images/logo.svg)

### Zrób to realnie.

---

## Dokumentacja Client.java

**1. Podsumowanie:**

Ten kod Java definiuje klasę `Client`, która wchodzi w interakcję z obiektem `Service` w celu generowania powitań. Klient otrzymuje imię jako dane wejściowe, sprawdza jego poprawność, ustala czy długość imienia jest parzysta, a następnie generuje odpowiednie pozdrowienie (pisane dużą literą jeśli liczba jest parzysta, małą literą w przeciwnym razie).

**2. Nazwa pakietu/modułu:**

org.example

**3. Nazwa klasy/pliku:**

Client.java

**4. Dokumentacja szczegółowa:**

   - **Klasa `Client`**:
     - **Opis:** Przedstawia klienta, który wchodzi w interakcję z usługą w celu generowania powitań na podstawie podanego imienia.
     - **Pola:**
       - `service`: Prywatne pole typu `Service`, reprezentujące obiekt usługi używany do generowania kontroli parzystości/nieparzystości.

     - **Konstruktor `Client(Service service)`**:
       - **Opis:** Inicjalizuje nowy obiekt `Client` z podanym obiektem `service`.
       - **Parametry:**
         - `service`: Obiekt `Service`, odpowiedzialny za sprawdzanie, czy liczba jest parzysta.
       - **Wartości zwracane:** Brak

     - **Metoda `greeting(String name)`**:
       - **Opis:** Generuje wiadomość powitania na podstawie podanego imienia. 
       - **Parametry:**
         - `name`: String reprezentujący imię, które ma być użyte w pozdrowieniu.
       - **Wartości zwracane:**
         - String zawierający wygenerowaną wiadomość powitania (pisana dużą literą jeśli długość imienia jest parzysta, małą literą w przeciwnym razie).
       - **Ważne logika:**
         - Sprawdza, czy parametr `name` jest null lub pusty. Jeśli tak, rzuca wyjątek `IllegalArgumentException`.
         - Wywołuje metodę `isEven()` obiektu `service`, aby ustalić, czy długość `name` jest parzysta.
         - Formatuje wiadomość powitania za pomocą interpolacji stringów ("Hello, %s").
         - Zwraca sformatowaną wiadomość powitania w formacie wielkiego pisma jeśli długość imienia jest parzysta, w przeciwnym razie zwraca ją w formacie małego pisma.

**5. Pseudo kod:**


```
// Klasa: Client

// Metoda: greeting(name)
  1. Sprawdź, czy 'name' jest null lub pusty:
    - Jeśli tak, rzuć wyjątek "IllegalArgumentException" z wiadomością "'name' nie może być null ani pustym".
  2. Wywołaj metodę 'isEven()' obiektu 'service', przekazując długość 'name' jako argument wejściowy. 
  3. Sformatuj wiadomość powitania za pomocą interpolacji stringów: "Hello, %s", zastępując '%s' 'name'.
  4. Jeśli 'isEven' zwraca wartość true (długość 'name' jest parzysta):
    - Przekształć sformatowaną wiadomość powitania na wielkie litery i zwróć ją.
  5. W przeciwnym razie (długość 'name' jest nieparzysta):
    - Zwróć sformatowaną wiadomość powitania bez zmian. 



```

**Zależności i biblioteki:**


* **Interfejs Service:** Kod zakłada istnienie interfejsu `Service` z metodą `isEven()`. Ten interfejs prawdopodobnie definiuje umowę sprawdzania, czy liczba jest parzysta. W dostarczonym kodzie nie wspomniano o żadnej konkretnej zależności bibliotecznej.





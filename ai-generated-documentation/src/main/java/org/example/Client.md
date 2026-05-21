![Capgemini Logo](https://www.capgemini.com/wp-content/themes/capgemini2020/assets/images/logo.svg)

### Make it real.

---
## Dokumentacja kodu Client.java

**1. Opis ogólny:**

Kod Java w pliku `Client.java` definiuje klas? `Client`, która implementuje prost? funkcjonalno?? powitania u?ytkownika. Klasa ta przyjmuje nazw? u?ytkownika jako argument i zwraca powitanie, które jest wy?wietlane z du?ej litery je?li d?ugo?? imienia jest parzysta.

**2. Nazwa pakietu/modu?u:**

`org.example`

**3. Nazwa klasy/pliku:**

`Client`

**4. Szczegó?owa dokumentacja:**

   - **Konstruktor `Client(Service service)`:**
     - **Opis:** Inicjalizuje obiekt klasy `Client`, ustawiaj?c pole `service` na podany obiekt typu `Service`.
     - **Parametry:**
       - `service`: Obiekt typu `Service`, który zapewne zawiera funkcjonalno?? do sprawdzania parzysto?ci.
     - **Zwracane warto?ci:** Nie zwraca ?adnej warto?ci.

   - **Metoda `greeting(String name)`:**
     - **Opis:** Zwraca powitanie dla podanego imienia u?ytkownika. Je?li d?ugo?? imienia jest parzysta, powitanie jest wy?wietlane z du?ej litery.
     - **Parametry:**
       - `name`: String reprezentuj?cy imi? u?ytkownika.
     - **Zwracane warto?ci:**
       - String: Powitanie w formacie "Hello, [name]". Je?li d?ugo?? imienia jest parzysta, powitanie jest wy?wietlane z du?ej litery.
     - **Wa?na logika:**
       - Sprawdza, czy podane imi? nie jest `null` ani puste. W przypadku braku lub pustych danych rzuca wyj?tek `IllegalArgumentException`.
       - Wywo?uje metod? `isEven()` obiektu `service`, aby sprawdzi? parzysto?? d?ugo?ci imienia.
       - Tworzy powitanie w formacie "Hello, [name]".
       - Je?li d?ugo?? imienia jest parzysta, zwraca powitanie w formacie wielkiego pisma. W przeciwnym razie zwraca oryginalne powitanie.

**5. Pseudokod:**


```
// Klasa: Client

// Konstruktor: Client(Service service)
  1. Ustaw pole 'service' na podany obiekt 'service'.

// Metoda: greeting(String name)
  1. Sprawd?, czy 'name' jest null lub pusty.
    - Je?eli tak, rzu? wyj?tek IllegalArgumentException z wiadomo?ci? "'name' must not be null or empty".
  2. Wywo?aj metod? 'isEven()' obiektu 'service', przekazuj?c d?ugo?? imienia jako argument.
  3. Stwórz powitanie w formacie "Hello, %s" z podanym imieniem.
  4. Je?eli d?ugo?? imienia jest parzysta:
    - Zwró? powitanie w formacie wielkiego pisma.
  5. W przeciwnym razie:
    - Zwró? oryginalne powitanie. 


```




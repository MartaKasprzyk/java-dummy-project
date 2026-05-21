![Capgemini Logo](https://www.capgemini.com/wp-content/themes/capgemini2020/assets/images/logo.svg)

### Make it real.

---
## Dokumentacja kodu Client.java

**1. Overview (Opis og√≥lny)**

Klasa `Client` implementuje prost? interfejs komunikacji, ktÛry przyjmuje imi? jako parametr i zwraca powitanie w zale?no?ci od parzysto?ci d?ugo?ci imienia. 

**2. Package/module name (Nazwa pakietu/modu≈Çu)**

org.example

**3. Class/file name (Nazwa klasy/pliku)**

Client.java

**4. Detailed Documentation (Szczeg√≥≈Çowa dokumentacja)**

* **Konstruktor `Client(Service service)`**
    *   **Opis:** Inicjalizuje obiekt klasy `Client` z podanym obiektem typu `Service`.
    *   **Parametry:**
        *   `service`: Obiekt typu `Service`, ktÛry zawiera funkcje do obs?ugi logiki biznesowej.
    *   **Return Values:**  Brak warto?ci zwracanej.

* **Metoda `greeting(String name)`**
    *   **Opis:** Zwraca powitanie z imieniem, ktÛre jest w du≈ºej literze je?li d?ugo?? imienia jest parzysta, w przeciwnym razie powitanie jest w normalnej postaci.
    *   **Parametry:**
        *   `name`: String reprezentuj?cy imi? osoby.
    *   **Return Values:**  String zawieraj?cy powitanie.
    *   **Wa≈ºna logika:**
        1. Sprawdza, czy `name` jest null lub pusty. Je?li tak, rzuca wyj?tek `IllegalArgumentException`.
        2. Wywo?uje metod? `isEven()` z obiektu `service`, aby sprawdzi? parzysto?? d?ugo?ci imienia.
        3. Tworzy string powitania u?ywaj?c formatowania `String.format()`.
        4. Je?li d?ugo?? imienia jest parzysta, zwraca powitanie w du≈ºej literze. W przeciwnym razie zwraca powitanie w normalnej postaci.

**5. Pseudo Code (Pseudokod)**


```
# Klasa: Client

# Konstruktor: Client(service)
  1. Przechowuj podany obiekt service jako prywatn? zmienn? 'this.service'.

# Metoda: greeting(name)
  1. Sprawd?, czy name jest null lub pusty.
    - Je?li tak, rzu? wyj?tek "IllegalArgumentException" z wiadomo?ci? "'name' must not be null or empty".
  2. Wywo?aj metod? 'isEven()' z obiektu service, przekazuj?c d?ugo?? imienia jako argument.
  3. StwÛrz string powitania u?ywaj?c formatowania String.format("%s", name).
  4. Je?li wynik metody 'isEven()' jest true (d?ugo?? imienia jest parzysta):
    - ZwrÛ? powitanie w du≈ºej literze.
  5. W przeciwnym razie:
    - ZwrÛ? powitanie w normalnej postaci. 


```




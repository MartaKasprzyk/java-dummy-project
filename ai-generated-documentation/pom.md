![Capgemini Logo](https://www.capgemini.com/wp-content/themes/capgemini2020/assets/images/logo.svg)

### Make it real.

---
## Dokumentacja skryptu pom.xml

**1. Przegl?d:**

Skrypt `pom.xml` jest plikiem konfiguracyjnym dla narz?dzia budowania Maven, definiuj?cym zale?no?ci projektu, konfiguracj? kompilacji oraz testy jednostkowe. 

**2. Narz?dzie buduj?ce:**

Maven

**3. Nazwa skryptu/pliku:**

pom.xml

**4. Szczegó?owa dokumentacja:**

* **Sekcja `<properties>`:**
    *   **Opis:** Definiuje w?a?ciwo?ci projektu, takie jak wersja j?zyka Java (`maven.compiler.source`, `maven.compiler.target`) oraz wersje u?ywanych bibliotek testów (`junit.jupiter.version`).
    *   **Parametry:**
        *   `maven.compiler.source`: Wersja j?zyka Java docelowa dla kompilacji (17 w tym przypadku).
        *   `maven.compiler.target`: Wersja j?zyka Java, która b?dzie u?ywana przez skrypt po skompilowaniu (17 w tym przypadku).
        *   `project.build.sourceEncoding`: Kodowanie ?ród?owe projektu (UTF-8).
        *   `junit.jupiter.version`: Wersja biblioteki JUnit Jupiter.

* **Sekcja `<dependencies>`:**
    *   **Opis:** Definiuje zale?no?ci projektu, czyli biblioteki zewn?trzne, których skrypt potrzebuje do dzia?ania. 
    *   **Parametry:**
        *   `groupId`, `artifactId`, `version`: Identyfikatory i wersje zale?nosci.
        *   `scope`: Okre?la zakres u?ycia zale?no?ci (np. `test` dla bibliotek testów).

* **Sekcja `<build>`:**
    *   **Opis:** Definiuje konfiguracj? procesu budowania, w tym pluginy Maven odpowiedzialne za wykonywanie zada? podczas budowy projektu.
    *   **Parametry:**
        *   `<plugins>`: Zawiera definicje pluginów Maven.

* **Plugin `<maven-surefire-plugin>`:**
    *   **Opis:** Plugin odpowiedzialny za uruchamianie testów jednostkowych w projekcie.


**5. Wersja j?zyka:**

Java 17 (oznaczone przez `maven.compiler.source` i `maven.compiler.target`)

**6. Wersje zale?no?ci:**

*   Mockito: 5.6.0
*   AssertJ: 3.24.2
*   JUnit Jupiter Engine: 5.10.0
*   JUnit Jupiter API: 5.10.0
*   Mockito JUnit Jupiter: 5.6.0

**7. Pseudokod:**


```
# Proces budowania projektu z pom.xml

1.  Wczytaj plik pom.xml do narz?dzia Maven.
2.  Pobierz wszystkie zale?no?ci zdefiniowane w sekcji `<dependencies>`.
3.  Ustaw w?a?ciwo?ci projektu zdefiniowane w sekcji `<properties>`, takie jak wersja j?zyka Java i wersje bibliotek testów.
4.  Wykonaj konfiguracj? kompilacji, u?ywaj?c ustawie? zdefiniowanych w sekcji `<build>`, np. wersja j?zyka Java docelowa.
5.  Uruchom plugin `<maven-surefire-plugin>` do uruchomienia testów jednostkowych.
6.  Zbuduj projekt i skompiluj kod ?ród?owy, u?ywaj?c wersji j?zyka Java zdefiniowanej w sekcji `<properties>`.



```

**8. Odpowiedniki zale?no?ci i pluginów:**

*   Maven: Gradle (z podobnymi konfiguracjami)
*   Mockito: Jest odpowiednik Mockito dla Gradle - `org.mockito`
*   AssertJ: Jest odpowiednik AssertJ dla Gradle - `org.assertj`
*   JUnit Jupiter: JUnit 5 jest dost?pny w Gradle, z pluginem `junit-platform`.




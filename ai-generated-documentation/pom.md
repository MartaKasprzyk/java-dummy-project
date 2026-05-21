![Capgemini Logo](https://www.capgemini.com/wp-content/themes/capgemini2020/assets/images/logo.svg)

### Make it real.

---
## Dokumentacja skryptu pom.xml

**1. Przegl?d:**

Skrypt `pom.xml` jest plikiem konfiguracyjnym dla narz?dzia budowania Maven, u?ywanego do zarz?dzania zale?no?ciami, kompilacji i testowania projektu Java. 

**2. Narz?dzie buduj?ce:**

Maven

**3. Nazwa skryptu/pliku:**

`pom.xml`

**4. Szczegó?owa dokumentacja:**

   - **Sekcja `<properties>`:**
     - **Opis:** Definiuje w?a?ciwo?ci projektu, takie jak wersja j?zyka Java (source i target), kodowanie ?ród?owe oraz wersje u?ywanych bibliotek testowych.
     - **Parametry:**
       - `maven.compiler.source`: Wersja j?zyka Java docelowa dla kompilacji (17).
       - `maven.compiler.target`: Wersja j?zyka Java, która b?dzie u?ywana przez skrypt (17).
       - `project.build.sourceEncoding`: Kodowanie ?ród?owe projektu (UTF-8).
       - `junit.jupiter.version`: Wersja biblioteki JUnit Jupiter (5.10.0).

   - **Sekcja `<dependencies>`:**
     - **Opis:** Definiuje zale?no?ci projektu, czyli biblioteki zewn?trzne, których skrypt potrzebuje do dzia?ania.
     - **Parametry:**
       - `groupId`, `artifactId`, `version`: Identyfikatory i wersje zale?nosci.
       - `scope`: Typ zakresu u?ycia zale?no?ci (np. `test` dla zale?no?ci testowych).

   - **Sekcja `<build>`:**
     - **Opis:** Definiuje konfiguracj? procesu budowania projektu, w tym u?ywane pluginy.
     - **Parametry:**
       - `<plugins>`: Lista pluginów Maven u?ywanych do budowy projektu.


**5. Wersja j?zyka:**

Java 17

**6. Wersje zale?no?ci:**

   - `mockito-core`: 5.6.0
   - `assertj-core`: 3.24.2
   - `junit-jupiter-engine`: 5.10.0
   - `junit-jupiter-api`: 5.10.0
   - `mockito-junit-jupiter`: 5.6.0

**7. Pseudokod:**

```
// Proces budowania projektu zdefiniowany w pliku pom.xml:

1.  Pobierz wszystkie zale?no?ci zdefiniowane w sekcji `<dependencies>`.
2.  U?yj pluginu `maven-surefire-plugin` do uruchomienia testów jednostkowych zdefiniowanych w projekcie.
3.  Skonfiguruj kompilacj? kodu ?ród?owego Java zgodnie z wersj? j?zyka Java zdefiniowan? w `<properties>`.
4.  Uruchom proces pakowania projektu, tworz?c plik jar lub inne formaty wyj?ciowe.

```


**8. Odpowiedniki zale?no?ci i pluginów:**

   - **Maven:** Gradle (w przypadku u?ycia `gradle.build`), npm (w przypadku JavaScript)




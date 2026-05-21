![Capgemini Logo](https://www.capgemini.com/wp-content/themes/capgemini2020/assets/images/logo.svg)

### Make it real.

---
![Logo Capgemini](https://www.capgemini.com/wp-content/themes/capgemini2020/assets/images/logo.svg)

### Zrób to realnie.

---

## Dokumentacja pliku pom.xml

**1. Podsumowanie:**

Plik `pom.xml` jest plikiem konfiguracyjnym narzędzia do budowania Maven, definiującym sposób budowy i zarządzania projektem Java o nazwie "dummy-java-project". Określa metadane projektu (ID grupy, ID artefaktu, wersja), zależności od innych bibliotek (w tym frameworków testowych), ustawienia kompilatora oraz wtyczki do wykonywania zadań takich jak uruchamianie testów.

**2. Narzędzie Budowy:** Maven

**3. Nazwa Skryptu/Pliku:** pom.xml

**4. Szczegółowa Dokumentacja:**

   - **Metadane Projektu:**
     - **Opis:** Definiuje podstawowe informacje o projekcie, w tym jego ID grupy (org.example), ID artefaktu (dummy-java-project) oraz wersję (1.0-SNAPSHOT).
     - **Parametry:** 
       - `groupId`: Unikalny identyfikator projektu w ramach większej struktury organizacyjnej.
       - `artifactId`: Unikatowa nazwa projektu w jego grupie.
       - `version`: Aktualna wersja projektu.

   - **Właściwości:**
     - **Opis:** Ustawia różne wartości konfiguracyjne używane podczas całego procesu budowania.
     - **Parametry:** 
       - `maven.compiler.source`: Określa wersję kodu źródłowego Java (17 w tym przypadku).
       - `maven.compiler.target`: Określa wersję bajtów kodowych Java (17).
       - `project.build.sourceEncoding`: Ustawia kodowanie znaków dla plików źródłowych (UTF-8).
       - `junit.jupiter.version`: Definiuje wersję JUnit Jupiter używaną do testowania.

   - **Zależności:**
     - **Opis:** Lista zewnętrznych bibliotek wymaganych przez projekt, wraz z ich ID grupy, ID artefaktu, wersją oraz zakresem.
     - **Parametry:** 
       - `groupId`: Unikalny identyfikator dostawcy biblioteki.
       - `artifactId`: Nazwa biblioteki w jej grupie.
       - `version`: Specyficzna wersja biblioteki do użycia.
       - `scope`: Określa fazę cyklu życia, w której zależność jest używana (np. "test" dla zależności testowych).

     - **Ważna Logika:** 
       - Skrypt zawiera zależności od:
         - Mockito (do mockowania): Używane w testach jednostkowych.
         - AssertJ (do asercji): Zapewnia płynne i czytelne metody asercji.
         - JUnit Jupiter (framework testowy):  Zapewnia podstawową funkcjonalność do pisania i uruchamiania testów.

   - **Wtyczki:**
     - **Opis:** Definiuje wtyczki, które rozszerzają możliwości Maven, takie jak uruchamianie testów.
     - **Parametry:** 
       - `groupId`: Unikalny identyfikator dostawcy wtyczki.
       - `artifactId`: Nazwa wtyczki w jej grupie.
       - `version`: Specyficzna wersja wtyczki do użycia.

     - **Ważna Logika:** 
       - Skrypt zawiera "maven-surefire-plugin", który odpowiada za wykonywanie testów zdefiniowanych w projekcie.


**5. Wersja Języka:** Java (na podstawie podanych ustawień kompilatora)

**6. Wersje Zależności:**

   - Mockito: 5.6.0
   - AssertJ: 3.24.2
   - JUnit Jupiter: 5.10.0

**7. Pseudo Kod:**


```
// Ustawienia Projektu (pom.xml)

1. Zdefiniuj metadane projektu:
    - ID Grupy: "org.example"
    - ID Artefaktu: "dummy-java-project"
    - Wersja: "1.0-SNAPSHOT"

2. Ustaw właściwości:
    - Wersja kodu źródłowego Java: 17
    - Wersja bajtów kodowych Java: 17
    - Kodowanie źródeł: UTF-8
    - Wersja JUnit Jupiter: 5.10.0

3. Zdefiniuj zależności:
    - Mockito (wersja 5.6.0) - Zakres: test
    - AssertJ (wersja 3.24.2) - Zakres: test
    - JUnit Jupiter Engine (wersja 5.10.0) - Zakres: test
    - JUnit Jupiter API (wersja 5.10.0) - Zakres: test
    - Mockito JUnit Jupiter Adapter (wersja 5.6.0) - Zakres: test

4. Konfiguruj wtyczki:
    - Maven Surefire Plugin (wersja 3.0.0): Odpowiedzialny za uruchamianie testów.


// Proces Budowy (Wyzwalany przez polecenia Maven takie jak "mvn clean compile test")

1. Skompiluj kod źródłowy Java używając podanej wersji Java (17).
2. Uruchom testy zdefiniowane w projekcie przy użyciu wtyczki Maven Surefire Plugin.
    - Wtyczka użyje JUnit Jupiter do wykonywania testów i generowania raportów.
3. Wygeneruj raporty na podstawie wykonania testów (jeśli skonfigurowano).



```


**8. Ekwiwalenty Zależności i Wtyczek:**

* **Maven:** 
   -  Gradle: Gradle używa podobnej struktury skryptu budowania z sekcjami "dependencies" i "plugins". Równoważne wtyczki byłyby "java" do kompilacji kodu Java, "test" do uruchamiania testów oraz potencjalnie inne w zależności od potrzeb projektu.
   - npm (Node.js): npm jest głównie używany do zarządzania zależnością JavaScript. Nie przekłada się bezpośrednio na funkcjonalność Maven. Do budowania i testowania projektów Node.js powszechnie stosuje się narzędzia takie jak Webpack lub Parcel.




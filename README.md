# Wprowadzenie
Projekt składa się z 4 modułów.
<ul>
    <li>Moduł "TableSku" - jest głownym modułem, zawiera aplikację serwerową w JavaFX, służy do pobierania danych z plików TXT, XML, JSON i z bazy danych. Wyświetla pobrane dane w tabeli</li>
    <li>Moduł "aplikacjaKlienta" - jest modułem, który pobiera dane z bazy danych i SOAP. Wyświetla dane w tabeli</li>
    <li>Moduł automat - zawiera automat do samodzielnego klikania po menu</li>
    <li>Moduł skudb - zawiera aplikację RESTową napisaną w SpringBoot, wystawia endpointy do zapisywania danych w bazie danych i pobierania ich. Baza danych to H2</li>
</ul>


Zadanie przedstawia GUI z tabelą, która wypełniana jest danymi sczytywanymi z plików .txt, .xml, i .json. 
W tabeli każda komórka jest edytowalna, wprowadzane dane są walidowane przed zapisaniem,
jeśli dane nie spełniają reguł walidacji to nie zostaną zapisane i powróci poprzednia wartość.
Dane wprowadza się poprzez podwójne kliknięcie myszką na komórkę, 
wprowadzeniu danych z klawiatury i kliknięciu klawisza "Enter".
Nowy wiersz dodajemy przyciskiem "Nowy wiersz" pod tabelą.

## Środowisko
Zadanie zostało napisane z użyciem:
<ul>
<li>Java 18 (OpenJDK 18)</li>
<li>Maven 3.6.3</li>
<li>Bibloteki JavaFX</li>
</ul>





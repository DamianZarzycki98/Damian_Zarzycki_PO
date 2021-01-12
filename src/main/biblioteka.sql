-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 12 Sty 2021, 19:19
-- Wersja serwera: 10.4.17-MariaDB
-- Wersja PHP: 8.0.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `biblioteka`
--
CREATE DATABASE IF NOT EXISTS `biblioteka` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `biblioteka`;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `autor`
--

DROP TABLE IF EXISTS `autor`;
CREATE TABLE `autor` (
  `Id_Autora` int(11) NOT NULL,
  `Imie` text NOT NULL,
  `Nazwisko` text NOT NULL,
  `Rok_Urodzenia` int(11) NOT NULL,
  `Narodowosc` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `autor`
--

INSERT INTO `autor` (`Id_Autora`, `Imie`, `Nazwisko`, `Rok_Urodzenia`, `Narodowosc`) VALUES
(1, 'Adam', 'Mickiewicz', 1798, 'Polska'),
(2, 'Henryk', 'Sienkiewicz', 1846, 'Polska');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `czytelnik`
--

DROP TABLE IF EXISTS `czytelnik`;
CREATE TABLE `czytelnik` (
  `Id_Czytelnika` int(11) NOT NULL,
  `Imie` text NOT NULL,
  `Nazwisko` text NOT NULL,
  `Miejscowosc` text NOT NULL,
  `Nr_Telefonu` int(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `czytelnik`
--

INSERT INTO `czytelnik` (`Id_Czytelnika`, `Imie`, `Nazwisko`, `Miejscowosc`, `Nr_Telefonu`) VALUES
(1, 'Damian', 'Zarzycki', 'Biłgoraj', 792321654),
(2, 'Honorata', 'Boruc', 'Warszawa', 523476912);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `egzemplarz`
--

DROP TABLE IF EXISTS `egzemplarz`;
CREATE TABLE `egzemplarz` (
  `Id_Egzemplarza` int(11) NOT NULL,
  `Id_Ksiazki` int(11) NOT NULL,
  `Ilosc_Ksiazek` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `egzemplarz`
--

INSERT INTO `egzemplarz` (`Id_Egzemplarza`, `Id_Ksiazki`, `Ilosc_Ksiazek`) VALUES
(6, 4, 6),
(8, 1, 6),
(9, 2, 9),
(10, 3, 120);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `ksiazka`
--

DROP TABLE IF EXISTS `ksiazka`;
CREATE TABLE `ksiazka` (
  `Id_Ksiazki` int(11) NOT NULL,
  `Tytul` text NOT NULL,
  `Rok_Wydania` int(11) NOT NULL,
  `Id_Autora` int(11) NOT NULL,
  `Id_Wydawnictwa` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `ksiazka`
--

INSERT INTO `ksiazka` (`Id_Ksiazki`, `Tytul`, `Rok_Wydania`, `Id_Autora`, `Id_Wydawnictwa`) VALUES
(1, 'Pan Tadeusz', 1834, 1, 1),
(2, 'Ballady i romanse', 1822, 1, 2),
(3, 'Potop', 1886, 2, 2),
(4, 'Ogniem i mieczem', 1884, 2, 1);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `wydawnictwo`
--

DROP TABLE IF EXISTS `wydawnictwo`;
CREATE TABLE `wydawnictwo` (
  `Id_Wydawnictwa` int(11) NOT NULL,
  `Nazwa_Wydawnictwa` text NOT NULL,
  `Siedziba_Glowna` text NOT NULL,
  `E-mail` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `wydawnictwo`
--

INSERT INTO `wydawnictwo` (`Id_Wydawnictwa`, `Nazwa_Wydawnictwa`, `Siedziba_Glowna`, `E-mail`) VALUES
(1, 'Wydawnictwo Naukowe PWN', 'Warszawa ', 'pwn@ksiazki.com'),
(2, 'Wydawnictwo Nowa Era', 'Kraków', 'nowa.era@gmail.com');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `wypozyczenia`
--

DROP TABLE IF EXISTS `wypozyczenia`;
CREATE TABLE `wypozyczenia` (
  `Id_Wypozyczenia` int(11) NOT NULL,
  `Id_Ksiazki` int(11) NOT NULL,
  `Id_Czytelnika` int(11) NOT NULL,
  `Data_Wypozyczenia` date NOT NULL,
  `Data_Oddania` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `wypozyczenia`
--

INSERT INTO `wypozyczenia` (`Id_Wypozyczenia`, `Id_Ksiazki`, `Id_Czytelnika`, `Data_Wypozyczenia`, `Data_Oddania`) VALUES
(2, 1, 1, '2020-12-08', '2021-01-07'),
(3, 3, 2, '2021-01-01', '2021-01-04');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `autor`
--
ALTER TABLE `autor`
  ADD PRIMARY KEY (`Id_Autora`);

--
-- Indeksy dla tabeli `czytelnik`
--
ALTER TABLE `czytelnik`
  ADD PRIMARY KEY (`Id_Czytelnika`);

--
-- Indeksy dla tabeli `egzemplarz`
--
ALTER TABLE `egzemplarz`
  ADD PRIMARY KEY (`Id_Egzemplarza`),
  ADD KEY `Id_Ksiazki` (`Id_Ksiazki`);

--
-- Indeksy dla tabeli `ksiazka`
--
ALTER TABLE `ksiazka`
  ADD PRIMARY KEY (`Id_Ksiazki`),
  ADD KEY `Id_Autora` (`Id_Autora`),
  ADD KEY `Id_Wydawnictwa` (`Id_Wydawnictwa`);

--
-- Indeksy dla tabeli `wydawnictwo`
--
ALTER TABLE `wydawnictwo`
  ADD PRIMARY KEY (`Id_Wydawnictwa`);

--
-- Indeksy dla tabeli `wypozyczenia`
--
ALTER TABLE `wypozyczenia`
  ADD PRIMARY KEY (`Id_Wypozyczenia`),
  ADD KEY `Id_Czytelnika` (`Id_Czytelnika`),
  ADD KEY `Id_Ksiazki` (`Id_Ksiazki`);

--
-- AUTO_INCREMENT dla zrzuconych tabel
--

--
-- AUTO_INCREMENT dla tabeli `autor`
--
ALTER TABLE `autor`
  MODIFY `Id_Autora` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT dla tabeli `czytelnik`
--
ALTER TABLE `czytelnik`
  MODIFY `Id_Czytelnika` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT dla tabeli `egzemplarz`
--
ALTER TABLE `egzemplarz`
  MODIFY `Id_Egzemplarza` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT dla tabeli `ksiazka`
--
ALTER TABLE `ksiazka`
  MODIFY `Id_Ksiazki` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT dla tabeli `wydawnictwo`
--
ALTER TABLE `wydawnictwo`
  MODIFY `Id_Wydawnictwa` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT dla tabeli `wypozyczenia`
--
ALTER TABLE `wypozyczenia`
  MODIFY `Id_Wypozyczenia` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `egzemplarz`
--
ALTER TABLE `egzemplarz`
  ADD CONSTRAINT `egzemplarz_ibfk_1` FOREIGN KEY (`Id_Ksiazki`) REFERENCES `ksiazka` (`Id_Ksiazki`);

--
-- Ograniczenia dla tabeli `ksiazka`
--
ALTER TABLE `ksiazka`
  ADD CONSTRAINT `ksiazka_ibfk_1` FOREIGN KEY (`Id_Autora`) REFERENCES `autor` (`Id_Autora`),
  ADD CONSTRAINT `ksiazka_ibfk_2` FOREIGN KEY (`Id_Wydawnictwa`) REFERENCES `wydawnictwo` (`Id_Wydawnictwa`);

--
-- Ograniczenia dla tabeli `wypozyczenia`
--
ALTER TABLE `wypozyczenia`
  ADD CONSTRAINT `wypozyczenia_ibfk_2` FOREIGN KEY (`Id_Czytelnika`) REFERENCES `czytelnik` (`Id_Czytelnika`),
  ADD CONSTRAINT `wypozyczenia_ibfk_3` FOREIGN KEY (`Id_Ksiazki`) REFERENCES `ksiazka` (`Id_Ksiazki`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

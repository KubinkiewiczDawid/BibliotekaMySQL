-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 19 Maj 2019, 14:51
-- Wersja serwera: 10.1.37-MariaDB
-- Wersja PHP: 7.3.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `dawid`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `book`
--

CREATE TABLE `book` (
  `id` int(11) NOT NULL,
  `title` varchar(20) COLLATE utf8_bin NOT NULL,
  `author` varchar(20) COLLATE utf8_bin NOT NULL,
  `publishedYear` smallint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Zrzut danych tabeli `book`
--

INSERT INTO `book` (`id`, `title`, `author`, `publishedYear`) VALUES
(1, 'Migracje', 'Zofia Kawczyńska', 2009),
(4, 'Elektronika', 'Zofia Miral', 2007),
(5, 'LOTR', 'Tolkien', 1998);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `pracownicy`
--

CREATE TABLE `pracownicy` (
  `id` int(11) NOT NULL,
  `imie` varchar(12) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `nazwisko` varchar(15) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `placa` int(5) NOT NULL,
  `tytul` varchar(10) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Zrzut danych tabeli `pracownicy`
--

INSERT INTO `pracownicy` (`id`, `imie`, `nazwisko`, `placa`, `tytul`) VALUES
(1, 'Dawid', 'Kubinkiewicz', 4500, 'Inzynier'),
(2, 'Maksymilian', 'Wojcik', 11300, 'Inzynier'),
(8, 'Maciek', 'Kubinkiewicz', 0, 'Uczen'),
(9, 'Oski', 'PamPam', 21312, 'Profesor'),
(10, 'Oski', 'PamPam', 21312, 'Profesor'),
(11, 'Oski', 'PamPam', 21312, 'Profesor');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `rent`
--

CREATE TABLE `rent` (
  `id` int(11) NOT NULL,
  `user` int(11) NOT NULL,
  `book` int(11) NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `name` varchar(20) COLLATE utf8_bin NOT NULL,
  `lastname` varchar(20) COLLATE utf8_bin NOT NULL,
  `tephoneNumber` int(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Zrzut danych tabeli `user`
--

INSERT INTO `user` (`id`, `name`, `lastname`, `tephoneNumber`) VALUES
(14, 'Martyna', 'Kseniak', 421532123),
(15, 'Dawid', 'Kubinkiewicz', 510192527);

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `pracownicy`
--
ALTER TABLE `pracownicy`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `rent`
--
ALTER TABLE `rent`
  ADD PRIMARY KEY (`id`),
  ADD KEY `book` (`book`),
  ADD KEY `user` (`user`);

--
-- Indeksy dla tabeli `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT dla tabeli `book`
--
ALTER TABLE `book`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT dla tabeli `pracownicy`
--
ALTER TABLE `pracownicy`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT dla tabeli `rent`
--
ALTER TABLE `rent`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `rent`
--
ALTER TABLE `rent`
  ADD CONSTRAINT `rent_ibfk_1` FOREIGN KEY (`user`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `rent_ibfk_2` FOREIGN KEY (`book`) REFERENCES `book` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `rent_ibfk_3` FOREIGN KEY (`user`) REFERENCES `user` (`id`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

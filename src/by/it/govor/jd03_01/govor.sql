-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Хост: 127.0.0.1:2016
-- Время создания: Ноя 30 2017 г., 12:04
-- Версия сервера: 10.1.10-MariaDB
-- Версия PHP: 5.6.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `govor`
--

-- --------------------------------------------------------

--
-- Структура таблицы `ad`
--

CREATE TABLE `ad` (
  `ID` int(11) NOT NULL,
  `processor` varchar(15) NOT NULL,
  `video_card` varchar(15) NOT NULL,
  `motherboard` varchar(15) NOT NULL,
  `ram` varchar(15) NOT NULL,
  `power_supply` varchar(15) NOT NULL,
  `HDD` varchar(15) NOT NULL,
  `user_ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `ad`
--

INSERT INTO `ad` (`ID`, `processor`, `video_card`, `motherboard`, `ram`, `power_supply`, `HDD`, `user_ID`) VALUES
(1, 'Intel i5 8600k', 'GTX 1080 TI', 'Z 270', '32,3200', '700W', '3Tb', 1);

-- --------------------------------------------------------

--
-- Структура таблицы `Address`
--

CREATE TABLE `address` (
  `ID` int(11) NOT NULL,
  `Country` varchar(15) NOT NULL,
  `City` varchar(15) NOT NULL,
  `user_ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблицы `role`
--

CREATE TABLE `role` (
  `ID` int(11) NOT NULL,
  `Role` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `role`
--

INSERT INTO `role` (`ID`, `Role`) VALUES
(1, 'Admin'),
(2, 'User'),
(3, 'Boss'),
(4, 'Observer');

-- --------------------------------------------------------

--
-- Структура таблицы `user`
--

CREATE TABLE `user` (
  `ID` int(11) NOT NULL,
  `Login` varchar(15) NOT NULL,
  `Password` varchar(15) NOT NULL,
  `Name` varchar(20) NOT NULL,
  `Nick` varchar(10) NOT NULL,
  `Telephone` int(12) NOT NULL,
  `role_ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `user`
--

INSERT INTO `user` (`ID`, `Login`, `Password`, `Name`, `Nick`, `Telephone`, `role_ID`) VALUES
(1, 'dfgdg', 'dgdg', 'WTF', 'IO', 345332, 1),
(3, 'dddd', 'ffff', 'Kto-to', 'Kto', 464363, 3),
(3757, 'gggg', 'gggg', 'Name 53535-2', 'Nagibator2', 53325, 2),
(3763, 'Login1', 'pass', 'name', 'nick', 243, 1),
(3764, 'Login1', 'pass', 'name', 'nick', 243, 1),
(3765, 'Login1', 'pass', 'name', 'nick', 243, 1),
(3766, 'Login1', 'pass', 'name', 'nick', 243, 1),
(3767, 'Login1', 'pass', 'name', 'nick', 243, 1),
(3771, 'Login1', 'pass', 'name', 'nick', 243, 1);

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `ad`
--
ALTER TABLE `ad`
  ADD PRIMARY KEY (`ID`,`user_ID`),
  ADD KEY `fk_ad_user_idx` (`user_ID`);

--
-- Индексы таблицы `Address`
--
ALTER TABLE `address`
  ADD PRIMARY KEY (`ID`,`user_ID`),
  ADD KEY `fk_adres_user1_idx` (`user_ID`);

--
-- Индексы таблицы `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`ID`);

--
-- Индексы таблицы `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`ID`,`role_ID`),
  ADD KEY `fk_user_role1_idx` (`role_ID`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `ad`
--
ALTER TABLE `ad`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT для таблицы `Address`
--
ALTER TABLE `address`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT для таблицы `role`
--
ALTER TABLE `role`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT для таблицы `user`
--
ALTER TABLE `user`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3772;
--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `ad`
--
ALTER TABLE `ad`
  ADD CONSTRAINT `fk_ad_user` FOREIGN KEY (`user_ID`) REFERENCES `user` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Ограничения внешнего ключа таблицы `Address`
--
ALTER TABLE `address`
  ADD CONSTRAINT `fk_adres_user1` FOREIGN KEY (`user_ID`) REFERENCES `user` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Ограничения внешнего ключа таблицы `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `fk_user_role1` FOREIGN KEY (`role_ID`) REFERENCES `role` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

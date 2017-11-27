-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Хост: localhost
-- Время создания: Ноя 27 2017 г., 11:44
-- Версия сервера: 5.7.16-log
-- Версия PHP: 7.0.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `shelkovich`
--
CREATE DATABASE IF NOT EXISTS `shelkovich` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `shelkovich`;

-- --------------------------------------------------------

--
-- Структура таблицы `comments`
--

DROP TABLE IF EXISTS `comments`;
CREATE TABLE `comments` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `post_id` int(11) NOT NULL,
  `text` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `comments`
--

INSERT INTO `comments` (`id`, `user_id`, `post_id`, `text`) VALUES
(1, 1, 2, 'Круто! Очень симпатично вышло!'),
(2, 3, 2, 'Спасибо, приходите ещё!');

-- --------------------------------------------------------

--
-- Структура таблицы `follow_links`
--

DROP TABLE IF EXISTS `follow_links`;
CREATE TABLE `follow_links` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `follower_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `follow_links`
--

INSERT INTO `follow_links` (`id`, `user_id`, `follower_id`) VALUES
(1, 3, 2),
(2, 2, 1),
(3, 2, 3);

-- --------------------------------------------------------

--
-- Структура таблицы `posts`
--

DROP TABLE IF EXISTS `posts`;
CREATE TABLE `posts` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `imageURL` varchar(200) NOT NULL,
  `description` varchar(500) NOT NULL,
  `likes` int(11) NOT NULL,
  `lat` varchar(20) DEFAULT NULL,
  `lng` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `posts`
--

INSERT INTO `posts` (`id`, `user_id`, `imageURL`, `description`, `likes`, `lat`, `lng`) VALUES
(1, 1, '/img/2017/11/24/1.jpg', 'Переобул резину на моей малыхе, потому что скоро снег!', 3, '53.9395326', '27.5886809'),
(2, 2, '/img/2017/11/24/2.jpg', 'Была сегодня в @nails_salon, очень довольна! Смотрите как красиво получилось!', 17, NULL, NULL),
(3, 3, '/img/2017/11/24/3.jpg', 'Акция! Рисуем паучков на ногтях! Всего 50 рублей! Не пропусти акцию в честь чёрной пятницы!', 250, NULL, NULL);

-- --------------------------------------------------------

--
-- Структура таблицы `roles`
--

DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `id` int(11) NOT NULL,
  `role` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `roles`
--

INSERT INTO `roles` (`id`, `role`) VALUES
(1, 'Администратор'),
(2, 'Модератор'),
(3, 'Пользователь');

-- --------------------------------------------------------

--
-- Структура таблицы `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `passhash` varchar(50) NOT NULL,
  `description` varchar(500) DEFAULT NULL,
  `role_id` int(11) NOT NULL DEFAULT '3',
  `name` varchar(50) DEFAULT NULL,
  `surname` varchar(50) DEFAULT NULL,
  `email` varchar(50) NOT NULL,
  `showPersonalData` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `users`
--

INSERT INTO `users` (`id`, `username`, `passhash`, `description`, `role_id`, `name`, `surname`, `email`, `showPersonalData`) VALUES
(1, 'vasily16', 'e10adc3949ba59abbe56e057f20f883e', 'Серьёзный парень на серьёзной тачке', 3, 'Василий', 'Ручкин', 'vasily16@gmail.com', 0),
(2, 'marina_e', 'd8578edf8458ce06fbc5bb76a58c5ca4', 'Владелица таксы и просто милаха! Мой вайбер 1312567, пишите!', 3, 'Марина', 'Емелина', 'emelina_m@tut.by', 1),
(3, 'nails_salon', 'd44779226be3e9d648bfe4713a0a9786', 'Салон ногтей "У Вероники". Лак, полировка, маникюр, педикюр, накладные, закладные, перекладные! +375(29)15-16-178', 3, 'Салон', 'Ногтей', 'nails1234@gmail.com', 1),
(4, 'admin', '8f728d6bfc4dd6cd00067451bf0fa0e4', 'Администратор', 1, 'Антон', 'Шелкович', 'anton.shelkovich@gmail.com', 0);

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `comments`
--
ALTER TABLE `comments`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `post_id` (`post_id`);

--
-- Индексы таблицы `follow_links`
--
ALTER TABLE `follow_links`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `follower_id` (`follower_id`);

--
-- Индексы таблицы `posts`
--
ALTER TABLE `posts`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Индексы таблицы `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD KEY `role_id` (`role_id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `comments`
--
ALTER TABLE `comments`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT для таблицы `follow_links`
--
ALTER TABLE `follow_links`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT для таблицы `posts`
--
ALTER TABLE `posts`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT для таблицы `roles`
--
ALTER TABLE `roles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT для таблицы `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `comments`
--
ALTER TABLE `comments`
  ADD CONSTRAINT `comments_ibfk_1` FOREIGN KEY (`post_id`) REFERENCES `posts` (`id`),
  ADD CONSTRAINT `comments_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Ограничения внешнего ключа таблицы `follow_links`
--
ALTER TABLE `follow_links`
  ADD CONSTRAINT `follow_links_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `follow_links_ibfk_2` FOREIGN KEY (`follower_id`) REFERENCES `users` (`id`);

--
-- Ограничения внешнего ключа таблицы `posts`
--
ALTER TABLE `posts`
  ADD CONSTRAINT `posts_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Ограничения внешнего ключа таблицы `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

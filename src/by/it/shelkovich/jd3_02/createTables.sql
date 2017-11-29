
CREATE TABLE `comments` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `post_id` int(11) NOT NULL,
  `text` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE `follow_links` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `follower_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE `posts` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `imageURL` varchar(200) NOT NULL,
  `description` varchar(500) NOT NULL,
  `likes` int(11) NOT NULL,
  `lat` varchar(20) DEFAULT NULL,
  `lng` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE `roles` (
  `id` int(11) NOT NULL,
  `role` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `passhash` varchar(50) NOT NULL,
  `description` varchar(500) DEFAULT NULL,
  `role_id` int(11) NOT NULL DEFAULT '3',
  `name` varchar(50) DEFAULT NULL,
  `surname` varchar(50) DEFAULT NULL,
  `email` varchar(50) NOT NULL,
  `showPersonalData` varchar(5) NOT NULL DEFAULT 'false'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


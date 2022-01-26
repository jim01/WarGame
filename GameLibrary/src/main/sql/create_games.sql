CREATE TABLE `leaderboard` (
   `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
   `game` varchar(100) DEFAULT NULL,
   `player` varchar(100) DEFAULT NULL,
   `wins` int(11) DEFAULT NULL,
   `updated` datetime DEFAULT NULL,
   PRIMARY KEY (`id`),
   UNIQUE KEY `game` (`game`,`player`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
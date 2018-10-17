-- noinspection SqlDialectInspectionForFile

-- noinspection SqlNoDataSourceInspectionForFile

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id`       INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
  `username`     VARCHAR(255) NOT NULL,
  `password`    VARCHAR (255) NOT NULL
);

DROP TABLE IF EXISTS `auth_tokens`;
CREATE TABLE `auth_tokens` (
  `id`       INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
  `token`     VARCHAR(255) NOT NULL,
  `username`  VARCHAR(255) NOT NULL,
  CONSTRAINT fk_username FOREIGN KEY (`username`) REFERENCES `users` (`username`)
);

DROP TABLE IF EXISTS `playlists`;
CREATE TABLE `playlists` (
  `id`       INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
  `name`     VARCHAR(255) DEFAULT NULL,
  `owner`    BIT DEFAULT FALSE
);

DROP TABLE IF EXISTS `tracks`;
CREATE TABLE `tracks` (
  `id`       INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
  `title`     VARCHAR(255) DEFAULT NULL,
  `performer`     VARCHAR(255) DEFAULT NULL,
  `duration`     INT DEFAULT 0,
  `album`     VARCHAR(255) DEFAULT NULL,
  `playcount`     INT DEFAULT 0,
  `publicationDate`     VARCHAR(255) DEFAULT NULL,
  `description`     VARCHAR(255) DEFAULT NULL,
  `offlineAvailable`     BIT DEFAULT FALSE,
);

DROP TABLE IF EXISTS `playlist_track`;
CREATE TABLE `playlist_track` (
  `playlist_id`  INT NOT NULL,
  `track_id`     INT NOT NULL,
  CONSTRAINT fk_playlist_id FOREIGN KEY (`playlist_id`) REFERENCES `playlists`(`id`),
  CONSTRAINT fk_track_id FOREIGN KEY (`track_id`) REFERENCES `tracks`(`id`),
);

INSERT INTO `users` (`username`, `password`) VALUES ('test', 'test123');
INSERT INTO `users` (`username`, `password`) VALUES ('testUser', 'test123');

INSERT INTO `auth_tokens` (`token`, `username`) VALUES ('randomToken', 'test');

INSERT INTO `playlists` (`name`, `owner`) VALUES ('Test playlist', TRUE);
INSERT INTO `playlists` (`name`, `owner`) VALUES ('Second test playlist', FALSE);
INSERT INTO `playlists` (`name`, `owner`) VALUES ('Third test playlist', FALSE);

INSERT INTO `tracks` (`title`, `performer`, `duration`, `album`, `playcount`, `publicationDate`, `description`, `offlineAvailable` ) VALUES ('My Day', 'Someone', 321, 'dont know', 0, null, 'desc',  FALSE);
INSERT INTO `tracks` (`title`, `performer`, `duration`, `album`, `playcount`, `publicationDate`, `description`, `offlineAvailable` ) VALUES ('My Night', 'Noone', 321, 'i know', 0, null, 'desc',  TRUE);

INSERT  INTO `playlist_track` (`playlist_id`, `track_id`) VALUES (2, 2);

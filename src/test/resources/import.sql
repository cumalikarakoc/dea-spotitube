-- noinspection SqlDialectInspectionForFile

-- noinspection SqlNoDataSourceInspectionForFile

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id`       INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
  `username`     VARCHAR(255) NOT NULL,
  `password`    VARCHAR (255) NOT NULL,
  `token` VARCHAR (25) DEFAULT NULL,
);

DROP TABLE IF EXISTS `playlists`;
CREATE TABLE `playlists` (
  `id`       INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
  `name`     VARCHAR(255) DEFAULT NULL,
  `owner_id`    INT NOT NULL,
    CONSTRAINT fk_owner FOREIGN KEY (`owner_id`) REFERENCES `users`(`id`),
);

DROP TABLE IF EXISTS `tracks`;
CREATE TABLE `tracks` (
  `id`       INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
  `title`     VARCHAR(255) NOT NULL,
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

INSERT INTO `users` (`username`, `password`, `token`) VALUES ('test', 'test123', 'token');
INSERT INTO `users` (`username`, `password`) VALUES ('testUser', 'test123');

INSERT INTO `playlists` (`name`, `owner_id`) VALUES ('Test playlist', 1);
INSERT INTO `playlists` (`name`, `owner_id`) VALUES ('Second test playlist', 1);
INSERT INTO `playlists` (`name`, `owner_id`) VALUES ('Third test playlist', 1);

INSERT INTO `tracks` (`title`, `performer`, `duration`, `album`, `playcount`, `publicationDate`, `description`, `offlineAvailable` ) VALUES ('My Day', 'Someone', 321, 'dont know', 0, null, 'desc',  FALSE);
INSERT INTO `tracks` (`title`, `performer`, `duration`, `album`, `playcount`, `publicationDate`, `description`, `offlineAvailable` ) VALUES ('My Night', 'Noone', 321, 'i know', 0, null, 'desc',  TRUE);

INSERT  INTO `playlist_track` (`playlist_id`, `track_id`) VALUES (2, 2);

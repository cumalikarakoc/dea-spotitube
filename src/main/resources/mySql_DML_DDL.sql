-- noinspection SqlDialectInspectionForFile

-- noinspection SqlNoDataSourceInspectionForFile

CREATE TABLE IF NOT EXISTS `users` (
  `id`       INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `username`     VARCHAR(255) NOT NULL UNIQUE,
  `password`    VARCHAR (255) NOT NULL,
  `token`     VARCHAR (255) NULL
);


CREATE TABLE IF NOT EXISTS `playlists` (
  `id`       INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `name`     VARCHAR(255) NOT NULL UNIQUE,
  `owner_id`    INT NOT NULL,
  FOREIGN KEY (`owner_id`) REFERENCES `users`(`id`) ON UPDATE CASCADE  ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS `tracks` (
  `id`       INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `title`     VARCHAR(255) NOT NULL,
  `performer`    VARCHAR(255) DEFAULT NULL,
  `duration`     INT DEFAULT 0,
  `album`         VARCHAR(255) DEFAULT NULL,
  `playcount`     INT DEFAULT 0,
  `publicationDate` VARCHAR(255) DEFAULT NULL,
  `description`     VARCHAR(255) DEFAULT NULL,
  `offlineAvailable` BIT DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS `playlist_track` (
  `playlist_id`  INT NOT NULL,
  `track_id`     INT NOT NULL,
  PRIMARY KEY (`playlist_id`, `track_id`),
  FOREIGN KEY (`playlist_id`) REFERENCES `playlists`(`id`) ON UPDATE CASCADE  ON DELETE CASCADE,
  FOREIGN KEY (`track_id`) REFERENCES `tracks`(`id`) ON UPDATE CASCADE  ON DELETE CASCADE
);

INSERT INTO `users` (`username`, `password`) VALUES ('admin', 'admin123');
INSERT INTO `users` (`username`, `password`) VALUES ('testUser', 'test123');

INSERT INTO `tracks` (`title`, `performer`, `duration`, `album`, `playcount`, `publicationDate`, `description`, `offlineAvailable` )
 VALUES ('Ocean and a rock', 'Lisa Hannigan', 337, 'Sea sew', 0, null, null,  FALSE);
INSERT INTO `tracks` (`title`, `performer`, `duration`, `album`, `playcount`, `publicationDate`, `description`, `offlineAvailable` )
VALUES ('So Long, Marianne', 'Leonard Cohen', 546, null, 37, '1-11-2001', 'Long version',  TRUE);
INSERT INTO `tracks` (`title`, `performer`, `duration`, `album`, `playcount`, `publicationDate`, `description`, `offlineAvailable` )
VALUES ('One', 'Metallica', 423, null, 37, '1-11-2001', 'Long version',  TRUE);
INSERT INTO `tracks` (`title`, `performer`, `duration`, `album`, `playcount`, `publicationDate`, `description`, `offlineAvailable` )
VALUES ('Song for someone', 'The Frames', 50, 'The Cost', 0, null, null, FALSE);
INSERT INTO `tracks` (`title`, `performer`, `duration`, `album`, `playcount`, `publicationDate`, `description`, `offlineAvailable` )
VALUES ('The Cost', 'The Frames', 423, null, 37, '10-01-2005', 'Title song from the Album The Cost',  TRUE);



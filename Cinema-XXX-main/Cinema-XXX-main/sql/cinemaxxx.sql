-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.24 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for cinemaxxx
CREATE DATABASE IF NOT EXISTS `cinemaxxx` /*!40100 DEFAULT CHARACTER SET armscii8 COLLATE armscii8_bin */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `cinemaxxx`;

-- Dumping structure for table cinemaxxx.cinemas
CREATE TABLE IF NOT EXISTS `cinemas` (
  `cinemaId` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`cinemaId`)
) ENGINE=InnoDB DEFAULT CHARSET=armscii8 COLLATE=armscii8_bin;

-- Dumping data for table cinemaxxx.cinemas: ~0 rows (approximately)
/*!40000 ALTER TABLE `cinemas` DISABLE KEYS */;
/*!40000 ALTER TABLE `cinemas` ENABLE KEYS */;

-- Dumping structure for table cinemaxxx.halls
CREATE TABLE IF NOT EXISTS `halls` (
  `hallId` int NOT NULL AUTO_INCREMENT,
  `numOfSeats` int NOT NULL,
  `cinemaId` int NOT NULL,
  PRIMARY KEY (`hallId`),
  KEY `cinemaId` (`cinemaId`),
  CONSTRAINT `cinemaId` FOREIGN KEY (`cinemaId`) REFERENCES `cinemas` (`cinemaId`)
) ENGINE=InnoDB DEFAULT CHARSET=armscii8 COLLATE=armscii8_bin;

-- Dumping data for table cinemaxxx.halls: ~0 rows (approximately)
/*!40000 ALTER TABLE `halls` DISABLE KEYS */;
/*!40000 ALTER TABLE `halls` ENABLE KEYS */;

-- Dumping structure for table cinemaxxx.movies
CREATE TABLE IF NOT EXISTS `movies` (
  `movieId` int NOT NULL AUTO_INCREMENT,
  `rating` int NOT NULL,
  `actors` varchar(50) COLLATE armscii8_bin NOT NULL,
  `title` varchar(50) COLLATE armscii8_bin NOT NULL DEFAULT '',
  `year` int NOT NULL,
  `genre` varchar(50) COLLATE armscii8_bin NOT NULL DEFAULT '',
  `description` text COLLATE armscii8_bin NOT NULL,
  `length` int NOT NULL,
  `hallId` int NOT NULL,
  PRIMARY KEY (`movieId`)
) ENGINE=InnoDB DEFAULT CHARSET=armscii8 COLLATE=armscii8_bin;

-- Dumping data for table cinemaxxx.movies: ~0 rows (approximately)
/*!40000 ALTER TABLE `movies` DISABLE KEYS */;
/*!40000 ALTER TABLE `movies` ENABLE KEYS */;

-- Dumping structure for table cinemaxxx.screenings
CREATE TABLE IF NOT EXISTS `screenings` (
  `screeningId` int NOT NULL AUTO_INCREMENT,
  `time` time NOT NULL DEFAULT '00:00:00',
  `movie` varchar(50) COLLATE armscii8_bin NOT NULL DEFAULT '0',
  `movieId` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`screeningId`)
) ENGINE=InnoDB DEFAULT CHARSET=armscii8 COLLATE=armscii8_bin;

-- Dumping data for table cinemaxxx.screenings: ~0 rows (approximately)
/*!40000 ALTER TABLE `screenings` DISABLE KEYS */;
/*!40000 ALTER TABLE `screenings` ENABLE KEYS */;

-- Dumping structure for table cinemaxxx.seats
CREATE TABLE IF NOT EXISTS `seats` (
  `seatId` int NOT NULL AUTO_INCREMENT,
  `row` int NOT NULL,
  `column` int NOT NULL,
  `hallId` int NOT NULL,
  `reserved` binary(1) NOT NULL,
  PRIMARY KEY (`seatId`),
  KEY `hallId` (`hallId`),
  KEY `column` (`column`),
  KEY `row` (`row`),
  CONSTRAINT `hallId` FOREIGN KEY (`hallId`) REFERENCES `halls` (`hallId`)
) ENGINE=InnoDB DEFAULT CHARSET=armscii8 COLLATE=armscii8_bin;

-- Dumping data for table cinemaxxx.seats: ~0 rows (approximately)
/*!40000 ALTER TABLE `seats` DISABLE KEYS */;
/*!40000 ALTER TABLE `seats` ENABLE KEYS */;

-- Dumping structure for table cinemaxxx.tickets
CREATE TABLE IF NOT EXISTS `tickets` (
  `ticketId` int NOT NULL AUTO_INCREMENT,
  `reservationName` varchar(50) COLLATE armscii8_bin NOT NULL DEFAULT '0',
  `seatsRow` int NOT NULL DEFAULT '0',
  `seatsColumn` int NOT NULL DEFAULT '0',
  `reservationMail` varchar(50) COLLATE armscii8_bin NOT NULL DEFAULT '0',
  PRIMARY KEY (`ticketId`),
  KEY `seatsRow` (`seatsRow`),
  KEY `seatsColumn` (`seatsColumn`),
  CONSTRAINT `seatsColumn` FOREIGN KEY (`seatsColumn`) REFERENCES `seats` (`column`),
  CONSTRAINT `seatsRow` FOREIGN KEY (`seatsRow`) REFERENCES `seats` (`row`)
) ENGINE=InnoDB DEFAULT CHARSET=armscii8 COLLATE=armscii8_bin;

-- Dumping data for table cinemaxxx.tickets: ~0 rows (approximately)
/*!40000 ALTER TABLE `tickets` DISABLE KEYS */;
/*!40000 ALTER TABLE `tickets` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;

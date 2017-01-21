-- phpMyAdmin SQL Dump
-- version 4.0.4.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 11, 2013 at 09:37 PM
-- Server version: 5.6.11
-- PHP Version: 5.5.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `bookbay`
--
CREATE DATABASE IF NOT EXISTS `bookbay` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `bookbay`;

-- --------------------------------------------------------

--
-- Table structure for table `admins`
--

CREATE TABLE IF NOT EXISTS `admins` (
  `login_name` varchar(255) NOT NULL,
  `password_hash` varchar(40) NOT NULL,
  PRIMARY KEY (`login_name`),
  UNIQUE KEY `login_name` (`login_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `bidhistory`
--

CREATE TABLE IF NOT EXISTS `bidhistory` (
  `book_isbn` varchar(13) NOT NULL,
  `book_title` varchar(255) NOT NULL,
  `seller_name` varchar(255) NOT NULL,
  `book_condition` varchar(255) NOT NULL,
  `buyer_name` varchar(255) NOT NULL,
  `bid_amount` double NOT NULL,
  PRIMARY KEY (`book_isbn`,`seller_name`,`book_condition`,`buyer_name`,`bid_amount`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bidhistory`
--

INSERT INTO `bidhistory` (`book_isbn`, `book_title`, `seller_name`, `book_condition`, `buyer_name`, `bid_amount`) VALUES
('1234567890123', 'Harry Potter', 'TestSeller', 'bad', 'test2', 150),
('1234567890123', '', 'TestSeller', 'bad', 'test3', 100),
('1234567890123', 'Harry Potter', 'TestSeller', 'bad', 'test3', 150),
('1234567890123', '', 'TestSeller', 'good', 'anmol', 99),
('1234567890123', 'Harry Potter', 'TestSeller', 'good', 'anmol', 100),
('1234567890123', '', 'TestSeller', 'good', 'test2', 20),
('1234567890123', 'Harry Potter', 'TestSeller', 'good', 'test2', 100),
('1234567890124', '', 'TestSeller', 'bad', 'test2', 99);

-- --------------------------------------------------------

--
-- Table structure for table `bids`
--

CREATE TABLE IF NOT EXISTS `bids` (
  `book_isbn` varchar(13) NOT NULL,
  `book_title` varchar(255) NOT NULL,
  `seller_name` varchar(255) NOT NULL,
  `book_condition` varchar(255) NOT NULL,
  `buyer_name` varchar(255) NOT NULL,
  `bid_amount` double NOT NULL,
  `buy_now` tinyint(1) NOT NULL,
  PRIMARY KEY (`book_isbn`,`seller_name`,`book_condition`,`buyer_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `books`
--

CREATE TABLE IF NOT EXISTS `books` (
  `isbn` varchar(13) NOT NULL,
  `title` varchar(255) NOT NULL,
  `author` varchar(255) NOT NULL,
  `year` year(4) NOT NULL,
  `publisher` varchar(255) NOT NULL,
  `genre` varchar(255) NOT NULL,
  `seller_name` varchar(255) NOT NULL,
  `price` double NOT NULL,
  `quantity` int(11) NOT NULL,
  `book_condition` varchar(255) NOT NULL,
  `buynow` double NOT NULL,
  `rating` int(11) NOT NULL DEFAULT '50',
  PRIMARY KEY (`isbn`,`seller_name`,`book_condition`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `books`
--

INSERT INTO `books` (`isbn`, `title`, `author`, `year`, `publisher`, `genre`, `seller_name`, `price`, `quantity`, `book_condition`, `buynow`, `rating`) VALUES
('1234567890123', 'Harry Potter', 'aut', 1990, 'pub', 'fiction', 'TestSeller', 20, 4, 'good', 100, 48),
('1234567890124', 'Harry Potter', 'aut', 1990, 'pub', 'fiction', 'TestSeller', 10, 2, 'bad', 100, 48),
('1234567890128', 'Hitchhikers Guide to The Galaxy', 'JK Rowling', 2016, 'Penguin', 'Fiction', 'anmol', 77, 2, 'Very New', 150, 50),
('1234567890129', 'Windows For Dummies', 'Smart Person', 2009, 'Penguin', 'Tech', 'ANMOL_1', 15, 9, 'New', 25, 50),
('15', 'Windows For Dummies', 'Smart Person', 2009, 'Penguin', 'Tech', 'ANMOL_1', 15, 9, 'New', 25, 50),
('77777777', 'iOS Programming', 'Steve', 2012, 'Apple', 'tech', 'anmol', 5, 1, 'new', 10, 50);

-- --------------------------------------------------------

--
-- Table structure for table `browsehistory`
--

CREATE TABLE IF NOT EXISTS `browsehistory` (
  `display_name` varchar(255) NOT NULL,
  `isbn` varchar(13) NOT NULL,
  `title` varchar(255) NOT NULL,
  `author` varchar(255) NOT NULL,
  `year` year(4) NOT NULL,
  `publisher` varchar(255) NOT NULL,
  `genre` varchar(255) NOT NULL,
  `seller_name` varchar(255) NOT NULL,
  PRIMARY KEY (`display_name`,`isbn`,`seller_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `browsehistory`
--

INSERT INTO `browsehistory` (`display_name`, `isbn`, `title`, `author`, `year`, `publisher`, `genre`, `seller_name`) VALUES
('anmol', '1234567890123', 'Harry Potter', 'aut', 1990, 'pub', 'fiction', 'TestSeller'),
('anmol', '77777777', 'iOS Programming', 'Steve', 2012, 'Apple', 'tech', 'anmol'),
('ANMOL_1', '1234567890129', 'Windows For Dummies', 'Smart Person', 2009, 'Penguin', 'Tech', 'ANMOL_1'),
('ANMOL_1', '15', 'Windows For Dummies', 'Smart Person', 2009, 'Penguin', 'Tech', 'ANMOL_1'),
('test2', '1234567890123', 'Harry Potter', 'aut', 1990, 'pub', 'fiction', 'TestSeller'),
('test2', '1234567890124', 'Harry Potter', 'aut', 1990, 'pub', 'fiction', 'TestSeller'),
('TestSeller', '1234567890123', 'Harry Potter', 'aut', 1990, 'pub', 'fic', 'TestSeller'),
('TestSeller', '1234567890124', 'Harry Potter', 'aut', 1990, 'pub', 'fiction', 'TestSeller'),
('TestSeller', '4561231452', 'harry pot and the cannabis', 'aut', 0000, 'pub', 'scifi', 'TestSeller');

-- --------------------------------------------------------

--
-- Table structure for table `complaints`
--

CREATE TABLE IF NOT EXISTS `complaints` (
  `book_isbn` varchar(255) NOT NULL,
  `seller_name` varchar(255) NOT NULL,
  `buyer_name` varchar(255) NOT NULL,
  PRIMARY KEY (`book_isbn`,`seller_name`,`buyer_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `complaints`
--

INSERT INTO `complaints` (`book_isbn`, `seller_name`, `buyer_name`) VALUES
('1234567890124', 'TestSeller', 'test2');

-- --------------------------------------------------------

--
-- Table structure for table `reviews`
--

CREATE TABLE IF NOT EXISTS `reviews` (
  `book_isbn` varchar(13) NOT NULL,
  `book_title` varchar(255) NOT NULL,
  `book_condition` varchar(255) NOT NULL,
  `seller_name` varchar(255) NOT NULL,
  `buyer_name` varchar(255) NOT NULL,
  `book_stars` tinyint(4) NOT NULL,
  `book_comments` text NOT NULL,
  `seller_stars` tinyint(4) NOT NULL,
  `seller_comments` varchar(255) NOT NULL,
  `buyer_stars` tinyint(4) NOT NULL,
  `buyer_comments` varchar(255) NOT NULL,
  `reviewed_by_buyer` tinyint(2) NOT NULL,
  `reviewed_by_seller` tinyint(2) NOT NULL,
  PRIMARY KEY (`book_isbn`,`book_condition`,`seller_name`,`buyer_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `reviews`
--

INSERT INTO `reviews` (`book_isbn`, `book_title`, `book_condition`, `seller_name`, `buyer_name`, `book_stars`, `book_comments`, `seller_stars`, `seller_comments`, `buyer_stars`, `buyer_comments`, `reviewed_by_buyer`, `reviewed_by_seller`) VALUES
('1234567890123', '', 'good', 'TestSeller', 'anmol', 3, 'Good book but it smells', 1, 'He smells', 5, 'YAY', 1, 1),
('1234567890123', 'Harry Potter', 'good', 'TestSeller', 'test2', 5, 'awesome', 5, 'great', 4, 'decent', 1, 1),
('1234567890123', 'Harry Potter', 'good', 'TestSeller', 'test3', 5, 'pretty good', 5, 'great', 4, 'decent', 1, 1),
('1234567890123', 'Harry Potter', 'good', 'TestSeller', 'test4', 5, 'okay', 5, 'great', 4, 'decent', 1, 1),
('1234567890124', '', 'bad', 'TestSeller', 'test2', 1, 'Not as listed', 1, 'Terrible', 5, 'Great buyer', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `suspendedbooks`
--

CREATE TABLE IF NOT EXISTS `suspendedbooks` (
  `isbn` varchar(13) NOT NULL,
  `seller_name` varchar(255) NOT NULL,
  PRIMARY KEY (`isbn`,`seller_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `login_name` varchar(255) NOT NULL,
  `password_hash` varchar(40) NOT NULL,
  `display_name` varchar(255) NOT NULL,
  `warnings` tinyint(4) NOT NULL,
  `credits` double NOT NULL,
  `suspended` tinyint(1) NOT NULL,
  `rating` int(11) NOT NULL,
  `accepted` tinyint(1) NOT NULL DEFAULT '0',
  `changedpass` tinyint(1) NOT NULL DEFAULT '0',
  `admin` tinyint(1) NOT NULL DEFAULT '0',
  UNIQUE KEY `login_name` (`login_name`),
  UNIQUE KEY `display_name` (`display_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`login_name`, `password_hash`, `display_name`, `warnings`, `credits`, `suspended`, `rating`, `accepted`, `changedpass`, `admin`) VALUES
('ANMOL', 'e878216221a39547800f53216f3b06c63a1fb40b', 'ANMOL_1', 0, 5000, 0, 50, 1, 1, 0),
('anmol1', 'd44bdaf0ed77ad86f21843702dc73ecadc78599b', 'anmol', 0, 8902, 0, 52, 1, 1, 0),
('test', '23c50a0d065f79234a0622ccb6667c1346b3ddd2', 'TestSeller', 0, 1707.1, 0, 48, 1, 1, 1),
('test2', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'test2', 0, 8731, 0, 53, 1, 1, 0);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

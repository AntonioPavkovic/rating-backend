-- phpMyAdmin SQL Dump
-- version 5.1.3
-- https://www.phpmyadmin.net/
--
-- Host: mariadb:3306
-- Generation Time: Mar 10, 2022 at 01:19 PM
-- Server version: 10.6.7-MariaDB-1:10.6.7+maria~focal
-- PHP Version: 8.0.15
CREATE DATABASE rating_app;
use rating_app;

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `rating_app`
--

-- --------------------------------------------------------

--
-- Table structure for table `emotion`
--

CREATE TABLE `emotion` (
                           `id` int(11) NOT NULL,
                           `name` varchar(250) NOT NULL,
                           `color` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `emotion`
--

INSERT INTO `emotion` (`id`, `name`, `color`) VALUES
                                                  (1, 'very satisfied', 'green'),
                                                  (2, 'satisfied', 'blue'),
                                                  (3, 'meh', 'grey'),
                                                  (4, 'dissatisfied', 'yellow'),
                                                  (5, 'very dissatisfied', 'red');

-- --------------------------------------------------------

--
-- Table structure for table `emotion_setting`
--

CREATE TABLE `emotion_setting` (
                                   `id` int(11) NOT NULL,
                                   `emotion_id` int(11) NOT NULL,
                                   `emotion_value` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `emotion_setting`
--

INSERT INTO `emotion_setting` (`id`, `emotion_id`, `emotion_value`) VALUES
                                                                        (1, 1, 3),
                                                                        (2, 3, 3),
                                                                        (3, 5, 3),
                                                                        (4, 1, 4),
                                                                        (5, 2, 4),
                                                                        (6, 4, 4),
                                                                        (7, 5, 4),
                                                                        (8, 1, 5),
                                                                        (9, 2, 5),
                                                                        (10, 3, 5),
                                                                        (11, 4, 5),
                                                                        (12, 5, 5);

-- --------------------------------------------------------

--
-- Table structure for table `rating`
--

CREATE TABLE `rating` (
                          `id` int(11) NOT NULL,
                          `created_at` datetime NOT NULL,
                          `emotion_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `rating`
--

INSERT INTO `rating` (`id`, `created_at`, `emotion_id`) VALUES
                                                            (1, '2022-03-07 13:33:32', 1),
                                                            (2, '2022-03-07 13:33:59', 3),
                                                            (3, '2022-03-07 13:33:59', 5),
                                                            (4, '2022-03-07 13:33:59', 1),
                                                            (5, '2022-03-07 13:34:18', 2),
                                                            (6, '2022-03-07 13:34:18', 4),
                                                            (7, '2022-03-07 13:34:46', 5),
                                                            (8, '2022-03-07 13:34:46', 1),
                                                            (9, '2022-03-07 13:34:46', 2),
                                                            (10, '2022-03-07 13:34:46', 3),
                                                            (11, '2022-03-07 13:34:46', 4),
                                                            (12, '2022-03-07 13:34:46', 5);

-- --------------------------------------------------------

--
-- Table structure for table `setting`
--

CREATE TABLE `setting` (
                           `id` int(11) NOT NULL,
                           `emotion_number` int(11) NOT NULL,
                           `message` varchar(255) NOT NULL,
                           `message_timeout` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `setting`
--

INSERT INTO `setting` (`id`, `emotion_number`, `message`, `message_timeout`) VALUES
    (1, 4, 'Thank you', 5);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
                        `id` int(11) NOT NULL,
                        `name` varchar(250) NOT NULL,
                        `email` varchar(250) NOT NULL,
                        `password` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `name`, `email`, `password`) VALUES
    (1, 'admin', 'admin@gmail.com', 'password');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `emotion`
--
ALTER TABLE `emotion`
    ADD PRIMARY KEY (`id`);

--
-- Indexes for table `emotion_setting`
--
ALTER TABLE `emotion_setting`
    ADD PRIMARY KEY (`id`),
    ADD KEY `emotion_id` (`emotion_id`);

--
-- Indexes for table `rating`
--
ALTER TABLE `rating`
    ADD PRIMARY KEY (`id`),
    ADD KEY `emotion_id` (`emotion_id`);

--
-- Indexes for table `setting`
--
ALTER TABLE `setting`
    ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
    ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `emotion`
--
ALTER TABLE `emotion`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `emotion_setting`
--
ALTER TABLE `emotion_setting`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `rating`
--
ALTER TABLE `rating`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `setting`
--
ALTER TABLE `setting`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `emotion_setting`
--
ALTER TABLE `emotion_setting`
    ADD CONSTRAINT `emotion_setting_ibfk_1` FOREIGN KEY (`emotion_id`) REFERENCES `emotion` (`id`);

--
-- Constraints for table `rating`
--
ALTER TABLE `rating`
    ADD CONSTRAINT `rating_ibfk_1` FOREIGN KEY (`emotion_id`) REFERENCES `emotion` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
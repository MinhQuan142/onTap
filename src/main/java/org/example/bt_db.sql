-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Aug 20, 2024 at 10:30 AM
-- Server version: 8.0.30
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bt_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `department`
--
create database bt_db;
user bt_db;

CREATE TABLE `department` (
  `department_id` int NOT NULL,
  `department_name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `is_deleted` bit(1) DEFAULT b'0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `department`
--

INSERT INTO `department` (`department_id`, `department_name`, `description`, `is_deleted`) VALUES
(1, 'Phòng Đào Tạo', 'Chuyên quản lý và đào tạo.', b'0'),
(3, 'Phòng Vận Hành', 'nơi vận hành', b'0');

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `employee_id` int NOT NULL,
  `first_name` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `last_name` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `date_of_birth` date NOT NULL,
  `phone` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL,
  `address` varchar(225) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `salary` double NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` datetime DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT b'0',
  `department_id` int DEFAULT NULL
) ;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`employee_id`, `first_name`, `last_name`, `date_of_birth`, `phone`, `address`, `salary`, `created_at`, `update_at`, `is_deleted`, `department_id`) VALUES
(1, 'Trâu Tinh', 'Tinh', '1989-02-03', '0321-0321-321', 'Hong Kong - Trung Quoc', 1000, '2024-08-17 07:58:12', NULL, b'0', 1),
(2, 'Nguyễn Văn', 'Hoa', '1990-06-01', '0949-511286', 'bắc ninh - viejt nam', 100, '2024-08-17 08:27:07', NULL, b'0', NULL),
(4, 'Phạm Thị', 'Sushi', '2017-07-08', '765-888-888', 'jhgf hgfd', 10, '2024-08-17 08:34:47', NULL, b'0', NULL),
(5, 'quan142', 'minh', '1990-06-01', '09876543', 'viet nam', 100, '2024-08-17 13:50:12', NULL, b'0', NULL),
(7, '43d', 'rdw', '9999-04-09', '0090-04-04', 'fds', 9876, '2024-08-18 16:57:57', NULL, b'0', NULL),
(8, '123', 'qưe', '1990-01-09', '123456', 'ưertyj', 23, '2024-08-18 17:00:34', NULL, b'0', NULL),
(9, 'pham', 'minh quan', '1990-01-06', '949511286', 'uytrew', 100, '2024-08-19 09:35:30', NULL, b'0', NULL),
(10, 'tre', 'rew', '1990-01-10', '+84949511286', 'hgfdsa', 10, '2024-08-20 02:00:12', NULL, b'0', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `department`
--
ALTER TABLE `department`
  ADD PRIMARY KEY (`department_id`),
  ADD UNIQUE KEY `department_name` (`department_name`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`employee_id`),
  ADD UNIQUE KEY `phone` (`phone`),
  ADD UNIQUE KEY `department_id` (`department_id`),
  ADD UNIQUE KEY `department_id_2` (`department_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `department`
--
ALTER TABLE `department`
  MODIFY `department_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `employee`
--
ALTER TABLE `employee`
  MODIFY `employee_id` int NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `employee`
--
ALTER TABLE `employee`
  ADD CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`department_id`) REFERENCES `department` (`department_id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

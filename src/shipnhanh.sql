-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 23, 2023 at 02:16 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.0.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `shipnhanh`
--

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE `account` (
  `checkboom` int(11) DEFAULT NULL,
  `id` int(11) NOT NULL,
  `pay` decimal(38,2) DEFAULT NULL,
  `role` int(11) DEFAULT NULL,
  `numberphone` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `address`
--

CREATE TABLE `address` (
  `id` int(11) NOT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `numberphone` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `merchants`
--

CREATE TABLE `merchants` (
  `id` bigint(20) NOT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `timeclose` time(6) DEFAULT NULL,
  `timeopen` time(6) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `numberphone` varchar(255) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `merchants`
--

INSERT INTO `merchants` (`id`, `latitude`, `longitude`, `timeclose`, `timeopen`, `address`, `image`, `name`, `numberphone`, `product_id`) VALUES
(1, 56, 43, '22:21:00.000000', '23:22:00.000000', '342', NULL, 'cửa hàng 1', '0365776577', NULL),
(2, 56, 43, '22:21:00.000000', '23:22:00.000000', '342', NULL, 'cửa hàng 2', '0356657765', NULL),
(3, 56, 43, '22:21:00.000000', '23:22:00.000000', '342', NULL, 'cửa hàng 3', '0356657765', NULL),
(4, 56, 43, '22:21:00.000000', '23:22:00.000000', '342', NULL, 'cửa hàng 4', '0357765899', NULL),
(5, 5, 43, '19:38:00.000000', '19:38:00.000000', '342', NULL, 'cửa hàng 5', '0957765554', NULL),
(6, 56, 43, '19:38:00.000000', '19:38:00.000000', '342', NULL, 'cửa hàng 6', '0356657765', NULL),
(7, 5, 43, '19:38:00.000000', '19:38:00.000000', '342', NULL, 'cửa hàng 6', '0957765554', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `order_detail`
--

CREATE TABLE `order_detail` (
  `id` bigint(20) NOT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `price_sale` double DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `sale_id` bigint(20) DEFAULT NULL,
  `status` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `id` bigint(20) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `count_seach` int(11) DEFAULT NULL,
  `time_seach` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`id`, `image`, `name`, `count_seach`, `time_seach`) VALUES
(2, 'https://i.imgur.com/q0ddO48.jpg', 'Trà Đào Cam Sả', 0, '2022-09-11'),
(3, 'https://i.imgur.com/d6zBLIs.jpg', 'Trà Xoài Hương Cam', 0, '2022-09-05'),
(4, 'https://i.imgur.com/piSpwbN.jpg', 'Trà Dâu Kim Cương', 0, '2022-09-05'),
(5, 'https://i.imgur.com/E6K5Ql4.jpg', 'Trà Quả Mọng', 0, '2022-09-05'),
(6, 'https://i.imgur.com/UI1A8Cn.jpg', 'Trà Xoài Hồng Công', 0, '2022-09-05'),
(7, 'https://i.imgur.com/Z4VJwGW.jpg', 'Trà Ô Long Yên Tử', 0, '2022-09-05'),
(8, 'https://i.imgur.com/yjdVijT.jpg', 'Hồng Trà Macchiato', 0, '2022-09-05'),
(9, 'https://i.imgur.com/Ihr3yhS.jpg', 'Sữa Dừa Trái Cây Nhiệt Đới Xoài', 0, '2022-09-05'),
(10, 'https://i.imgur.com/eK9pYVM.jpg', 'Sữa Dừa Trái Cây Nhiệt Đới Dâu', 0, '2022-09-05'),
(11, 'https://i.imgur.com/iU2aUvy.jpg', 'Sữa Dừa Trái Cây Nhiệt Đới Việt Quất', 0, '2022-09-05'),
(12, 'https://i.imgur.com/Dv2ZBIA.jpg', 'Nước Quất Hoa Đậu Biếc', 0, '2022-09-05'),
(13, 'https://i.imgur.com/lUwnrwI.jpg', 'Trà Chanh', 0, '2022-09-05'),
(14, 'https://i.imgur.com/Ln4nY5E.jpg', 'Sữa Tươi Trân Châu Đường Đen', 0, '2022-09-05'),
(15, 'https://i.imgur.com/XC9YK9a.jpg', 'Sữa Tươi Diệp Hoa Kem Trứng Trân Châu Đường Đen', 0, '2022-09-05'),
(16, 'https://i.imgur.com/ZWwbzSe.jpg', 'Sữa Tươi Matcha Kem Phô Mai', 0, '2022-09-05'),
(17, 'https://i.imgur.com/15Xz7LH.jpg', 'Hồng Trà Late Hoa Hồng', 0, '2022-09-05'),
(18, 'https://i.imgur.com/NVAjPh3.jpg', 'Sữa Dừa Hương Cốm', 0, '2022-09-05'),
(19, 'https://i.imgur.com/XwixNS3.jpg', 'Trà Sữa Nướng Kem Trứng Cháy', 0, '2022-09-05'),
(20, 'https://i.imgur.com/nEe7M5B.jpg', 'Trà Sữa Trân Châu Đường Đen', 0, '2022-09-05'),
(21, 'https://i.imgur.com/C27oOcx.jpg', 'Trà Sữa Matcha Kem Trứng', 0, '2022-09-05'),
(22, NULL, 'Trà Sữa Bạc Hà', 0, '2022-09-05'),
(23, 'https://i.imgur.com/pplBPPk.jpg', 'Trà Sữa Dâu', 0, '2022-09-05'),
(24, 'https://i.imgur.com/s50t1gi.jpg', 'Sữa Dừa Matcha Trân Châu Đường Đen', 0, '2022-09-05'),
(25, 'https://i.imgur.com/or3h6r6.jpg', 'Sữa Dừa Coffee Trân Châu Đường Đen', 0, '2022-09-05'),
(26, 'https://i.imgur.com/Pfj2KYC.jpg', 'Coffee Nâu', 0, '2022-09-05'),
(27, 'https://i.imgur.com/HHTjJhq.jpg', 'Coffee Đen', 0, '2022-09-05'),
(28, 'https://i.imgur.com/O3HzmXX.jpg', 'Bạc Xỉu', 0, '2022-09-05'),
(29, 'https://i.imgur.com/NjqPt7i.jpg', 'Coffee Caramel', 0, '2022-09-05'),
(30, 'https://i.imgur.com/FxQdYvy.jpg', 'Coffee Cốt Dừa', 0, '2022-09-05'),
(31, 'https://i.imgur.com/vmEJ50h.jpg', 'Việt Quất Đá Xay', 0, '2022-09-05'),
(32, 'https://i.imgur.com/sJ3Qqy5.jpg', 'Matcha Freeze', 0, '2022-09-05'),
(33, 'https://i.imgur.com/zJWzcFF.jpg', 'Cookies Chocolate', 0, '2022-09-05'),
(34, 'https://i.imgur.com/h4WxVs1.jpg', 'Xoài Sữa Chua Đá Xay', 0, '2022-09-05'),
(35, 'https://i.imgur.com/HTGBBQA.jpg', 'Chanh Tuyết', 0, '2022-09-05'),
(36, 'https://i.imgur.com/ERDJnW8.jpg', 'Chanh Leo Tuyết', 0, '2022-09-05'),
(37, 'https://i.imgur.com/RucYz5G.jpg', 'Khoai Môn Cốt Dừa', 0, '2022-09-05'),
(38, 'https://i.imgur.com/lUGV4IN.jpg', 'Sữa Chua Nhiệt Đới Xoài', 0, '2022-09-05'),
(39, 'https://i.imgur.com/c7DDTL7.jpg', 'Sữa Chua Nhiệt Đới Dâu', 0, '2022-09-05'),
(40, 'https://i.imgur.com/OslQyKd.jpg', 'Sữa Chua Nhiệt Đới Việt Quất', 0, '2022-09-05'),
(41, 'https://i.imgur.com/kE7MeDx.jpg', 'Trà Chanh Gừng', 0, '2022-09-05'),
(42, 'https://i.imgur.com/jdk4wFt.jpg', 'Trà Gừng Táo Đỏ', 0, '2022-09-05'),
(43, 'https://i.imgur.com/gBe2y68.jpg', 'Trà Gừng Ô Mai', 0, '2022-09-05'),
(44, 'https://i.imgur.com/8tWXirb.jpg', 'Nước Ép Cam Tươi', 0, '2022-09-05'),
(45, 'https://i.imgur.com/Oro8bN8.jpg', 'Nước Ép Dứa', 0, '2022-09-05'),
(46, 'https://i.imgur.com/hFlCPl3.jpg', 'Nước Ép Dưa Hấu', 0, '2022-09-05'),
(47, 'https://i.imgur.com/X3YKW07.jpg', 'Sinh Tố Bơ', 0, '2022-09-05'),
(48, 'https://i.imgur.com/o1llUNQ.jpg', 'Sinh Tố Xoài', 0, '2022-09-05'),
(49, 'https://i.imgur.com/2CxQHAw.jpg', 'Sinh Tố Mãng Cầu', 0, '2022-09-05'),
(50, 'https://i.imgur.com/r8lM1CH.jpg', 'Trà Mãng Cầu', 0, '2022-09-05'),
(51, 'https://i.imgur.com/7Q0dn9N.jpg', 'Trà Chanh Nha Đam', 0, '2022-09-05'),
(52, 'https://i.imgur.com/rB9LHtp.jpg', 'demo1', 0, '2022-09-05'),
(53, NULL, '12', 0, '2022-09-05'),
(54, 'https://i.imgur.com/u8Akx54.jpg', '123', 0, '2022-09-05'),
(55, NULL, '432', 0, '2022-09-05'),
(56, NULL, '123', 0, '2022-09-05'),
(57, 'https://i.imgur.com/nVhVdvs.png', '1235654', 0, '2022-09-05');

-- --------------------------------------------------------

--
-- Table structure for table `productsdetail`
--

CREATE TABLE `productsdetail` (
  `id` int(11) NOT NULL,
  `idMerchants` int(11) DEFAULT NULL,
  `idProduct` int(11) DEFAULT NULL,
  `idSize` int(11) DEFAULT NULL,
  `price1` decimal(38,2) DEFAULT NULL,
  `price2` decimal(38,2) DEFAULT NULL,
  `status` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `productsdetail`
--

INSERT INTO `productsdetail` (`id`, `idMerchants`, `idProduct`, `idSize`, `price1`, `price2`, `status`) VALUES
(1, 5, 5, 1, 10000.00, 0.00, 0),
(2, 2, 5, 2, 10000.00, 2000.00, 0),
(3, 1, 2, 2, 1000.00, 100000.00, 0),
(4, 1, 3, 2, 1000.00, 100000.00, 0),
(5, 1, 5, 1, 1000.00, 0.00, 0),
(6, 2, 5, 2, 1000.00, 100000.00, 0),
(7, 3, 5, 2, 1000.00, 100000.00, 0),
(8, 3, 5, 1, 123445.00, 0.00, 0);

-- --------------------------------------------------------

--
-- Table structure for table `size`
--

CREATE TABLE `size` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `size`
--

INSERT INTO `size` (`id`, `name`) VALUES
(1, '1 Size'),
(2, '2 Size');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `address`
--
ALTER TABLE `address`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `merchants`
--
ALTER TABLE `merchants`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `order_detail`
--
ALTER TABLE `order_detail`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `productsdetail`
--
ALTER TABLE `productsdetail`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `size`
--
ALTER TABLE `size`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `account`
--
ALTER TABLE `account`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `address`
--
ALTER TABLE `address`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `merchants`
--
ALTER TABLE `merchants`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `order_detail`
--
ALTER TABLE `order_detail`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=58;

--
-- AUTO_INCREMENT for table `productsdetail`
--
ALTER TABLE `productsdetail`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `size`
--
ALTER TABLE `size`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

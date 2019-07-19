-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jul 09, 2019 at 04:16 PM
-- Server version: 10.1.8-MariaDB
-- PHP Version: 5.6.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dbjasacetak`
--

-- --------------------------------------------------------

--
-- Table structure for table `tblogin`
--

CREATE TABLE `tblogin` (
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `hak_akses` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tblogin`
--

INSERT INTO `tblogin` (`username`, `password`, `hak_akses`) VALUES
('admin', 'admin', 'admin'),
('pegawai', 'pegawai', 'user'),
('pemilik', 'pemilik', 'admin'),
('user', 'user', 'user');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_kategori`
--

CREATE TABLE `tbl_kategori` (
  `kd_kategori` varchar(10) NOT NULL,
  `nm_kategori` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_kategori`
--

INSERT INTO `tbl_kategori` (`kd_kategori`, `nm_kategori`) VALUES
('CF', 'Cetak Foto'),
('CP', 'Cetak Print '),
('FC', 'Fotocopy');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_produk`
--

CREATE TABLE `tbl_produk` (
  `kd_produk` varchar(10) NOT NULL,
  `kd_kategori` varchar(10) NOT NULL,
  `nm_produk` varchar(225) NOT NULL,
  `hrg_beli` int(11) NOT NULL,
  `hrg_jual` int(11) NOT NULL,
  `stok` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_produk`
--

INSERT INTO `tbl_produk` (`kd_produk`, `kd_kategori`, `nm_produk`, `hrg_beli`, `hrg_jual`, `stok`) VALUES
('CP001', 'CP', 'Print Hitam Putih Kertas HVS A4/F4/Folio', 300, 500, 1000),
('CP002', 'CP', 'Print Warna Kertas HVS A4/F4/Folio', 700, 1000, 1000),
('CP003', 'CP', 'Print Warna Kertas HVS A4/F4/Folio', 1100, 1500, 1000),
('CP004', 'CP', 'Print Warna Kertas HVS A4/F4/Folio', 1500, 2000, 1000),
('CP005', 'CP', 'Print Full Warna Kertas HVS A4/F4/Folio', 2000, 3000, 1000),
('FC001', 'FC', 'Fotocopy Kertas HVS A4/F4/Folio', 80, 250, 1000),
('FC002', 'FC', 'Fotocopy 2 Sisi Kertas HVS A4/F4/Folio', 150, 400, 1000);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_transaksi`
--

CREATE TABLE `tbl_transaksi` (
  `no_jual` char(225) NOT NULL,
  `kd_produk` varchar(10) NOT NULL,
  `nm_produk` varchar(225) NOT NULL,
  `hrg_jual` int(11) NOT NULL,
  `qty` int(11) NOT NULL,
  `total` int(11) NOT NULL,
  `bayar` int(11) NOT NULL,
  `kembali` int(11) NOT NULL,
  `nm_pelanggan` varchar(50) NOT NULL,
  `alamat` varchar(100) NOT NULL,
  `tanggal` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_transaksi`
--

INSERT INTO `tbl_transaksi` (`no_jual`, `kd_produk`, `nm_produk`, `hrg_jual`, `qty`, `total`, `bayar`, `kembali`, `nm_pelanggan`, `alamat`, `tanggal`) VALUES
('J0001', '', 'Fotocopy Kertas HVS A4/F4/Folio', 250, 20, 5000, 10000, 5000, 'joyo', 'solo', '03-07-2019'),
('J0002', 'J0002', 'Fotocopy Kertas HVS A4/F4/Folio', 250, 5, 1250, 2000, 750, 'Erfan', 'Solo', '09-07-2019'),
('J0003', 'CP001', 'Print Hitam Putih Kertas HVS A4/F4/Folio', 500, 8, 4000, 5000, 1000, 'Erfan', 'solo', '09-07-2019');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tblogin`
--
ALTER TABLE `tblogin`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `tbl_kategori`
--
ALTER TABLE `tbl_kategori`
  ADD PRIMARY KEY (`kd_kategori`);

--
-- Indexes for table `tbl_produk`
--
ALTER TABLE `tbl_produk`
  ADD PRIMARY KEY (`kd_produk`);

--
-- Indexes for table `tbl_transaksi`
--
ALTER TABLE `tbl_transaksi`
  ADD PRIMARY KEY (`no_jual`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

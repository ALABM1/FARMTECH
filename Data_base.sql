-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 27, 2023 at 12:53 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `infinityfarm`
--

-- --------------------------------------------------------

--
-- Table structure for table `activite`
--

CREATE TABLE `activite` (
  `idAct` int(11) NOT NULL,
  `objetAct` varchar(255) NOT NULL,
  `descriptionAct` varchar(255) NOT NULL,
  `distAct` varchar(255) NOT NULL,
  `emailDist` varchar(255) NOT NULL,
  `speciesRES` varchar(255) NOT NULL,
  `etatAct` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `activite`
--

INSERT INTO `activite` (`idAct`, `objetAct`, `descriptionAct`, `distAct`, `emailDist`, `speciesRES`, `etatAct`) VALUES
(12, 'hdvfvdcdc', 'cdsvfggbfd', 'hassan', 'hassan@esprit.com', 'vac', ''),
(13, 'jrghsjkgh', 'gehhgf', 'hassan', 'hassan@esprit.com', 'mouton', ''),
(21, 'aaaa', 'nettoyage', 'hassan', 'hassanjlassi23@gmail.com', 'mouton', ''),
(24, 'aaaa', 'nettoyer', 'hassan', 'hassanjlassi23@gmail.com', 'mouton', ''),
(25, 'aaaa', 'nettoyer', 'hassan', 'hassanjlassi23@gmail.com', 'mouton', ''),
(26, 'aaa', 'aaaa', 'hassan', 'hassanjlassi23@gmail.com', 'mouton', 'en_attente'),
(27, 'aaa', 'aaaaaaa', 'hassan', 'hassanjlassi23@gmail.com', 'mouton', 'en_attente'),
(28, 'aaa', 'aaaaaa', 'aaaa', 'yj5733118@gmail.com', 'mouton', 'en_attente'),
(29, 'aa', 'aaaaaa', 'aaaa', 'megbli.houssam@gmail.com', '3', 'en_attente'),
(30, 'aaa', 'aaaaa', 'houssam', 'megbli.houssam@gmail.com', '22', 'terminé');

-- --------------------------------------------------------

--
-- Table structure for table `categtrans`
--

CREATE TABLE `categtrans` (
  `id_cat_tra` int(11) NOT NULL,
  `nom_cat_tra` varchar(255) NOT NULL,
  `descrip_cat_tra` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `categtrans`
--

INSERT INTO `categtrans` (`id_cat_tra`, `nom_cat_tra`, `descrip_cat_tra`) VALUES
(8, 'payement facture', 'Payement facture STEG, SONEDE'),
(9, 'vente blé', 'vente blé de saison'),
(10, 'Payement ouvrier', 'payement du travail des ouvriers'),
(11, 'vente poules', 'vente des poules non cuite');

-- --------------------------------------------------------

--
-- Table structure for table `materiel`
--

CREATE TABLE `materiel` (
  `idMat` int(11) NOT NULL,
  `nomMat` varchar(255) NOT NULL,
  `etatMat` varchar(255) NOT NULL,
  `QuantiteMat` float NOT NULL,
  `dateAjout` date NOT NULL,
  `nomParc` varchar(255) NOT NULL,
  `idParc` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `materiel`
--

INSERT INTO `materiel` (`idMat`, `nomMat`, `etatMat`, `QuantiteMat`, `dateAjout`, `nomParc`, `idParc`) VALUES
(114, 'camion TN21', 'On panne', 1, '2023-10-19', 'Parc Tataouine', 31),
(117, 'camionc', 'On marche', 1, '2023-10-22', 'Parc de gabes', 33),
(118, 'camioncu', 'On panne ', 1, '2023-10-22', 'Parc de gabes', 33),
(119, 'camions', 'On marche', 1, '2023-10-22', 'Parc de gabes', 33),
(120, 'camionss', 'On panne ', 1, '2023-10-22', 'Parc de gabes', 33),
(121, 'camionss,', 'On panne ', 1, '2023-10-22', 'Parc de gabes', 33),
(124, 'mmm', 'On panne ', 4, '2023-10-22', 'Parc de gabes', 33),
(125, 'mm3m', 'On panne ', 4, '2023-10-22', 'Parc de gabes', 33),
(126, 'mateiriel 1 ', 'On panne ', 1, '2023-10-22', 'Parc Tataouine', 31),
(127, 'materiel 3', 'On panne ', 1, '2023-10-22', 'Parc Tataouine', 31),
(128, 'mmmm', 'On panne ', 4, '2023-10-22', 'Parc Tataouine', 31),
(129, 'mmmmo', 'On panne ', 3, '2023-10-22', 'Parc de gabes', 33),
(130, 'mmmmmm', 'On panne ', 4, '2023-10-22', 'Parc Tataouine', 31),
(132, 'aaaaa', 'On panne ', 4, '2023-10-22', 'Parc de gabes', 33),
(133, 'mmmmmpp', 'On panne ', 4, '2023-10-22', 'Parc Tataouine', 31),
(134, 'materiel testt', 'On panne ', 1, '2023-10-22', 'Parc Tataouine', 31),
(135, 'camtion TN96', 'On panne ', 3, '2023-10-22', 'Parc Tataouine', 31),
(137, 'camion g', 'On panne ', 1, '2023-10-22', 'Parc de gabes', 33),
(138, 'aaaa', 'On marche', 1, '2023-10-22', 'Parc de gabes', 33),
(139, 'aaaaee', 'On panne ', 1, '2023-10-22', 'Parc de gabes', 33),
(142, 'ala', 'On panne ', 1, '2023-10-23', 'Parc Tataouine', 31),
(143, 'aafgfh', 'On panne ', 4, '2023-10-24', 'Parc de gabes', 33),
(146, 'camion TN10', 'On marche', 1, '2023-10-26', 'Parc de sfax', 35),
(147, 'tracteur tn23', 'On marche', 1, '2023-10-26', 'Parc de sfax', 35),
(148, 'tracteur tn24', 'On panne ', 1, '2023-10-26', 'Parc de sfax', 35),
(149, 'camionssss', 'On panne ', 1, '2023-10-26', 'Parc de sfax', 35),
(150, 'aasds', 'On panne ', 1, '2023-10-26', 'Parc de sfax', 35),
(151, 'mmmmsfsf', 'On panne ', 1, '2023-10-26', 'Parc de sfax', 35);

-- --------------------------------------------------------

--
-- Table structure for table `messages`
--

CREATE TABLE `messages` (
  `id` int(11) NOT NULL,
  `text` varchar(255) DEFAULT NULL,
  `destinataire` int(11) DEFAULT NULL,
  `source` int(11) DEFAULT NULL,
  `date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `messages`
--

INSERT INTO `messages` (`id`, `text`, `destinataire`, `source`, `date`) VALUES
(5, 'Joli Text', 38, 39, '2023-10-02'),
(6, 'Not that Good Message', 38, 39, '2023-10-03'),
(7, 'Very Good Message', 38, 39, '2023-10-09'),
(11, 'llll', 44, 41, '2023-10-05');

-- --------------------------------------------------------

--
-- Table structure for table `parc`
--

CREATE TABLE `parc` (
  `idParc` int(11) NOT NULL,
  `nomParc` varchar(255) NOT NULL,
  `adresseParc` varchar(255) NOT NULL,
  `superficieParc` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `parc`
--

INSERT INTO `parc` (`idParc`, `nomParc`, `adresseParc`, `superficieParc`) VALUES
(31, 'Parc Tataouine', 'Tataouine sud,tataouine', 2),
(33, 'Parc de gabes', 'gabes', 3.5),
(35, 'Parc de sfax', 'sfax', 3);

-- --------------------------------------------------------

--
-- Table structure for table `password_reset`
--

CREATE TABLE `password_reset` (
  `id` int(11) NOT NULL,
  `email` varchar(255) NOT NULL,
  `reset_code` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `password_reset`
--

INSERT INTO `password_reset` (`id`, `email`, `reset_code`) VALUES
(1, 'aladain2000@gmail.com', 'e1b541dd-5ccb-4c43-a814-5ec478ff5fae'),
(33, 'email@admin.com', '2834dae3-b15f-4050-b086-6f2b39313048'),
(34, 'aladain2000@gmail.com', '02a87259-2871-4495-9686-12f8072ded80');

-- --------------------------------------------------------

--
-- Table structure for table `password_reset1`
--

CREATE TABLE `password_reset1` (
  `id` int(11) NOT NULL,
  `numeroTelephone` varchar(255) DEFAULT NULL,
  `reset_code` varchar(255) DEFAULT NULL,
  `date` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `password_reset1`
--

INSERT INTO `password_reset1` (`id`, `numeroTelephone`, `reset_code`, `date`) VALUES
(1, '+21692310616', '7e3bc603-936f-4cf8-bbf2-8bb4a22c4ea4', '2023-10-24 18:54:11'),
(2, '+21692310616', 'c48061dc-365c-49a1-a910-3f288dd542bc', '2023-10-24 18:56:26'),
(3, '+21692310616', 'e69dec76-2cdc-4382-8248-413f45c9f17f', '2023-10-24 20:15:34'),
(4, '+21692310616', 'c2b63867-7897-4afc-899c-c8e6c6d39ed1', '2023-10-24 20:16:13'),
(5, '+21692310616', '10517e26-64b6-4684-aed3-4e4308da010b', '2023-10-24 20:16:20'),
(6, '+21692310616', '5a7b9b64-55ae-4011-adcf-46bf16241977', '2023-10-25 11:53:47'),
(7, '+21692310616', '49cbc9', '2023-10-26 10:36:06'),
(8, '+21692310616', 'a96e85', '2023-10-26 10:41:05'),
(9, '+21692310616', '1d32be', '2023-10-26 12:41:03'),
(10, '+21692310616', '12bddb', '2023-10-26 12:46:11'),
(11, '+21692310616', 'da7c3a', '2023-10-26 13:58:13');

-- --------------------------------------------------------

--
-- Table structure for table `reclamations`
--

CREATE TABLE `reclamations` (
  `id` int(11) NOT NULL,
  `type` varchar(255) NOT NULL,
  `description` text NOT NULL,
  `email` varchar(255) NOT NULL,
  `telephone` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `reclamations`
--

INSERT INTO `reclamations` (`id`, `type`, `description`, `email`, `telephone`) VALUES
(1, 'Problème technique', 'Le site a des problèmes de chargement. Veuillez corriger cela rapidement.', 'example1@example.com', '123456789'),
(4, 'aaaaaaaaaaaa', 'aaaa', 'aa@gmail.com', '222'),
(5, 'Alerte', 'Urgent', 'houssemhenchir@gmail.com', '55562933'),
(6, 'alerte', 'reclamation ouvrier', 'houssemibrahim9@gmail.com', '20033622'),
(7, 'RDV de recolte ', 'Prise de decision ', 'houssemeddine86@gmail.com', '58123104'),
(8, 'aa', 'aaaa', 'aaaa@gmail.com', '555'),
(9, 'Reparation', 'Reparer le tracteur ', 'houssemibrahim9@gmail.com', '21658123104'),
(10, 'Reparation', 'Reparer le tracteur ', 'houssemibrahim9@gmail.com', '21658123104'),
(11, 'Reparation', 'Reparer le tracteur ', 'houssemibrahim9@gmail.com', '444'),
(12, 'Reparation', 'Reparer le tracteur ', 'houssemibrahim9@gmail.com', '444'),
(13, 'aaaa', 'zzz', 'houssemibrahom9@gmail.com', '555'),
(14, 'demande d\'intervention', 'appel d\'intervention', 'houssemibrahim9@gmail.com', '58123104'),
(15, 'demande d\'intervention', 'appel d\'intervention', 'houssemibrahim9@gmail.com', '+216 58123104'),
(16, 'demande d\'intervention', 'appel d\'intervention', 'houssemibrahim9@gmail.com', '+21658123104'),
(17, 'demande d\'intervention', 'appel d\'intervention', 'houssemibrahim9@gmail.com', '21658123104'),
(18, 'aaa', 'aaa', 'houssemibrahim9@gmail.com', '21658123104'),
(19, 'aaaaaa', 'aaaaaaaaaaa', 'houssemibrahim9@gmail.com', '21658123104'),
(20, 'bbbb', 'asfdf', 'houssemibrahim9@gmail.com', '44444444444'),
(21, 'urgent', 'appel d\'intervention', 'houssemibrahim9@gmail.com', '58123104'),
(22, 'yyyyy', 'uuuu', 'jjjj@gmail.com', '+21658123104'),
(23, 'aaaaa', 'dfdf', 'houssemibrahim9@gmail.com', '+21658123104');

-- --------------------------------------------------------

--
-- Table structure for table `ressource`
--

CREATE TABLE `ressource` (
  `idRes` int(11) NOT NULL,
  `typeRes` varchar(255) NOT NULL,
  `speciesRes` varchar(255) NOT NULL,
  `quantiteRes` varchar(255) NOT NULL,
  `idterrain` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `ressource`
--

INSERT INTO `ressource` (`idRes`, `typeRes`, `speciesRes`, `quantiteRes`, `idterrain`) VALUES
(19, 'animaux', 'mouton', '11', 7),
(23, 'plantes', 'arbre de pomme', '12', 12),
(25, 'plantes', 'arbre', '10', 7);

-- --------------------------------------------------------

--
-- Table structure for table `terrain`
--

CREATE TABLE `terrain` (
  `idTerrain` int(11) NOT NULL,
  `nomTerrain` varchar(255) NOT NULL,
  `localisation` varchar(255) NOT NULL,
  `superficie` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `terrain`
--

INSERT INTO `terrain` (`idTerrain`, `nomTerrain`, `localisation`, `superficie`) VALUES
(7, 'hassan', 'borj el amri', 200000),
(12, 'hassan3', 'ariana', 10000),
(13, 'forna', '36.701799, 9.845337', 12000);

-- --------------------------------------------------------

--
-- Table structure for table `transaction`
--

CREATE TABLE `transaction` (
  `id_tra` int(11) NOT NULL,
  `categ_tra` varchar(255) NOT NULL,
  `type_tra` varchar(255) NOT NULL,
  `date_tra` date NOT NULL,
  `montant` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `transaction`
--

INSERT INTO `transaction` (`id_tra`, `categ_tra`, `type_tra`, `date_tra`, `montant`) VALUES
(41, 'vente blé', 'Revenu', '2023-10-24', 2000),
(42, 'payement facture', 'Dépense', '2023-10-12', 500),
(44, 'vente blé', 'Dépense', '2023-10-12', 111111);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `mail` varchar(255) DEFAULT NULL,
  `numeroTelephone` varchar(20) DEFAULT NULL,
  `role` enum('ADMIN','AGRICULTEUR','VETERINAIRE','OUVRIER') DEFAULT NULL,
  `motDePasse` varchar(255) DEFAULT NULL,
  `ville` varchar(100) DEFAULT NULL,
  `sexe` varchar(10) DEFAULT NULL,
  `specialite` varchar(100) DEFAULT NULL,
  `certification` blob DEFAULT NULL,
  `superficieFerme` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `nom`, `prenom`, `mail`, `numeroTelephone`, `role`, `motDePasse`, `ville`, `sexe`, `specialite`, `certification`, `superficieFerme`) VALUES
(38, 'saif', 'ala', 'fawzi@gmail.com', '12355', 'AGRICULTEUR', '50a9a57d70d5a2b920a018f3e708efec8efaa53a85b7768d600915707847bbf7', 'tunis', 'm', NULL, NULL, 0.00),
(39, 'Ben Mahmoud', 'Ala', 'email@admin.com', '0123456789', 'ADMIN', '50a9a57d70d5a2b920a018f3e708efec8efaa53a85b7768d600915707847bbf7', NULL, NULL, NULL, NULL, NULL),
(40, 'ala', 'ala', 'ala@gmail.com', '21692310616', 'VETERINAIRE', '5d530613969feac08946e337b5f3b1189b2f0b0ca73a812f4b83a504a0c773b2', 'tunis', 'M', NULL, NULL, NULL),
(41, 'Jilani', 'Belhassan', 'jilani.belhassan@yahoo.fr', '21693872628', 'AGRICULTEUR', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 'tunis', 'homme', NULL, NULL, NULL),
(42, 'root', 'root', 'root@gmail.com', '21653708976', 'AGRICULTEUR', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 'T', 'M', NULL, NULL, NULL),
(43, 'hssan', 'hssan', 'hassanjlassi23@gmail.com', '21693872628', 'OUVRIER', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 'tunis', 'M', NULL, NULL, NULL),
(44, 'Houssem', 'Meguebli', 'megbli.houssam@gmail.com', '21655562933', 'OUVRIER', '23dc685c39f6386815dddf759cb124d7535681152738fd0501a7678b14583ad9', 'tunis', 'homme', NULL, NULL, NULL),
(46, 'houssem', 'a', 'aa@gmail.com', '444666666', 'AGRICULTEUR', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'ttt', 'homme', NULL, NULL, NULL),
(47, 'ALA', 'ala', 'ala21@gmail.com', '12345678', 'OUVRIER', '50a9a57d70d5a2b920a018f3e708efec8efaa53a85b7768d600915707847bbf7', 't', 'm', NULL, NULL, NULL),
(48, 'zaa', 'aaaaa', 'aaaa@gmail.com', '5555', 'VETERINAIRE', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'dg', 'ss', NULL, NULL, NULL),
(49, 'hsan', 'hsan', 'hsan@gmail.com', '21693872628', 'AGRICULTEUR', '50a9a57d70d5a2b920a018f3e708efec8efaa53a85b7768d600915707847bbf7', 'tunis', 'm', NULL, NULL, NULL),
(51, 'ala', 'ala', 'aladinbenmahmoud@gmail.com', '21692310616', 'AGRICULTEUR', '5d530613969feac08946e337b5f3b1189b2f0b0ca73a812f4b83a504a0c773b2', 't', 'm', NULL, NULL, NULL),
(52, 'ala', 'ala', 'ala22@gmail.com', '21692310616', 'AGRICULTEUR', '5d530613969feac08946e337b5f3b1189b2f0b0ca73a812f4b83a504a0c773b2', 'tunis', 'm', NULL, NULL, NULL),
(53, 't', 'a', 'gdgn@gmail.com', '5555', 'AGRICULTEUR', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'efe', 't', NULL, NULL, NULL),
(54, 'Houssem', 'Meguebli', 'houssemibrahim9@gmail.com', '55555555', 'AGRICULTEUR', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'tunis', 'homme', NULL, NULL, NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `activite`
--
ALTER TABLE `activite`
  ADD PRIMARY KEY (`idAct`);

--
-- Indexes for table `categtrans`
--
ALTER TABLE `categtrans`
  ADD PRIMARY KEY (`id_cat_tra`);

--
-- Indexes for table `materiel`
--
ALTER TABLE `materiel`
  ADD PRIMARY KEY (`idMat`),
  ADD KEY `idParc` (`idParc`);

--
-- Indexes for table `messages`
--
ALTER TABLE `messages`
  ADD PRIMARY KEY (`id`),
  ADD KEY `destinataire` (`destinataire`),
  ADD KEY `source` (`source`);

--
-- Indexes for table `parc`
--
ALTER TABLE `parc`
  ADD PRIMARY KEY (`idParc`);

--
-- Indexes for table `password_reset`
--
ALTER TABLE `password_reset`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `password_reset1`
--
ALTER TABLE `password_reset1`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `reclamations`
--
ALTER TABLE `reclamations`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `ressource`
--
ALTER TABLE `ressource`
  ADD PRIMARY KEY (`idRes`),
  ADD KEY `fk_idterrain` (`idterrain`);

--
-- Indexes for table `terrain`
--
ALTER TABLE `terrain`
  ADD PRIMARY KEY (`idTerrain`);

--
-- Indexes for table `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`id_tra`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `activite`
--
ALTER TABLE `activite`
  MODIFY `idAct` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `categtrans`
--
ALTER TABLE `categtrans`
  MODIFY `id_cat_tra` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `materiel`
--
ALTER TABLE `materiel`
  MODIFY `idMat` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=152;

--
-- AUTO_INCREMENT for table `messages`
--
ALTER TABLE `messages`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `parc`
--
ALTER TABLE `parc`
  MODIFY `idParc` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- AUTO_INCREMENT for table `password_reset`
--
ALTER TABLE `password_reset`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT for table `password_reset1`
--
ALTER TABLE `password_reset1`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `reclamations`
--
ALTER TABLE `reclamations`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `ressource`
--
ALTER TABLE `ressource`
  MODIFY `idRes` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT for table `terrain`
--
ALTER TABLE `terrain`
  MODIFY `idTerrain` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `transaction`
--
ALTER TABLE `transaction`
  MODIFY `id_tra` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=55;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `materiel`
--
ALTER TABLE `materiel`
  ADD CONSTRAINT `idParc` FOREIGN KEY (`idParc`) REFERENCES `parc` (`idParc`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `messages`
--
ALTER TABLE `messages`
  ADD CONSTRAINT `messages_ibfk_1` FOREIGN KEY (`destinataire`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `messages_ibfk_2` FOREIGN KEY (`source`) REFERENCES `users` (`id`);

--
-- Constraints for table `ressource`
--
ALTER TABLE `ressource`
  ADD CONSTRAINT `fk_idterrain` FOREIGN KEY (`idterrain`) REFERENCES `terrain` (`idTerrain`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 14 Jul 2025 pada 05.23
-- Versi server: 10.4.32-MariaDB
-- Versi PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tiara_school`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `kelas`
--

CREATE TABLE `kelas` (
  `id_kelas` varchar(20) NOT NULL,
  `tingkat` enum('1','2','3','4','5','6') NOT NULL,
  `nama_kelas` varchar(10) NOT NULL,
  `kapasitas` int(11) DEFAULT 30,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `kelas`
--

INSERT INTO `kelas` (`id_kelas`, `tingkat`, `nama_kelas`, `kapasitas`, `created_at`, `updated_at`) VALUES
('KLS1A', '1', '1A', 30, '2025-07-06 10:19:26', '2025-07-06 10:19:26'),
('KLS1B', '1', '1B', 30, '2025-07-06 10:19:26', '2025-07-06 10:19:26'),
('KLS2A', '2', '2A', 30, '2025-07-06 10:19:26', '2025-07-06 10:19:26'),
('KLS2B', '2', '2B', 30, '2025-07-06 10:19:26', '2025-07-06 10:19:26'),
('KLS3A', '3', '3A', 30, '2025-07-06 10:19:26', '2025-07-06 10:19:26'),
('KLS3B', '3', '3B', 30, '2025-07-06 10:19:26', '2025-07-06 10:19:26'),
('KLS4A', '4', '4A', 30, '2025-07-06 10:19:26', '2025-07-06 10:19:26'),
('KLS4B', '4', '4B', 30, '2025-07-06 10:19:26', '2025-07-06 10:19:26'),
('KLS5A', '5', '5A', 30, '2025-07-06 10:19:26', '2025-07-06 10:19:26'),
('KLS5B', '5', '5B', 30, '2025-07-06 10:19:26', '2025-07-06 10:19:26'),
('KLS6A', '6', '6A', 30, '2025-07-06 10:19:26', '2025-07-06 10:19:26'),
('KLS6B', '6', '6B', 30, '2025-07-06 10:19:26', '2025-07-06 10:19:26');

-- --------------------------------------------------------

--
-- Struktur dari tabel `mata_pelajaran`
--

CREATE TABLE `mata_pelajaran` (
  `id_pelajaran` varchar(20) NOT NULL,
  `nama_pelajaran` varchar(100) NOT NULL,
  `kategori` enum('Wajib','Muatan Lokal','Ekstrakurikuler') DEFAULT 'Wajib',
  `deskripsi` text DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `mata_pelajaran`
--

INSERT INTO `mata_pelajaran` (`id_pelajaran`, `nama_pelajaran`, `kategori`, `deskripsi`, `created_at`, `updated_at`) VALUES
('AGAMA', 'Pendidikan Agama', 'Wajib', 'Mata pelajaran Agama untuk semua tingkat kelas 1-6', '2025-07-06 04:48:40', '2025-07-06 04:48:40'),
('BASKET', 'Basket', 'Ekstrakurikuler', 'Kegiatan ekstrakurikuler Basket', '2025-07-06 04:48:40', '2025-07-06 04:48:40'),
('BIND', 'Bahasa Indonesia', 'Wajib', 'Mata pelajaran Bahasa Indonesia untuk semua tingkat kelas 1-6', '2025-07-06 04:48:40', '2025-07-06 04:48:40'),
('BING', 'Bahasa Inggris', 'Wajib', 'Mata pelajaran Bahasa Inggris untuk semua tingkat kelas 1-6', '2025-07-06 04:48:40', '2025-07-06 04:48:40'),
('FUTSAL', 'Futsal', 'Ekstrakurikuler', 'Kegiatan ekstrakurikuler Futsal', '2025-07-06 04:48:40', '2025-07-06 04:48:40'),
('IPAS', 'Ilmu Pengetahuan Alam dan Sosial', 'Wajib', 'Mata pelajaran IPAS khusus untuk kelas 3-6 menggantikan IPA dan IPS terpisah', '2025-07-06 04:48:40', '2025-07-06 04:48:40'),
('MATH', 'Math', 'Wajib', 'Mata pelajaran Math (Matematika dalam Bahasa Inggris) untuk semua tingkat kelas 1-6', '2025-07-06 04:48:40', '2025-07-06 04:48:40'),
('MTK', 'Matematika', 'Wajib', 'Mata pelajaran Matematika untuk semua tingkat kelas 1-6', '2025-07-06 04:48:40', '2025-07-06 04:48:40'),
('MUSIK', 'Musik', 'Ekstrakurikuler', 'Kegiatan ekstrakurikuler Musik', '2025-07-06 04:48:40', '2025-07-06 04:48:40'),
('PI', 'Praktik Ibadah', 'Wajib', 'Mata pelajaran Praktik Ibadah untuk semua tingkat kelas 1-6', '2025-07-06 04:48:40', '2025-07-06 09:01:59'),
('PJOK', 'Pendidikan Jasmani, Olahraga dan Kesehatan', 'Wajib', 'Mata pelajaran PJOK untuk semua tingkat kelas 1-6', '2025-07-06 04:48:40', '2025-07-06 04:48:40'),
('PLBJ', 'Pendidikan Lingkungan dan Budaya Jakarta', 'Muatan Lokal', 'Mata pelajaran PLBJ untuk semua tingkat kelas 1-6', '2025-07-06 04:48:40', '2025-07-06 04:48:40'),
('PPKN', 'Pendidikan Pancasila dan Kewarganegaraan', 'Wajib', 'Mata pelajaran PPKn untuk semua tingkat kelas 1-6', '2025-07-06 04:48:40', '2025-07-06 04:48:40'),
('PRAMUKA', 'Pramuka', 'Ekstrakurikuler', 'Kegiatan ekstrakurikuler Pramuka', '2025-07-06 04:48:40', '2025-07-06 04:48:40'),
('SBDP', 'Seni Budaya dan Prakarya', 'Wajib', 'Mata pelajaran SBDP untuk semua tingkat kelas 1-6', '2025-07-06 04:48:40', '2025-07-06 04:48:40'),
('SCI', 'Science', 'Wajib', 'Mata pelajaran Science (Sains) untuk semua tingkat kelas 1-6', '2025-07-06 04:48:40', '2025-07-06 04:48:40'),
('TARI', 'Tari', 'Ekstrakurikuler', 'Kegiatan ekstrakurikuler Tari', '2025-07-06 04:48:40', '2025-07-06 04:48:40'),
('TIK', 'Teknologi Informasi dan Komunikasi', 'Wajib', 'Mata pelajaran TIK untuk semua tingkat kelas 1-6', '2025-07-06 04:48:40', '2025-07-06 04:48:40');

-- --------------------------------------------------------

--
-- Struktur dari tabel `nilai`
--

CREATE TABLE `nilai` (
  `NIS` varchar(20) NOT NULL,
  `id_pelajaran` varchar(20) NOT NULL,
  `nama_pelajaran` varchar(100) NOT NULL,
  `id_kelas` varchar(20) NOT NULL,
  `semester` enum('1','2') NOT NULL,
  `tahun_ajaran` varchar(9) NOT NULL,
  `nilai_akhir` decimal(5,2) DEFAULT NULL,
  `grade` varchar(2) GENERATED ALWAYS AS (case when `nilai_akhir` >= 90 then 'A' when `nilai_akhir` >= 80 then 'B' when `nilai_akhir` >= 70 then 'C' when `nilai_akhir` >= 60 then 'D' else 'E' end) STORED,
  `kompre` varchar(1000) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `nilai`
--

INSERT INTO `nilai` (`NIS`, `id_pelajaran`, `nama_pelajaran`, `id_kelas`, `semester`, `tahun_ajaran`, `nilai_akhir`, `kompre`, `created_at`, `updated_at`) VALUES
('080871', 'AGAMA', 'Pendidikan Agama', '6B', '2', '2025', 96.00, 'Menunjukkan penguasaan yang sangat baik dalam hal Menjelaskan perintah peduli lingkungan dalam Islam, dan menjelaskan manfaat peduli lingkungan, serta sangat baik dalam hal Menjelaskan macam-macam puasa Sunnah beserta dalilnya, dan menjelaskan ketentuan puasa Sunnah.', '2025-07-06 09:15:20', '2025-07-08 03:13:43'),
('080871', 'BIND', 'Bahasa Indonesia', '6B', '2', '2025', 95.00, 'Menunjukkan penguasaan yang baik dalam hal pidato, resensi buku kuitansi serta baik dalam hal kaidah kebahasan, kosa kata ungkapan dan peribahasa.', '2025-07-06 09:15:20', '2025-07-08 03:13:43'),
('080871', 'BING', 'Bahasa Inggris', '6B', '2', '2025', 97.00, 'Demonstrates very good mastery in terms of understanding simple Information which are got orally and responding to them by actions and verbally, and is very good in terms of understanding the information in simple description text, narration reading text.', '2025-07-06 09:15:20', '2025-07-08 03:13:43'),
('080871', 'IPAS', 'Ilmu Pengetahuan Alam dan Sosial', '6B', '2', '2025', 95.00, 'Menunjukkan penguasaan yang baik dalam hal Peserta didik mengidentifikasi benda langit di tata surya serta hubungan Bumi, Bulan dan matahari, serta baik dalam hal Peserta didik dapat mengembangkan ketrampilan penelitian, analisis dan presentasi dari suatu topik.', '2025-07-06 09:15:20', '2025-07-08 03:13:43'),
('080871', 'MATH', 'Math', '6B', '2', '2025', 98.00, 'Menunjukkan penguasaan yang baik dalam hal Membandingkan prisma, tabung, limas, kerucut dan bola serta baik dalam hal Menjelaskan gabungan dari beberapa bangun ruang, luas dan volume serta membandingkan modus, median dan mean.', '2025-07-06 09:15:20', '2025-07-08 03:13:43'),
('080871', 'MTK', 'Matematika', '6B', '2', '2025', 97.00, 'Menunjukkan penguasaan yang baik dalam hal Membandingkan prisma, tabung, limas, kerucut dan bola serta baik dalam hal Menjelaskan gabungan dari beberapa bangun ruang, luas dan volume serta membandingkan modus, median dan mean.', '2025-07-06 09:15:20', '2025-07-08 03:13:44'),
('080871', 'SCI', 'Science', '6B', '2', '2025', 97.00, 'Menunjukkan penguasaan yang baik dalam hal Peserta didik mengidentifikasi benda langit di tata surya serta hubungan Bumi, Bulan dan matahari, serta baik dalam hal Peserta didik dapat mengembangkan ketrampilan penelitian, analisis dan presentasi dari suatu topik.', '2025-07-06 09:15:20', '2025-07-08 03:13:44'),
('080871', 'TIK', 'Teknologi Informasi dan Komunikasi', '6B', '2', '2025', 94.00, 'Menunjukkan penguasaan yang sangat baik dalam hal Membuat dokumen menggunakan program layar presentasi, serta sangat baik dalam hal Mengidentifikasi Perangkat Lunak Program Layar Presentasi.', '2025-07-06 09:15:20', '2025-07-08 03:13:44'),
('080873', 'AGAMA', 'Pendidikan Agama', '6B', '2', '2025', 91.00, 'Menunjukkan penguasaan yang sangat baik dalam hal Menjelaskan perintah peduli lingkungan dalam Islam, dan menjelaskan manfaat peduli lingkungan, serta sangat baik dalam hal Menjelaskan macam-macam puasa Sunnah beserta dalilnya, dan menjelaskan ketentuan puasa Sunnah.', '2025-07-06 09:15:20', '2025-07-08 03:13:43'),
('080873', 'BIND', 'Bahasa Indonesia', '6B', '2', '2025', 90.00, 'Menunjukkan penguasaan yang baik dalam hal pidato, resensi buku kuitansi serta baik dalam hal kaidah kebahasan, kosa kata ungkapan dan peribahasa.', '2025-07-06 09:15:20', '2025-07-08 03:13:43'),
('080873', 'BING', 'Bahasa Inggris', '6B', '2', '2025', 92.00, 'Demonstrates very good mastery in terms of understanding simple Information which are got orally and responding to them by actions and verbally, and is very good in terms of understanding the information in simple description text, narration reading text.', '2025-07-06 09:15:20', '2025-07-08 03:13:43'),
('080873', 'IPAS', 'Ilmu Pengetahuan Alam dan Sosial', '6B', '2', '2025', 90.00, 'Menunjukkan penguasaan yang baik dalam hal Peserta didik mengidentifikasi benda langit di tata surya serta hubungan Bumi, Bulan dan matahari, serta baik dalam hal Peserta didik dapat mengembangkan ketrampilan penelitian, analisis dan presentasi dari suatu topik.', '2025-07-06 09:15:20', '2025-07-08 03:13:43'),
('080873', 'MATH', 'Math', '6B', '2', '2025', 93.00, 'Menunjukkan penguasaan yang baik dalam hal Membandingkan prisma, tabung, limas, kerucut dan bola serta baik dalam hal Menjelaskan gabungan dari beberapa bangun ruang, luas dan volume serta membandingkan modus, median dan mean.', '2025-07-06 09:15:20', '2025-07-08 03:13:43'),
('080873', 'MTK', 'Matematika', '6B', '2', '2025', 92.00, 'Menunjukkan penguasaan yang baik dalam hal Membandingkan prisma, tabung, limas, kerucut dan bola serta baik dalam hal Menjelaskan gabungan dari beberapa bangun ruang, luas dan volume serta membandingkan modus, median dan mean.', '2025-07-06 09:15:20', '2025-07-08 03:13:44'),
('080873', 'PJOK', 'Pendidikan Jasmani, Olahraga dan Kesehatan', '6B', '2', '2025', 87.00, 'Menunjukkan penguasaan yang baik dalam hal Peserta didik dapat Memahami dan Mempraktikkan kombinasi gerak Kebugaran Jasmani terkait Kesehatan serta baik dalam hal Peserta didik dapat Memahami dan Mempraktikkan Aktivitas Gerak Senam, Gerak Berirama dan Permainan Tradisional.', '2025-07-06 09:15:20', '2025-07-08 03:13:44'),
('080873', 'PLBJ', 'Pendidikan Lingkungan dan Budaya Jakarta', '6B', '2', '2025', 91.00, 'Menunjukkan penguasaan yang baik dalam hal Peserta didik dapat mengetahui dan menjelaskan tanaman khas Jakarta serta baik dalam hal Peserta didik dapat mengenal dan menjelaskan fasilitas umum pusat perbelanjaan modern.', '2025-07-06 09:15:20', '2025-07-08 03:13:44'),
('080873', 'PPKN', 'Pendidikan Pancasila dan Kewarganegaraan', '6B', '2', '2025', 89.00, 'Menunjukkan penguasaan yang baik dalam hal berperilaku gotong royong, menjaga persatuan dan meneladani sikap tokoh Indonesia serta baik dalam hal menjelaskan keberagaman budaya, peran budaya dan dampak yang merusak kebinekaan.', '2025-07-06 09:15:20', '2025-07-08 03:13:44'),
('080873', 'SBDP', 'Seni Budaya dan Prakarya', '6B', '2', '2025', 90.00, 'Menunjukkan penguasaan yang sangat baik dalam hal Peserta didik mampu menciptakan karya 2 dimensi atau 3 dimensi dengan menggabungkan elemen seni rupa, serta sangat baik dalam hal Peserta didik secara mandiri menggunakan berbagai prosedur untuk berkarya dengan aneka pilihan media.', '2025-07-06 09:15:20', '2025-07-08 03:13:44'),
('080873', 'SCI', 'Science', '6B', '2', '2025', 92.00, 'Menunjukkan penguasaan yang baik dalam hal Peserta didik mengidentifikasi benda langit di tata surya serta hubungan Bumi, Bulan dan matahari, serta baik dalam hal Peserta didik dapat mengembangkan ketrampilan penelitian, analisis dan presentasi dari suatu topik.', '2025-07-06 09:15:20', '2025-07-08 03:13:44'),
('080873', 'TIK', 'Teknologi Informasi dan Komunikasi', '6B', '2', '2025', 89.00, 'Menunjukkan penguasaan yang sangat baik dalam hal Membuat dokumen menggunakan program layar presentasi, serta sangat baik dalam hal Mengidentifikasi Perangkat Lunak Program Layar Presentasi.', '2025-07-06 09:15:20', '2025-07-08 03:13:44'),
('080874', 'AGAMA', 'Pendidikan Agama', '6B', '2', '2025', 88.00, 'Menunjukkan penguasaan yang sangat baik dalam hal Menjelaskan perintah peduli lingkungan dalam Islam, dan menjelaskan manfaat peduli lingkungan, serta sangat baik dalam hal Menjelaskan macam-macam puasa Sunnah beserta dalilnya, dan menjelaskan ketentuan puasa Sunnah.', '2025-07-06 09:15:20', '2025-07-08 03:13:43'),
('080874', 'BIND', 'Bahasa Indonesia', '6B', '2', '2025', 87.00, 'Menunjukkan penguasaan yang baik dalam hal pidato, resensi buku kuitansi serta baik dalam hal kaidah kebahasan, kosa kata ungkapan dan peribahasa.', '2025-07-06 09:15:20', '2025-07-08 03:13:43'),
('080874', 'BING', 'Bahasa Inggris', '6B', '2', '2025', 89.00, 'Demonstrates very good mastery in terms of understanding simple Information which are got orally and responding to them by actions and verbally, and is very good in terms of understanding the information in simple description text, narration reading text.', '2025-07-06 09:15:20', '2025-07-08 03:13:43'),
('080874', 'IPAS', 'Ilmu Pengetahuan Alam dan Sosial', '6B', '2', '2025', 87.00, 'Menunjukkan penguasaan yang baik dalam hal Peserta didik mengidentifikasi benda langit di tata surya serta hubungan Bumi, Bulan dan matahari, serta baik dalam hal Peserta didik dapat mengembangkan ketrampilan penelitian, analisis dan presentasi dari suatu topik.', '2025-07-06 09:15:20', '2025-07-08 03:13:43'),
('080874', 'MATH', 'Math', '6B', '2', '2025', 90.00, 'Menunjukkan penguasaan yang baik dalam hal Membandingkan prisma, tabung, limas, kerucut dan bola serta baik dalam hal Menjelaskan gabungan dari beberapa bangun ruang, luas dan volume serta membandingkan modus, median dan mean.', '2025-07-06 09:15:20', '2025-07-08 03:13:43'),
('080874', 'MTK', 'Matematika', '6B', '2', '2025', 89.00, 'Menunjukkan penguasaan yang baik dalam hal Membandingkan prisma, tabung, limas, kerucut dan bola serta baik dalam hal Menjelaskan gabungan dari beberapa bangun ruang, luas dan volume serta membandingkan modus, median dan mean.', '2025-07-06 09:15:20', '2025-07-08 03:13:44'),
('080874', 'PJOK', 'Pendidikan Jasmani, Olahraga dan Kesehatan', '6B', '2', '2025', 84.00, 'Menunjukkan penguasaan yang baik dalam hal Peserta didik dapat Memahami dan Mempraktikkan kombinasi gerak Kebugaran Jasmani terkait Kesehatan serta baik dalam hal Peserta didik dapat Memahami dan Mempraktikkan Aktivitas Gerak Senam, Gerak Berirama dan Permainan Tradisional.', '2025-07-06 09:15:20', '2025-07-08 03:13:44'),
('080874', 'PLBJ', 'Pendidikan Lingkungan dan Budaya Jakarta', '6B', '2', '2025', 88.00, 'Menunjukkan penguasaan yang baik dalam hal Peserta didik dapat mengetahui dan menjelaskan tanaman khas Jakarta serta baik dalam hal Peserta didik dapat mengenal dan menjelaskan fasilitas umum pusat perbelanjaan modern.', '2025-07-06 09:15:20', '2025-07-08 03:13:44'),
('080874', 'PPKN', 'Pendidikan Pancasila dan Kewarganegaraan', '6B', '2', '2025', 86.00, 'Menunjukkan penguasaan yang baik dalam hal berperilaku gotong royong, menjaga persatuan dan meneladani sikap tokoh Indonesia serta baik dalam hal menjelaskan keberagaman budaya, peran budaya dan dampak yang merusak kebinekaan.', '2025-07-06 09:15:20', '2025-07-08 03:13:44'),
('080874', 'SBDP', 'Seni Budaya dan Prakarya', '6B', '2', '2025', 87.00, 'Menunjukkan penguasaan yang sangat baik dalam hal Peserta didik mampu menciptakan karya 2 dimensi atau 3 dimensi dengan menggabungkan elemen seni rupa, serta sangat baik dalam hal Peserta didik secara mandiri menggunakan berbagai prosedur untuk berkarya dengan aneka pilihan media.', '2025-07-06 09:15:20', '2025-07-08 03:13:44'),
('080874', 'SCI', 'Science', '6B', '2', '2025', 89.00, 'Menunjukkan penguasaan yang baik dalam hal Peserta didik mengidentifikasi benda langit di tata surya serta hubungan Bumi, Bulan dan matahari, serta baik dalam hal Peserta didik dapat mengembangkan ketrampilan penelitian, analisis dan presentasi dari suatu topik.', '2025-07-06 09:15:20', '2025-07-08 03:13:44'),
('080874', 'TIK', 'Teknologi Informasi dan Komunikasi', '6B', '2', '2025', 86.00, 'Menunjukkan penguasaan yang sangat baik dalam hal Membuat dokumen menggunakan program layar presentasi, serta sangat baik dalam hal Mengidentifikasi Perangkat Lunak Program Layar Presentasi.', '2025-07-06 09:15:20', '2025-07-08 03:13:44'),
('080875', 'AGAMA', 'Pendidikan Agama', '6B', '2', '2025', 86.00, 'Menunjukkan penguasaan yang sangat baik dalam hal Menjelaskan perintah peduli lingkungan dalam Islam, dan menjelaskan manfaat peduli lingkungan, serta sangat baik dalam hal Menjelaskan macam-macam puasa Sunnah beserta dalilnya, dan menjelaskan ketentuan puasa Sunnah.', '2025-07-06 09:15:20', '2025-07-08 03:13:43'),
('080875', 'BIND', 'Bahasa Indonesia', '6B', '2', '2025', 85.00, 'Menunjukkan penguasaan yang baik dalam hal pidato, resensi buku kuitansi serta baik dalam hal kaidah kebahasan, kosa kata ungkapan dan peribahasa.', '2025-07-06 09:15:20', '2025-07-08 03:13:43'),
('080875', 'BING', 'Bahasa Inggris', '6B', '2', '2025', 87.00, 'Demonstrates very good mastery in terms of understanding simple Information which are got orally and responding to them by actions and verbally, and is very good in terms of understanding the information in simple description text, narration reading text.', '2025-07-06 09:15:20', '2025-07-08 03:13:43'),
('080875', 'IPAS', 'Ilmu Pengetahuan Alam dan Sosial', '6B', '2', '2025', 85.00, 'Menunjukkan penguasaan yang baik dalam hal Peserta didik mengidentifikasi benda langit di tata surya serta hubungan Bumi, Bulan dan matahari, serta baik dalam hal Peserta didik dapat mengembangkan ketrampilan penelitian, analisis dan presentasi dari suatu topik.', '2025-07-06 09:15:20', '2025-07-08 03:13:43'),
('080875', 'MATH', 'Math', '6B', '2', '2025', 88.00, 'Menunjukkan penguasaan yang baik dalam hal Membandingkan prisma, tabung, limas, kerucut dan bola serta baik dalam hal Menjelaskan gabungan dari beberapa bangun ruang, luas dan volume serta membandingkan modus, median dan mean.', '2025-07-06 09:15:20', '2025-07-08 03:13:43'),
('080875', 'MTK', 'Matematika', '6B', '2', '2025', 87.00, 'Menunjukkan penguasaan yang baik dalam hal Membandingkan prisma, tabung, limas, kerucut dan bola serta baik dalam hal Menjelaskan gabungan dari beberapa bangun ruang, luas dan volume serta membandingkan modus, median dan mean.', '2025-07-06 09:15:20', '2025-07-08 03:13:44'),
('080875', 'PJOK', 'Pendidikan Jasmani, Olahraga dan Kesehatan', '6B', '2', '2025', 82.00, 'Menunjukkan penguasaan yang baik dalam hal Peserta didik dapat Memahami dan Mempraktikkan kombinasi gerak Kebugaran Jasmani terkait Kesehatan serta baik dalam hal Peserta didik dapat Memahami dan Mempraktikkan Aktivitas Gerak Senam, Gerak Berirama dan Permainan Tradisional.', '2025-07-06 09:15:20', '2025-07-08 03:13:44'),
('080875', 'PLBJ', 'Pendidikan Lingkungan dan Budaya Jakarta', '6B', '2', '2025', 86.00, 'Menunjukkan penguasaan yang baik dalam hal Peserta didik dapat mengetahui dan menjelaskan tanaman khas Jakarta serta baik dalam hal Peserta didik dapat mengenal dan menjelaskan fasilitas umum pusat perbelanjaan modern.', '2025-07-06 09:15:20', '2025-07-08 03:13:44'),
('080875', 'PPKN', 'Pendidikan Pancasila dan Kewarganegaraan', '6B', '2', '2025', 84.00, 'Menunjukkan penguasaan yang baik dalam hal berperilaku gotong royong, menjaga persatuan dan meneladani sikap tokoh Indonesia serta baik dalam hal menjelaskan keberagaman budaya, peran budaya dan dampak yang merusak kebinekaan.', '2025-07-06 09:15:20', '2025-07-08 03:13:44'),
('080875', 'SBDP', 'Seni Budaya dan Prakarya', '6B', '2', '2025', 85.00, 'Menunjukkan penguasaan yang sangat baik dalam hal Peserta didik mampu menciptakan karya 2 dimensi atau 3 dimensi dengan menggabungkan elemen seni rupa, serta sangat baik dalam hal Peserta didik secara mandiri menggunakan berbagai prosedur untuk berkarya dengan aneka pilihan media.', '2025-07-06 09:15:20', '2025-07-08 03:13:44'),
('080875', 'SCI', 'Science', '6B', '2', '2025', 87.00, 'Menunjukkan penguasaan yang baik dalam hal Peserta didik mengidentifikasi benda langit di tata surya serta hubungan Bumi, Bulan dan matahari, serta baik dalam hal Peserta didik dapat mengembangkan ketrampilan penelitian, analisis dan presentasi dari suatu topik.', '2025-07-06 09:15:20', '2025-07-08 03:13:44'),
('080875', 'TIK', 'Teknologi Informasi dan Komunikasi', '6B', '2', '2025', 84.00, 'Menunjukkan penguasaan yang sangat baik dalam hal Membuat dokumen menggunakan program layar presentasi, serta sangat baik dalam hal Mengidentifikasi Perangkat Lunak Program Layar Presentasi.', '2025-07-06 09:15:20', '2025-07-08 03:13:44'),
('080876', 'AGAMA', 'Pendidikan Agama', '6B', '2', '2025', 84.00, 'Menunjukkan penguasaan yang sangat baik dalam hal Menjelaskan perintah peduli lingkungan dalam Islam, dan menjelaskan manfaat peduli lingkungan, serta sangat baik dalam hal Menjelaskan macam-macam puasa Sunnah beserta dalilnya, dan menjelaskan ketentuan puasa Sunnah.', '2025-07-06 09:15:20', '2025-07-08 03:13:43'),
('080876', 'BIND', 'Bahasa Indonesia', '6B', '2', '2025', 83.00, 'Menunjukkan penguasaan yang baik dalam hal pidato, resensi buku kuitansi serta baik dalam hal kaidah kebahasan, kosa kata ungkapan dan peribahasa.', '2025-07-06 09:15:20', '2025-07-08 03:13:43'),
('080876', 'BING', 'Bahasa Inggris', '6B', '2', '2025', 85.00, 'Demonstrates very good mastery in terms of understanding simple Information which are got orally and responding to them by actions and verbally, and is very good in terms of understanding the information in simple description text, narration reading text.', '2025-07-06 09:15:20', '2025-07-08 03:13:43'),
('080876', 'IPAS', 'Ilmu Pengetahuan Alam dan Sosial', '6B', '2', '2025', 83.00, 'Menunjukkan penguasaan yang baik dalam hal Peserta didik mengidentifikasi benda langit di tata surya serta hubungan Bumi, Bulan dan matahari, serta baik dalam hal Peserta didik dapat mengembangkan ketrampilan penelitian, analisis dan presentasi dari suatu topik.', '2025-07-06 09:15:20', '2025-07-08 03:13:43'),
('080876', 'MATH', 'Math', '6B', '2', '2025', 86.00, 'Menunjukkan penguasaan yang baik dalam hal Membandingkan prisma, tabung, limas, kerucut dan bola serta baik dalam hal Menjelaskan gabungan dari beberapa bangun ruang, luas dan volume serta membandingkan modus, median dan mean.', '2025-07-06 09:15:20', '2025-07-08 03:13:43'),
('080876', 'MTK', 'Matematika', '6B', '2', '2025', 85.00, 'Menunjukkan penguasaan yang baik dalam hal Membandingkan prisma, tabung, limas, kerucut dan bola serta baik dalam hal Menjelaskan gabungan dari beberapa bangun ruang, luas dan volume serta membandingkan modus, median dan mean.', '2025-07-06 09:15:20', '2025-07-08 03:13:44'),
('080876', 'PJOK', 'Pendidikan Jasmani, Olahraga dan Kesehatan', '6B', '2', '2025', 80.00, 'Menunjukkan penguasaan yang baik dalam hal Peserta didik dapat Memahami dan Mempraktikkan kombinasi gerak Kebugaran Jasmani terkait Kesehatan serta baik dalam hal Peserta didik dapat Memahami dan Mempraktikkan Aktivitas Gerak Senam, Gerak Berirama dan Permainan Tradisional.', '2025-07-06 09:15:20', '2025-07-08 03:13:44'),
('080876', 'PLBJ', 'Pendidikan Lingkungan dan Budaya Jakarta', '6B', '2', '2025', 84.00, 'Menunjukkan penguasaan yang baik dalam hal Peserta didik dapat mengetahui dan menjelaskan tanaman khas Jakarta serta baik dalam hal Peserta didik dapat mengenal dan menjelaskan fasilitas umum pusat perbelanjaan modern.', '2025-07-06 09:15:20', '2025-07-08 03:13:44'),
('080876', 'PPKN', 'Pendidikan Pancasila dan Kewarganegaraan', '6B', '2', '2025', 82.00, 'Menunjukkan penguasaan yang baik dalam hal berperilaku gotong royong, menjaga persatuan dan meneladani sikap tokoh Indonesia serta baik dalam hal menjelaskan keberagaman budaya, peran budaya dan dampak yang merusak kebinekaan.', '2025-07-06 09:15:20', '2025-07-08 03:13:44'),
('080876', 'SBDP', 'Seni Budaya dan Prakarya', '6B', '2', '2025', 83.00, 'Menunjukkan penguasaan yang sangat baik dalam hal Peserta didik mampu menciptakan karya 2 dimensi atau 3 dimensi dengan menggabungkan elemen seni rupa, serta sangat baik dalam hal Peserta didik secara mandiri menggunakan berbagai prosedur untuk berkarya dengan aneka pilihan media.', '2025-07-06 09:15:20', '2025-07-08 03:13:44'),
('080876', 'SCI', 'Science', '6B', '2', '2025', 85.00, 'Menunjukkan penguasaan yang baik dalam hal Peserta didik mengidentifikasi benda langit di tata surya serta hubungan Bumi, Bulan dan matahari, serta baik dalam hal Peserta didik dapat mengembangkan ketrampilan penelitian, analisis dan presentasi dari suatu topik.', '2025-07-06 09:15:20', '2025-07-08 03:13:44'),
('080876', 'TIK', 'Teknologi Informasi dan Komunikasi', '6B', '2', '2025', 82.00, 'Menunjukkan penguasaan yang sangat baik dalam hal Membuat dokumen menggunakan program layar presentasi, serta sangat baik dalam hal Mengidentifikasi Perangkat Lunak Program Layar Presentasi.', '2025-07-06 09:15:20', '2025-07-08 03:13:44'),
('080877', 'AGAMA', 'Pendidikan Agama', '6B', '2', '2025', 82.00, 'Menunjukkan penguasaan yang sangat baik dalam hal Menjelaskan perintah peduli lingkungan dalam Islam, dan menjelaskan manfaat peduli lingkungan, serta sangat baik dalam hal Menjelaskan macam-macam puasa Sunnah beserta dalilnya, dan menjelaskan ketentuan puasa Sunnah.', '2025-07-06 09:15:20', '2025-07-08 03:13:43'),
('080877', 'BIND', 'Bahasa Indonesia', '6B', '2', '2025', 81.00, 'Menunjukkan penguasaan yang baik dalam hal pidato, resensi buku kuitansi serta baik dalam hal kaidah kebahasan, kosa kata ungkapan dan peribahasa.', '2025-07-06 09:15:20', '2025-07-08 03:13:43'),
('080877', 'BING', 'Bahasa Inggris', '6B', '2', '2025', 83.00, 'Demonstrates very good mastery in terms of understanding simple Information which are got orally and responding to them by actions and verbally, and is very good in terms of understanding the information in simple description text, narration reading text.', '2025-07-06 09:15:20', '2025-07-08 03:13:43'),
('080877', 'IPAS', 'Ilmu Pengetahuan Alam dan Sosial', '6B', '2', '2025', 81.00, 'Menunjukkan penguasaan yang baik dalam hal Peserta didik mengidentifikasi benda langit di tata surya serta hubungan Bumi, Bulan dan matahari, serta baik dalam hal Peserta didik dapat mengembangkan ketrampilan penelitian, analisis dan presentasi dari suatu topik.', '2025-07-06 09:15:20', '2025-07-08 03:13:43'),
('080877', 'MATH', 'Math', '6B', '2', '2025', 84.00, 'Menunjukkan penguasaan yang baik dalam hal Membandingkan prisma, tabung, limas, kerucut dan bola serta baik dalam hal Menjelaskan gabungan dari beberapa bangun ruang, luas dan volume serta membandingkan modus, median dan mean.', '2025-07-06 09:15:20', '2025-07-08 03:13:43'),
('080877', 'MTK', 'Matematika', '6B', '2', '2025', 83.00, 'Menunjukkan penguasaan yang baik dalam hal Membandingkan prisma, tabung, limas, kerucut dan bola serta baik dalam hal Menjelaskan gabungan dari beberapa bangun ruang, luas dan volume serta membandingkan modus, median dan mean.', '2025-07-06 09:15:20', '2025-07-08 03:13:44'),
('080877', 'PJOK', 'Pendidikan Jasmani, Olahraga dan Kesehatan', '6B', '2', '2025', 78.00, 'Menunjukkan penguasaan yang baik dalam hal Peserta didik dapat Memahami dan Mempraktikkan kombinasi gerak Kebugaran Jasmani terkait Kesehatan serta baik dalam hal Peserta didik dapat Memahami dan Mempraktikkan Aktivitas Gerak Senam, Gerak Berirama dan Permainan Tradisional.', '2025-07-06 09:15:20', '2025-07-08 03:13:44'),
('080877', 'PLBJ', 'Pendidikan Lingkungan dan Budaya Jakarta', '6B', '2', '2025', 82.00, 'Menunjukkan penguasaan yang baik dalam hal Peserta didik dapat mengetahui dan menjelaskan tanaman khas Jakarta serta baik dalam hal Peserta didik dapat mengenal dan menjelaskan fasilitas umum pusat perbelanjaan modern.', '2025-07-06 09:15:20', '2025-07-08 03:13:44'),
('080877', 'PPKN', 'Pendidikan Pancasila dan Kewarganegaraan', '6B', '2', '2025', 80.00, 'Menunjukkan penguasaan yang baik dalam hal berperilaku gotong royong, menjaga persatuan dan meneladani sikap tokoh Indonesia serta baik dalam hal menjelaskan keberagaman budaya, peran budaya dan dampak yang merusak kebinekaan.', '2025-07-06 09:15:20', '2025-07-08 03:13:44'),
('080877', 'SBDP', 'Seni Budaya dan Prakarya', '6B', '2', '2025', 81.00, 'Menunjukkan penguasaan yang sangat baik dalam hal Peserta didik mampu menciptakan karya 2 dimensi atau 3 dimensi dengan menggabungkan elemen seni rupa, serta sangat baik dalam hal Peserta didik secara mandiri menggunakan berbagai prosedur untuk berkarya dengan aneka pilihan media.', '2025-07-06 09:15:20', '2025-07-08 03:13:44'),
('080877', 'SCI', 'Science', '6B', '2', '2025', 83.00, 'Menunjukkan penguasaan yang baik dalam hal Peserta didik mengidentifikasi benda langit di tata surya serta hubungan Bumi, Bulan dan matahari, serta baik dalam hal Peserta didik dapat mengembangkan ketrampilan penelitian, analisis dan presentasi dari suatu topik.', '2025-07-06 09:15:20', '2025-07-08 03:13:44'),
('080877', 'TIK', 'Teknologi Informasi dan Komunikasi', '6B', '2', '2025', 80.00, 'Menunjukkan penguasaan yang sangat baik dalam hal Membuat dokumen menggunakan program layar presentasi, serta sangat baik dalam hal Mengidentifikasi Perangkat Lunak Program Layar Presentasi.', '2025-07-06 09:15:20', '2025-07-08 03:13:44'),
('080878', 'AGAMA', 'Pendidikan Agama', '6B', '2', '2025', 80.00, 'Menunjukkan penguasaan yang sangat baik dalam hal Menjelaskan perintah peduli lingkungan dalam Islam, dan menjelaskan manfaat peduli lingkungan, serta sangat baik dalam hal Menjelaskan macam-macam puasa Sunnah beserta dalilnya, dan menjelaskan ketentuan puasa Sunnah.', '2025-07-06 09:15:20', '2025-07-08 03:13:43'),
('080878', 'BIND', 'Bahasa Indonesia', '6B', '2', '2025', 79.00, 'Menunjukkan penguasaan yang baik dalam hal pidato, resensi buku kuitansi serta baik dalam hal kaidah kebahasan, kosa kata ungkapan dan peribahasa.', '2025-07-06 09:15:20', '2025-07-08 03:13:43'),
('080878', 'BING', 'Bahasa Inggris', '6B', '2', '2025', 81.00, 'Demonstrates very good mastery in terms of understanding simple Information which are got orally and responding to them by actions and verbally, and is very good in terms of understanding the information in simple description text, narration reading text.', '2025-07-06 09:15:20', '2025-07-08 03:13:43'),
('080878', 'IPAS', 'Ilmu Pengetahuan Alam dan Sosial', '6B', '2', '2025', 79.00, 'Menunjukkan penguasaan yang baik dalam hal Peserta didik mengidentifikasi benda langit di tata surya serta hubungan Bumi, Bulan dan matahari, serta baik dalam hal Peserta didik dapat mengembangkan ketrampilan penelitian, analisis dan presentasi dari suatu topik.', '2025-07-06 09:15:20', '2025-07-08 03:13:43'),
('080878', 'MATH', 'Math', '6B', '2', '2025', 82.00, 'Menunjukkan penguasaan yang baik dalam hal Membandingkan prisma, tabung, limas, kerucut dan bola serta baik dalam hal Menjelaskan gabungan dari beberapa bangun ruang, luas dan volume serta membandingkan modus, median dan mean.', '2025-07-06 09:15:20', '2025-07-08 03:13:43'),
('080878', 'MTK', 'Matematika', '6B', '2', '2025', 81.00, 'Menunjukkan penguasaan yang baik dalam hal Membandingkan prisma, tabung, limas, kerucut dan bola serta baik dalam hal Menjelaskan gabungan dari beberapa bangun ruang, luas dan volume serta membandingkan modus, median dan mean.', '2025-07-06 09:15:20', '2025-07-08 03:13:44'),
('080878', 'PJOK', 'Pendidikan Jasmani, Olahraga dan Kesehatan', '6B', '2', '2025', 76.00, 'Menunjukkan penguasaan yang baik dalam hal Peserta didik dapat Memahami dan Mempraktikkan kombinasi gerak Kebugaran Jasmani terkait Kesehatan serta baik dalam hal Peserta didik dapat Memahami dan Mempraktikkan Aktivitas Gerak Senam, Gerak Berirama dan Permainan Tradisional.', '2025-07-06 09:15:20', '2025-07-08 03:13:44'),
('080878', 'PLBJ', 'Pendidikan Lingkungan dan Budaya Jakarta', '6B', '2', '2025', 80.00, 'Menunjukkan penguasaan yang baik dalam hal Peserta didik dapat mengetahui dan menjelaskan tanaman khas Jakarta serta baik dalam hal Peserta didik dapat mengenal dan menjelaskan fasilitas umum pusat perbelanjaan modern.', '2025-07-06 09:15:20', '2025-07-08 03:13:44'),
('080878', 'PPKN', 'Pendidikan Pancasila dan Kewarganegaraan', '6B', '2', '2025', 78.00, 'Menunjukkan penguasaan yang baik dalam hal berperilaku gotong royong, menjaga persatuan dan meneladani sikap tokoh Indonesia serta baik dalam hal menjelaskan keberagaman budaya, peran budaya dan dampak yang merusak kebinekaan.', '2025-07-06 09:15:20', '2025-07-08 03:13:44'),
('080878', 'SBDP', 'Seni Budaya dan Prakarya', '6B', '2', '2025', 79.00, 'Menunjukkan penguasaan yang sangat baik dalam hal Peserta didik mampu menciptakan karya 2 dimensi atau 3 dimensi dengan menggabungkan elemen seni rupa, serta sangat baik dalam hal Peserta didik secara mandiri menggunakan berbagai prosedur untuk berkarya dengan aneka pilihan media.', '2025-07-06 09:15:20', '2025-07-08 03:13:44'),
('080878', 'SCI', 'Science', '6B', '2', '2025', 81.00, 'Menunjukkan penguasaan yang baik dalam hal Peserta didik mengidentifikasi benda langit di tata surya serta hubungan Bumi, Bulan dan matahari, serta baik dalam hal Peserta didik dapat mengembangkan ketrampilan penelitian, analisis dan presentasi dari suatu topik.', '2025-07-06 09:15:20', '2025-07-08 03:13:44'),
('080878', 'TIK', 'Teknologi Informasi dan Komunikasi', '6B', '2', '2025', 78.00, 'Menunjukkan penguasaan yang sangat baik dalam hal Membuat dokumen menggunakan program layar presentasi, serta sangat baik dalam hal Mengidentifikasi Perangkat Lunak Program Layar Presentasi.', '2025-07-06 09:15:20', '2025-07-08 03:13:44'),
('080879', 'AGAMA', 'Pendidikan Agama', '6B', '2', '2025', 78.00, 'Menunjukkan penguasaan yang sangat baik dalam hal Menjelaskan perintah peduli lingkungan dalam Islam, dan menjelaskan manfaat peduli lingkungan, serta sangat baik dalam hal Menjelaskan macam-macam puasa Sunnah beserta dalilnya, dan menjelaskan ketentuan puasa Sunnah.', '2025-07-06 09:15:20', '2025-07-08 03:13:43'),
('080879', 'BIND', 'Bahasa Indonesia', '6B', '2', '2025', 77.00, 'Menunjukkan penguasaan yang baik dalam hal pidato, resensi buku kuitansi serta baik dalam hal kaidah kebahasan, kosa kata ungkapan dan peribahasa.', '2025-07-06 09:15:20', '2025-07-08 03:13:43'),
('080879', 'BING', 'Bahasa Inggris', '6B', '2', '2025', 79.00, 'Demonstrates very good mastery in terms of understanding simple Information which are got orally and responding to them by actions and verbally, and is very good in terms of understanding the information in simple description text, narration reading text.', '2025-07-06 09:15:20', '2025-07-08 03:13:43'),
('080879', 'IPAS', 'Ilmu Pengetahuan Alam dan Sosial', '6B', '2', '2025', 77.00, 'Menunjukkan penguasaan yang baik dalam hal Peserta didik mengidentifikasi benda langit di tata surya serta hubungan Bumi, Bulan dan matahari, serta baik dalam hal Peserta didik dapat mengembangkan ketrampilan penelitian, analisis dan presentasi dari suatu topik.', '2025-07-06 09:15:20', '2025-07-08 03:13:43'),
('080879', 'MATH', 'Math', '6B', '2', '2025', 80.00, 'Menunjukkan penguasaan yang baik dalam hal Membandingkan prisma, tabung, limas, kerucut dan bola serta baik dalam hal Menjelaskan gabungan dari beberapa bangun ruang, luas dan volume serta membandingkan modus, median dan mean.', '2025-07-06 09:15:20', '2025-07-08 03:13:43'),
('080879', 'MTK', 'Matematika', '6B', '2', '2025', 79.00, 'Menunjukkan penguasaan yang baik dalam hal Membandingkan prisma, tabung, limas, kerucut dan bola serta baik dalam hal Menjelaskan gabungan dari beberapa bangun ruang, luas dan volume serta membandingkan modus, median dan mean.', '2025-07-06 09:15:20', '2025-07-08 03:13:44'),
('080879', 'PJOK', 'Pendidikan Jasmani, Olahraga dan Kesehatan', '6B', '2', '2025', 74.00, 'Menunjukkan penguasaan yang baik dalam hal Peserta didik dapat Memahami dan Mempraktikkan kombinasi gerak Kebugaran Jasmani terkait Kesehatan serta baik dalam hal Peserta didik dapat Memahami dan Mempraktikkan Aktivitas Gerak Senam, Gerak Berirama dan Permainan Tradisional.', '2025-07-06 09:15:20', '2025-07-08 03:13:44'),
('080879', 'PLBJ', 'Pendidikan Lingkungan dan Budaya Jakarta', '6B', '2', '2025', 78.00, 'Menunjukkan penguasaan yang baik dalam hal Peserta didik dapat mengetahui dan menjelaskan tanaman khas Jakarta serta baik dalam hal Peserta didik dapat mengenal dan menjelaskan fasilitas umum pusat perbelanjaan modern.', '2025-07-06 09:15:20', '2025-07-08 03:13:44'),
('080879', 'PPKN', 'Pendidikan Pancasila dan Kewarganegaraan', '6B', '2', '2025', 76.00, 'Menunjukkan penguasaan yang baik dalam hal berperilaku gotong royong, menjaga persatuan dan meneladani sikap tokoh Indonesia serta baik dalam hal menjelaskan keberagaman budaya, peran budaya dan dampak yang merusak kebinekaan.', '2025-07-06 09:15:20', '2025-07-08 03:13:44'),
('080879', 'SBDP', 'Seni Budaya dan Prakarya', '6B', '2', '2025', 77.00, 'Menunjukkan penguasaan yang sangat baik dalam hal Peserta didik mampu menciptakan karya 2 dimensi atau 3 dimensi dengan menggabungkan elemen seni rupa, serta sangat baik dalam hal Peserta didik secara mandiri menggunakan berbagai prosedur untuk berkarya dengan aneka pilihan media.', '2025-07-06 09:15:20', '2025-07-08 03:13:44'),
('080879', 'SCI', 'Science', '6B', '2', '2025', 79.00, 'Menunjukkan penguasaan yang baik dalam hal Peserta didik mengidentifikasi benda langit di tata surya serta hubungan Bumi, Bulan dan matahari, serta baik dalam hal Peserta didik dapat mengembangkan ketrampilan penelitian, analisis dan presentasi dari suatu topik.', '2025-07-06 09:15:20', '2025-07-08 03:13:44'),
('080879', 'TIK', 'Teknologi Informasi dan Komunikasi', '6B', '2', '2025', 76.00, 'Menunjukkan penguasaan yang sangat baik dalam hal Membuat dokumen menggunakan program layar presentasi, serta sangat baik dalam hal Mengidentifikasi Perangkat Lunak Program Layar Presentasi.', '2025-07-06 09:15:20', '2025-07-08 03:13:44'),
('080880', 'AGAMA', 'Pendidikan Agama', '6B', '2', '2025', 76.00, 'Menunjukkan penguasaan yang sangat baik dalam hal Menjelaskan perintah peduli lingkungan dalam Islam, dan menjelaskan manfaat peduli lingkungan, serta sangat baik dalam hal Menjelaskan macam-macam puasa Sunnah beserta dalilnya, dan menjelaskan ketentuan puasa Sunnah.', '2025-07-06 09:15:20', '2025-07-08 03:13:43'),
('080880', 'BIND', 'Bahasa Indonesia', '6B', '2', '2025', 75.00, 'Menunjukkan penguasaan yang baik dalam hal pidato, resensi buku kuitansi serta baik dalam hal kaidah kebahasan, kosa kata ungkapan dan peribahasa.', '2025-07-06 09:15:20', '2025-07-08 03:13:43'),
('080880', 'BING', 'Bahasa Inggris', '6B', '2', '2025', 77.00, 'Demonstrates very good mastery in terms of understanding simple Information which are got orally and responding to them by actions and verbally, and is very good in terms of understanding the information in simple description text, narration reading text.', '2025-07-06 09:15:20', '2025-07-08 03:13:43'),
('080880', 'IPAS', 'Ilmu Pengetahuan Alam dan Sosial', '6B', '2', '2025', 75.00, 'Menunjukkan penguasaan yang baik dalam hal Peserta didik mengidentifikasi benda langit di tata surya serta hubungan Bumi, Bulan dan matahari, serta baik dalam hal Peserta didik dapat mengembangkan ketrampilan penelitian, analisis dan presentasi dari suatu topik.', '2025-07-06 09:15:20', '2025-07-08 03:13:43'),
('080880', 'MATH', 'Math', '6B', '2', '2025', 78.00, 'Menunjukkan penguasaan yang baik dalam hal Membandingkan prisma, tabung, limas, kerucut dan bola serta baik dalam hal Menjelaskan gabungan dari beberapa bangun ruang, luas dan volume serta membandingkan modus, median dan mean.', '2025-07-06 09:15:20', '2025-07-08 03:13:43'),
('080880', 'MTK', 'Matematika', '6B', '2', '2025', 77.00, 'Menunjukkan penguasaan yang baik dalam hal Membandingkan prisma, tabung, limas, kerucut dan bola serta baik dalam hal Menjelaskan gabungan dari beberapa bangun ruang, luas dan volume serta membandingkan modus, median dan mean.', '2025-07-06 09:15:20', '2025-07-08 03:13:44'),
('080880', 'PJOK', 'Pendidikan Jasmani, Olahraga dan Kesehatan', '6B', '2', '2025', 72.00, 'Menunjukkan penguasaan yang baik dalam hal Peserta didik dapat Memahami dan Mempraktikkan kombinasi gerak Kebugaran Jasmani terkait Kesehatan serta baik dalam hal Peserta didik dapat Memahami dan Mempraktikkan Aktivitas Gerak Senam, Gerak Berirama dan Permainan Tradisional.', '2025-07-06 09:15:20', '2025-07-08 03:13:44'),
('080880', 'PLBJ', 'Pendidikan Lingkungan dan Budaya Jakarta', '6B', '2', '2025', 76.00, 'Menunjukkan penguasaan yang baik dalam hal Peserta didik dapat mengetahui dan menjelaskan tanaman khas Jakarta serta baik dalam hal Peserta didik dapat mengenal dan menjelaskan fasilitas umum pusat perbelanjaan modern.', '2025-07-06 09:15:20', '2025-07-08 03:13:44'),
('080880', 'PPKN', 'Pendidikan Pancasila dan Kewarganegaraan', '6B', '2', '2025', 74.00, 'Menunjukkan penguasaan yang baik dalam hal berperilaku gotong royong, menjaga persatuan dan meneladani sikap tokoh Indonesia serta baik dalam hal menjelaskan keberagaman budaya, peran budaya dan dampak yang merusak kebinekaan.', '2025-07-06 09:15:20', '2025-07-08 03:13:44'),
('080880', 'SBDP', 'Seni Budaya dan Prakarya', '6B', '2', '2025', 75.00, 'Menunjukkan penguasaan yang sangat baik dalam hal Peserta didik mampu menciptakan karya 2 dimensi atau 3 dimensi dengan menggabungkan elemen seni rupa, serta sangat baik dalam hal Peserta didik secara mandiri menggunakan berbagai prosedur untuk berkarya dengan aneka pilihan media.', '2025-07-06 09:15:20', '2025-07-08 03:13:44'),
('080880', 'SCI', 'Science', '6B', '2', '2025', 77.00, 'Menunjukkan penguasaan yang baik dalam hal Peserta didik mengidentifikasi benda langit di tata surya serta hubungan Bumi, Bulan dan matahari, serta baik dalam hal Peserta didik dapat mengembangkan ketrampilan penelitian, analisis dan presentasi dari suatu topik.', '2025-07-06 09:15:20', '2025-07-08 03:13:44'),
('080880', 'TIK', 'Teknologi Informasi dan Komunikasi', '6B', '2', '2025', 74.00, 'Menunjukkan penguasaan yang sangat baik dalam hal Membuat dokumen menggunakan program layar presentasi, serta sangat baik dalam hal Mengidentifikasi Perangkat Lunak Program Layar Presentasi.', '2025-07-06 09:15:20', '2025-07-08 03:13:44'),
('080881', 'AGAMA', 'Pendidikan Agama', '6B', '2', '2025', 74.00, 'Menunjukkan penguasaan yang sangat baik dalam hal Menjelaskan perintah peduli lingkungan dalam Islam, dan menjelaskan manfaat peduli lingkungan, serta sangat baik dalam hal Menjelaskan macam-macam puasa Sunnah beserta dalilnya, dan menjelaskan ketentuan puasa Sunnah.', '2025-07-06 09:15:21', '2025-07-08 03:13:43'),
('080881', 'BIND', 'Bahasa Indonesia', '6B', '2', '2025', 73.00, 'Menunjukkan penguasaan yang baik dalam hal pidato, resensi buku kuitansi serta baik dalam hal kaidah kebahasan, kosa kata ungkapan dan peribahasa.', '2025-07-06 09:15:21', '2025-07-08 03:13:43'),
('080881', 'BING', 'Bahasa Inggris', '6B', '2', '2025', 75.00, 'Demonstrates very good mastery in terms of understanding simple Information which are got orally and responding to them by actions and verbally, and is very good in terms of understanding the information in simple description text, narration reading text.', '2025-07-06 09:15:21', '2025-07-08 03:13:43'),
('080881', 'IPAS', 'Ilmu Pengetahuan Alam dan Sosial', '6B', '2', '2025', 73.00, 'Menunjukkan penguasaan yang baik dalam hal Peserta didik mengidentifikasi benda langit di tata surya serta hubungan Bumi, Bulan dan matahari, serta baik dalam hal Peserta didik dapat mengembangkan ketrampilan penelitian, analisis dan presentasi dari suatu topik.', '2025-07-06 09:15:21', '2025-07-08 03:13:43'),
('080881', 'MATH', 'Math', '6B', '2', '2025', 76.00, 'Menunjukkan penguasaan yang baik dalam hal Membandingkan prisma, tabung, limas, kerucut dan bola serta baik dalam hal Menjelaskan gabungan dari beberapa bangun ruang, luas dan volume serta membandingkan modus, median dan mean.', '2025-07-06 09:15:21', '2025-07-08 03:13:43'),
('080881', 'MTK', 'Matematika', '6B', '2', '2025', 75.00, 'Menunjukkan penguasaan yang baik dalam hal Membandingkan prisma, tabung, limas, kerucut dan bola serta baik dalam hal Menjelaskan gabungan dari beberapa bangun ruang, luas dan volume serta membandingkan modus, median dan mean.', '2025-07-06 09:15:21', '2025-07-08 03:13:44'),
('080881', 'PJOK', 'Pendidikan Jasmani, Olahraga dan Kesehatan', '6B', '2', '2025', 70.00, 'Menunjukkan penguasaan yang baik dalam hal Peserta didik dapat Memahami dan Mempraktikkan kombinasi gerak Kebugaran Jasmani terkait Kesehatan serta baik dalam hal Peserta didik dapat Memahami dan Mempraktikkan Aktivitas Gerak Senam, Gerak Berirama dan Permainan Tradisional.', '2025-07-06 09:15:21', '2025-07-08 03:13:44'),
('080881', 'PLBJ', 'Pendidikan Lingkungan dan Budaya Jakarta', '6B', '2', '2025', 74.00, 'Menunjukkan penguasaan yang baik dalam hal Peserta didik dapat mengetahui dan menjelaskan tanaman khas Jakarta serta baik dalam hal Peserta didik dapat mengenal dan menjelaskan fasilitas umum pusat perbelanjaan modern.', '2025-07-06 09:15:21', '2025-07-08 03:13:44'),
('080881', 'PPKN', 'Pendidikan Pancasila dan Kewarganegaraan', '6B', '2', '2025', 72.00, 'Menunjukkan penguasaan yang baik dalam hal berperilaku gotong royong, menjaga persatuan dan meneladani sikap tokoh Indonesia serta baik dalam hal menjelaskan keberagaman budaya, peran budaya dan dampak yang merusak kebinekaan.', '2025-07-06 09:15:21', '2025-07-08 03:13:44'),
('080881', 'SBDP', 'Seni Budaya dan Prakarya', '6B', '2', '2025', 73.00, 'Menunjukkan penguasaan yang sangat baik dalam hal Peserta didik mampu menciptakan karya 2 dimensi atau 3 dimensi dengan menggabungkan elemen seni rupa, serta sangat baik dalam hal Peserta didik secara mandiri menggunakan berbagai prosedur untuk berkarya dengan aneka pilihan media.', '2025-07-06 09:15:21', '2025-07-08 03:13:44'),
('080881', 'SCI', 'Science', '6B', '2', '2025', 75.00, 'Menunjukkan penguasaan yang baik dalam hal Peserta didik mengidentifikasi benda langit di tata surya serta hubungan Bumi, Bulan dan matahari, serta baik dalam hal Peserta didik dapat mengembangkan ketrampilan penelitian, analisis dan presentasi dari suatu topik.', '2025-07-06 09:15:21', '2025-07-08 03:13:44'),
('080881', 'TIK', 'Teknologi Informasi dan Komunikasi', '6B', '2', '2025', 72.00, 'Menunjukkan penguasaan yang sangat baik dalam hal Membuat dokumen menggunakan program layar presentasi, serta sangat baik dalam hal Mengidentifikasi Perangkat Lunak Program Layar Presentasi.', '2025-07-06 09:15:21', '2025-07-08 03:13:44');

-- --------------------------------------------------------

--
-- Struktur dari tabel `peserta_ujian`
--

CREATE TABLE `peserta_ujian` (
  `id_peserta` varchar(20) NOT NULL,
  `id_ujian` varchar(20) NOT NULL,
  `NIS` varchar(20) NOT NULL,
  `nomor_peserta` varchar(20) DEFAULT NULL,
  `kehadiran` varchar(100) NOT NULL,
  `catatan` text DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `peserta_ujian`
--

INSERT INTO `peserta_ujian` (`id_peserta`, `id_ujian`, `NIS`, `nomor_peserta`, `kehadiran`, `catatan`, `created_at`, `updated_at`) VALUES
('PUJ-080871', 'UJIAN-001', '080871', '080871', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080873', 'UJIAN-001', '080873', '080873', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080874', 'UJIAN-001', '080874', '080874', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080875', 'UJIAN-001', '080875', '080875', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080876', 'UJIAN-001', '080876', '080876', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080877', 'UJIAN-001', '080877', '080877', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080878', 'UJIAN-001', '080878', '080878', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080879', 'UJIAN-001', '080879', '080879', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080880', 'UJIAN-001', '080880', '080880', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080881', 'UJIAN-001', '080881', '080881', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080882', 'UJIAN-001', '080882', '080882', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080883', 'UJIAN-001', '080883', '080883', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080884', 'UJIAN-001', '080884', '080884', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080885', 'UJIAN-001', '080885', '080885', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080886', 'UJIAN-001', '080886', '080886', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080887', 'UJIAN-001', '080887', '080887', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080889', 'UJIAN-001', '080889', '080889', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080890', 'UJIAN-001', '080890', '080890', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080891', 'UJIAN-001', '080891', '080891', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080892', 'UJIAN-001', '080892', '080892', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080895', 'UJIAN-001', '080895', '080895', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080898', 'UJIAN-001', '080898', '080898', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080899', 'UJIAN-001', '080899', '080899', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080900', 'UJIAN-001', '080900', '080900', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080902', 'UJIAN-001', '080902', '080902', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080903', 'UJIAN-001', '080903', '080903', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080914', 'UJIAN-001', '080914', '080914', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080916', 'UJIAN-001', '080916', '080916', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080918', 'UJIAN-001', '080918', '080918', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080919', 'UJIAN-001', '080919', '080919', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080920', 'UJIAN-001', '080920', '080920', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080921', 'UJIAN-001', '080921', '080921', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080922', 'UJIAN-001', '080922', '080922', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080923', 'UJIAN-001', '080923', '080923', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080924', 'UJIAN-001', '080924', '080924', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080925', 'UJIAN-001', '080925', '080925', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080926', 'UJIAN-001', '080926', '080926', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080927', 'UJIAN-001', '080927', '080927', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080928', 'UJIAN-001', '080928', '080928', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080929', 'UJIAN-001', '080929', '080929', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080930', 'UJIAN-001', '080930', '080930', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080931', 'UJIAN-001', '080931', '080931', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080932', 'UJIAN-001', '080932', '080932', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080933', 'UJIAN-001', '080933', '080933', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080934', 'UJIAN-001', '080934', '080934', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080935', 'UJIAN-001', '080935', '080935', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080936', 'UJIAN-001', '080936', '080936', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080937', 'UJIAN-001', '080937', '080937', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080939', 'UJIAN-001', '080939', '080939', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080942', 'UJIAN-001', '080942', '080942', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080943', 'UJIAN-001', '080943', '080943', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080944', 'UJIAN-001', '080944', '080944', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080945', 'UJIAN-001', '080945', '080945', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080946', 'UJIAN-001', '080946', '080946', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080947', 'UJIAN-001', '080947', '080947', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080948', 'UJIAN-001', '080948', '080948', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080949', 'UJIAN-001', '080949', '080949', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080950', 'UJIAN-001', '080950', '080950', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080951', 'UJIAN-001', '080951', '080951', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080952', 'UJIAN-001', '080952', '080952', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080954', 'UJIAN-001', '080954', '080954', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080956', 'UJIAN-001', '080956', '080956', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080957', 'UJIAN-001', '080957', '080957', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080958', 'UJIAN-001', '080958', '080958', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080959', 'UJIAN-001', '080959', '080959', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080960', 'UJIAN-001', '080960', '080960', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080961', 'UJIAN-001', '080961', '080961', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080962', 'UJIAN-001', '080962', '080962', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080963', 'UJIAN-001', '080963', '080963', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080964', 'UJIAN-001', '080964', '080964', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080965', 'UJIAN-001', '080965', '080965', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080966', 'UJIAN-001', '080966', '080966', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080967', 'UJIAN-001', '080967', '080967', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080968', 'UJIAN-001', '080968', '080968', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080969', 'UJIAN-001', '080969', '080969', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080970', 'UJIAN-001', '080970', '080970', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080971', 'UJIAN-001', '080971', '080971', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080972', 'UJIAN-001', '080972', '080972', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080973', 'UJIAN-001', '080973', '080973', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080974', 'UJIAN-001', '080974', '080974', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080975', 'UJIAN-001', '080975', '080975', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080976', 'UJIAN-001', '080976', '080976', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080978', 'UJIAN-001', '080978', '080978', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080979', 'UJIAN-001', '080979', '080979', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080980', 'UJIAN-001', '080980', '080980', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080981', 'UJIAN-001', '080981', '080981', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080982', 'UJIAN-001', '080982', '080982', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080983', 'UJIAN-001', '080983', '080983', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080985', 'UJIAN-001', '080985', '080985', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080986', 'UJIAN-001', '080986', '080986', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080987', 'UJIAN-001', '080987', '080987', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080989', 'UJIAN-001', '080989', '080989', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080990', 'UJIAN-001', '080990', '080990', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080991', 'UJIAN-001', '080991', '080991', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080992', 'UJIAN-001', '080992', '080992', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080993', 'UJIAN-001', '080993', '080993', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080994', 'UJIAN-001', '080994', '080994', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080995', 'UJIAN-001', '080995', '080995', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080997', 'UJIAN-001', '080997', '080997', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080998', 'UJIAN-001', '080998', '080998', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-080999', 'UJIAN-001', '080999', '080999', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-081000', 'UJIAN-001', '081000', '081000', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-081001', 'UJIAN-001', '081001', '081001', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-081002', 'UJIAN-001', '081002', '081002', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-081003', 'UJIAN-001', '081003', '081003', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-081004', 'UJIAN-001', '081004', '081004', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-081005', 'UJIAN-001', '081005', '081005', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-081006', 'UJIAN-001', '081006', '081006', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-081007', 'UJIAN-001', '081007', '081007', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-081008', 'UJIAN-001', '081008', '081008', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-081011', 'UJIAN-001', '081011', '081011', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-081012', 'UJIAN-001', '081012', '081012', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-081013', 'UJIAN-001', '081013', '081013', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-081014', 'UJIAN-001', '081014', '081014', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-081015', 'UJIAN-001', '081015', '081015', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-081016', 'UJIAN-001', '081016', '081016', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-081017', 'UJIAN-001', '081017', '081017', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-081018', 'UJIAN-001', '081018', '081018', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-081019', 'UJIAN-001', '081019', '081019', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-081020', 'UJIAN-001', '081020', '081020', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-081021', 'UJIAN-001', '081021', '081021', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-081022', 'UJIAN-001', '081022', '081022', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-081023', 'UJIAN-001', '081023', '081023', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-081024', 'UJIAN-001', '081024', '081024', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-081026', 'UJIAN-001', '081026', '081026', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-081027', 'UJIAN-001', '081027', '081027', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-081028', 'UJIAN-001', '081028', '081028', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-081029', 'UJIAN-001', '081029', '081029', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-081030', 'UJIAN-001', '081030', '081030', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-081031', 'UJIAN-001', '081031', '081031', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-081033', 'UJIAN-001', '081033', '081033', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-081034', 'UJIAN-001', '081034', '081034', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-081035', 'UJIAN-001', '081035', '081035', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-081036', 'UJIAN-001', '081036', '081036', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-081037', 'UJIAN-001', '081037', '081037', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-081038', 'UJIAN-001', '081038', '081038', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-081039', 'UJIAN-001', '081039', '081039', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-081040', 'UJIAN-001', '081040', '081040', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-081041', 'UJIAN-001', '081041', '081041', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-081042', 'UJIAN-001', '081042', '081042', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-081043', 'UJIAN-001', '081043', '081043', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-081044', 'UJIAN-001', '081044', '081044', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-081045', 'UJIAN-001', '081045', '081045', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-081046', 'UJIAN-001', '081046', '081046', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-081047', 'UJIAN-001', '081047', '081047', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-081048', 'UJIAN-001', '081048', '081048', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-081049', 'UJIAN-001', '081049', '081049', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-081050', 'UJIAN-001', '081050', '081050', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-081051', 'UJIAN-001', '081051', '081051', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19'),
('PUJ-081052', 'UJIAN-001', '081052', '081052', 'Hadir', NULL, '2025-07-07 23:43:19', '2025-07-07 23:43:19');

-- --------------------------------------------------------

--
-- Struktur dari tabel `rapor`
--

CREATE TABLE `rapor` (
  `NIS` varchar(20) NOT NULL,
  `Nama` varchar(255) NOT NULL,
  `nama_kelas` varchar(10) NOT NULL,
  `semester` enum('1','2') NOT NULL,
  `tahun_ajaran` varchar(9) NOT NULL,
  `jumlah_sakit` int(11) DEFAULT 0,
  `jumlah_izin` int(11) DEFAULT 0,
  `jumlah_alpa` int(11) DEFAULT 0,
  `catatan_wali_kelas` text DEFAULT NULL,
  `tanggal_rapor` date DEFAULT NULL,
  `status_naik` enum('Naik','Tidak Naik','Tinggal Kelas') DEFAULT 'Naik',
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `rapor`
--

INSERT INTO `rapor` (`NIS`, `Nama`, `nama_kelas`, `semester`, `tahun_ajaran`, `jumlah_sakit`, `jumlah_izin`, `jumlah_alpa`, `catatan_wali_kelas`, `tanggal_rapor`, `status_naik`, `created_at`, `updated_at`) VALUES
('080873', 'DESAK MADE AYUMAS PRAMESWARI', '6B', '2', '2025', 2, 1, 0, 'Prestasi sangat baik dengan nilai memuaskan. Konsisten dalam belajar dan aktif di kelas.', '2019-12-20', 'Naik', '2025-07-06 09:15:21', '2025-07-08 15:30:14'),
('080874', 'FILIO NATHAN ANINDITO', '6B', '2', '2025', 3, 2, 0, 'Siswa dengan prestasi baik, khususnya dalam matematika. Perlu peningkatan di beberapa mata pelajaran.', '2019-12-20', 'Naik', '2025-07-06 09:15:21', '2025-07-08 04:01:45'),
('080875', 'GHANIE ATHARIZ MUHAMMAD', '6B', '2', '2025', 4, 2, 1, 'Prestasi cukup baik dengan nilai yang stabil. Perlu motivasi lebih untuk meningkatkan hasil belajar.', '2019-12-20', 'Naik', '2025-07-06 09:15:21', '2025-07-08 20:34:16'),
('080876', 'GEODWIPA AHMAD RAFANDHYRA', '6B', '2', '2025', 5, 3, 1, 'Siswa dengan kemampuan baik, namun perlu lebih fokus dan disiplin dalam belajar.', '2019-12-20', 'Naik', '2025-07-06 09:15:21', '2025-07-08 04:01:45'),
('080877', 'KAFEELINDRA ATHAR ABBASY', '6B', '2', '2025', 6, 4, 1, 'Perlu peningkatan dalam beberapa mata pelajaran, terutama olahraga. Semangat belajar perlu ditingkatkan.', '2019-12-20', 'Naik', '2025-07-06 09:15:21', '2025-07-08 04:01:45'),
('080878', 'KHAIRA ASSYABIYA RAFILAH', '6B', '2', '2025', 7, 5, 2, 'Prestasi cukup dengan beberapa mata pelajaran yang perlu perhatian khusus. Tingkatkan kehadiran.', '2019-12-20', 'Naik', '2025-07-06 09:15:21', '2025-07-08 04:01:45'),
('080879', 'KINARA ABILA ZAHRA', '6B', '2', '2025', 8, 6, 2, 'Perlu bimbingan lebih intensif dan motivasi untuk meningkatkan prestasi akademik secara keseluruhan.', '2019-12-20', 'Naik', '2025-07-06 09:15:21', '2025-07-08 04:01:45'),
('080880', 'KIRANA AISYAH SYARIF', '6B', '2', '2025', 9, 7, 2, 'Kemampuan cukup namun perlu usaha lebih keras. Tingkatkan kehadiran dan partisipasi di kelas.', '2019-12-20', 'Naik', '2025-07-06 09:15:21', '2025-07-08 04:01:45'),
('080881', 'KSATRIA AKMAL ZAIN ADINUGROHO', '6B', '2', '2025', 10, 8, 2, 'Perlu bimbingan intensif dan motivasi kuat untuk meningkatkan prestasi. Tingkatkan kedisiplinan belajar.', '2019-12-20', 'Naik', '2025-07-06 09:15:21', '2025-07-08 04:01:45');

-- --------------------------------------------------------

--
-- Struktur dari tabel `ruangan`
--

CREATE TABLE `ruangan` (
  `id_ruang` varchar(20) NOT NULL,
  `nama_ruang` varchar(100) NOT NULL,
  `jenis_ruang` enum('Kelas','Lab Komputer','Lab Bahasa','Perpustakaan','Aula','Ruang Guru','Lainnya') DEFAULT 'Kelas',
  `kapasitas` int(11) NOT NULL,
  `lantai` int(11) DEFAULT 1,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `ruangan`
--

INSERT INTO `ruangan` (`id_ruang`, `nama_ruang`, `jenis_ruang`, `kapasitas`, `lantai`, `created_at`, `updated_at`) VALUES
('RG001', 'Ruang Kelas 1A', 'Kelas', 35, 1, '2025-06-22 10:33:03', '2025-06-22 10:33:03'),
('RG002', 'Ruang Kelas 1B', 'Kelas', 35, 1, '2025-06-22 10:33:03', '2025-06-22 10:33:03'),
('RG003', 'Ruang Kelas 2A', 'Kelas', 35, 1, '2025-06-22 10:33:03', '2025-06-22 10:33:03'),
('RG004', 'Ruang Kelas 2B', 'Kelas', 35, 1, '2025-06-22 10:33:03', '2025-06-22 10:33:03'),
('RG005', 'Ruang Kelas 3A', 'Kelas', 35, 2, '2025-06-22 10:33:03', '2025-06-22 10:33:03'),
('RG006', 'Ruang Kelas 3B', 'Kelas', 35, 2, '2025-06-22 10:33:03', '2025-06-22 10:33:03'),
('RG007', 'Ruang Kelas 4A', 'Kelas', 35, 2, '2025-06-22 10:33:03', '2025-06-22 10:33:03'),
('RG008', 'Ruang Kelas 4B', 'Kelas', 35, 2, '2025-06-22 10:33:03', '2025-06-22 10:33:03'),
('RG009', 'Ruang Kelas 5A', 'Kelas', 35, 3, '2025-06-22 10:33:03', '2025-06-22 10:33:03'),
('RG010', 'Ruang Kelas 5B', 'Kelas', 35, 3, '2025-06-22 10:33:03', '2025-06-22 10:33:03'),
('RG011', 'Ruang Kelas 6A', 'Kelas', 35, 3, '2025-06-22 10:33:03', '2025-06-22 10:33:03'),
('RG012', 'Ruang Kelas 6B', 'Kelas', 35, 3, '2025-06-22 10:33:03', '2025-06-22 10:33:03'),
('RG013', 'Lab Komputer', 'Lab Komputer', 30, 2, '2025-06-22 10:33:03', '2025-06-22 10:33:03'),
('RG014', 'Lab Bahasa', 'Lab Bahasa', 25, 2, '2025-06-22 10:33:03', '2025-06-22 10:33:03'),
('RG015', 'Perpustakaan', 'Perpustakaan', 50, 1, '2025-06-22 10:33:03', '2025-06-22 10:33:03'),
('RG016', 'Aula Sekolah', 'Aula', 200, 1, '2025-06-22 10:33:03', '2025-06-22 10:33:03');

-- --------------------------------------------------------

--
-- Struktur dari tabel `siswa`
--

CREATE TABLE `siswa` (
  `nama` varchar(255) NOT NULL,
  `NIS` varchar(255) NOT NULL,
  `JK` varchar(255) NOT NULL,
  `NISN` varchar(255) NOT NULL,
  `tempatLahir` varchar(255) NOT NULL,
  `tanggalLahir` date NOT NULL,
  `agama` varchar(255) NOT NULL,
  `alamat` varchar(1000) NOT NULL,
  `noTelp` varchar(255) NOT NULL,
  `tahunMasuk` int(11) NOT NULL,
  `status` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `siswa`
--

INSERT INTO `siswa` (`nama`, `NIS`, `JK`, `NISN`, `tempatLahir`, `tanggalLahir`, `agama`, `alamat`, `noTelp`, `tahunMasuk`, `status`) VALUES
('AHZA GIO DEYOSA', '080871', 'Laki-Laki', '0135734536', 'Jakarta', '2013-07-25', 'Islam', 'KOMP. PURI IDAMAN BLOK I NO. 4, BINTARA JAYA', '', 2019, 'Aktif'),
('DESAK MADE AYUMAS PRAMESWARI', '080873', 'Perempuan', '3137548628', 'JAKARTA', '2013-07-09', 'Hindu', 'JL. NUSA INDAH II GG.5 NO. 173 ,Malaka Jaya, Duren Sawit, JAKARTA TIMUR', '', 2019, 'Aktif'),
('FILIO NATHAN ANINDITO', '080874', 'Laki-Laki', '3121929212', 'JAKARTA', '2012-07-31', 'Islam', 'KAV DKI BLOK D5 NO.14/15 Rt. 004/009, Malaka Sari, Duren Sawit', '', 2019, 'Aktif'),
('GHANIE ATHARIZ MUHAMMAD', '080875', 'Laki-Laki', '3138618862', 'JAKARTA', '2013-05-02', 'Islam', 'PONDOK KOPI BLOK AC 5/1, Pondok Kopi, Duren Sawit', '', 2019, 'Aktif'),
('GEODWIPA AHMAD RAFANDHYRA', '080876', 'Laki-Laki', '3122876972', 'JAKARTA', '2012-11-12', 'Islam', 'JL. MALAKA HIJAU VI NO. 7 Rt. 004/010, Pondok Kopi, Duren Sawit, JAKARTA TIMUR', '', 2019, 'Aktif'),
('KAFEELINDRA ATHAR ABBASY', '080877', 'Laki-Laki', '3126754138', 'JAKARTA', '2012-11-10', 'Islam', 'JL. H. RAMLI NO. 18 RT 010 / 015 MENTENG DALAM, Tebet, JAKARTA SELATAN', '', 2019, 'Aktif'),
('KHAIRA ASSYABIYA RAFILAH', '080878', 'Perempuan', '3128760403', 'JAKARTA', '2012-12-24', 'Islam', 'KAV DKI BLOK J.12/26,Rt. 005/009, Pondok Kelapa, DUren Sawit, JAKARTA TIMUR', '', 2019, 'Aktif'),
('KINARA ABILA ZAHRA', '080879', 'Perempuan', '3124460137', 'BEKASI', '2012-12-12', 'Islam', 'GRAND PRIMA BINTARA BLOK A/36, Rt. 009/016, Bintara', '', 2019, 'Aktif'),
('KIRANA AISYAH SYARIF', '080880', 'Perempuan', '3125455362', 'JAKARTA', '2012-11-19', 'Islam', 'JL. BALAI RAKYAT NO. 31 Rt. 10/9, Klender, Duren Sawit, JAKARTA TIMUR ', '', 2019, 'Aktif'),
('KSATRIA AKMAL ZAIN ADINUGROHO', '080881', 'Laki-Laki', '3132852109', 'BEKASI', '2013-01-07', 'Islam', 'PONDOK KELAPA SELATAN NO. 34', '', 2019, 'Aktif'),
('MUHAMMAD KINAN ARKAN SENO', '080882', 'Laki-Laki', '3120977797', 'BEKASI', '2012-07-23', 'Islam', 'JL. SABUT I B BLOK E.6 NO.9 Rt. 9/11, KAV DKI, Pdk Kelapa, Duren Sawit, JAKARTA TIMUR', '', 2019, 'Aktif'),
('MUHAMAD ZADDE ALJABBAR', '080883', 'Laki-Laki', '3122754515', 'JAKARTA', '2012-11-24', 'Islam', 'JL. PONDOK KELAPA SELATAN NO. 14, Rt. 005/005', '', 2019, 'Aktif'),
('RAKHA DANISH MAHARDHIKA', '080884', 'Laki-Laki', '3138846227', 'Jakarta', '2013-05-18', 'Islam', 'KAV DKI BLOK B.7/9 RT. 008/009, Malaka Sari. Duren Sawit, JAKARTA TIMUR', '', 2019, 'Aktif'),
('SULTAN DANENDRA ALKALIFI', '080885', 'Laki-Laki', '3135882035', 'Jakarta', '2013-08-17', 'Islam', 'JL. LIDI BLOK A.18 NO.63 KAV DKI RT 004 / 004 PONDOK KELAPA', '', 2019, 'Aktif'),
('ZIANKA CALLYSTA ZORA', '080886', 'Perempuan', '0133351469', 'Jakarta', '2013-06-14', 'Islam', 'JL. DELIMA IV/7/116 Rt. 017/003, Malaka Sari, DUren Sawit, JAKARTA TIMUR ', '', 2019, 'Aktif'),
('ZIGGY RIFQI AZZANA', '080887', 'Laki-Laki', '3132414434', 'Jakarta', '2012-11-30', 'Islam', 'JL. PANGKALAN JATI V NO. 30 Rt. 002/005, Cipinang Melayu, Makasar, JAKARTA TIMUR', '', 2019, 'Aktif'),
('CALISTA MEILANI REJILENA', '080889', 'Perempuan', '3136732414', 'JAKARTA', '2013-05-02', 'Kristen', 'Jl. Swadaya I No. 8 Rt. 005/001, Duren Sawit', '', 2019, 'Aktif'),
('DIANDA CALLIA QUINNARA SISWARDHANI', '080890', 'Perempuan', '3135642223', 'BEKASI', '2013-04-24', 'Islam', 'KOMP. BINTARA III BLOK B/11 RT 002 / 007', '', 2019, 'Aktif'),
('DIVYA QUINZAFRINA', '080891', 'Perempuan', '0136547743', 'JAKARTA', '2013-03-04', 'Islam', 'JL. PENDIDIKAN V BLOK H / 108 RT 004 / 014, duren sawit', '', 2019, 'Aktif'),
('HASNA AQILA FIRMANSYAH', '080892', 'Perempuan', '3139162702', 'BANDUNG', '2013-03-23', 'Islam', 'KOMP. BINTARA JAYA PERMAI C2 27 RT. 009/011, BINTARA jAYA', '', 2019, 'Aktif'),
('NADIA ASFA MASAYU', '080895', 'Perempuan', '3124327140', 'JAKARTA', '2012-06-17', 'Islam', 'JATINEGARA INDAH CA.1 NO.3 RT 007 / 009 ', '', 2019, 'Aktif'),
('RAIHAN ZAKI IKHWANI', '080898', 'Laki-Laki', '3123459714', 'BEKASI', '2012-02-10', 'Islam', 'JL. BINTARA IV NO. 112 RT 001 / 015', '', 2019, 'Aktif'),
('RAISYA ADHELIA SHAKILA', '080899', 'Perempuan', '0126252719', 'Jakarta', '2012-10-25', 'Islam', 'GRIYA BINTARA INDAH BLOK FF 2 NO.18 RT 008 / 012', '', 2019, 'Aktif'),
('REYHANA FAEZYA AULIA', '080900', 'Perempuan', '0139291684', 'Jakarta', '2013-04-24', 'Islam', 'KOMP. PERUMAHAN KARYAWAN DKI BLOK B.10 NO.6', '', 2019, 'Aktif'),
('ZHAFIRA NUFAH AZZAHRA', '080902', 'Perempuan', '3134030871', 'Jakarta', '2013-09-14', 'Islam', 'JL. KERAJINAN I/10, KEAGUNGAN, TAMAN SARI RT 006 / 009', '', 2019, 'Aktif'),
('ZIDANE ALVARO LESMANA', '080903', 'Laki-Laki', '0121345360', 'Jakarta', '2012-10-30', 'Islam', 'JL. H. MEAN NO , 93 C RT 009 / 007, Jatiwaringin, Pdk Gede', '', 2019, 'Aktif'),
('Fiona Amelia Putri', '080914', 'Perempuan', '3137360984', 'Sleman', '2013-02-12', 'Islam', 'KAVLING MARINIR BLOK AB.9 NO.11, Rt. 008/013, Pondok Kelapa, Duren Sawit, JAKARTA TIMUR', '', 2019, 'Aktif'),
('Abdul Jibran Rettob', '080916', 'Laki-Laki', '3136593415', 'Jakarta', '2013-08-07', 'Islam', 'Komplek Pemda DKI Blok G 8 No. 7', '', 2020, 'Aktif'),
('AINA CETTA HIDAYATI', '080918', 'Perempuan', '0147422288', 'BEKASI', '2014-05-20', 'Islam', 'Komplek Bintara 3 Blok C/21', '', 2020, 'Aktif'),
('ALISHA ANINDITA NOEGROHO', '080919', 'Perempuan', '0131925016', 'JAKARTA', '2013-10-25', 'Islam', 'JL. MURAI II/E5', '', 2020, 'Aktif'),
('ALVINO AUFAA SUSANTIO', '080920', 'Laki-Laki', '0146427252', 'JAKARTA', '2014-03-24', 'Islam', 'JL. CIMERAK SELATAN NO.22', '', 2020, 'Aktif'),
('AMOKSHA RAKAI TATTWA', '080921', 'Laki-Laki', '0131826353', 'JAKARTA', '2013-11-15', 'Islam', 'Komplek DKI Blok B7 No. 20', '', 2020, 'Aktif'),
('BARA DIMITRI FIRMAN SIREGAR', '080922', 'Laki-Laki', '0131719253', 'JAKARTA', '2013-10-17', 'Islam', 'Pulomas Barat 5C No 2', '', 2020, 'Aktif'),
('Carellio Nalendra', '080923', 'Laki-Laki', '0125204530', 'Tasikmalaya', '2012-10-23', 'Islam', 'Jl.Bumi Mas Raya No.16 Ruko 3 Lt', '', 2020, 'Aktif'),
('DANENDRO TASHI PUTRA ARDANA', '080924', 'Laki-Laki', '0146927572', 'BEKASI', '2014-01-04', 'Kristen', 'Pondok Kopi Blok AB 6/15', '', 2020, 'Aktif'),
('Dianda Cathya Qailula Siswardhani', '080925', 'Perempuan', '3149433883', 'Jakarta', '2014-05-15', 'Islam', 'Komplek Bintara 3 Blok B 11', '', 2020, 'Aktif'),
('ELLIAN AZZAM WINATRA', '080926', 'Laki-Laki', '0133250868', 'MALANG', '2013-07-05', 'Islam', 'PONDOK KELAPA GARDENIA NO A6', '', 2020, 'Aktif'),
('KELLY AUSTIN', '080927', 'Laki-Laki', '0133924867', 'BEKASI', '2013-09-24', 'Islam', 'Jl. Utan Kayu No. 57C', '', 2020, 'Aktif'),
('Fadhil Aurellio Firmansyah', '080928', 'Laki-Laki', '3130929728', 'Jakarta', '2013-09-13', 'Islam', 'Jl. Pondok Kelapa IX Blok D.7/14, Pondok Kelapa', '', 2020, 'Aktif'),
('KUINCIYA TASMIRA MURTIWIBAWA', '080929', 'Perempuan', '0148672756', 'JAKARTA', '2014-01-08', 'Islam', 'MELATI NO 39 B, Duren Sawit', '', 2020, 'Aktif'),
('Mikaila Putri Faradina Danastri', '080930', 'Perempuan', '0131479747', 'Tangerang', '2013-10-29', 'Islam', 'Bukit Cimanggu city blok U7 NO.5', '', 2020, 'Aktif'),
('MUHAMMAD ALTHAF HUSAIN FIRMANSYAH', '080931', 'Laki-Laki', '0145714426', 'JAKARTA', '2014-01-01', 'Islam', 'KOMPLEK DKI BLOK B7 NO. 12', '', 2020, 'Aktif'),
('MUHAMMAD IBRAHIM ASYAKI DZAKWAN', '080932', 'Laki-Laki', '0133082099', 'JAKARTA', '2013-10-15', 'Islam', 'Komplek Puri Idaman Blok F No. 2', '', 2020, 'Aktif'),
('Muhammad Zhafir Dewandra', '080933', 'Laki-Laki', '0137843630', 'Jakarta', '2013-10-20', 'Islam', 'Jl. Taman Malaka Selatan 3 Blok B 7 No 4', '', 2020, 'Aktif'),
('Narendra Devan Prabaswara', '080934', 'Laki-Laki', '0147696545', 'Bekasi', '2014-04-03', 'Islam', 'Perum. Buaran Regency. Blok A/37', '', 2020, 'Aktif'),
('QIREEN WINATA RAMADHANI', '080935', 'Perempuan', '0135262615', 'JAKARTA', '2014-07-26', 'Islam', 'BOJONG INDAH, Pondok Kelapa', '', 2020, 'Aktif'),
('VIYANA ZAKIYA ROZAN', '080936', 'Perempuan', '0135888865', 'JAKARTA', '2013-09-21', 'Islam', 'PERUM ANILOPE, Jl. Transformator Raya A 35/72', '', 2020, 'Aktif'),
('ZAHRIANSYAH MURAD', '080937', 'Laki-Laki', '0134344142', 'JAKARTA', '2013-02-14', 'Islam', 'Komplek DKI Blok B7 No 25', '', 2020, 'Aktif'),
('Zidni Rastafari Kusuma', '080939', 'Laki-Laki', '3132085941', 'Jakarta', '2013-11-01', 'Islam', 'Komp. Kimia Farma II, Jl. Rifadin Blok AG 10 No.3', '', 2020, 'Aktif'),
('ALIKA BELLECIA MUCHELLY', '080942', 'Perempuan', '0148057856', 'JAKARTA', '2014-11-09', 'Islam', 'KAVLING MARINIR BLOK AB5 N0 33', '', 2021, 'Aktif'),
('ALYSSA KHALUNA PUTRI', '080943', 'Perempuan', '0149566344', 'JAKARTA', '2014-06-02', 'Islam', 'JLN. MAYANG 6 A ', '', 2021, 'Aktif'),
('Anjani Arsaquila Firdausy', '080944', 'Perempuan', '3140540228', 'Jakarta', '2014-06-14', 'Islam', 'Jl. Melati I No. 41 Rt.004/002', '', 2021, 'Aktif'),
('Arsya Zefanya Turnip', '080945', 'Perempuan', '0151551484', 'Jakarta', '2015-01-25', 'Islam', 'Jl Kp Jembatan no 83', '', 2021, 'Aktif'),
('DANISHA NAURA ADRIYANSYAH', '080946', 'Perempuan', '0156384261', 'JAKARTA', '2015-01-18', 'Islam', 'KOMP. BINTARA II BLOK B/59', '', 2021, 'Aktif'),
('DYSON GIBRANOV HARTAWAN', '080947', 'Laki-Laki', '0147529152', 'BANYUMAS', '2014-11-14', 'Islam', 'JL. PONDOK KELAPA KAV DKI BLOK S1 NO. 23', '', 2021, 'Aktif'),
('EVAN DEWANGGA PUTRA WICAKSENA', '080948', 'Laki-Laki', '3150498154', 'JAKARTA', '2015-04-25', 'Islam', 'The Green Bintara No. 12 A, Bintara', '', 2021, 'Aktif'),
('FAIQAH KHANSA PUTRI NIAR', '080949', 'Perempuan', '0149881214', 'JAKARTA', '2014-09-04', 'Islam', 'JL SWAKARSA', '', 2021, 'Aktif'),
('FIRZA AL FARIZI ATHALLAH', '080950', 'Laki-Laki', '0146338994', 'JAKARTA', '2014-09-19', 'Islam', 'KOMP. TUGU PERMAI B6 NO. 23', '', 2021, 'Aktif'),
('INARA SHAKILA', '080951', 'Perempuan', '0131820871', 'JAKARTA', '2015-11-23', 'Islam', 'JL. TAMAN MALAKA UTARA BLOK A3 NO. 1A', '', 2021, 'Aktif'),
('KEANU ARSYAD SUTAKUSUMAH', '080952', 'Laki-Laki', '3151935694', 'BEKASI', '2015-01-24', 'Islam', 'PONDOK KELAPA 5B BLOK C4 NO.10A', '', 2021, 'Aktif'),
('KHANZA SALSABILLA AKBARSYAH', '080954', 'Perempuan', '0144360326', 'JAKARTA', '2014-09-10', 'Islam', 'JL. PONDOK BAMBU ASRI SELATAN I NO. 19', '', 2021, 'Aktif'),
('MATTHEW BENEDICTUS PANJAITAN', '080956', 'Laki-Laki', '0156599430', 'JAKARTA', '2015-04-19', 'Kristen', 'KOMPLEK DKI BLOK P3/16', '', 2021, 'Aktif'),
('Muhammad Reynan Deyosa', '080957', 'Laki-Laki', '3151477225', 'Jakarta', '2015-03-29', 'Islam', 'Komplek Puri Idaman Blok I No. 4', '', 2021, 'Aktif'),
('Muhammad Rizky Ferdiansyah Nugroho', '080958', 'Laki-Laki', '3151388127', 'Jakarta', '2015-03-02', 'Islam', 'Perumkar DKI Blok D1/19 Rt.001/002', '', 2021, 'Aktif'),
('NATHANAEL ALDO ANDHANU', '080959', 'Laki-Laki', '0156897544', 'JAKARTA', '2015-02-28', 'Kristen', 'JL. TAMAN MALAKA SELATAN BLOK A2/50', '', 2021, 'Aktif'),
('NOER SYIFA FEBRIYANTI', '080960', 'Perempuan', '0151745577', 'JAKARTA', '2015-02-27', 'Islam', 'JL. SWAKARSA IV', '', 2021, 'Aktif'),
('RADDIN PANDYANARYA ARKHAREGA', '080961', 'Laki-Laki', '0149868431', 'JAKARTA', '2014-09-19', 'Islam', 'KOMPLEK BINTARA JAYA PERMAI BLOK A NO. 110', '', 2021, 'Aktif'),
('RADITYA HARITH WIBISONO', '080962', 'Laki-Laki', '0137158659', 'JAKARTA', '2013-12-04', 'Islam', 'Tapas Blok D 3 / 1', '', 2021, 'Aktif'),
('RAIHANA NADHIFA WIJANARKO', '080963', 'Perempuan', '0146854528', 'JAKARTA', '2014-11-11', 'Islam', 'PALM MANSION NO. 6, JL. SWAKARSA III', '', 2021, 'Aktif'),
('RAMZAN RABBANI SANGAJI', '080964', 'Laki-Laki', '0158816204', 'JAKARTA', '2015-05-14', 'Islam', 'JL. MADRASAH II NO. 13/14', '', 2021, 'Aktif'),
('Shafeea Aqila Pratama', '080965', 'Perempuan', '0142941746', 'Jakarta', '2014-03-09', 'Islam', 'Billy Moon Blok AL 1. No. 1', '', 2021, 'Aktif'),
('Sophia Kamila Firmansyah', '080966', 'Perempuan', '0154214032', 'Bandung', '2015-04-22', 'Islam', 'Bjb Blok C No. 227', '', 2021, 'Aktif'),
('VENESSA BELLVANIA SALAM', '080967', 'Perempuan', '3133445179', 'JAKARTA', '2013-08-26', 'Kristen', 'PERUM DKI BLOK R2 NO.8 Rt. 015/002, Pondok Kelapa, Duren Sawit, KELAPA JAKARTA TIMUR', '', 2019, 'Aktif'),
('MAYLIKA PUTERI', '080968', 'Perempuan', '0133966296', 'JAKARTA', '2013-05-10', 'Islam', 'PERUMKAR PEMDA DKI BLOK S 4 / 26 DUREN SAWIT PONDOK KELAPA JAKARTA TIMUR', '', 2019, 'Aktif'),
('RYUU SATRIA HIRAI ', '080969', 'Laki-Laki', '0135657735', 'Jakarta', '2013-08-28', 'Islam', 'KOMP DKI BLOK M 1 / 4 RT 011 / 002 PONDOK KELAPA ', '', 2019, 'Aktif'),
('ALDRIC HELGA ARDANA', '080970', 'Laki-Laki', '3163525571', 'Bekasi', '2016-01-23', 'Islam', 'Jl. Kebon Baru No. 38', '', 2022, 'Aktif'),
('ALRAIQ RUMI TATTWA', '080971', 'Laki-Laki', '3156066761', 'Jakarta', '2015-01-15', 'Islam', 'Komplek DKI Blok B7/20', '', 2022, 'Aktif'),
('ALVINO DWI SYAHPUTRA KURNIADI', '080972', 'Laki-Laki', '3152386300', 'Jakarta', '2015-07-18', 'Islam', 'Komplek Pemda DKI Blok P.5 No. 11', '', 2022, 'Aktif'),
('ANSEL ARFIN JORDAN', '080973', 'Laki-Laki', '3154327777', 'Bekasi', '2015-11-20', 'Islam', 'Jl. Setia IV No. 20', '', 2022, 'Aktif'),
('DANISH RAFIF ANAYA BRILIANTO', '080974', 'Laki-Laki', '3153777461', 'Bekasi', '2015-10-28', 'Islam', 'Jl. Swakarsa I No. 1', '', 2022, 'Aktif'),
('DINDAKU ZAKARIA', '080975', 'Perempuan', '0154629023', 'Bekasi', '2015-11-10', 'Islam', 'Pondok Cipta Blok D No. 17', '', 2022, 'Aktif'),
('Fahima Ra Adel', '080976', 'Perempuan', '0157534312', 'Jakarta', '2015-04-23', 'Islam', 'Jl Lembah Pinang', '', 2022, 'Aktif'),
('HALWA QANITA PERMATA BASRI', '080978', 'Perempuan', '3144302144', 'Jakarta', '2014-12-11', 'Islam', 'Komplek DKI Blok B3 No. 26', '', 2022, 'Aktif'),
('HUGO MOAZZAM MALIK NARARYA', '080979', 'Laki-Laki', '0135450929', 'Jakarta', '2015-11-13', 'Islam', 'Lembah Pinang III Blok i 11/6', '', 2022, 'Aktif'),
('KEANU ALDEN MUCHELLY', '080980', 'Laki-Laki', '0136880602', 'Jakarta', '2016-01-23', 'Islam', 'KAV Marinir AB V5, No. 33', '', 2022, 'Aktif'),
('KIANDRA ADELA SHADIQA', '080981', 'Perempuan', '3168717649', 'Bekasi', '2016-05-28', 'Islam', 'Perum BIntara II Blok A/81', '', 2022, 'Aktif'),
('MIKHAIL ALFATH ADIESTA', '080982', 'Laki-Laki', '3152133170', 'BEKASI', '2015-10-21', 'Islam', 'JALAN PANGKALAN JATI', '', 2022, 'Aktif'),
('NARENDRA ERABBANI CAESSA', '080983', 'Laki-Laki', '0152739558', 'Jakarta', '2015-08-15', 'Islam', 'Kav DKI Blok E7/4/5', '', 2022, 'Aktif'),
('RAFFA AL SUDAIS SANGAJI', '080985', 'Laki-Laki', '3164951610', 'Jakarta', '2016-04-02', 'Islam', 'Jl. Madrasah', '', 2022, 'Aktif'),
('RAFFASYA RADIKA SHALIH', '080986', 'Laki-Laki', '3156347181', 'Jakarta', '2015-12-17', 'Islam', 'Jl. Swakarsa', '', 2022, 'Aktif'),
('SAUT MIKHAEL RIKARRO MALAU', '080987', 'Laki-Laki', '3157460400', 'Bekasi', '2015-12-21', 'Islam', 'Jl. Bintara Jaya', '', 2022, 'Aktif'),
('AISHALUNA NADINDRA MUMPUNI', '080989', 'Perempuan', '3165408892', 'Jakarta', '2016-03-23', 'Islam', 'Cluster Gemini Resindence Blok B No. 16', '', 2022, 'Aktif'),
('ANGEL NATHANIA SARAGIH', '080990', 'Perempuan', '3160072254', 'Jakarta', '2016-08-26', 'Kristen', 'KAV Marinir Blok AB 4/10.A', '', 2022, 'Aktif'),
('ARSAKHA KHIAR PRAYOGO', '080991', 'Laki-Laki', '3161146667', 'Bekasi', '2016-03-14', 'Islam', 'Swakarsa I No. 33', '', 2022, 'Aktif'),
('ATHALETA KIRANA ATIRA', '080992', 'Perempuan', '3159564237', 'Bekasi', '2015-03-19', 'Islam', 'Jl. Munggang No. 74', '', 2022, 'Aktif'),
('ERNEST HAZEL ALFARIZQI', '080993', 'Laki-Laki', '3151498261', 'Jakarta', '2015-12-16', 'Islam', 'Balimatraman', '', 2022, 'Aktif'),
('FARREL ATHARIZZ PRATAMA', '080994', 'Laki-Laki', '3160228402', 'Jakarta', '2016-04-21', 'Islam', 'Jl. Bintara 13 No. 45', '', 2022, 'Aktif'),
('JOACHIM GIBRAN ARKANANTA RAJA', '080995', 'Laki-Laki', '3148959837', 'Jakarta', '2014-08-05', 'Katholik', 'Jl. Bening Indah No. 53', '', 2022, 'Aktif'),
('MAHESYA JAVAZ RIANDI', '080997', 'Laki-Laki', '3158664135', 'BEKASI', '2015-11-10', 'Islam', 'KOMPLEK DEPKES I JL.HUSADA II NO.138', '', 2022, 'Aktif'),
('MUHAMMAD NAILUL AUTHAR', '080998', 'Laki-Laki', '3157854706', 'Bekasi', '2015-06-09', 'Islam', 'Jl. Hidayah I', '', 2022, 'Aktif'),
('MUHAMMAD RAMAFAHRI FANSURI', '080999', 'Laki-Laki', '3158075166', 'Jakarta', '2015-09-15', 'Islam', 'Jatinegara Indah CA 1 No. 3', '', 2022, 'Aktif'),
('QUINSA KIREINA ANANDIKA FAYA', '081000', 'Perempuan', '3153144289', 'KUTAI BARAT', '2015-10-21', 'Islam', 'JL.JAMBU NO.15A', '', 2022, 'Aktif'),
('RADINKA MEYKA RYONA', '081001', 'Perempuan', '3167942459', 'Jakarta', '2016-05-23', 'Islam', 'Komplek DKI Blok M.4/19', '', 2022, 'Aktif'),
('SAFARAZ AKMA FADHIL', '081002', 'Laki-Laki', '0159456978', 'Bekasi', '2015-11-28', 'Islam', 'Wirun', '', 2022, 'Aktif'),
('SHREYAS NALENDRA', '081003', 'Laki-Laki', '3148575935', 'Tasikmalaya', '2014-10-14', 'Islam', 'KAV DKI Jl. Lembah Aren I Blok K.9/11', '', 2022, 'Aktif'),
('SUMDATIYAH AHMAD SUBHAN', '081004', 'Perempuan', '0161044729', 'Jakarta', '2016-05-16', 'Islam', 'Jl. Swakarsa IV A No. 76 E, Ikma Residence 2', '', 2022, 'Aktif'),
('TAVISHA ALLURA PUTRI ANGGRIWAN', '081005', 'Perempuan', '0135329074', 'Jakarta', '2016-09-09', 'Islam', 'Jl. Taman Malaka Utara Blok D20 No. 13', '', 2022, 'Aktif'),
('Andhara Putri Denissa', '081006', 'Perempuan', '0143280421', 'Bekasi', '2014-08-01', 'Islam', 'Jl H Ilyas Cikunir Ceger ', '', 2020, 'Aktif'),
('ARKANSHA ZHAFRANO MARUDDANI', '081007', 'Laki-Laki', '0136973658', 'Jakarta', '2013-07-03', 'Islam', 'Perum. Masnaga Bintara Jl. Merapi Raya No.A 500', '', 2020, 'Aktif'),
('KEENAN ABYAN SUSILO', '081008', 'Laki-Laki', '0141849007', 'JAKARTA', '2014-11-22', 'Islam', 'Jl.Swakarsa V No.37, Komp. Bina Marga 2', '', 2021, 'Aktif'),
('TOBIAS AMMAR ALI', '081009', '', '', '', '1900-01-01', '', '', '', 0, ''),
('ABDILAH APOORI AGAM ASEGAF', '081011', 'Laki-Laki', '', 'BEKASI', '2017-01-04', 'Islam', '', '', 2023, 'Aktif'),
('ADEEVA AYU NAIARAPUTRI', '081012', 'Perempuan', '3161158414', 'Jakarta', '2016-11-23', 'Islam', '', '', 2023, 'Aktif'),
('ADLAN DIRGA MAHESA', '081013', 'Laki-Laki', '3168751844', 'BEKASI', '2016-05-16', 'Islam', '', '', 2023, 'Aktif'),
('ALINDYA NDARU ZAYDA TURNIP', '081014', 'Perempuan', '2223774004', 'Jakarta', '2017-06-29', 'Islam', '', '', 2023, 'Aktif'),
('ATHAR ALVARO LESMANA', '081015', 'Laki-Laki', '', 'Jakarta', '2015-09-03', 'Islam', '', '', 2023, 'Aktif'),
('DAREVAN GANESH IRIANDI', '081016', 'Laki-Laki', '3174626708', 'BEKASI', '2017-04-10', 'Islam', '', '', 2023, 'Aktif'),
('DHUHAYU WARANGGANI FERDYANDRA', '081017', '', '', 'Jakarta', '2017-07-21', '', '', '', 2023, 'Aktif'),
('ENZO RASYAD NUGRAHA', '081018', 'Laki-Laki', '3169687887', 'Jakarta', '2016-08-10', 'Islam', '', '', 2023, 'Aktif'),
('GAVRIELL DAMARGALIH PRATOMO', '081019', 'Laki-Laki', '0153667539', 'BEKASI', '2015-03-29', 'Islam', '', '', 2023, 'Aktif'),
('GHINA ADEERA ROCHIM', '081020', 'Perempuan', '', 'Jakarta', '2016-05-06', 'Islam', '', '', 2023, 'Aktif'),
('ILONA AUDIA SHOPI', '081021', 'Perempuan', '', 'BEKASI', '2017-05-05', 'Islam', '', '', 2023, 'Aktif'),
('JAEDEN ARISTO R', '081022', 'Laki-Laki', '3167869759', 'Jakarta', '2017-04-29', 'Islam', '', '', 2023, 'Aktif'),
('JAVIER AKTAM KHAIRULLAH ARUAN', '081023', 'Laki-Laki', '3163049650', 'Jakarta', '2016-10-01', 'Islam', '', '', 2023, 'Aktif'),
('KANYA ZAYNA', '081024', 'Perempuan', '3164264815', 'Jakarta', '2016-10-15', 'Islam', '', '', 2023, 'Aktif'),
('KIAN AZKA SYARIF', '081025', '', '', '', '1900-01-01', '', '', '', 2023, ''),
('LITUHAYU CHESTA PUTRI INDIRA', '081026', '', '3166607530', 'Tangerang', '2016-07-17', '', '', '', 2023, 'Aktif'),
('MISEL JANZABILA MEA PRIANKA', '081027', 'Perempuan', '3166206630', 'Jakarta', '2016-09-09', 'Islam', '', '', 2023, 'Aktif'),
('NAIRA GISTA ANJANI', '081028', 'Perempuan', '', 'Jakarta', '2016-04-30', 'Islam', '', '', 2023, 'Aktif'),
('SAFFANA ARISHA KINARIAN', '081029', 'Perempuan', '', 'Jakarta', '2016-06-08', 'Islam', '', '', 2023, 'Aktif'),
('SHAFFINA FLORENCIA PRADETA', '081030', 'Perempuan', '3170003916', 'Jakarta', '2017-03-12', 'Islam', '', '', 2023, 'Aktif'),
('STEVEN WILLIAM SARAGIH', '081031', 'Laki-Laki', '2223774015', 'Jakarta', '2017-01-07', 'KRISTEN', '', '', 2023, 'Aktif'),
('ALBIANDO GHANI PUTRA', '081032', 'Laki-Laki', '', '', '1900-01-01', '', '', '', 2022, ''),
('Akhatar Naufal Kurniadi', '081033', 'Laki-Laki', '', 'Jakarta', '2017-10-17', 'Islam', 'Komplek Pemda DKI Blok P5 No. 11 RT 021 RW 002 Pondok Kelapa, Duren Sawit, Jakata Timur / 13450', '087870447585', 2024, 'Aktif'),
('Aldeara Syafa Susantio', '081034', 'Perempuan', '', 'Jakarta', '2018-02-08', 'Islam', 'JL. Cimerak Selatan No. 22 RT 006 RW 016 Duren Sawit, Duren Sawit, Jakarta Timur / 13440', '083875001312', 2024, 'Aktif'),
('Aluna Kiandra', '081035', 'Perempuan', '', 'Jakarta', '2017-07-21', 'Islam', 'Komplek DKI Blok O1 No. 20 RT 012 RW 002 Pondok Kelapa, Duren Sawit, Jakarta Timur / 13450', '087876568726', 2024, 'Aktif'),
('Ameera Carissa Adiesta', '081036', 'Perempuan', '', 'Bekasi', '2017-10-12', 'Islam', 'Pangkalan Jati 005/009 Cipinang Melayu, Makasar, Jakarta Timur / 13620', '089624429453', 2024, 'Aktif'),
('Annasya Ayudia Noegroho', '081037', 'Perempuan', '', 'Jakarta', '2017-09-13', 'Islam', 'Jl. Murai II Blok B No. 5 RT 007 RW 005 Jati Padang, Pasar Minggu, Jakarta Selatan / 12540', '082260226645', 2024, 'Aktif'),
('Benzema Raafi Bramasta Kusmayadi', '081038', 'Laki-Laki', '', 'Jakarta', '2017-12-25', 'Islam', 'Kav. PTB DKI Blok J9 No. 27 RT 005 RW 009 Pondok Kelapa Duren Sawit, Jakarta Timur / 13450', '089693203626', 2024, 'Aktif'),
('Caleb El Dastan Yuzanda', '081039', 'Laki-Laki', '', 'Jakarta', '2017-11-07', 'Islam', 'Kav. DKI Blok K8 No. 31 RT 010 RW 009 Pondok Kelapa, Duren Sawit, Jakarta Timur / 13450', '085715222605', 2024, 'Aktif'),
('Dareen Geovino Hartawan', '081040', 'Laki-Laki', '', 'Purwokerto', '2017-05-15', 'Islam', 'Jl. Pondok Kelapa Kav DKI Blok S1 No. 23 RT 017 RW 002 Pondok Kelapa, Duren Sawit, Jakarta Timur / 13450', '081286531988', 2024, 'Aktif'),
('Delicia Geneva Hartawan', '081041', 'Perempuan', '', 'Purwokerto', '2017-05-15', 'Islam', 'Jl. Pondok Kelapa Kav DKI Blok S1 No. 23 RT 017 RW 002 Pondok Kelapa, Duren Sawit, Jakarta Timur / 13450', '081286531988', 2024, 'Aktif'),
('Jason Meshach Alexander', '081042', 'Laki-Laki', '', 'Jakarta', '2018-09-10', 'Kristen', 'Menara Samawa Unit 1220, Jl. Haji Naman No. 54 RT 003 RW 015 Pondok Kelapa, Duren Sawit, Jakata Timur / 13450', '081212095891', 2024, 'Aktif'),
('Jehan Almahryra Sopandi', '081043', 'Perempuan', '', 'Jakarta', '2017-04-01', 'Islam', 'JL. Sawah Balong No. 99B RT 004 RW 006 Srengseng, Kembangan, Jakarta Barat / 11630', '081210104546', 2024, 'Aktif'),
('Keenandra Abhiseva Nugraha ', '081044', 'Laki-Laki', '', 'Jakarta', '2018-04-29', 'Islam', 'Kav. Marinir Blok AC4 No. 31 RT 002 RW 013 Pondok Kelapa, Duren Sawit, Jakarta Timur / 13450', '087878381698', 2024, 'Aktif'),
('Keenan Athallah Lalamentik', '081045', 'Laki-Laki', '', 'Jakarta', '2017-09-17', 'Islam', 'Jl. Haji naman No. 79 RT 002 RW 002 Pondok Kelapa, Duren sawit, Jakarta Timur / 13450', '081399998515', 2024, 'Aktif'),
('Kestara Putra Laksamana', '081046', 'Laki-Laki', '', 'Jakarta', '2017-06-13', 'Islam', 'Jatinegara Indah Blok CA2 No. 10 RT 007 RW 009 Jatinegara, Cakung, Jakarta Timur / 13930', '085647088585', 2024, 'Aktif'),
('Mikhaila Alesha Anindita', '081047', 'Perempuan', '', 'Jakarta', '2017-10-28', 'Islam', 'Kav. DKI Blok D5 No.14/15 RT 004 RW 009 Malaka Sari, Duren Sawit, Jakarta Timur / 13460', '08567877330', 2024, 'Aktif'),
('Miracle Matthew Bugar', '081048', 'Laki-Laki', '', 'Jakarta', '2018-07-26', 'Kristen Protestan', 'Jl. Padat Karya No. 116 RT 002 RW 001 Pondok Kelapa, Duren Sawit, Jakarta Timur / 13450', '081310130289', 2024, 'Aktif'),
('Miserocordias Domini Felicia', '081049', 'Perempuan', '', 'Jakarta', '2018-07-26', 'Kristen Protestan', 'Jl. Padat Karya No. 116 RT 002 RW 001 Pondok Kelapa, Duren Sawit, Jakarta Timur / 13450', '081310130289', 2024, 'Aktif'),
('Raffasya Alkhalifi Wahyuwibowo', '081050', 'Laki-Laki', '', 'Jakarta', '2018-01-06', 'Islam', 'Jl. Selat Sunda IV No. 1 RT 005 RW 017 Duren sawit, Duren Sawit, Jakarta Timur / 13440', '0811860928', 2024, 'Aktif'),
('Sandrine Alanis Medina', '081051', 'Perempuan', '', 'Jakarta', '2017-07-18', 'Islam', 'Jl. Kesatrian IV No. 4 RT 006 RW 003 Kebon Manggis, Matraman, Jakarta Timur / 13150', '085159618879', 2024, 'Aktif'),
('Tabina Arsyila Wibisono', '081052', 'Perempuan', '', 'Tangerang Selatan', '2016-12-28', 'Islam', 'Jl. Tapas Blok D3 No. 1 RT 012 RW 011 Pondok Kelapa, Duren Sawit, Jakarta Timur / 13450', '081288964914', 2024, 'Aktif'),
('BINAR HADYANING GUNAWAN', '081053', 'Perempuan', '', '', '1900-01-01', '', '', '', 2023, ''),
('Restu Hanindina Avantie', '081054', 'Perempuan', '', 'Bekasi', '2017-04-24', 'Islam', 'Pondok Kopi Blok Y9 No. 6 RT 010 RW 006 Pondok Kopi, Duren Sawit, Jakarta Timur / 13460', '089519588543', 2024, 'Non/Aktif');

-- --------------------------------------------------------

--
-- Struktur dari tabel `ujian`
--

CREATE TABLE `ujian` (
  `id_ujian` varchar(20) NOT NULL,
  `nama_ujian` varchar(100) NOT NULL,
  `id_pelajaran` varchar(20) NOT NULL,
  `id_kelas` varchar(20) NOT NULL,
  `id_ruang` varchar(20) NOT NULL,
  `tanggal_ujian` date NOT NULL,
  `waktu_mulai` time NOT NULL,
  `waktu_selesai` time NOT NULL,
  `durasi_menit` int(11) GENERATED ALWAYS AS (timestampdiff(MINUTE,timestamp(`tanggal_ujian`,`waktu_mulai`),timestamp(`tanggal_ujian`,`waktu_selesai`))) STORED,
  `jumlah_peserta` int(11) DEFAULT 0,
  `created_by` varchar(100) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `ujian`
--

INSERT INTO `ujian` (`id_ujian`, `nama_ujian`, `id_pelajaran`, `id_kelas`, `id_ruang`, `tanggal_ujian`, `waktu_mulai`, `waktu_selesai`, `jumlah_peserta`, `created_by`, `created_at`, `updated_at`) VALUES
('UJ001', 'Ulangan Harian Matematika Kelas 1A', 'MP001', 'KLS001', 'RG001', '2024-07-15', '08:00:00', '09:30:00', 28, 'Ibu Sari Wulandari', '2025-06-22 10:33:51', '2025-06-22 10:33:51'),
('UJ002', 'UTS Bahasa Indonesia Kelas 3A', 'BING', 'KLS1A', 'RG005', '2025-07-20', '09:00:00', '11:00:00', 30, 'Bapak Ahmad Hidayat', '2025-06-22 10:33:51', '2025-07-08 15:27:02'),
('UJ003', 'Ulangan Harian TIK Kelas 5B', 'MP020', 'KLS010', 'RG013', '2024-07-18', '10:00:00', '11:30:00', 25, 'Ibu Ranti Sari', '2025-06-22 10:33:51', '2025-06-22 10:33:51');

-- --------------------------------------------------------

--
-- Struktur dari tabel `users`
--

CREATE TABLE `users` (
  `id_access` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `tgl_lulus` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `users`
--

INSERT INTO `users` (`id_access`, `password`, `tgl_lulus`) VALUES
('Admin', 'tiara2005', '2025-06-16'),
('martha90', 'martha90', NULL),
('tiara90', 'tiara90', NULL);

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `kelas`
--
ALTER TABLE `kelas`
  ADD PRIMARY KEY (`nama_kelas`),
  ADD UNIQUE KEY `id_kelas` (`id_kelas`),
  ADD UNIQUE KEY `unique_kelas` (`tingkat`,`nama_kelas`) USING BTREE;

--
-- Indeks untuk tabel `mata_pelajaran`
--
ALTER TABLE `mata_pelajaran`
  ADD PRIMARY KEY (`id_pelajaran`);

--
-- Indeks untuk tabel `nilai`
--
ALTER TABLE `nilai`
  ADD UNIQUE KEY `unique_nilai` (`NIS`,`id_pelajaran`,`id_kelas`,`semester`,`tahun_ajaran`),
  ADD KEY `nilai_ibfk_3` (`id_kelas`),
  ADD KEY `nilai_ibfk_2` (`id_pelajaran`,`nama_pelajaran`) USING BTREE;

--
-- Indeks untuk tabel `peserta_ujian`
--
ALTER TABLE `peserta_ujian`
  ADD PRIMARY KEY (`id_peserta`),
  ADD UNIQUE KEY `unique_peserta` (`id_ujian`,`NIS`),
  ADD KEY `NIS` (`NIS`);

--
-- Indeks untuk tabel `rapor`
--
ALTER TABLE `rapor`
  ADD UNIQUE KEY `unique_rapor` (`NIS`,`semester`,`tahun_ajaran`),
  ADD KEY `rapor_ifbk_3` (`nama_kelas`);

--
-- Indeks untuk tabel `ruangan`
--
ALTER TABLE `ruangan`
  ADD PRIMARY KEY (`id_ruang`);

--
-- Indeks untuk tabel `siswa`
--
ALTER TABLE `siswa`
  ADD PRIMARY KEY (`NIS`);

--
-- Indeks untuk tabel `ujian`
--
ALTER TABLE `ujian`
  ADD PRIMARY KEY (`id_ujian`),
  ADD KEY `id_pelajaran` (`id_pelajaran`),
  ADD KEY `id_kelas` (`id_kelas`),
  ADD KEY `id_ruang` (`id_ruang`);

--
-- Indeks untuk tabel `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id_access`);

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `nilai`
--
ALTER TABLE `nilai`
  ADD CONSTRAINT `nilai_ibfk_1` FOREIGN KEY (`NIS`) REFERENCES `siswa` (`NIS`) ON DELETE CASCADE,
  ADD CONSTRAINT `nilai_ibfk_2` FOREIGN KEY (`id_pelajaran`) REFERENCES `mata_pelajaran` (`id_pelajaran`),
  ADD CONSTRAINT `nilai_ibfk_3` FOREIGN KEY (`id_kelas`) REFERENCES `kelas` (`id_kelas`);

--
-- Ketidakleluasaan untuk tabel `peserta_ujian`
--
ALTER TABLE `peserta_ujian`
  ADD CONSTRAINT `peserta_ujian_ibfk_1` FOREIGN KEY (`id_ujian`) REFERENCES `ujian` (`id_ujian`) ON DELETE CASCADE,
  ADD CONSTRAINT `peserta_ujian_ibfk_2` FOREIGN KEY (`NIS`) REFERENCES `siswa` (`NIS`) ON DELETE CASCADE;

--
-- Ketidakleluasaan untuk tabel `rapor`
--
ALTER TABLE `rapor`
  ADD CONSTRAINT `rapor_ibfk_1` FOREIGN KEY (`NIS`) REFERENCES `siswa` (`NIS`) ON DELETE CASCADE,
  ADD CONSTRAINT `rapor_ifbk_3` FOREIGN KEY (`nama_kelas`) REFERENCES `kelas` (`nama_kelas`) ON DELETE CASCADE;

--
-- Ketidakleluasaan untuk tabel `ujian`
--
ALTER TABLE `ujian`
  ADD CONSTRAINT `ujian_ibfk_1` FOREIGN KEY (`id_pelajaran`) REFERENCES `mata_pelajaran` (`id_pelajaran`),
  ADD CONSTRAINT `ujian_ibfk_2` FOREIGN KEY (`id_kelas`) REFERENCES `kelas` (`id_kelas`),
  ADD CONSTRAINT `ujian_ibfk_3` FOREIGN KEY (`id_ruang`) REFERENCES `ruangan` (`id_ruang`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

# Responsi-1-Mobile_H1D023074
Nama: Ahmad Zaky
NIM: H1D023074
Shift Baru: D
Shift Asal: A

![Image](https://github.com/user-attachments/assets/63082786-4819-4461-8ef9-a0c1534b61ae)

Penjelasan:
Aplikasi ini menggunakan arsitektur yang terstruktur dengan baik untuk mengelola alur data dari API hingga tampilan di layar. Dimulai dari konfigurasi network layer di NetworkModule.kt, aplikasi menggunakan Retrofit sebagai framework utama untuk melakukan HTTP requests ke API Football Data dengan base URL https://api.football-data.org/ dan API token yang telah dikonfigurasi. NetworkModule ini mengatur OkHttpClient dengan timeout 30 detik, logging interceptor untuk debugging, dan GsonConverterFactory untuk konversi otomatis antara JSON response dan Kotlin data classes.
Ketika user berinteraksi dengan aplikasi, misalnya mengetuk menu "Head Coach" atau "Team Squad", aplikasi akan memanggil ApiService.getTeam() dengan parameter team ID 332 (Birmingham City FC) dan API token sebagai header authentication. Pemanggilan API ini dilakukan secara asynchronous menggunakan Kotlin Coroutines dalam lifecycleScope.launch untuk menghindari blocking UI thread. Response dari API kemudian diproses menggunakan Gson untuk mengkonversi JSON menjadi objek Team yang telah didefinisikan dalam data classes seperti Team, Coach, Player, dan Staff.
Untuk data pelatih di CoachActivity, setelah response berhasil diterima, aplikasi mengekstrak objek Coach dari Team dan langsung melakukan binding ke UI components seperti TextView untuk menampilkan nama pelatih, tanggal lahir yang telah diformat, dan kewarganegaraan. Sedangkan untuk data pemain di PlayersFragment, list Player dari Team.squad diteruskan ke PlayerAdapter yang kemudian mengelola RecyclerView untuk menampilkan daftar pemain dengan layout yang konsisten.
PlayerAdapter menggunakan ListAdapter dengan DiffUtil untuk efisiensi rendering, dimana setiap item pemain ditampilkan dengan informasi posisi dan kewarganegaraan, serta color coding berdasarkan posisi pemain (goalkeeper, defender, midfielder, forward) untuk memberikan visual feedback yang lebih baik. Ketika user mengetuk item pemain, detail pemain ditampilkan dalam card overlay dengan animasi fade-in untuk pengalaman user.
Seluruh proses ini dilengkapi dengan error handling yang komprehensif menggunakan try-catch blocks, loading states dengan ProgressBar yang ditampilkan selama proses fetching data, dan user feedback melalui Toast messages jika terjadi error. Aplikasi juga menggunakan ViewBinding untuk type-safe access ke UI components dan mengikuti MVVM pattern dengan pemisahan yang jelas antara network layer, data models, dan UI components, sehingga kode menjadi maintainable dan scalable.


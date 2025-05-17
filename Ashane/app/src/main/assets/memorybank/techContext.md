# Aşhane Teknik Bağlam

## Teknoloji Yığını

### Programlama Dili ve Platform
- **Dil:** Kotlin ✅
- **Platform:** Android ✅
- **Minimum SDK:** API 24 (Android 7.0 Nougat) ✅
- **Hedef SDK:** API 35 (Android 15) ✅

### Kullanılan Kütüphaneler

#### Temel Kütüphaneler ✅
- **AndroidX Core:** `androidx.core:core-ktx:1.16.0`
- **AppCompat:** `androidx.appcompat:appcompat:1.7.0`
- **Material Design:** `com.google.android.material:material:1.12.0`
- **ConstraintLayout:** `androidx.constraintlayout:constraintlayout:2.2.1`

#### Navigasyon ✅
- **Navigation Component:** 
  - `androidx.navigation:navigation-fragment-ktx:2.7.7`
  - `androidx.navigation:navigation-ui-ktx:2.7.7`
  - Navigation Graph oluşturuldu
  - MainActivity entegrasyonu tamamlandı

#### Ağ İşlemleri ✅
- **Retrofit:** 
  - `com.squareup.retrofit2:retrofit:2.9.0`
  - `com.squareup.retrofit2:converter-gson:2.9.0`
  - FoodApiService interface'i oluşturuldu
  - RetrofitClient singleton sınıfı oluşturuldu
- **OkHttp:** 
  - `com.squareup.okhttp3:okhttp:4.11.0`
  - `com.squareup.okhttp3:logging-interceptor:4.11.0`
  - Logging interceptor eklendi

#### Resim Yükleme ✅
- **Glide:** 
  - `com.github.bumptech.glide:glide:4.16.0`
  - `com.github.bumptech.glide:compiler:4.16.0`
  - FoodAdapter'da kullanımı başlatıldı

#### Yaşam Döngüsü ve MVVM ✅
- **ViewModel:** `androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0`
- **LiveData:** `androidx.lifecycle:lifecycle-livedata-ktx:2.7.0`
- **Lifecycle Extensions:** `androidx.lifecycle:lifecycle-runtime-ktx:2.7.0`
- HomeViewModel sınıfı oluşturuldu

#### Coroutines ✅
- **Coroutines Core:** `org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3`
- **Coroutines Android:** `org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3`
- Repository sınıfında asenkron işlemler için kullanıldı

#### SwipeRefreshLayout ✅
- **SwipeRefreshLayout:** `androidx.swiperefreshlayout:swiperefreshlayout:1.1.0`
- HomeFragment'ta yemek listesini yenilemek için kullanıldı

#### Test Kütüphaneleri ✅
- **JUnit:** `junit:junit:4.13.2`
- **Android Test:** `androidx.test.ext:junit:1.2.1`
- **Espresso:** `androidx.test.espresso:espresso-core:3.6.1`

### Geliştirme Ortamı
- **IDE:** Android Studio
- **Gradle Sürümü:** 8.9.1
- **Kotlin Sürümü:** 2.0.21
- **Derleyici Seçenekleri:** Java 11 uyumluluğu

### Yapı Özellikleri
- **ViewBinding:** Etkinleştirildi ✅
- **Proguard/R8:** Release build'lerde kod küçültme ve karıştırma (şimdilik devre dışı)

## Çözülen Teknik Sorunlar

### 1. ViewBinding Sorunları ✅
- **Sorun:** MainActivity, HomeFragment ve FoodAdapter sınıflarında ViewBinding ile ilgili hatalar vardı.
- **Hata Mesajları:**
  - `Unresolved reference: databinding`
  - `Unresolved reference: ActivityMainBinding`
  - `Unresolved reference: FragmentHomeBinding`
  - `Unresolved reference: ItemFoodBinding`
- **Çözüm:**
  - build.gradle.kts dosyasında viewBinding etkinleştirildi
  - Gerekli import'lar eklendi
  - Gradle senkronizasyonu yapıldı

### 2. Navigation Sorunları ✅
- **Sorun:** MainActivity'de AppBarConfiguration ve setupActionBarWithNavController ile ilgili hatalar vardı.
- **Hata Mesajları:**
  - `Unresolved reference: R`
  - `None of the following functions can be called with the arguments supplied: public inline fun AppBarConfiguration(...)`
  - `Overload resolution ambiguity: public fun AppCompatActivity.setupActionBarWithNavController(...)`
- **Çözüm:**
  - R sınıfı import edildi
  - AppBarConfiguration için navController.graph kullanıldı
  - setupActionBarWithNavController fonksiyonu doğru parametrelerle çağrıldı

### 3. RecyclerView Sorunları ✅
- **Sorun:** FoodAdapter'da bindingAdapterPosition kullanımında hata vardı.
- **Hata Mesajları:**
  - `Unresolved reference: bindingAdapterPosition`
- **Çözüm:**
  - bindingAdapterPosition yerine adapterPosition kullanıldı

### 4. SwipeRefreshLayout Sorunları ✅
- **Sorun:** HomeFragment'ta SwipeRefreshLayout ile ilgili hatalar vardı.
- **Hata Mesajları:**
  - `Cannot access class 'SwipeRefreshLayout'`
  - `Unresolved reference: setOnRefreshListener`
  - `Unresolved reference: isRefreshing`
- **Çözüm:**
  - SwipeRefreshLayout bağımlılığı eklendi
  - HomeFragment'a gerekli import eklendi

## API Entegrasyon Detayları

### Yemekleri Listeleme ✅
- **Endpoint:** `http://kasimadalan.pe.hu/yemekler/tumYemekleriGetir.php`
- **Method:** GET
- **Response Format:** JSON
- **Response Örneği:**
```json
{
  "yemekler": [
    {
      "yemek_id": "1",
      "yemek_adi": "Ayran",
      "yemek_resim_adi": "ayran.png",
      "yemek_fiyat": "3"
    },
    ...
  ],
  "success": 1
}
```

### Sepete Yemek Ekleme ✅
- **Endpoint:** `http://kasimadalan.pe.hu/yemekler/sepeteYemekEkle.php`
- **Method:** POST
- **Request Format:** Form-URL-Encoded
- **Parameters:**
  - `yemek_adi` (String)
  - `yemek_resim_adi` (String)
  - `yemek_fiyat` (int)
  - `yemek_siparis_adet` (int)
  - `kullanici_adi` (String)
- **Response Format:** JSON
- **Response Örneği:**
```json
{
  "success": 1,
  "message": "success"
}
```

### Sepetteki Yemekleri Getirme ✅
- **Endpoint:** `http://kasimadalan.pe.hu/yemekler/sepettekiYemekleriGetir.php`
- **Method:** POST
- **Request Format:** Form-URL-Encoded
- **Parameters:**
  - `kullanici_adi` (String)
- **Response Format:** JSON
- **Response Örneği:**
```json
{
  "sepet_yemekler": [
    {
      "sepet_yemek_id": "1",
      "yemek_adi": "Ayran",
      "yemek_resim_adi": "ayran.png",
      "yemek_fiyat": "3",
      "yemek_siparis_adet": "2",
      "kullanici_adi": "AshaneTestKullanicisi"
    },
    ...
  ],
  "success": 1
}
```

### Sepetten Yemek Silme ✅
- **Endpoint:** `http://kasimadalan.pe.hu/yemekler/sepettenYemekSil.php`
- **Method:** POST
- **Request Format:** Form-URL-Encoded
- **Parameters:**
  - `sepet_yemek_id` (int)
  - `kullanici_adi` (String)
- **Response Format:** JSON
- **Response Örneği:**
```json
{
  "success": 1,
  "message": "success"
}
```

## Performans Değerlendirmeleri
- **Resim Yükleme Optimizasyonu:** Glide ile resim önbelleğe alma ve boyutlandırma
- **Ağ İsteği Optimizasyonu:** OkHttp önbelleğe alma mekanizması
- **UI Performansı:** RecyclerView için DiffUtil kullanımı
- **Bellek Yönetimi:** Yaşam döngüsü farkındalığı ve bellek sızıntılarını önleme

## Teknik Borç
- Şimdilik basit bir kullanıcı kimlik doğrulama sistemi kullanılacak (sabit kullanıcı adı)
- Ödeme sistemi entegrasyonu gelecek sürümlere bırakılacak
- Offline mod desteği şimdilik eklenmeyecek
- Kapsamlı hata işleme mekanizmaları gelecek sürümlerde iyileştirilecek 
# Teknik Bağlam: Bootcamp Odaklı Temel Hesap Makinesi Uygulaması

## Teknoloji Yığını

### Programlama Dilleri
- **Kotlin**: Android uygulaması geliştirmek için kullanılan ana programlama dili
- **XML**: Kullanıcı arayüzü tanımlamaları için kullanılan işaretleme dili

### Çerçeveler ve Kütüphaneler
- **Android SDK**: Android uygulama geliştirme için temel SDK
- **AndroidX**: Android Jetpack bileşenleri
  - **ConstraintLayout**: Duyarlı UI düzenleri için
  - **GridLayout**: Hesap makinesi tuş düzeni için
  - **Core-KTX**: Kotlin ile Android geliştirme için eklentiler
  - **AppCompat**: Geriye dönük uyumluluk için

### Yapı Sistemi
- **Gradle**: Uygulama derleme ve bağımlılık yönetimi
- **Kotlin Gradle DSL**: Kotlin tabanlı yapı betiği konfigürasyonu

## Geliştirme Ortamı

### Araçlar
- **Android Studio**: Başlangıç IDE'si
- **Cursor IDE**: Mevcut geliştirme IDE'si
- **Git**: Sürüm kontrol sistemi (isteğe bağlı)
- **Memory Bank**: Proje dokümantasyonu ve yönetimi (Claude tarafından sağlanan MCP)

### Geliştirme Gereksinimleri
- **JDK**: Java Geliştirme Kiti
- **Kotlin Derleyicisi**: Android SDK ile uyumlu
- **Android Emülatör/Fiziksel Cihaz**: Test için

## Kod Organizasyonu

### Paket Yapısı
```
com.eduplayconnect.calculator/
  |- MainActivity.kt      // Ana aktivite ve hesap makinesi mantığı
```

### Kaynak Organizasyonu
```
res/
  |- layout/
     |- activity_main.xml  // Hesap makinesi arayüzü
  |- values/
     |- strings.xml        // UI metinleri
     |- colors.xml         // Renk tanımları
     |- themes.xml         // Uygulama temaları
```

## Uygulama Mimarisi

Uygulama basit bir mimariye sahiptir ve bootcamp seviyesindeki öğrenciler için anlaşılır olması için tasarlanmıştır:

- **Single Activity**: Tüm uygulama tek bir aktivite içinde
- **UI State Management**: Sınıf değişkenleri ile işlem durumu yönetimi
- **Event Handling**: OnClickListener kullanarak buton etkileşimlerini yakalama
- **Modüler Fonksiyonlar**: İşlevlerin ayrı metodlarda tanımlanması

## Performans Değerlendirmeleri

- **UI Yanıt Verme Süresi**: Hesaplama işlemleri basit olduğundan UI thread üzerinde yapılabilir
- **Bellek Kullanımı**: Uygulama hafif ve minimal bellek kullanımına sahip

## Teknik Borç

Halihazırda kabul edilebilir teknik borç:
- İlk aşamada sadece toplama işleminin desteklenmesi
- ViewModel veya MVC/MVVM gibi daha karmaşık mimari desenlerin kullanılmaması
- Kapsamlı hata işleme ve test eksikliği

Planlanan borç çözümü:
- Gelecek sürümlerde diğer matematiksel işlemlerin eklenmesi
- Kod tabanının daha iyi organizasyonu (ViewModel kullanımı gibi)
- Daha kapsamlı test ve hata yönetimi eklemek
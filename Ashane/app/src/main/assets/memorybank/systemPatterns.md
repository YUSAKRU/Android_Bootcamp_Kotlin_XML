# Aşhane Sistem Mimarisi

## Sistem Mimarisi
Aşhane uygulaması MVVM (Model-View-ViewModel) mimari deseni kullanılarak geliştirilmektedir. Bu mimari, kullanıcı arayüzü (View) ile veri modeli (Model) arasında bir köprü görevi gören ViewModel katmanı sayesinde sorumlulukların ayrılmasını sağlar.

### Katmanlar:

1. **Model Katmanı** ✅
   - Veri modelleri (Data Classes)
     - `YemekModel`: Yemek bilgilerini tutan model (Parcelable implementasyonu ile fragment'lar arası geçişi destekler)
     - `SepetYemekModel`: Sepetteki yemek bilgilerini tutan model
     - `YemekResponse`, `SepetYemekResponse`, `CRUDResponse`: API yanıtlarını tutan modeller
   - Repository sınıfları
     - `FoodRepository`: Yemek ve sepet işlemleri için API çağrılarını yöneten sınıf. Sepete aynı üründen eklendiğinde güncelleme mantığını da içerir.
   - API servisleri
     - `FoodApiService`: Retrofit ile API isteklerini tanımlayan interface
     - `RetrofitClient`: Retrofit istemcisini oluşturan singleton sınıf
   - Yardımcı sınıflar
     - `Resource`: API yanıtlarını saran wrapper sınıf (Success, Error, Loading durumları)

2. **View Katmanı** ✅
   - Activity ve Fragment sınıfları
     - `MainActivity`: Ana aktivite ✅
     - `HomeFragment`: Anasayfa fragment'ı ✅
     - `DetailFragment`: Detay sayfası fragment'ı ✅
     - `CartFragment`: Sepet sayfası fragment'ı ✅
     - `CheckoutFragment`: Sipariş onaylama fragment'ı ✅
   - XML layout dosyaları
     - `activity_main.xml`: Ana aktivite layout'u ✅
     - `fragment_home.xml`: Anasayfa fragment layout'u ✅
     - `item_food.xml`: Yemek listesi öğesi layout'u ✅
     - `fragment_detail.xml`: Detay sayfası layout'u ✅
     - `fragment_cart.xml`: Sepet sayfası layout'u ✅
     - `item_cart.xml`: Sepet listesi öğesi layout'u ✅
     - `fragment_checkout.xml`: Sipariş onaylama layout'u ✅
     - `main_menu.xml`: Toolbar menu layout'u ✅
   - UI geliştirmeleri
     - `ic_ashane_logo.xml`: Uygulama ikonu ✅
     - `ic_splash_logo.xml`: Splash screen ikonu ✅
     - `splash.xml`: Splash screen renk ve stil tanımları ✅
     - Animasyon dosyaları
       - `slide_in_right.xml`, `slide_out_left.xml`, `slide_in_left.xml`, `slide_out_right.xml`: Yatay kaydırma animasyonları ✅
       - `fade_in.xml`, `fade_out.xml`: Solma animasyonları ✅
   - Adapter sınıfları
     - `FoodAdapter`: Yemek listesi için RecyclerView adapter'ı ✅
     - `CartAdapter`: Sepet listesi için RecyclerView adapter'ı ✅

3. **ViewModel Katmanı** ✅
   - ViewModel sınıfları
     - `HomeViewModel`: Anasayfa için ViewModel ✅
     - `DetailViewModel`: Detay sayfası için ViewModel ✅
     - `CartViewModel`: Sepet sayfası için ViewModel ✅
     - `CheckoutViewModel`: Sipariş onaylama için ViewModel ✅
   - LiveData nesneleri
     - Yemek listesi, filtrelenmiş yemek listesi, sepet listesi vb. için LiveData'lar

## Bileşen Diyagramları

### Veri Akışı Diyagramı
```
[API] <---> [Repository] <---> [ViewModel] <---> [View (Activity/Fragment)]
```

### Ekran Akış Diyagramı
```
[Splash Screen] ---> [Anasayfa (Yemek Listesi)] ---> [Ürün Detay Sayfası] ---> [Sepet Sayfası] ---> [Sipariş Onay] ---> [Anasayfa (Başlangıç)]
```

## Kullanılan Tasarım Desenleri

1. **MVVM (Model-View-ViewModel)** ✅
   - View ve Model arasındaki bağımlılığı azaltır
   - Test edilebilirliği artırır
   - Yaşam döngüsü sorunlarını çözer

2. **Repository Pattern** ✅
   - Veri kaynaklarını soyutlar
   - Tek bir veri erişim noktası sağlar
   - API isteklerini yönetir
   - İş mantığını (sepet güncelleme gibi) içerir

3. **Adapter Pattern** ✅
   - RecyclerView için veri adaptörleri
   - Farklı veri türlerini görünümlere bağlar

4. **Singleton Pattern** ✅
   - Retrofit istemcisi için kullanıldı

5. **Observer Pattern** ✅
   - LiveData ile veri değişikliklerini izleme

6. **Navigation Pattern** ✅
   - Navigation Component ile ekranlar arası geçiş
   - Safe Args ile tip güvenli veri geçişi
   - NavOptions ile backstack yönetimi
   - Fragment geçiş animasyonları

7. **Callback Pattern** ✅
   - Adapter'larda tıklama olaylarını fragment'lara iletmek için kullanıldı

8. **Form Pattern** ✅
   - Sipariş onaylama sayfasında teslimat bilgileri formu için kullanıldı
   - Basit validasyon kontrolleri eklenmiştir

9. **Composite Command Pattern** ✅
   - Sepete aynı üründen eklendiğinde, mevcut öğeyi silme ve yeni miktarla ekleme işlemlerini birleştiren yaklaşım
   - API'da doğrudan güncelleme endpointi olmaması durumunda alternatif çözüm olarak uygulandı

10. **Splash Screen Pattern** ✅
    - Android 12+ için Core Splashscreen API kullanımı
    - Uygulama açılışında kullanıcı deneyimini iyileştirme
    - Marka tanıtımı

## Entegrasyon Noktaları

1. **API Entegrasyonu** ✅
   - `http://kasimadalan.pe.hu/yemekler/` endpoint'leri
   - Retrofit kütüphanesi ile REST API çağrıları
   - JSON veri dönüşümü için Gson

2. **Resim Yükleme** ✅
   - Glide kütüphanesi ile resim yükleme
   - `http://kasimadalan.pe.hu/yemekler/resimler/` endpoint'i

3. **Navigasyon** ✅
   - Android Navigation Component ile ekranlar arası geçiş
   - Navigation Graph oluşturuldu ve entegre edildi
   - Safe Args ile fragment'lar arası veri geçişi (HomeFragment -> DetailFragment)
   - Bundle ile veri geçişi (CartFragment -> CheckoutFragment)
   - Toolbar'da sepet ikonu ile sepet sayfasına erişim
   - NavOptions ile backstack yönetimi (CheckoutFragment -> HomeFragment)
   - Fragment geçiş animasyonları (slide ve fade)

4. **Splash Screen** ✅
   - Core Splashscreen kütüphanesi ile modern splash screen
   - AndroidManifest.xml'de tema entegrasyonu
   - MainActivity'de `installSplashScreen()` çağrısı

## Veri Akışı

1. **Uygulama Başlatma** ✅
   - Kullanıcı uygulamayı açıyor
   - Splash screen görüntüleniyor
   - MainActivity yükleniyor ve anasayfa gösteriliyor

2. **Yemek Listeleme** ✅
   - API'dan yemek listesi alınır (Repository)
   - Repository'den ViewModel'e aktarılır
   - ViewModel'den View'a LiveData ile aktarılır
   - RecyclerView ile listelenir

3. **Detay Sayfası Navigasyonu** ✅
   - Kullanıcı bir yemek öğesine tıklar
   - Safe Args ile YemekModel nesnesi DetailFragment'a aktarılır
   - DetailViewModel'de yemek bilgileri işlenir
   - Detay sayfasında yemek bilgileri gösterilir

4. **Sepet İşlemleri** ✅
   - ✅ Kullanıcı sepete ürün ekler
   - ✅ Sepette aynı ürün olup olmadığı kontrol edilir
   - ✅ Aynı ürün varsa, mevcut öğe silinir ve yeni miktarla tekrar eklenir
   - ✅ Aynı ürün yoksa, doğrudan sepete eklenir
   - ✅ ViewModel aracılığıyla Repository'e iletilir
   - ✅ API'a POST isteği gönderilir
   - ✅ Başarı/hata durumu View'a bildirilir (ekleme/güncelleme durumuna göre farklı mesajlar)
   - ✅ Sepet sayfasında sepet içeriği görüntülenir
   - ✅ Sepetten ürün silinebilir

5. **Sepet Görüntüleme** ✅
   - ✅ API'dan kullanıcıya ait sepet verileri alınır
   - ✅ Repository'den ViewModel'e aktarılır
   - ✅ ViewModel'den View'a LiveData ile aktarılır
   - ✅ RecyclerView ile listelenir
   - ✅ Toplam tutar hesaplanır ve gösterilir

6. **Sipariş Onaylama** ✅
   - ✅ Kullanıcı sepet sayfasındaki "Siparişi Onayla" butonuna tıklar
   - ✅ Toplam tutar ve ürün sayısı CheckoutFragment'a aktarılır
   - ✅ Kullanıcı teslimat bilgilerini girer ve "Siparişi Tamamla" butonuna tıklar
   - ✅ Basit validasyon kontrolleri yapılır
   - ✅ Başarı durumunda kullanıcıya bilgi mesajı gösterilir
   - ✅ Kullanıcı anasayfaya yönlendirilir ve backstack temizlenir

## Çözülen Sorunlar

1. **ViewBinding Sorunları** ✅
   - MainActivity, HomeFragment ve FoodAdapter sınıflarında ViewBinding ile ilgili hatalar çözüldü
   - Gerekli import'lar eklendi

2. **Navigation Sorunları** ✅
   - MainActivity'de AppBarConfiguration ve setupActionBarWithNavController ile ilgili hatalar çözüldü
   - AppBarConfiguration için navController.graph kullanıldı

3. **RecyclerView Sorunları** ✅
   - FoodAdapter'da bindingAdapterPosition yerine adapterPosition kullanıldı

4. **SwipeRefreshLayout Sorunları** ✅
   - SwipeRefreshLayout için eksik bağımlılık eklendi
   - HomeFragment'a gerekli import eklendi

5. **Navigation Safe Args Sorunları** ✅
   - Safe Args plugin'i eklendi
   - YemekModel sınıfı Parcelable olarak işaretlendi
   - kotlin-parcelize plugin'i eklendi

6. **Sepete Ekleme İşlevselliği** ✅
   - DetailViewModel'de sepete ekleme işlevselliği eklendi
   - API çağrısı için Repository kullanıldı
   - İşlem sonucu LiveData ile gözlemlendi
   - Kullanıcıya Toast mesajları ile bildirildi

7. **Sepet Sayfası İşlevselliği** ✅
   - CartFragment, CartViewModel ve CartAdapter sınıfları oluşturuldu
   - Sepet içeriği görüntüleme işlevselliği eklendi
   - Sepetten ürün silme işlevselliği eklendi
   - Toplam tutarı hesaplama ve gösterme işlevselliği eklendi
   - Boş sepet durumu kontrol edildi ve uygun mesaj gösterildi

8. **Sipariş Onaylama İşlevselliği** ✅
   - CheckoutFragment, CheckoutViewModel ve fragment_checkout.xml oluşturuldu
   - Teslimat bilgileri formu eklendi
   - Ödeme yöntemi (Kapıda Ödeme) seçeneği eklendi
   - Sipariş özeti gösterimi eklendi
   - Basit form validasyonu eklendi
   - Navigasyon ve backstack yönetimi eklendi
   
9. **Sepete Aynı Üründen Eklendiğinde Güncelleme Sorunu** ✅
   - FoodRepository'ye addOrUpdateItemInCart metodu eklendi
   - Repository'de sepete ekleme öncesi mevcut sepet içeriği kontrol edildi
   - Aynı ürün varsa silme ve yeni miktarla ekleme yaklaşımı uygulandı
   - DetailViewModel güncellendi ve başarı/hata durumları iyileştirildi
   - Kullanıcıya ekleme/güncelleme durumuna göre farklı mesajlar gösterildi

10. **UI/UX İyileştirmeleri** ✅
    - XML Vector Drawable ile uygulama ikonu oluşturuldu
    - Core Splashscreen kütüphanesi ile modern splash screen eklendi
    - Fragment geçişleri için animasyonlar eklendi
    - AndroidManifest.xml'de tema yapılandırması güncellendi 
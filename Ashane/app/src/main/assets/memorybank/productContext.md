# Aşhane Ürün Bağlamı

## Kullanıcı Personaları
- **Hızlı Sipariş Veren**: Zamanı kısıtlı, hızlı ve kolay bir şekilde yemek siparişi vermek isteyen kullanıcı
- **Detaylı İnceleyici**: Yemek seçeneklerini detaylı inceleyerek karar veren, fiyat-performans analizi yapan kullanıcı
- **Düzenli Müşteri**: Sık sık sipariş veren, favori yemekleri olan ve hızlı erişim isteyen kullanıcı

## Özellik Listesi ve Öncelikler

### Yüksek Öncelikli Özellikler
1. **Yemek Listeleme (Anasayfa)**
   - ✅ API'dan yemekleri çekme ve listeleme için model ve servis sınıfları oluşturuldu
   - ✅ Her yemek için resim, ad ve fiyat bilgisi gösterme için layout tasarlandı
   - ✅ RecyclerView ile kart görünümünde listeleme için adapter hazırlandı
   - ✅ ViewBinding sorunları çözüldü

2. **Ürün Detay Sayfası**
   - ✅ Navigation Component entegrasyonu tamamlandı
   - 🔜 Seçilen yemeğin detaylı görünümü oluşturulacak
   - 🔜 Adet seçimi için +/- butonları eklenecek
   - 🔜 "Sepete Ekle" butonu ve işlevselliği eklenecek

3. **Sepet Yönetimi**
   - ✅ API servis ve repository sınıfları oluşturuldu
   - 🔜 Sepete eklenen ürünleri listeleme ekranı oluşturulacak
   - 🔜 Ürün adedi artırma/azaltma işlevselliği eklenecek
   - 🔜 Ürünleri sepetten silme işlevselliği eklenecek
   - 🔜 Toplam sipariş tutarını gösterme eklenecek
   - 🔜 "Sepeti Onayla" butonu ve işlevselliği eklenecek

### Orta Öncelikli Özellikler
1. **Arama/Filtreleme**
   - ✅ Arama çubuğu layout'u eklendi
   - ✅ Arama işlevselliği için ViewModel'de fonksiyonlar oluşturuldu
   - ⏳ Arama işlevselliğinin test edilmesi

2. **Teslimat Adresi Gösterimi**
   - 🔜 Statik olarak bir teslimat adresi gösterimi eklenecek

### Düşük Öncelikli Özellikler
1. **Kullanıcı Girişi**
   - ✅ Sabit kullanıcı adı (AshaneTestKullanicisi) repository'de tanımlandı
   - 🔜 Basit bir kullanıcı girişi ekranı (gelecekte eklenebilir)
2. **Sipariş Geçmişi**
   - 🔜 Geçmiş siparişleri görüntüleme (gelecekte eklenebilir)

## Kullanıcı Hikayeleri
1. Kullanıcı olarak, mevcut yemekleri görebilmek istiyorum ki seçim yapabileyim.
   - ✅ Anasayfa tasarımı ve yemek listeleme altyapısı oluşturuldu
   - ⏳ Yemek listeleme işlevselliğinin test edilmesi

2. Kullanıcı olarak, bir yemeğin detaylarını görebilmek istiyorum ki içeriği hakkında bilgi sahibi olabileyim.
   - 🔜 Detay sayfası oluşturulacak

3. Kullanıcı olarak, istediğim yemeği sepete ekleyebilmek istiyorum ki sipariş verebileyim.
   - ✅ API servisleri ve repository sınıfları hazır
   - 🔜 UI ve işlevsellik eklenecek

4. Kullanıcı olarak, sepetime eklediğim ürünleri görebilmek istiyorum ki siparişimi kontrol edebileyim.
   - ✅ API servisleri ve repository sınıfları hazır
   - 🔜 Sepet sayfası oluşturulacak

5. Kullanıcı olarak, sepetteki ürünlerin miktarını değiştirebilmek istiyorum ki istediğim kadar sipariş verebileyim.
   - 🔜 Sepet sayfasında miktar değiştirme işlevselliği eklenecek

6. Kullanıcı olarak, sepetteki ürünleri silebilmek istiyorum ki istemediğim ürünleri çıkarabileyim.
   - ✅ API servisleri ve repository sınıfları hazır
   - 🔜 UI ve işlevsellik eklenecek

7. Kullanıcı olarak, toplam sipariş tutarını görebilmek istiyorum ki ne kadar ödeyeceğimi bileyim.
   - 🔜 Sepet sayfasında toplam tutar gösterimi eklenecek

8. Kullanıcı olarak, siparişimi onaylayabilmek istiyorum ki yemek siparişi verebileyim.
   - 🔜 Sipariş onaylama işlevselliği eklenecek

## Rekabet Analizi
- Yemeksepeti, Getir, Trendyol Yemek gibi uygulamalar incelenerek kullanıcı deneyimi ve arayüz tasarımı referans alınabilir.
- Rekabet avantajı olarak basit ve kullanıcı dostu arayüz tasarımı hedeflenecek.

## Ürün Yol Haritası
1. **Temel Sürüm (v1.0)**
   - ✅ Proje yapısı ve temel bileşenler oluşturuldu
   - ⏳ Yemek listeleme (test edilecek)
   - 🔜 Ürün detay görüntüleme
   - 🔜 Sepet yönetimi (ekleme, silme, miktar değiştirme)
   - 🔜 Sipariş onaylama

2. **Gelecek Sürümler**
   - Kullanıcı hesap yönetimi
   - Favori yemekler
   - Sipariş geçmişi
   - Kategori bazlı filtreleme
   - Gerçek ödeme sistemi entegrasyonu 
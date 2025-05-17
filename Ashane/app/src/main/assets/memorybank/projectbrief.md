# Aşhane Projesi - Proje Özeti

## Proje Vizyonu ve Hedefler
Aşhane, kullanıcıların yemek siparişi verebilecekleri kullanıcı dostu bir Android mobil uygulamasıdır. Uygulama, yemeklerin listelendiği bir anasayfa, yemek detaylarının görüntülendiği bir detay sayfası ve sepet yönetimi için bir sepet sayfası içerecektir.

### Ana Hedefler:
- Kullanıcılara yemekleri listeleyerek sunmak
- Yemek detaylarını görüntüleme imkanı sağlamak
- Sepete yemek ekleme, silme ve sipariş miktarını düzenleme işlevselliği sunmak
- Kullanıcı dostu ve modern bir arayüz tasarımı oluşturmak

## Kilit Paydaşlar
- Geliştirici Ekip
- Test Kullanıcıları
- API Sağlayıcı (kasimadalan.pe.hu)

## Başarı Metrikleri
- Tüm API entegrasyonlarının başarıyla tamamlanması
- Uygulama akışının sorunsuz çalışması
- MVVM mimarisine uygun kod yapısı
- Kullanıcı dostu arayüz tasarımı

## Kısıtlamalar ve Sınırlamalar
- Sadece Android platformu için geliştirme yapılacak
- Kotlin ve XML kullanılacak
- Kullanıcı kimlik doğrulama sistemi basit tutulacak (sabit kullanıcı adı ile)
- Ödeme sistemi entegrasyonu şimdilik yapılmayacak

## API Entegrasyon Noktaları
- `http://kasimadalan.pe.hu/yemekler/tumYemekleriGetir.php` (GET) - Tüm yemekleri getirme
- `http://kasimadalan.pe.hu/yemekler/sepeteYemekEkle.php` (POST) - Sepete yemek ekleme
- `http://kasimadalan.pe.hu/yemekler/sepettekiYemekleriGetir.php` (POST) - Sepetteki yemekleri getirme
- `http://kasimadalan.pe.hu/yemekler/sepettenYemekSil.php` (POST) - Sepetten yemek silme
- Resim URL: `http://kasimadalan.pe.hu/yemekler/resimler/{yemek_resim_adi}`

## Zaman Çizelgesi ve Mevcut Durum
1. ✅ Proje yapısının oluşturulması ve gerekli kütüphanelerin eklenmesi
2. ✅ Veri modellerinin ve API servislerinin oluşturulması
3. ✅ Repository sınıfının oluşturulması
4. ✅ ViewBinding ve Navigation sorunlarının çözülmesi
5. ⏳ Anasayfa tasarımı ve yemek listeleme işlevselliği (test edilecek)
6. 🔜 Ürün detay sayfası tasarımı ve sepete ekleme işlevselliği
7. 🔜 Sepet sayfası tasarımı ve sepet yönetimi işlevselliği
8. 🔜 Test ve hata düzeltmeleri 
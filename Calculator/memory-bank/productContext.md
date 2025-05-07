# Ürün Bağlamı: Bootcamp Odaklı Temel Hesap Makinesi Uygulaması

## Kullanıcı Personaları

### Bootcamp Öğrencisi
- **Açıklama**: Android geliştirme konusunda temel bilgileri öğrenmek isteyen öğrenci
- **İhtiyaçlar**: Kotlin ile basit uygulama geliştirme, UI tasarımı, olay yönetimi, durum kontrolü
- **Zorluklar**: Programlama deneyimi sınırlı olabilir, Android geliştirme konseptlerini anlamak için pratik örneklere ihtiyaç duyar

### Hesap Makinesi Kullanıcısı
- **Açıklama**: Basit hesaplamalar yapmak isteyen son kullanıcı
- **İhtiyaçlar**: Rakam girişi, toplama işlemi, temizleme fonksiyonu, sonuç görüntüleme
- **Zorluklar**: Kullanıcı arayüzünün anlaşılır olması, hesaplamaların doğru sonuç vermesi

## Özellik Listesi ve Öncelikler

### Temel Özellikler (Öncelik 1)
- Rakam tuşları (0-9) ile sayı girişi yapabilme
- Toplama (+) işlemi gerçekleştirebilme
- Eşittir (=) tuşu ile hesaplama sonucunu görüntüleme
- Temizleme (C) tuşu ile hesap makinesini sıfırlama

### İkincil Özellikler (Gelecekte Eklenecek, Öncelik 2)
- Çıkarma (-) işlemi
- Çarpma (*) işlemi
- Bölme (/) işlemi
- Sıfıra bölme kontrolü

### İsteğe Bağlı Özellikler (Gelecekte Eklenebilir, Öncelik 3)
- Ondalık sayı desteği
- Negatif sayı desteği
- Hafıza fonksiyonları (M+, M-, MR, MC)
- Hesaplama geçmişi

## Kullanım Senaryoları

1. **Temel Toplama İşlemi**:
   - Kullanıcı ilk sayıyı girer (örn: 15)
   - Kullanıcı toplama (+) tuşuna basar
   - Kullanıcı ikinci sayıyı girer (örn: 7)
   - Kullanıcı eşittir (=) tuşuna basar
   - Sistem sonucu gösterir (22)

2. **Hesap Makinesini Sıfırlama**:
   - Kullanıcı hesap makinesini kullanırken temizleme (C) tuşuna basar
   - Sistem ekranı sıfırlar ve tüm işlem durumunu temizler
   - Kullanıcı yeni bir hesaplama işlemine başlayabilir

3. **Ardışık İşlemler**:
   - Kullanıcı bir hesaplama yaptıktan sonra (örn: 5+3=8)
   - Kullanıcı sonuç üzerine yeni bir işlem yapmak ister
   - Kullanıcı yeni operatör tuşuna basar (örn: +)
   - Kullanıcı yeni sayı girer (örn: 2)
   - Kullanıcı eşittir (=) tuşuna basar
   - Sistem yeni sonucu gösterir (10)

## Rekabet Analizi

Piyasadaki hesap makinesi uygulamaları genellikle:
- Temel ve bilimsel hesap makinesi modları sunar
- Tam özellikli matematik işlemlerini destekler
- Gelişmiş kullanıcı arayüzleri içerir

Bizim uygulamamız, bootcamp öğrencileri için öğrenme odaklı basit bir hesap makinesi olarak konumlandırılacaktır.

## Ürün Yol Haritası

### Sürüm 1.0 (Mevcut Hedef)
- Temel hesap makinesi UI
- Rakam girişi ve görüntüleme
- Toplama işlemi
- Temizleme fonksiyonu
- Basit hata yönetimi

### Sürüm 1.1 (Gelecek)
- Çıkarma işlemi
- Çarpma işlemi
- Bölme işlemi (sıfıra bölme kontrolü ile)

### Sürüm 2.0 (İleride)
- Ondalık ve negatif sayı desteği
- Hafıza fonksiyonları
- Hesaplama geçmişi
- Gelişmiş kullanıcı arayüzü
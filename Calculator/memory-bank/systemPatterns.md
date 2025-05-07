# Sistem Desenleri: Bootcamp Odaklı Temel Hesap Makinesi Uygulaması

## Sistem Mimarisi

Hesap makinesi uygulaması, temel Android Activity-Layout ilişkisi üzerine kurulu basit bir mimariye sahiptir:

- **Activity Katmanı**: MainActivity, kullanıcı etkileşimlerini ve hesaplama mantığını içerir
- **UI Katmanı**: XML layout dosyaları ile tanımlanan kullanıcı arayüzü
- **State Yönetimi**: MainActivity içinde temel durum değişkenleriyle hesap makinesi durumu yönetilir

## Bileşen Diyagramları

### Yüksek Seviye Bileşen Yapısı
```
┌───────────────┐      ┌───────────────┐
│               │      │               │
│   MainActivity│◄─────►     Layout    │
│ (İş Mantığı ve│      │   (UI Tanımı) │
│  Etkileşim)   │      │               │
│               │      │               │
└───────────────┘      └───────────────┘
```

### Ana Bileşenler
- **MainActivity**: Hesap makinesi mantığı ve kullanıcı etkileşimlerini yöneten ana sınıf
- **activity_main.xml**: Hesap makinesi arayüzünü tanımlayan layout dosyası
- **TextView**: Kullanıcı girişi ve sonuçları görüntülemek için
- **Butonlar**: Rakamlar ve işlemler için

## Kullanılan Tasarım Desenleri

1. **Olay Yönetimi (Event Handling)**: Button OnClickListener ile kullanıcı etkileşimlerinin yönetilmesi
2. **Durum Makinesi (State Machine)**: Hesap makinesi durumunun değişkenlerle yönetilmesi
3. **İşlev Modülerizasyonu**: Farklı buton türleri için ayrı setup fonksiyonları kullanılması

## Entegrasyon Noktaları

- **Activity - Layout Entegrasyonu**: MainActivity ile activity_main.xml arasındaki bağlantı
- **OnClickListener - UI Etkileşimi**: Buton tıklamaları ile hesap makinesi fonksiyonları arasındaki bağlantı

## Veri Akışı

### İşlem Akışı (Temel Toplama İşlemi)
1. Kullanıcı, rakam butonlarına tıklayarak ilk sayıyı girer
2. Kullanıcı, toplama (+) butonuna tıklar
   - Sistem, girilen sayıyı operand1 olarak saklar
   - Sistem, pendingOperation'ı "+" olarak ayarlar
   - Sistem, newOperation'ı true olarak ayarlar
3. Kullanıcı, rakam butonlarına tıklayarak ikinci sayıyı girer
4. Kullanıcı, eşittir (=) butonuna tıklar
   - Sistem, girilen sayıyı operand2 olarak alır
   - Sistem, operand1 ve operand2 üzerinde toplama işlemi gerçekleştirir
   - Sistem, sonucu ekranda gösterir
   - Sistem, sonucu yeni operand1 olarak saklar (ardışık işlemler için)

### Temizleme Akışı
1. Kullanıcı, temizleme (C) butonuna tıklar
2. Sistem, tüm durum değişkenlerini (operand1, pendingOperation) sıfırlar
3. Sistem, ekranı "0" değerine sıfırlar
4. Sistem, kullanıcıya temizleme işlemi hakkında geri bildirim verir

## Hata Yönetimi Akışı
1. Ardışık operatör girişleri: Kullanıcı bir işlem seçtikten sonra başka bir işlem seçerse, sistem önceki işlemi tamamlar
2. Geçersiz giriş: Geçersiz sayı formatları kontrolü ve Toast bildirimleri
3. Operand olmadan işlem: Kullanıcı bir sayı girmeden işlem yapmaya çalışırsa uyarı verilir
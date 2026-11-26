# Gelecek Bilimde Android

Gelecek Bilimde'nin Android uygulaması. Makaleleri gelecekbilimde.net'in WordPress API'sinden, videoları YouTube Data API'den çeker; yeni içerik bildirimleri Firebase Cloud Messaging ile gelir.

## Teknolojiler

- Kotlin, MVVM
- Hilt (bağımlılık enjeksiyonu)
- Retrofit (HTTP), Room (yerel veritabanı), Coroutines
- ViewBinding, Jetpack Navigation
- Firebase: Analytics, Remote Config, Crashlytics, In-App Messaging, Cloud Messaging

## Kurulum

### 1. Uygulama yapılandırması

Örnek dosyayı kopyalayıp değerleri doldurun:

```bash
cp config/gelecekbilimde-config.properties.example config/gelecekbilimde-config.properties
```

| Anahtar | Açıklama |
| --- | --- |
| `BASE_URL` | WordPress sitesinin kök adresi, sonunda `/` ile (ör. `https://gelecekbilimde.net/`) |
| `YOUTUBE_API` | YouTube Data API kök adresi (`https://www.googleapis.com/youtube`) |
| `YOUTUBE_API_KEY` | Google Cloud Console'dan alınan, Android uygulamasıyla kısıtlanmış API anahtarı |
| `CHANNEL_ID` | YouTube kanal kimliği |
| `PLAYLIST_ID` | Videoların çekileceği oynatma listesi kimliği |

`config/gelecekbilimde-config.properties` git'e eklenmez. Dosya yoksa aynı isimli ortam değişkenleri kullanılır; CI'da bu yöntem tercih edilmelidir.

### 2. Firebase

Firebase Console'dan `google-services.json` dosyasını indirip `app/` klasörüne koyun. Bu dosya da repoya eklenmemelidir.

### 3. Build

```bash
./gradlew assembleDebug
```

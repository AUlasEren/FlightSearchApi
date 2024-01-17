# FlightSearchApi
# Uçuş Arama Uygulaması

Bu proje, kullanıcılara uçuş arama ve yönetim işlevleri sağlayan bir Spring Boot uygulamasıdır. Uygulama, her gün belirli bir zamanda mock bir API'dan uçuş bilgilerini alır ve veritabanına kaydeder.

## Başlarken

Bu bölüm, projenin nasıl kurulup çalıştırılacağını açıklar.

### Önkoşullar

Projeyi çalıştırmak için aşağıdaki araçlara ihtiyacınız vardır:

- Java JDK 17 veya üzeri
- Gradle (Proje bağımlılıklarını yönetmek ve uygulamayı derlemek için)

### Kurulum

Projeyi yerel makinenize kurmak için aşağıdaki adımları takip edin:

1. Projeyi klonlayın: 
git clone https://github.com/AUlasEren/FlightSearchApi.git
2. Proje dizinine gidin:
cd your-project-name
3. Gradle ile projeyi derleyin: 
./gradlew build 

### Çalıştırma

Uygulamayı aşağıdaki komutla başlatın:

java -jar build/libs/flightsearchapp-0.0.1-SNAPSHOT.jar

Uygulama varsayılan olarak `8082` portunda çalışacaktır.

## Kullanım

Bu bölümde, uygulamanın temel özellikleri ve nasıl kullanılacağı açıklanır.

- Uçuşları aramak için `/api/flights/findFlights` endpoint'ine GET isteği gönderin.
- Yeni uçuş eklemek için `/api/flights/create` endpoint'ine POST isteği gönderin.
- Var olan bir uçuşu güncellemek için `/api/flights/update` endpoint'ine PUT isteği gönderin.

## Geliştirme

Bu bölüm, geliştiriciler için yararlı bilgiler içerir.

- `src/main/java` klasörü uygulamanın Java kaynak kodlarını içerir.
- `src/main/resources` klasörü, uygulama ayarları ve diğer kaynak dosyalarını içerir.



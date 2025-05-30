1. Склонировать на VPS проект с гитхаба
2. Скачать и установить Java 21.* (sdkman)
3. Собрать JAR для микросервисов (./gradlew clean bootJar)
4. Скачать и установить docker + docker-compose
5. Собрать docker-compose приложение (docker-compose build)
6. Запустить docker-compose приложение (docker-compose up -d)
## Запуск тестов

Для локального запуска тестов используйте следующую команду:
```
./gradlew clean test -Denv=local
```
Либо стандартную команду:
```
./gradlew clean test
```

Для запуска тестов удаленно на Selenoid используйте команду:
```
./gradlew clean test -Denv=remote
```

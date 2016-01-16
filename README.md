# Artopia mud
### Mud - console multiplayer rpg

[![Build Status](https://travis-ci.org/Vehsamrak/Artopia.svg?branch=master)](https://travis-ci.org/Vehsamrak/Artopia) [![Code Coverage](https://codecov.io/github/Vehsamrak/Artopia/coverage.svg?branch=master)](https://codecov.io/github/vehsamrak/artopia?branch=master)

### Сборка
```
gradle build
```

### Запуск
```
java -jar build/libs/artopia-*.jar
```

### Тесты
Расчет покрытия: `gradle check`

После обработки отчет будет доступен в файле `build/reports/jacoco/test/html/index.html`

Список классов особо нуждающихся в тестировании [доступен по этой ссылке](https://codecov.io/github/Vehsamrak/Artopia/features/suggestions?ref=master)

![График изменения покрытия](https://codecov.io/github/Vehsamrak/Artopia/branch.svg?branch=master)

# Getting Started

### Reference Documentation

Учебное приложение для работы с животными

В корне проекта уже есть файл с докер компоузом, в который включен postgres, по мере добавления будут добавляться другие 
контейнеры.
Для запуска просто перейти в файл docker-compose.yaml и нажать на зеленую стрелочку.
После этого приложение можно запускать. 
Сейчас стратегия генерации таблиц стоит create, это указано в файле application.yaml а именно
spring:
  jpa:  
    hibernate:
      ddl-auto: create
Это значит что таблицы сами создатутся на основании наши энтити. Впоследствии переделаем на миграции.
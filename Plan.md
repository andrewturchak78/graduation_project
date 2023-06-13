# План автоматизации приложения мобильного банка
## Перечень автоматизируемых сценариев
**Предусловия запусков автотестов:**
* на компьютере установлена IntelliJ IDEA и [этот код](https://github.com/andrewturchak78/graduation_project/) открыт в ней;
* установлен Docker и NodeJS;
* В браузере открыт [проект](http://localhost:8080/);
### Положительные:
**Оплата по карте:** 
  1. Нажимаем на кнопку "Купить" на [странице заявки](http://localhost:8080/);
  2. Заполняем поле "номер карты" валидным значением "4444 4444 4444 4441";
  3. Заполняем поля месяца и года так, чтобы срок действия карты уже не истёк (например: месяц - "06", год - "25");
  4. Поле владельца заполняем валидным значением - два слова на латинице, допускается только один пробел между именем и фамилией, дефис в имени или фамилии допускается, например: Pyotr Petrov;
  5. CVC/CVV код заполняем валидным значением - любыми тремя цифрами (например, 456);
  6. Нажать кнопку "Продолжить".
    
  *Ожидаемый результат:* появляется всплывающее окно, сообщающее о том, что тур успешно оплачен.
  
 **Оплата в кредит:**
   1. Нажимаем на кнопку "Купить в кредит" на [странице заявки](http://localhost:8080/);
   2. Заполняем поле "номер карты" валидным значением "4444 4444 4444 4441";
   3. Заполняем поля месяца и года так, чтобы срок действия карты уже не истёк (например: месяц - "07", год - "26");
   4. Поле владельца заполняем валидным значением - два слова на латинице, допускается только один пробел между именем и фамилией, дефис в имени или фамилии допускается, например: Ivan Ivanov;
   5. CVC/CVV код заполняем валидным значением - любыми тремя цифрами (например, 456);
   6. Нажать кнопку "Продолжить".
    
  *Ожидаемый результат:* появляется всплывающее окно, сообщающее о том, что тур успешно оплачен.
  
  ### Негативные:
  **Оплата по карте:**
   * **Оплата неподходящей картой**
   1. Нажимаем на кнопку "Купить" на [странице заявки](http://localhost:8080/);
   2. Заполняем поле "номер карты" невалидным значением "4444 4444 4444 4442";
   3. Заполняем поля месяца и года так, чтобы срок действия карты уже не истёк (например: месяц - "06", год - "25");
   4. Поле владельца заполняем валидным значением - два слова на латинице, допускается только один пробел между именем и фамилией, дефис в имени или фамилии допускается, например: Andrei Andreev;
   5. CVC/CVV код заполняем валидным значением - любыми тремя цифрами (например, 356);
   6. Нажать кнопку "Продолжить".
    
   *Ожидаемый результат:* появляется информационное сообщение, символизируещее о том, что оплата отклонена.
    
   * **Набор символов в поле карты**
   1. Нажимаем на кнопку "Купить" на [странице заявки](http://localhost:8080/);
   2. Заполняем поле "номер карты" невалидным значением ";помтва№!*".
   
   *Ожидаемый результат:* поле остается неизменным, указываемое невалидное значение не попадает в поле ввода карты.
   
   * **Невалидный срок действия карты**
   1. Нажимаем на кнопку "Купить" на [странице заявки](http://localhost:8080/);
   2. Заполняем поле "номер карты" валидным значением "4444 4444 4444 4441";
   3. Заполняем поля месяца и года так, чтобы срок действия карты уже истёк (например: месяц - "07", год - "21");
   4. Поле владельца заполняем валидным значением - два слова на латинице, допускается только один пробел между именем и фамилией, дефис в имени или фамилии допускается, например: Oleg Olegov;
   5. CVC/CVV код заполняем валидным значением - любыми тремя цифрами (например, 123);
   6. Нажать кнопку "Продолжить".
   
   *Ожидаемый результат:* появляется информационное сообщение, символизирующее о том, что оплата отклонена.
   
   * **Невалидное значение полей месяца и года**
   1. Нажимаем на кнопку "Купить" на [странице заявки](http://localhost:8080/);
   2. Выбираем поле "Месяц" и заполняем его невалидным значением(например, dfыл);
   3. Выбираем поле "Год" и заполняем его невалидным значением(например, yuшд).
   
   *Ожидаемый результат:* оба поля остаются неизменными.
   
   * **Невалидное значение поля "Владелец"**
   1. Нажимаем на кнопку "Купить" на [странице заявки](http://localhost:8080/);
   2. Заполняем поле "Владелец" набором символов, состоящим из цифр, символов и кириллицы, например: 34прое89*;
   
   *Ожидаемый результат:* поле остается без изменений.
   
   * **Невалидное значение поля CVC/CVV**
   1. Нажимаем на кнопку "Купить" на [странице заявки](http://localhost:8080/);
   2. Заполняем поле "CVC/CVV" набором символов, состоящим из букв и символов, например, GП*;
   
   *Ожидаемый результат:* поле остается без изменений.
   
   * **Попытка превысить допустимое количество цифр CVC/CVV кода**
   1. Нажимаем на кнопку "Купить" на [странице заявки](http://localhost:8080/);
   2. Заполняем поле CVC/CVV четырьмя цифрами, например, 5678;
   
   *Ожидаемый результат:* в поле осталось только 567, 8 не попало в поле.
   
   
   **Оплата в кредит:**
   * **Оплата неподходящей картой**
   1. Нажимаем на кнопку "Купить в кредит" на [странице заявки](http://localhost:8080/);
   2. Заполняем поле "номер карты" невалидным значением "4444 4444 4444 4442";
   3. Заполняем поля месяца и года так, чтобы срок действия карты уже не истёк (например: месяц - "06", год - "25");
   4. Поле владельца заполняем валидным значением - два слова на латинице, допускается только один пробел между именем и фамилией, дефис в имени или фамилии допускается, например: Andrei Andreev;
   5. CVC/CVV код заполняем валидным значением - любыми тремя цифрами (например, 356);
   6. Нажать кнопку "Продолжить".
    
   *Ожидаемый результат:* появляется информационное сообщение, символизируещее о том, что оплата отклонена.
    
   * **Набор символов в поле карты**
   1. Нажимаем на кнопку "Купить в кредит" на [странице заявки](http://localhost:8080/);
   2. Заполняем поле "номер карты" невалидным значением ";помтва№!*".
   
   *Ожидаемый результат:* поле остается неизменным, указываемое невалидное значение не попадает в поле ввода карты.
   
   * **Невалидный срок действия карты**
   1. Нажимаем на кнопку "Купить в кредит" на [странице заявки](http://localhost:8080/);
   2. Заполняем поле "номер карты" валидным значением "4444 4444 4444 4441";
   3. Заполняем поля месяца и года так, чтобы срок действия карты уже истёк (например: месяц - "07", год - "21");
   4. Поле владельца заполняем валидным значением - два слова на латинице, допускается только один пробел между именем и фамилией, дефис в имени или фамилии допускается, например: Oleg Olegov;
   5. CVC/CVV код заполняем валидным значением - любыми тремя цифрами (например, 123);
   6. Нажать кнопку "Продолжить".
   
   *Ожидаемый результат:* появляется информационное сообщение, символизирующее о том, что оплата отклонена.
   
   * **Невалидное значение полей месяца и года**
   1. Нажимаем на кнопку "Купить в кредит" на [странице заявки](http://localhost:8080/);
   2. Выбираем поле "Месяц" и заполняем его невалидным значением(например, dfыл);
   3. Выбираем поле "Год" и заполняем его невалидным значением(например, yuшд).
   
   *Ожидаемый результат:* оба поля остаются неизменными.
   
   * **Невалидное значение поля "Владелец"**
   1. Нажимаем на кнопку "Купить в кредит" на [странице заявки](http://localhost:8080/);
   2. Заполняем поле "Владелец" набором символов, состоящим из цифр, символов и кириллицы, например: ено7898%;
   
   *Ожидаемый результат:* поле остается без изменений.
   
   * **Невалидное значение поля CVC/CVV**
   1. Нажимаем на кнопку "Купить в кредит" на [странице заявки](http://localhost:8080/);
   2. Заполняем поле "CVC/CVV" набором символов, состоящим из букв и символов, например, DИ^;
   
   *Ожидаемый результат:* поле остается без изменений.
   
   * **Попытка превысить допустимое количество цифр CVC/CVV кода**
   1. Нажимаем на кнопку "Купить в кредит" на [странице заявки](http://localhost:8080/);
   2. Заполняем поле CVC/CVV четырьмя цифрами, например, 1234;
   
   *Ожидаемый результат:* в поле осталось только 123, 4 не попало в поле.
   

## Перечень используемых инструментов:
1. **IntelliJ IDEA** - для поднятия тестовой системы и написания автотестов на **Java 11**. Эта программа очень удобна для написания кода;
2. Проект будет на базе **Gradle** - он проще, чем Maven, зависимости там подключаются значительно проще;
3. **Selenide** - для тестирования UI части. Он лаконичнее по сравнению с Selenium - не нужны "танцы с бубном" с настройкой веб-драйвера;
4. **Java-faker** будет использоваться для генерации случайных пользователей в автоматическом режиме;
5. **Lombok** - для упрощения кода;
6. Тестовая среда - **JUnit**. Она самая распространенная и понятная.
7. **Docker** и **Docker compose** для подключения к базе данных и получения оттуда сведений;
8. **SQL** - для запросов в базу данных;
9. **Allure** - для генерации понятных отчетов;
10. **Git** и **GitHub** - для коммитов и сдачи проекта.

## Перечень и описание возможных рисков при автоматизации:
1. Нехватка времени из-за занятости на других проектах;
2. Сложность в связывании тестового приложения, базы данных и заглушки через код - гораздо быстрее это будет сделать вручную.

## Интервальная оценка с учётом рисков в часах
**Интервальная оценка с учётом рисков в часах:** 30-35 часов.

## План сдачи работ:
* 31 мая - сдача плана автоматизации тестирования;
* ~ 15 июня - готовность автотестов и результаты их прогонов
# Телеграм бот "Напоминалка" 
Телеграм бот напоминалка. Разовые, ежедневные, еженедельные, ежемесячные и ежегодные напоминания.

@SimpleTaskSchedulerBot

Минус бота: перелив напоминаний в event_cash происходит раз в сутки в 22:15. Т.е. для того, чтоб бот отправил напоминание нужно создать напоминание до 22:15 с временем отправки после 22:15. 

# Запуск бота

1.	Зарегистрировать бота в botFather
2.	Создать приложение на heroku. 
3.	Создать базу на heroku (можно и локальную)
4.	Создать 3 таблички в базе: 

CREATE TABLE users
(
    id               INTEGER PRIMARY KEY UNIQUE NOT NULL,
    name             VARCHAR,
    time_zone        INTEGER DEFAULT 0,
    on_off           BOOLEAN DEFAULT true
);

CREATE TABLE user_events
(
    user_id INTEGER ,
    time timestamp ,
    description varchar ,
    event_id serial,
    event_freq varchar default 'TIME',
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE event_cash
(
    time timestamp ,
    description varchar ,
    user_id INTEGER ,
    id serial
);


5.	Прописать в application.properties (main/resourses/application.properties) следующие параметры для бота: 
-	telegramBot.webHookPath – адрес приложения с heroku
-	telegramBot.userName – Имя бота
-	telegramBot.botToken – Токен бота. 
-	telegrambot.adminId – указать id пользователя telegram. Пользователь с этим id будет получать уведомления о перезагрузке бота, сможет просмотреть все напоминания и всех пользователей воспользовавшихся ботом. 
Все, что ниже можно получить в настройках базы на heroku:
-	spring.datasource.url – путь к базе данных
-	spring.datasource.username – пользователь для подключения к БД
-	spring.datasource.password – пароль для подключения к БД

6. Задеплоить на heroku.

7. Зарегистрировать webhook: 
Заменить в следующем адресе BOT_TOKEN и HEROKU_APP_URL на свой бот токен и url приложения heroku. 

https://api.telegram.org/botBOT_TOKEN/setWebhook?url=HEROKU_APP_URL

# Blogs App
### Задание

Написать реализацию бэкенда для сайта "блогов". Фронт не нужен, только RESTfull бэк. 
На сайте можно:
1. Взаимодействовать с аккаунтом. Аккаунт может иметь много постов и комментов.
2. Взаимодействовать с постами. Пост может иметь много комментариев и одного автора.
3. Комментировать посты. Комментарий имеет одного автора и привязан к одному посту.
Желательно использовать следующие технологии:
1. Spring Boot
2. Lombok
3. Spring Data (JPA2.1/Specifications, особо обратить внимание на проблему N+1 join fetch)
4. Spring Validation
5. H2 база в памяти, создаваемая при старте приложения из DDL
Плюсом будут:
1. Живой сервер и присланные нам исполняемые curl запросы помимо голого кода на github
2. Swagger спецификация.


### Описание

Приложение реализует работу с тремя сущностями: авторы, посты и комментарии. 


### Запуск
`$ mvnw spring-boot:run`
База данных франится в файле [h2.db.mv.db](./h2.db.mv.db) и наполнена некоторыми тестовыми значениями.

### SOAP UI
Для тестов можно использовать soap ui проект [Blogs-soapui-project.xml](Blogs-soapui-project.xml). В нем есть примеры вызова всех операций.

### Описание REST API
#### Authors

1. Создание нового автора (аккаунта)

        POST http://localhost:8080/authors HTTP/1.1
        Тело:
        {
            "name":"sergey"
        }

2. Получение всех авторов

        GET http://localhost:8080/authors HTTP/1.1

3. Получение автора по id
        
        Общий вид:
        GET http://localhost:8080/authors/{id} HTTP/1.1
        Пример:
        GET http://localhost:8080/authors/2 HTTP/1.1

4. Удаление автора по id
        
        Общий вид:
        DELETE http://localhost:8080/authors/{id} HTTP/1.1
        Пример:
        DELETE http://localhost:8080/authors/2 HTTP/1.1
                
5. Обновление данных автора
        
        Общий вид:
        PUT http://localhost:8080/authors/{id} HTTP/1.1
        Пример:
        PUT http://localhost:8080/authors/2 HTTP/1.1
        Тело:
        {
        	"name":"alex"
        }
        
6. Поиск автора по имени автора
        
        POST http://localhost:8080/authors/findByAuthorName HTTP/1.1
        Тело:
        {
        	"name":"alex"
        }
        
#### Posts

1. Создание нового поста

        POST http://localhost:8080/posts HTTP/1.1
        Тело:
        {
        	"authorId":1,
        	"content":"my new post"
        }
        
2. Получение всех постов

        GET http://localhost:8080/posts HTTP/1.1
        
        *Вывод реализован постранично с возможностью указания размера страницы, сортировки. 
        Пример: GET /posts?page=0&size=2&sort=createdAt,desc
        
3. Поиск постов по автору
        
        POST http://localhost:8080/posts/findByAuthorId HTTP/1.1
        
        Тело:
        {
            "authorId":1,
        }
        
4. Удаление поста по id
        
        Общий вид:
        DELETE http://localhost:8080/posts/{id} HTTP/1.1
        Пример:
        DELETE http://localhost:8080/posts/2 HTTP/1.1
                
5. Обновление контента
        
        Общий вид:
        PUT http://localhost:8080/posts/{id} HTTP/1.1
        Пример:
        PUT http://localhost:8080/posts/2 HTTP/1.1
        Тело:
        {
        	"content":"updated content"
        }

#### Comments

1. Создание комментария

        Общий вид:
        POST http://localhost:8080/posts/{postId}/comments HTTP/1.1
        Пример:
        POST http://localhost:8080/posts/1/comments HTTP/1.1
        Тело:
        {
        	"authorId":2,
        	"content":"new comment2"
        }
        
2. Получение комментариев поста

        Общий вид:
        GET http://localhost:8080/posts/{postId}/comments HTTP/1.1
        Пример:
        GET http://localhost:8080/posts/1/comments HTTP/1.1
        
        *Вывод реализован постранично с возможностью указания размера страницы, сортировки. 
        Пример: GET /comments?page=0&size=2&sort=createdAt,desc
        
3. Получение всех комментариев
        
        GET http://localhost:8080/comments HTTP/1.1

4. Получение комментариев автора

        POST http://localhost:8080/comments/findByAuthorId HTTP/1.1
        Тело:
        {
        	"authorId":2	
        }
        
5. Обновление комментария

        PUT http://localhost:8080/comments/1 HTTP/1.1
        Тело
        {
        	"content":"updated comment"
        }                
# CRUDAPP

## УСЛОВИЕ ЗАДАЧИ

Необходимо реализовать консольное CRUD приложение, которое имеет следующие сущности:

Writer (id, firstName, lastName, List<Post> posts)
Post (id, content, created, updated, List<Label> labels)
Label (id, name)
PostStatus (enum ACTIVE, UNDER_REVIEW, DELETED)

В качестве хранилища данных необходимо использовать json файлы:
writers.json, posts.json, labels.json
Пользователь в консоли должен иметь возможность создания, получения, редактирования и удаления данных.
Слои:
model - POJO классы
repository - классы, реализующие доступ к текстовым файлам
controller - обработка запросов от пользователя
view - все данные, необходимые для работы с консолью

Например: Writer, WriterRepository, WriterController, WriterView и т.д.
Для репозиторного слоя желательно использовать базовый интерфейс:
interface GenericRepository<T,ID>

interface WriterRepository extends GenericRepository<Writer, Long>
class JsonWriterRepositoryImpl implements WriterRepository

Результатом выполнения задания должен быть отдельный репозиторий с README.md файлом, который содержит описание задачи, 
проекта и инструкции запуска приложения через командную строку.
Для работы с json необходимо использовать библиотеку Gson. Для импорта зависимостей использовать на выбор Maven/Gradle.

## ИНСТРУКЦИЯ ПО ЗАПУСКУ ПРИЛОЖЕНИЯ ЧЕРЕЗ КОМАНДНУЮ СТРОКУ
Предварительно убедитесь, что на вашем компьютере установлены JVM и JRE. 
1. Скачать программу CRUDAPP.jar по ссылке https://github.com/IreneYanushkevich/CRUDAPP/raw/master/CRUDAPP.jar
2. Вызвать командную строку сочетанием клавиш WIN+R.
3. Прописать в командной строке: cd пробел и путь к папке со скачанной программой + Enter (пример: 
   C:\Users\Irene>cd C:\Users\Irene\IdeaProjects\CRUDAPP\out\artifacts\CRUDAPP_jar)
4. Запустить программу, прописав в командной строке: java -jar CRUDAPP.jar    

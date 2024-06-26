# Лабораторные работу по предмету "Объектно-ориентированные технологии пограммирования и стандарты проетирования" БГУИР, КСиС, ПОИТ, 4 семестр 

## Лабораторная работа №1
### Ознакомление с концепциями ООП: наследование и полиморфизм типов (виртуальные методы)

- Построить иерархию классов для вывода графических фигур (или на другую тема): отрезок, прямоугольник, эллипс и т.д - не менее 6 фигур. 
- Распределить классы по модулям. Создать список фигур в виде отдельного класса. 
- В главном модуле программы добавить в список различные фигуры (статическая инициализация), после чего запустить рисование списка фигур.

## Лабораторная работа №2
### Графический редактор (с использования фабрик)

- Расширить пример с графическими фигурами так, чтобы фигуры можно было создавать на уровне пользовательского интерфейса. Существуют несколько способов: ввод координат с помощью мыши, диалоговый ввод значений, ввод на скриптовом языке. Студент может выбрать любой способ ввода. 
- Создание объекта должно выполняться так, чтобы добавление нового класса в систему не требовало изменения существующего кода (выбор типа с помощью оператора case/switch и множественного if делать нельзя). 
- Получившаяся программа должна представлять собой примитивный графический редактор.
- Классы фигур не должны содержать метод рисования.

## Лабораторная работа №3
### Сериализация объектов

- Реализовать сериализацию/десереализацию объектов из полученной иерархии классов в файл/из файла, формат сериализации выбирается самостоятельно. 
- В пользовательском интерфейсе необходимо реализовать следующие функции:
  - возможность изменять свойства объектов (редактирование);
  - добавлять/удалять объекты из списка;
  - сериализация/десериализация списка объектов.
  - Добавление новых классов в иерархию не должно приводить к необходимости переписать существующий код, и не использовать if-else/switch-case, рефлексию. 
  - Опционально: реализация графический интерфейс.

Варианты форматов сериализации(были выбраны JSON и Binary)

- XML
- Binary
- JSON
- BSON
- Text

# Лабораторная работа №4
## Плагины - иерархия

- На основе 3 лабораторной работы расширить имеющуюся иерархию новыми классами с помощью динамической загрузки модуля (плагина).
- Новые модули должны добавлять или расширять функциональность базовой программы: новый класс в иерархии, функции по работе с ним, новые элементы в пользовательскм интерфейсе для работы с новым классом.
- Загружать модули можно из папки либо посредством строки-параметра в главном модуле с именем нового модуля и возможной перекомпиляцией.
- В идеале добавление нового модуля должно выполняться его динамической загрузкой, т.е. вообще не должно требовать изменения кода программы.
- Сделать подпись плагина с последующей проверкой базовой программой данной подписи на достоверность (время активации и целостность).
- Разработать механизм подписывания плагинов.

# Лабораторная работа №5
## Плагины - функциональность

- На базе предыдущей лабораторной работы (#4) на основе плагинов (2-3 плагина) реализовать возможность обработки структур перед сохранением в файл и после загрузки из файла.
- Тип обработки задается вариантом.
- Дополнительная функциональность должна находиться в меню настроек и зависит от загруженных плагинов.
- Загрузка плагинов производится автоматически из папки, либо выбором файла с плагином через пользовательский интерфейс.
- Предусмотреть дополнительную настройку функциональности плагина в меню настройки плагинов.
- Например, заданием параметров шифрования/архивации, выбор алгоритма шифрования, дополнительные правила трансформации, кодировки и т.д.
- Варианты(3 - обязательно)
1. Трансформация XML данных в JSON
2. Архивация
3. Шифрования/дешифрование
4. Трансформация XML (можно XSLT)

# Лабораторная работа №6     
## Паттерны
- На базе предыдущей лабораторной работы (#5) обменяться с товарищем функциональными плагинами (минимум одним) и адаптировать их в этой же работе помощью паттерна Адаптер (т.е. появятся новые функции от плагина товарища, загруженные через плагин с адаптером).
- Также необходимо реализовать 2 паттерна (любых) в программе, пояснив уместность их использования.

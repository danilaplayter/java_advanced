[![Java CI with Gradle](https://github.com/danilaplayter/java_advanced/actions/workflows/main.yml/badge.svg)](https://github.com/danilaplayter/java_advanced/actions/workflows/main.yml)

# java_advanced

1. User (Пользователь). Основная сущность, представляющая пользователя соцсети.
  1. Атрибуты: userId, userName, email, password, registrationDate, profileInfo.(Все поля должны бать private, потому необходимо добавить get, set)
  2. Методы: createPost(), addComment(), likePost(), editPofile().(Сделайем public)
2. Post (Пост). Запись, созданная пользователем.
  1. Атрибуты: postId, authorId (ссылка на User), content, creationDate, likesCount, commentsCount.(Все поля должны бать private, потому необходимо добавить get, set)
  2. Методы: editContent(), delete(), addLike().(Сделайем public)
3. Comment (Комментарий). Ответ пользователя на пост или другой комментарий.
  1. Атрибуты: commentId, postId (ссылка на Post), authorId (ссылка на User), text, timeStamp.(Все поля должны бать private, потому необходимо добавить get, set)
  2. Методы: editText(), delete(), replyToComment().(Сделайем public, Можно сделать delete() через полиморфизм) 

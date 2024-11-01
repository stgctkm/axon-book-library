# Axon - CQRS with Spring Boot by examples

https://sgitario.github.io/axon-by-example/
https://github.com/Sgitario/axon-getting-started

- Axon UI
  - http:/localhost:8024

## command
- libraryの作成
```shell
curl localhost:8080/api/library \
  -X POST \
  -H "Content-Type: application/json" \
  -d '{"libraryId":1973, "name":"練馬"}' 
```

- bookの登録
```shell
curl localhost:8080/api/library/1973/book \
  -X POST \
  -H "Content-Type: application/json" \
  -d '{"isbn":12346589, "title":"失われた時を求めて", "author": "マルセル・プルースト"}' 
```


## Query
- Bookの取得
```shell
curl localhost:8080/api/library/1973/book
```

- Libraryの取得
```shell
curl localhost:8080/api/library/1973
```
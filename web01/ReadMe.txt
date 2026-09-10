查询所有：
curl -X GET "http://192.168.214.1:8082/addressbook/contactsA"

查询指定的：
curl -X GET "http://192.168.214.1:8082/addressbook/contactsA?id=2"

修改指定的：
curl -X PUT \
-H "Content-Type: application/json" \
-d '{"phone":"18715120834","email":"changzhi233@qq.com"}' \
"http://192.168.214.1:8082/addressbook/contactsA?id=2"

curl -X PUT \
-H "Content-Type: application/json" \
-d '{"name":"寒月シ星邪","phone":"1335609373","email":"hanyue@test.com"}' \
"http://192.168.214.1:8082/addressbook/contactsA?id=1"


插入指定：
curl -X POST \
-H "Content-Type: application/json" \
-d '{"name":"王俊","phone":"13270823668","email":"wj1973279036@163.com"}' \
"http://192.168.214.1:8082/addressbook/contactsA"


删除指定：
curl -X DELETE \
-H "Content-Type: application/json" \
"http://192.168.214.1:8082/addressbook/contactsA?id=1"

# stc-assessment




#### Problem solving

For the problem solving part of the assement there's two solutions one in java and the other one is in golang.
## System design - File system

There's a swagger documentation for the APIs that can be accessed from the broswer using.

```http
  GET /swagger-ui/index.html
```

## API Reference

### Create new space

```http
  Post /space
```

### Sample request
```json
  {
  "name": "stc-assessments",
  "group": {
    "groupName": "admins",
    "users": [
      {
        "email": "edit@stc.com",
        "permission": "EDIT"
      },
      {
        "email": "view@stc.com",
        "permission": "VIEW"
      }
    ]
  }
}
```
### Sample response
```json
{
    "id": "9baafbd7-52ca-43bb-b83c-ca2228c90425",
    "name": "stc-assessments",
    "type": "SPACE",
    "group": {
        "id": "5505291d-5a78-47a6-8bcd-a0d465d4d9d8",
        "groupName": "admins"
    }
}
```

#### Notes
1- Any IDs supplied in the request will be Ignored.

2- Any item type other than SPACE will be Ignored.

3- Groups and users are resued if they are already exists.

4- No two spaces can have the same name.

### Create folder

```http
  Post /parent/{parentId}/folder
```
### Sample request
```json
{
  "name": "backend"
}
```

| Header | Type     | Description                       | example
| :-------- | :------- | :-------------------------------- | :--------------
| `user`      | `string` | **Required**. user email for auth | edit@stc.com |


### Sample response
```json
{
    "id": "25d0309f-1e7f-48e4-b06c-60bcd84b498b",
    "name": "stc-assessments-folder2",
    "type": "FOLDER",
    "group": {
        "id": "769cc161-112d-433a-bc9c-bdc9929cc103",
        "groupName": "admins"
    },
    "parent": {
        "id": "242cbd52-144d-46e1-849a-c4646d95f13e",
        "name": "stc-assessments-folder1",
        "type": "FOLDER",
        "group": {
            "id": "769cc161-112d-433a-bc9c-bdc9929cc103",
            "groupName": "admins"
        },
        "parent": {
            "id": "107bbb6e-c396-4881-a485-dbf16aa69678",
            "name": "stc-assessments",
            "type": "SPACE",
            "group": {
                "id": "769cc161-112d-433a-bc9c-bdc9929cc103",
                "groupName": "admins"
            }
        }
    }
}
```

#### Notes
1- Any field other than the folder name will Igonred.

2- User header must be supplied with a user email with edit access. 

3- No two folders in the same space can have the same name.

### Upload file

```http
  Post /parent/{parentId}/file
```

### Sample request

Request is multipart form with "file" as the key.

| Header | Type     | Description                       | example
| :-------- | :------- | :-------------------------------- | :--------------
| `user`      | `string` | **Required**. user email for auth | edit@stc.com |

### Sample response
```json
{
    "id": "74cd5828-516c-4e4c-aba1-d30e011a34c7",
    "name": "v8_context_snapshot.bin",
    "type": "FILE",
    "group": {
        "id": "769cc161-112d-433a-bc9c-bdc9929cc103",
        "groupName": "admins"
    },
    "parent": {
        "id": "eca63d15-1fc8-4b16-84e3-66d7b3bbd53b",
        "name": "mySecondfolder",
        "type": "FOLDER",
        "group": {
            "id": "769cc161-112d-433a-bc9c-bdc9929cc103",
            "groupName": "admins"
        },
        "parent": {
            "id": "3afe2ce5-d7e8-4354-9b35-350c415b1396",
            "name": "myfolder",
            "type": "FOLDER",
            "group": {
                "id": "769cc161-112d-433a-bc9c-bdc9929cc103",
                "groupName": "admins"
            },
            "parent": {
                "id": "4f5a82a7-9ebe-4dd1-829d-437ad5128e2c",
                "name": "myspace",
                "type": "SPACE",
                "group": {
                    "id": "769cc161-112d-433a-bc9c-bdc9929cc103",
                    "groupName": "admins"
                }
            }
        }
    }
}
```

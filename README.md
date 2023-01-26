
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
        "groupName": "admins",
        "users": [
            {
                "id": "9f823e3c-5c82-46cf-8ece-0c00ae23d560",
                "email": "edit@stc.com",
                "permission": "EDIT"
            },
            {
                "id": "aeaea104-69b5-495b-8672-d2d1d35f4a73",
                "email": "view@stc.com",
                "permission": "VIEW"
            }
        ]
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
  Post /space/{spaceName}/folder
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
    "id": "1d5aed49-5fd8-47cb-aa16-b9e10747e0b9",
    "name": "backend",
    "type": "FOLDER",
    "parent": {
        "id": "9baafbd7-52ca-43bb-b83c-ca2228c90425",
        "name": "stc-assessments",
        "type": "SPACE",
        "group": {
            "id": "5505291d-5a78-47a6-8bcd-a0d465d4d9d8",
            "groupName": "admins",
            "users": [
                {
                    "id": "9f823e3c-5c82-46cf-8ece-0c00ae23d560",
                    "email": "edit@stc.com",
                    "permission": "EDIT"
                },
                {
                    "id": "aeaea104-69b5-495b-8672-d2d1d35f4a73",
                    "email": "view@stc.com",
                    "permission": "VIEW"
                }
            ]
        },
    }
}
```

#### Notes
1- Any field other than the folder name will Igonred.

2- User header must be supplied with a user email with edit access. 

3- No two folders in the same space can have the same name.

### Upload file

```http
  Post /space/{spaceName}/folder/{folderName}/file
```

### Sample request

Request is multipart form with "file" as the key.

| Header | Type     | Description                       | example
| :-------- | :------- | :-------------------------------- | :--------------
| `user`      | `string` | **Required**. user email for auth | edit@stc.com |

### Sample response
```json
{
    "id": "8e14fb8b-c6af-4bae-b16b-9b6024c997cd",
    "name": "Squirrel-UpdateSelf.log",
    "type": "FILE",
    "parent": {
        "id": "91de6656-484d-41a0-8309-90c894c1cde8",
        "name": "aaaaaaaaaaaaa",
        "type": "FOLDER",
        "parent": {
            "id": "b559c651-bc5d-42a4-ab44-6dcab414ad34",
            "name": "stc-assessments",
            "type": "SPACE",
            "group": {
                "id": "5505291d-5a78-47a6-8bcd-a0d465d4d9d8",
                "groupName": "admins",
                "users": [
                    {
                        "id": "9f823e3c-5c82-46cf-8ece-0c00ae23d560",
                        "email": "edit@stc.com",
                        "permission": "EDIT"
                    },
                    {
                        "id": "aeaea104-69b5-495b-8672-d2d1d35f4a73",
                        "email": "view@stc.com",
                        "permission": "VIEW"
                    }
                ]
            }
       }
    }
}
```

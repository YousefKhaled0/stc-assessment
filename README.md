
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
  Post /items/spaces
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
    "id": "7d75724d-d975-496f-bcde-934d3e701783",
    "name": "stc-assessments",
    "type": "SPACE",
    "group": {
        "id": "5ea119bb-352a-4c00-a32b-bd2742fb3327",
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
  Post /items/{itemId}/folders
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
    "id": "ee70a114-8633-425a-b767-4a5b7b25951d",
    "name": "stc-assessments-folder-2",
    "type": "FOLDER",
    "group": {
        "id": "5ea119bb-352a-4c00-a32b-bd2742fb3327",
        "groupName": "admins"
    },
    "parent": {
        "id": "626f427e-a158-41b1-9392-75ccc8cb869f",
        "name": "stc-assessments-folder-1",
        "type": "FOLDER",
        "group": {
            "id": "5ea119bb-352a-4c00-a32b-bd2742fb3327",
            "groupName": "admins"
        },
        "parent": {
            "id": "7d75724d-d975-496f-bcde-934d3e701783",
            "name": "stc-assessments",
            "type": "SPACE",
            "group": {
                "id": "5ea119bb-352a-4c00-a32b-bd2742fb3327",
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
  Post /items/{itemId}/files
```

### Sample request

Request is multipart form with "file" as the key.

| Header | Type     | Description                       | example
| :-------- | :------- | :-------------------------------- | :--------------
| `user`      | `string` | **Required**. user email for auth | edit@stc.com |

### Sample response
```json
{
    "id": "56793332-a4cf-4196-b24f-777824077648",
    "name": "chrome_100_percent.pak",
    "type": "FILE",
    "group": {
        "id": "5ea119bb-352a-4c00-a32b-bd2742fb3327",
        "groupName": "admins"
    },
    "parent": {
        "id": "ee70a114-8633-425a-b767-4a5b7b25951d",
        "name": "stc-assessments-folder-2",
        "type": "FOLDER",
        "group": {
            "id": "5ea119bb-352a-4c00-a32b-bd2742fb3327",
            "groupName": "admins"
        },
        "parent": {
            "id": "626f427e-a158-41b1-9392-75ccc8cb869f",
            "name": "stc-assessments-folder-1",
            "type": "FOLDER",
            "group": {
                "id": "5ea119bb-352a-4c00-a32b-bd2742fb3327",
                "groupName": "admins"
            },
            "parent": {
                "id": "7d75724d-d975-496f-bcde-934d3e701783",
                "name": "stc-assessments",
                "type": "SPACE",
                "group": {
                    "id": "5ea119bb-352a-4c00-a32b-bd2742fb3327",
                    "groupName": "admins"
                }
            }
        }
    }
}
```
### Get file metadata

```http
  Post /files/{fileId}
```

### Sample request

| Header | Type     | Description                       | example
| :-------- | :------- | :-------------------------------- | :--------------
| `user`      | `string` | **Required**. user email for auth | view@stc.com |

### Sample response
```json
{
    "id": "56793332-a4cf-4196-b24f-777824077648",
    "name": "chrome_100_percent.pak",
    "type": "FILE",
    "group": {
        "id": "5ea119bb-352a-4c00-a32b-bd2742fb3327",
        "groupName": "admins"
    },
    "parent": {
        "id": "ee70a114-8633-425a-b767-4a5b7b25951d",
        "name": "stc-assessments-folder-2",
        "type": "FOLDER",
        "group": {
            "id": "5ea119bb-352a-4c00-a32b-bd2742fb3327",
            "groupName": "admins"
        },
        "parent": {
            "id": "626f427e-a158-41b1-9392-75ccc8cb869f",
            "name": "stc-assessments-folder-1",
            "type": "FOLDER",
            "group": {
                "id": "5ea119bb-352a-4c00-a32b-bd2742fb3327",
                "groupName": "admins"
            },
            "parent": {
                "id": "7d75724d-d975-496f-bcde-934d3e701783",
                "name": "stc-assessments",
                "type": "SPACE",
                "group": {
                    "id": "5ea119bb-352a-4c00-a32b-bd2742fb3327",
                    "groupName": "admins"
                }
            }
        }
    }
}
```

### Download file

```http
  Get /files/{fileId}/download
```

| Header | Type     | Description                       | example
| :-------- | :------- | :-------------------------------- | :--------------
| `user`      | `string` | **Required**. user email for auth | view@stc.com |


### Run Locally

Clone the project

```bash
  git clone https://github.com/YousefKhaled0/stc-assessment.git
```

Go to the project directory

```bash
  cd 'stc-assessment\System design\file-system\'
  docker-compose up
```

Visit http://localhost:8080/swagger-ui/index.html to check if the server is running.

Book management application

Create book request example:

curl --location --request POST 'http://localhost:8080/api/saveBook' \
--header 'Content-Type: application/json' \
--data-raw '{
    "title": "title",
    "annotation": "annot",
    "authorsDtos": [
        {
            "id": 1
        }
    ]
}'

Create book response example: 

{
    "id": 3,
    "title": "title",
    "yearPublishing": null,
    "annotation": "annot",
    "authorsDtos": [
        {
            "id": 1,
            "firstName": null,
            "lastName": null,
            "yearOfBirth": null
        }
    ]
}
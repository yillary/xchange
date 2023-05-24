# **xchange Design Doc**

**xchange is all about cutting down on textile waste by exchanging clothes with someone else. Anytime you get bored of what's in your closet, see what's in a friend's!**

___________
## What Our Users Want

**Member:** *someone who has an account.*

**Visitor:** *someone who is visiting the site, isn't logged in*

- Make an account
- Members have a dashboard to see all their listings simultaneously
- Members can create a listing
- Members can update listings' fields
- Members can mark listing as exchanged from the dashboard
- Visitors can search listings by type
- Visitors can search listings by zip code and type and keywords in description
- Visitors can view a single item listing
- Visitors can email owner of item

## Stretch Goals
- Integrate Maps to location page. 
- In home screen search bar, have revolving search suggestions populated
- Create more ways to search and sort/filter results
- Update member's zipcode 

-------------
## Data Tables

### members Table
| Attribute | Description |
|-----------|-------------|
| member_Id | String, key |
| listings  | String Set  | 
| zip_Code  | String      |

### items Table
| Attribute   | Description       |
|-------------|-------------------|
| item_Id     | String, partition |
| title       | String            | 
| description | String            |
| type        | String            |
| exchanged   | Boolean           | 
| images      | String Set        |
| zip_Code    | String            |


### search_by_type_zip GSI
| Attribute | Description   |
|-----------|---------------|
| zip_Code  | String, key   |
| type      | String, range |


## API Endpoints
| Name                    | HTTP Request                              | Returns                                               | Description                                               | Exception thrown . . .   | . . . When this occurs                             |
|-------------------------|-------------------------------------------|-------------------------------------------------------|-----------------------------------------------------------|--------------------------|----------------------------------------------------|
| GetItem                 | **GET** request to /items/{id}            | All the attributes of single item                     | Retrieves an Item from the items table.                   | ItemNotFoundException    | A valid request was made, but item doesn't exist   | 
| UpdateItem              | **PUT** request to /items/{id}            | The updated item and attributes                       | Updates the item in the items table except id.            | InvalidArgumentException | Invalid request is made.                           |
| CreateItem              | **POST** request to /items                | Boolean true                                          | Create a new item in the items table.                     | ItemTitleException       | A title exceeds allowed number of characters.      | 
| InactiveItem            | **PUT** request to /items/{id}/exchanged  | Boolean true                                          | Updates exchanged boolean of the item in the items table. | ItemNotFoundException    | A valid request was made, but item doesn't exist   |
| AddItemToMemberListings | **PUT** request to /members/{id}          | Boolean true                                          | Adds item to member's listings.                           | MemberNotFoundException  | A valid request was made, but member doesn't exist | 
| GetMemberItems          | **GET** request to /members/{id}/listings | Retrieves all of member's listings and all attributes | Updates the item in the items table.                      | MemberNotFoundException  | A valid request was made, but member doesn't exist |
| UpdateMember            | **PUT** request to /members/{id}          | The updated Member and attributes                     | Updates member fields except id.                          | MemberNotFoundException  | A valid request was made, but member doesn't exist | 
| CreateMember            | **POST** request to /members/             | Boolean true                                          | Creates the member in the members table.                  |                          |                                                    |
| SearchItems             | **GET** request to /items/search          | Items found or none                                   | Retrieves items from the table of items.                  |                          |                                                    |


## UML Diagrams

Class Diagram
: Refer to xchange > resources > **class.puml**

Sequence Diagram
: Refer to xchage > resources > **sequence.puml**

## Front End Mockup

[View Mockup](https://www.canva.com/design/DAFjBILOsTw/qcSympJgDloXb7YyiXnesA/edit?utm_content=DAFjBILOsTw&utm_campaign=designshare&utm_medium=link2&utm_source=sharebutton)

[View Website Diagram](https://www.canva.com/design/DAFjAzpV-W8/cSoeyuu61ewwA2j63ujnZw/edit?utm_content=DAFjAzpV-W8&utm_campaign=designshare&utm_medium=link2&utm_source=sharebutton)
## AWS Services
CloudFormation, S3, DynamoDB, Cognito, CloudWatch

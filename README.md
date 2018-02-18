Implementation of Sample Rest Based API's of MarketPlace for Self Employed.

Time taken for Building the Application (Excluding Dev Setup): 4.5 hours

Exercise Difficulty: Moderate

How did you feel about the exercise itself : 9

How do you feel about coding an exercise as a step in the interview process: 9

What would you change in the exercise and/or process : Good as is.


Technology Stack: Java 1.8, Spring Boot with JAXRS-Jersey and Mongo DB
Build Tool: Maven

Steps to Run the Application
1- The Application bootstraps and connects to Mongo DB on host and Port localhost:27017 by default, It can be changed using application.properties.
2- Run "mvn spring-boot:run"
3- The System creates Sample Users for Test Purposes



Details for Testing the API's below:

1-Creating Project

URL: http://localhost.corp.apple.com:8080/api/users/<user-Id>/projects
Method : HTTP POST
Sample Request
{
  "name": "Test Project",
  "requirements": "Test Project Requirements",
  "budget": 110.5,
  "bidOpenDate": "02/18/2018 13:21:00 PST",
  "bidCloseDate": "02/25/2018 11:40:00 PST",
  "expectedDelivery": "03/20/2018 11:40:00 PST"
}


2-Get Project Based on Project ID
URL: http://localhost.corp.apple.com:8080/api/projects/<project-Id>
Method: HTTP GET
Sample Response
{
    "createdTimeStamp": "02/18/2018 21:20:51 UTC",
    "lastModifiedTimeStamp": "02/18/2018 21:20:51 UTC",
    "projectId": "PR-BOJPAL",
    "name": "Test Project",
    "requirements": "Test Project Requirements",
    "budget": 110.5,
    "bidOpenDate": "02/18/2018 21:21:00 UTC",
    "bidCloseDate": "02/25/2018 19:40:00 UTC",
    "expectedDelivery": "03/20/2018 19:40:00 UTC",
    "userId": "USR-OLVYFD",
    "bestBid": {
        "summary": "Project Plan Details",
        "createdTimeStamp": "02/18/2018 22:37:08 UTC",
        "lastModifiedTimeStamp": "02/18/2018 22:37:08 UTC",
        "bidId": "BID-CKUL40",
        "bidPrice": 85.5,
        "userId": "USR-5RLJOH",
        "projectId": "PR-BOJPAL"
    }
}



3-Bid for a Project
URL: http://localhost.corp.apple.com:8080/api/users/<user-Id>/projects/<project-id>/bids
Method : HTTP POST
Sample Request
{

	"bidPrice":95.50,
	"summary":"Project Plan Details"
}


4-Get All Open Projects
URL: http://localhost.corp.apple.com:8080/api/projects?offset=0&limit=1
Method : HTTP GET
Sample Response
{
    "projects": [
        {
            "createdTimeStamp": "02/18/2018 21:20:51 UTC",
            "lastModifiedTimeStamp": "02/18/2018 21:20:51 UTC",
            "projectId": "PR-BOJPAL",
            "name": "Test Project",
            "requirements": "Test Project Requirements",
            "budget": 110.5,
            "bidOpenDate": "02/18/2018 21:21:00 UTC",
            "bidCloseDate": "02/25/2018 19:40:00 UTC",
            "expectedDelivery": "03/20/2018 19:40:00 UTC",
            "userId": "USR-OLVYFD"
        }
    ],
    "records_found": 1,
    "offset": 0,
    "limit": 1
}

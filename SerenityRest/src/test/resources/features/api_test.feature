#languaje: en

Feature: Test an API with Serenity Screenplay Rest


#https://reqres.in/api/users?page=2
  @GETMethod
  Scenario: GET Method Test
    Given that the user wants to make a request
    When he makes the request, using the GET method
    Then the service return the data


#https://reqres.in/api/register
  @POSTMethod1
  Scenario: Register a new user
    Given that the user wants to make a request
    When he wants to register a new user, using the POST method1
    Then the user is registered correctly


  #https://reqres.in/api/register
   #https://reqres.in/api/users
  #https://reqres.in/api/login
  @POSTMethod2
  Scenario: POST Method Test
    Given that the user wants to make a request
    When he makes the request, using the POST method2
    Then a valid response is returned
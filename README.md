# cuurrencyRateNotifier
## General
  Java Spring Api set up on Spring Boot. App keeps track of currency rates and notifies users via mail if rate exceed given level.
  Uses spring-qurtz to asynchronously  track currency rates from remote Api. App stores Users and their notifications objects that         represents async tracking tasks in db. Also readings of currency rate are stored in MySQL db. App uses notifications from db on start   up to automatically run saved tasks. Tasks in db are sychronized with those in quartz scheduler, that means for example if you delete   notification from   db via endpoint, it will unschedule task also.  Notification tasks works until they are removed from db. If you     turn off app and then turn on, they will be automatically run again. 

## Endpoints:  
  [POST]  
  /api/users/  
   - example json:  
   {  
      "firstName": "John",  
      "lastName" : "Doe",  
      "email" : "example@gmail.com"  
   }
   email: user's email recives notifications about currency rates  
   - registers user to db.  
     
     
   [POST]  
   /api/notifications/  
   - example json:  
   {  
     "userId": "1",  
     "frequency" : "10",  
     "currency" : "EUR",  
     "currencyVal" : "5.1"  
   }  
   "userdId": id of user to whom notification will be assigned,  
   "frequency: how often currency rate is checked by scheduler. For example 10 means app will fetch data from api in 10 hour interval.  
   "currency": currency that is tracked by this task,  
   "currencyVal": if this value is exceeded, app sends aler email to user on given email.  
   - Saves notification task to db mapped to user with given id. Runs task after it is added to db. App fetches info about given            currency form remote api, then compares it with given currencyVal. If actual value is higher than currency val sends alert mail.
   All fetched data is stored in db mapped to notification task that fetched it.    
   
   [DELETE]  
   /api/notifications/{notificationId}  
   - deleteS notification from db, and delete releted task.
    
   [GET] 
   /api/notifications/  
   - returns json representing all running tasks.  
   
   [GET] 
   /api/users/{userId}   
   - returns json representing all user who has given userId. Json contains tasks assigned to user and   readings that belongs to that      tasks.
   
   ## Testing with postman
  - Download app
  - run on your ide (preferably Intellij)
  - create mysql db on your pc. Then modify connection url in application.properties
  - modify application.properties settings related with smtp.You need to get acces to some smtp server(for example smtp.gmail.com).
  - use endpoints in postman
  
  ## Upcoming changes:
  - tests addition.
  - User authentication
   
   
   

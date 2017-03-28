# config-service

  For the sake of simplicity, the current implementation uses Spring Boot's default Database - H2. This allows for rapid development of   REST apis without worrying about a database schema. It is in memory database hence REST call data will be available only in the      current execution session.

<b>Save App Config</b>
- - - -
Add new or update existing JSON document for specified appCode and version

* URL<br>
  /api/{appCode}/config/{version}

* POST

* URL Params<br>
  Required:<br>
  appCode=[String]<br>
  version=[String]

* Data Params<br>
  appConfigJson = [JSON]

* Success Response: <br>
  Code: 200<br>
  Response: Successfully created new JSON Config document
  
<b>Get App Config</b>
- - - -
Return JSON document for specified appCode and version

* URL<br>
/api/{appCode}/config/{version}

* GET

* URL Params<br>
  Required:<br>
  appCode=[String]<br>
  version=[String]

* Success Response: <br>
  Code: 200<br>
  Content: [JSON] <br>
  
* Error Response: <br>
  Code: 404 <br>
  Response: <br>
  [{<br>
    "logref": {appCode},<br>
    "message": "JSON Config not found for appcode : {appCode}, version : {version}",<br>
    "links": []<br>
  }]<br>
  
<b>Get list of available versions</b>
- - - -
Return list of available versions in JSON sorted by last modified date in descending order

* URL<br>
api/{appCode}/config

* GET

* URL Params
  Required:<br>
  appCode=[String]<br>
  version=[String]

* Success Response: <br>
  Code: 200<br>
  Content: [JSON] <br>
  
* Error Response: <br>
  Code: 404 <br>
  Response: <br>
  [{<br>
    "logref": {appCode},<br>
    "message": "JSON Config not found for appcode : {appCode}, version : {version}",<br>
    "links": []<br>
  }]<br>


Tools and Technologies Used :

1)JDK 1.8.0_51
2)Apache Tomcat 8.0.24
3)Spring 3.0.5.RELEASE
4)Hibernate 3.3.2.GA
5)H2 InMemory Database 1.2.126
6)Maven


Following Columns are added to the 'People' Table : ID, FIRSTNAME, LASTNAME, STREETADDRESS, CITY, STATE, COUNTRY, PRIMARYPHONE, PRIMARYEMAIL


For Spring MVC Web Application:

http://localhost:8080/UptakeWebApplication/index - This will bring up the Home Page for the Spring MVC Web Application and it is pretty much self explanatory after that.



For Spring MVC Rest Application, JSON Format is used for the input and output.

REST Endpoints :

http://localhost:8080/UptakeWebApplication/list - Listing all the Peoples in the table.

http://localhost:8080/UptakeWebApplication/create - For adding a new Person to table. Input should be in JSON Format. Following is an example input.
{"firstName":"Vaibhav","lastName":"Malhotra","streetAddress":"1824 North Lincoln Park West","city":"Chicago","state":"Illinois","country":"USA","primaryPhone":"3129531635","primaryEmail":"vaibhav.malhotra0509@gmail.com"}

http://localhost:8080/UptakeWebApplication/get/{id} - For retrieving a specific person from table with the given id.

http://localhost:8080/UptakeWebApplication/update/{id} - For updating a specific person from table with the given id. Input should be in JSON Format. Following is an example input.
{"firstName":"Rohan","lastName":"Joshi","primaryPhone":"4572671625","primaryEmail":"rohan.joshi@gmail.com"}

http://localhost:8080/UptakeWebApplication/delete/{id} - For deleting a specific person from table with the given id.

http://localhost:8080/UptakeWebApplication/search/{searchString} - For searching the table rows for a particular substring.

http://localhost:8080/UptakeWebApplication/createFamilyFromExistingPeople/{familyName} - For creating new Family from existing people in the table. Input should be in JSON Format. Following is an example input.
[1,2,4,5,11]

http://localhost:8080/UptakeWebApplication/createFamilyFromNewPeople/{familyName}  - For creating new Family from new people. Input should be in JSON Format. Following is an example input.
[{"firstName":"Abhishek","lastName":"Arora","streetAddress":"1824 North Lincoln Park West","city":"Chicago","state":"Illinois","country":"USA","primaryPhone":"3129531635","primaryEmail":"abhishek.arora@gmail.com"},{"firstName":"Anju","lastName":"Arora","streetAddress":"8/1, Block No. 1, Govind Nagar","city":"Kanpur","state":"U.P","country":"India","primaryPhone":"9919972204","primaryEmail":"anju.arora@gmail.com"}, {"firstName":"Inder","lastName":"Arora","streetAddress":"8/1, Block No. 1, Govind Nagar","city":"Kanpur","state":"U.P","country":"India","primaryPhone":"9919972204","primaryEmail":"inder.arora@gmail.com"}]   

http://localhost:8080/UptakeWebApplication/addExistingPeopleToFamily/{familyName} - For adding existing people to an existing Family. Input should be in JSON Format. Following is an example input.
[5,8,9]

http://localhost:8080/UptakeWebApplication/addNewPeopleToFamily/{familyName} - For adding new people to an existing Family. Input should be in JSON Format. Following is an example input.
[{"firstName":"Saksham","lastName":"Malhotra","streetAddress":"1824 North Lincoln Park West","city":"Chicago","state":"Illinois","country":"USA","primaryPhone":"3129531635","primaryEmail":"saksham.malhotra@gmail.com"},{"firstName":"Preeti","lastName":"Khanna","streetAddress":"8/1, Block No. 1, Govind Nagar","city":"Kanpur","state":"U.P","country":"India","primaryPhone":"9919972204","primaryEmail":"preeti.khanna@gmail.com"}]

http://localhost:8080/UptakeWebApplication/getPeopleFromFamily/{familyName} - For retrieving all people of a Family.   

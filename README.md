# SampleMoviesBackEnd

Steps to set up environment:

1. Open IntelliJ
2. Import project to IntelliJ
3. Make sure to have XAMPP
4. Go to your localhost dashboard and create a new Database
5. Name your Database to 'movies'
6. Go to db.txt to create the tables needed.
7. Add users or movies using POSTMAN with the url /api/users/add and /api/movies/add respectively. Check entities of
   Movie and User to set the fields for adding. Put the users or movies as a Request Body and as a JSON when adding. Example
   Request Body:
   {
     "name" : "Godzilla",
     "director" : "Zack Zns",
     "description" : "Very big animal"
  }
  
 8. Add users first before adding movies because you need an apiKey to add movies. 
 
 -All endpoints need apiKey except for adding users. Check your localhost database to check for the apiKey of each users. 
 
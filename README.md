An application written in Clojure ,using SQLlite database for displaying,inserting data, and for data manipulation.
This application allows users to  log in  or register themselves.
When user is logged in, he/she can post his/hers cocktail recipe. 
If user wants, he/she can determine which recipes are most similar to his/hers recipes.
A user who does not register can only look at cocktails with the best rating, or search through base by cocktail ingredient.


#Requirements

The application requires Leiningen 2.0.0 or later

#Usage

To start application, open command line, and navigate to the application folder.
Then run 
lein ring server
If the command is successfully executed, the ring server should be up and running on port 3000 (by default).
The application can then be viewed on http://localhost:3000/
if the user wants to log into the application he/she should navigate through menu on tab login, 
or type http://localhost:8080/login in browser address bar.

#Goal

The aim of this project was to learn Clojure programming language, which is very different from object -oriented languages. 
Also, the goal was also to learn how to make systems recommendations application.

#References

Bootstrap templates: http://startbootstrap.com/template-categories/all/
Library for rendering HTML in Clojure: http://weavejester.github.io/hiccup/
Clojure wrapper for JDBC-based access to databases.: https://github.com/clojure/java.jdbc

License

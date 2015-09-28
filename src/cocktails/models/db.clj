(ns cocktails.models.db
  (:require [clojure.java.jdbc :as sql])
  (:import java.sql.DriverManager))


(def db {:classname "org.sqlite.JDBC",
         :subprotocol "sqlite",
         :subname "master-cocktails.db"})

(defn list-cocktails
   "Returns a 20 best rated recepies from the database"
  []
    (sql/query db ["SELECT * FROM `cocktails` order by rating desc LIMIT 20"]))

(defn save-cocktail [name ingredient ingredient1 ingredient2 rating text]
  (sql/insert! db
      :cocktails
      [:name :ingredient :ingredient1 :ingredient2 :rating :text ]
      [ name ingredient ingredient1 ingredient2 rating text]))

(defn select-specific-cocktail
  "Returns records related to the input ingrediant"
  [ingredient]
  (sql/query db  [(str "SELECT * FROM `cocktails` WHERE `ingredient` = '" ingredient "'")] :result-set-fn first))


(defn list-ingredient
  "Returns 10 ingredients best rated recepies from the database"
  []
   (sql/query db
    ["SELECT ingredient FROM cocktails WHERE rating>9 LIMIT 10"]
    ))


(defn login
  "Function wich checks whether the user exists"
  [UserName Pass]
    (sql/query db
      ["select count(UserName) as total
       from users
       where UserName = ? and Pass = ? " UserName Pass]
      :result-set-fn first))

(defn save-user [UserName Pass ]
  (sql/insert! db
       :users
      [:UserName :Pass ]
      [ UserName Pass]))

(defn users-list
   "Returns users from the database"
  []
    (sql/query db ["SELECT * FROM `users`"]))





(defn user-recipes
  "Returns recipes for specific user"
  [IdUser]
  (sql/query db [(str "select * from recipe WHERE IdUser = '" IdUser "'")]))

(user-recipes 1)


(defn Id-user-recipes
  "Returns recipes for specific user"
  [IdUser]
  (sql/query db [(str "select IdCocktail from recipe WHERE IdUser = '" IdUser "'")]))

(Id-user-recipes 1)

(defn user-cocktails-ingredient
   "Returns list of ingredient for certain cocktail"
  [IdCocktail]
  (sql/query db [(str "select ingredient,ingredient1,ingredient2,ingredient3, ingredient4 from cocktails WHERE IdCocktail = '" IdCocktail "'")]))


(user-cocktails-ingredient 4)

(defn user-cocktails-ingredient1
   "Returns list of ingredient for all cocktails for certain User"
  [IdUser]
  (sql/query db ["select ingredient,ingredient1,ingredient2,ingredient3, ingredient4 from cocktails WHERE IdCocktail in
                      (select IdCocktail from recipe WHERE IdUser =?) order by Rating desc" IdUser]))

(user-cocktails-ingredient1 1)
(user-cocktails-ingredient1 2)





(defn user-intersection
  "Function which checks similar recipe for User2 and User1; IdUser1-User2-mutually-recipes"
  [IdUser1 IdUser2]
  (sql/query db [(str "Select * from recipe where IdCocktail
                        IN (SELECT IdCocktail from recipe where IdUser = '" IdUser1 "') and IdUser = '" IdUser2 "'")]))

( user-intersection 2 1)

(defn  user-union
  "Unija recepta dva korisnika; tj unija broja recepta"
  [IdUser1 IdUser2]
  (sql/query db [(str "select COUNT(DISTINCT IdCocktail) from recipe where IdUser='" IdUser1 "' or IdUser = '" IdUser2 "'")]))


(user-union 1 2)

(defn Jaccard-similarity-coeficient
 [IdUser1 IdUser2]
  (/ (user-intersection IdUser1 IdUser2) (user-union IdUser1 IdUser2)))


(jaccard-index 1 2)


;; (defn similar-recepies
;;   [IdCocktail]
;;   (sql/query db [(str "Select IdCocktail ")]))










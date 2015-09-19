(ns cocktails.models.db
  (:require [clojure.java.jdbc :as sql])
  (:import java.sql.DriverManager))


(def db {:classname "org.sqlite.JDBC",
         :subprotocol "sqlite",
         :subname "master-cocktails.db"})




(defn list-cocktails
   "Returns a 20 best rated recepies from the database"
  []
    (sql/with-connection
    db
    (sql/with-query-results res
    ["SELECT * FROM `cocktails` order by rating desc LIMIT 20"]
    (doall res))))

(defn save-user [UserName Pass ]
  (sql/with-connection
    db
      (sql/insert-values
      :users
      [:UserName :Pass ]
      [ UserName Pass])))


(defn list-ingredient
  "Returns 10 ingredients best rated recepies from the database"
  []
    (sql/with-connection
    db
    (sql/with-query-results res
    ["SELECT ingredient FROM cocktails WHERE rating>9 LIMIT 10"]
    (doall res))))


(defn select-specific-cocktail
  "Returns records related to the input ingrediant"
  [ingredient]
  (sql/with-query-results res [(str "SELECT * FROM `cocktails` WHERE `ingredient` = '" ingredient "'")]))


(defn login
  "Function wich checks whether the user exists"
  [UserName Pass]
    (sql/with-connection db
      (sql/with-query-results res
      ["select count(UserName) as total
       from users
       where UserName = ? and Pass = ? " UserName Pass]
      :result-set-fn first)))

(defn save-cocktail [name ingredient ingredient1 ingredient2 rating text]
  (sql/with-connection
    db
      (sql/insert-values
      :cocktails
      [:name :ingredient :ingredient1 :ingredient2 :rating :text ]
      [ name ingredient ingredient1 ingredient2 rating text])))

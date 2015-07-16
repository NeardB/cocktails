(ns cocktails.db
  (:require [clojure.java.jdbc :as sql]
            [hiccup.page :as hic-p])
  (:import java.sql.DriverManager))

(def db
  {:classname   "org.sqlite.JDBC"
   :subprotocol "sqlite"
   :subname     "master-cocktails.db"})


(defn list-cocktails
  "Returns a 20 best rated recepies from the database"
  []
  (let [results (sql/query db
      ["SELECT * FROM `cocktails` order by rating desc LIMIT 20"])]
    results))




(defn list-ingredient
  "Returns 10 ingredients best rated recepies from the database"
  []
  (let [results (sql/query db
      ["SELECT ingredient FROM cocktails WHERE rating>9 LIMIT 10"])]
    results))


(defn select-specific-cocktail
  "Returns records related to the input ingrediant"
  [ingredient]
  (sql/query db [(str "SELECT * FROM `cocktails` WHERE `ingredient` = '" ingredient "'")]))



(defn insert-record
  "insert function"
  [UserName Pass]
  (sql/query db [(str "INSERT INTO users (UserName, Pass) VALUES ('" UserName "','" Pass "')")]));



(defn select-specific-user
  "Returns records related to the input ingrediant"
  [UserName Pass]
  (sql/query db [(str "SELECT * FROM users WHERE `UserName` = '" UserName "'")]))

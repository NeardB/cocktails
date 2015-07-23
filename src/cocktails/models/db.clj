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


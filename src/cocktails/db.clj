(ns cocktails.db
  (:require [clojure.java.jdbc :as sql]
            [hiccup.page :as hic-p]))

(def db {:classname "com.mysql.jdbc.Driver"
         :subprotocol "mysql"
         :subname "//localhost:3306/master_cockails"
         :user "root"
         :password ""})



;; (defn list-cocktails
;;   "Returns a list of all the users from the database"
;;   []
;;   (let [results (sql/query db
;;       ["select * from test LIMIT 1000"])]
;;     results))

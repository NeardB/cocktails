(ns cocktails.db
  (:require [clojure.java.jdbc :as sql]
            [hiccup.page :as hic-p]))

(def db {:classname "com.mysql.jdbc.Driver"
         :subprotocol "mysql"
         :subname "//localhost:3306/master_cockails"
         :user "root"
         :password ""})



(defn list-cocktails
  "Returns a 20 best rated recepies from the database"
  []
  (let [results (sql/query db
      ["SELECT * FROM `cocktails` order by rating desc LIMIT 20"])]
    results))



<<<<<<< HEAD
(defn list-ingredient
  "Returns 10 ingredients best rated recepies from the database"
  []
  (let [results (sql/query db
      ["SELECT `ingredient` FROM `cocktails` WHERE `rating`= 10 LIMIT 10"])]
    results))
=======

>>>>>>> 4ca4e4a4ad005607584a35f02d35c05c8cc0e7af


(defn select-specific-cocktail
  "Returns records related to the input ingrediant"
  [ingredient]
  (sql/query db [(str "SELECT * FROM `cocktails` WHERE `ingredient` = '" ingredient "'")]))
<<<<<<< HEAD


;; (defn recommended-songs-HTML
;;   [songList]
;;   (hic-p/html5
;;     [:h2 "Recommended songs"]
;;      [:table {:class "table"}
;;       [:tr [:th "song"] [:th "score"]]
;;       (for [item songList]
;;        [:tr [:td (:idsong item)] [:td (:score item)]])]
;;   ))
=======
>>>>>>> 4ca4e4a4ad005607584a35f02d35c05c8cc0e7af

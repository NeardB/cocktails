
(ns cocktails.models.db
  (:require [clojure.java.jdbc :as sql]
            [clojure.string :as string])
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
  "Returns records related to the selected ingrediant from combobox"
  [ingredient]
  (sql/query db  [(str "SELECT * FROM `cocktails` WHERE `ingredient` = '" ingredient "'")] :result-set-fn first))


(defn list-ingredient
  "Returns 10 ingredients best rated recepies from the database for the combobox on the page"
  []
   (sql/query db
    ["SELECT ingredient FROM cocktails WHERE rating>9 LIMIT 10"]))

(list-ingredient)


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
   "Returns all users from the database"
  []
    (sql/query db ["SELECT * FROM `users`"]))

(users-list)
(defn user-recipes-list
  "Returns 10 best rated cocktail recipes for specific user"
  [IdUser]
  (sql/query db ["SELECT IdCocktail FROM recipe where IdUser like ? LIMIT 10" IdUser]))

(user-recipes-list 1)


(defn cocktail-list
  "Returns all cocktail IdCocktail from database"
  []
  (sql/query db ["SELECT IdCocktail FROM cocktails order by rating desc "]))

(cocktail-list)


(defn ingredient-cocktail-list
  "Returns cocktail ingredient from database"
  []
  (sql/query db ["SELECT * from cocktails order by rating desc"]))

(ingredient-cocktail-list)


(defn ingredient-cocktail-list1
  "Returns cocktail ingredient from database"
  [IdCocktail]
  (sql/query db ["SELECT * from cocktails where IdCocktail = ?" IdCocktail]))

(defn Id-user-recipes
  "Returns recipes for specific user"
  [IdUser]
  (sql/query db ["select IdCocktail from recipe WHERE IdUser = ?" IdUser ]))

(Id-user-recipes 1)



(defn user-ingredient-cocktail-list
  " All cocktails for one |User"
  [IdUser]
  (sql/query db ["select * from  recipe where IdUser =?" IdUser]))


(defn similarity-coeficient
"Coefficient that determines which cocktails are similar and how similar. It returns -1 if there is not similarity,
  and returns 1 if they are complitlly similar"
  [IdUser]
  (let [list-ingrediant1 (user-ingredient-cocktail-list IdUser)
       list-ingrediant2 (ingredient-cocktail-list )]
    (frequencies(flatten
    (for [list1 list-ingrediant1]
      (for [list2 list-ingrediant2]
        (let [x list-ingrediant2](str (:idcocktail x))
        (cond
         (=(:ingredient list1)(:ingredient list2)) 1/5
         (or (=(:ingredient list1)(:ingredient list2)) (=(:ingredient1 list1)(:ingredient1 list2))) 2/5
         (or (=(:ingredient list1)(:ingredient list2))
              (=(:ingredient1 list1)(:ingredient1 list2))(=(:ingredient2 list1)(:ingredient2 list2))) 3/5

          (or (=(:ingredient list1)(:ingredient list2)) (=(:ingredient1 list1)(:ingredient1 list2))
               (=(:ingredient2 list1)(:ingredient2 list2)) (=(:ingredient3 list1)(:ingredient3 list2))) 4/5

          (and (=(:ingredient list1)(:ingredient list2)) (=(:ingredient1 list1)(:ingredient1 list2))
               (=(:ingredient2 list1)(:ingredient2 list2)) (=(:ingredient3 list1)(:ingredient3 list2))
               (=(:ingredient4 list1)(:ingredient4 list2)) (=(:ingredient5 list1)(:ingredient5 list2))) 1
         :else -1))  ))))))

(similarity-coeficient 9)



(defn recommended-cocktail
  "Returns 10 most similar cocktail from database to user"
  [IdUser]
  (let [similar-cocktail-list (similarity-coeficient IdUser)]
  (sql/query db ["select * from cocktail where IdCocktail in  ("
               (string/join "," (into [] (for [x similar-cocktail-list] (str (:idcocktail x)))))")
                order by Rating desc limit 10"])))





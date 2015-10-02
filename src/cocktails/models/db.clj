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
  "Returns records related to the input ingrediant"
  [ingredient]
  (sql/query db  [(str "SELECT * FROM `cocktails` WHERE `ingredient` = '" ingredient "'")] :result-set-fn first))


(defn list-ingredient
  "Returns 10 ingredients best rated recepies from the database"
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





(defn user-recipes-list
  "Returns 10 best rated cocktail recipes for specific user"
  [IdUser]
  (sql/query db ["SELECT IdCocktail FROM recipe where IdUser like ? LIMIT 10" IdUser]))

(user-recipes-list 1)


;; svi sastojci svih koktela odredjenog usera
(defn ingrediant-list
  [IdUser]
  (let [ingrediant-list (user-recipes-list IdUser)]
;;         (for [x ingrediant-list]
;;           (:idcocktail x))

      (sql/query db ["select ingredient,ingredient1,ingredient2,
                   ingredient3, ingredient4 from cocktails WHERE
                     IdCocktail in ("
               (string/join "," (into []
                    (for [x ingrediant-list] (str (:idcocktail x)))  )) ")"])))


(defn cocktail-list
  "Returns all cocktail IdCocktail from database"
  []
  (sql/query db ["SELECT IdCocktail FROM cocktails order by rating desc "]))

(cocktail-list)

(defn ingredient-cocktail-list
  "Returns cocktail ingredient from database"
  []
  (sql/query db ["SELECT ingredient,ingredient1,ingredient2,ingredient3,
                 ingredient4 from cocktails order by rating desc"]))

(ingredient-cocktail-list)

;; (let [ingrediant-list (user-recipes 2)]
;;         (for [x ingrediant-list]
;;           (:idcocktail x)))



(defn Id-user-recipes
  "Returns recipes for specific user"
  [IdUser]
  (sql/query db [(str "select IdCocktail from recipe WHERE IdUser = '" IdUser "'")]))

(Id-user-recipes 1)

(defn user-cocktails-ingredient
   "Returns list of ingredient for certain cocktail"
  [IdCocktail]
  (sql/query db [(str "select ingredient,ingredient1,ingredient2,ingredient3,
                      ingredient4 from cocktails WHERE IdCocktail = '" IdCocktail "'")]))


(user-cocktails-ingredient 4)

(defn user-cocktails-ingredient1
   "Returns list of ingredient for all cocktails for certain User"
  [IdUser]
  (sql/query db ["select ingredient,ingredient1,ingredient2,ingredient3, ingredient4 from cocktails WHERE IdCocktail in
                      (select IdCocktail from recipe WHERE IdUser =?) order by Rating desc" IdUser]))

(user-cocktails-ingredient1 1)
(user-cocktails-ingredient1 2)





(defn similarity-coeficient
  "Coefficient that determines which cocktails are similar and how similar. It returns -1 if there is not similarity. "
  [IdUser]
  (let [list-ingrediant1 (user-cocktails-ingredient1 IdUser)
       list-ingrediant2 (user-cocktails-ingredient 1)]

    (for [list1 list-ingrediant1]
      (for [list2 list-ingrediant2]
        (cond
         (=(:ingredient list1)(:ingredient list2)) 1/5
         (and (=(:ingredient list1)(:ingredient list2)) (=(:ingredient1 list1)(:ingredient1 list2))) 2/5
         (and (=(:ingredient list1)(:ingredient list2))
              (=(:ingredient1 list1)(:ingredient1 list2))(=(:ingredient2 list1)(:ingredient2 list2))) 3/5

          (and (=(:ingredient list1)(:ingredient list2)) (=(:ingredient1 list1)(:ingredient1 list2))
               (=(:ingredient2 list1)(:ingredient2 list2)) (=(:ingredient3 list1)(:ingredient3 list2))) 4/5

          (and (=(:ingredient list1)(:ingredient list2)) (=(:ingredient1 list1)(:ingredient1 list2))
               (=(:ingredient2 list1)(:ingredient2 list2)) (=(:ingredient3 list1)(:ingredient3 list2))
               (=(:ingredient4 list1)(:ingredient4 list2))) 1
         :else -1)))))

(similarity-coeficient 2)

(frequencies  (similarity-coeficient 2))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;










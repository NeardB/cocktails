(ns cocktails.routes.addCocktail
  (:require [compojure.core :refer :all]
            [cocktails.views.layout :as layout]
            [hiccup.form :refer :all]
            [cocktails.models.db :as db]
            [noir.session :as session]
            [cocktails.routes.helper :as helper]
            ;;validacija
            [noir.validation :refer [rule errors? has-value? on-error]]
            ;;redirect
            [noir.response :refer [redirect]]))


(defn addCocktail [& [success]]
  (layout/common
   (helper/navbar)
    (helper/header "Add new recipie " "Insert your recepie here!" "img/a.jpg")
   [:div.container
    [:div.row
     [:div.col-lg-8.col-lg-offset-2.col-md-10.col-md-offset-1
      (cond
       (has-value? success)
        [:div.alert.alert-success success])
      [:form#Form {:action "addCocktail" :method "POST" :novalidate ""}

       [:div.row.control-group
        [:div.form-group.col-xs-12.floating-label-form-group.controls
         [:label "Name"]
         [:input.form-control {:type "text" :id "name" :name "name" :placeholder "name" :required "required" :data-validation-required-message "Please enter name."}]
         [:p.help-block.text-danger]]]

        [:div.row.control-group
        [:div.form-group.col-xs-12.floating-label-form-group.controls
         [:label "Ingredient"]
         [:input.form-control {:type "text" :id "ingredient" :name "ingredient" :placeholder "ingredient" :required "required" :data-validation-required-message "Please enter at lest one ingrediant."}]
         [:p.help-block.text-danger]]]

        [:div.row.control-group
        [:div.form-group.col-xs-12.floating-label-form-group.controls
         [:label "Ingredient "]
         [:input.form-control {:type "text" :id "ingredient1" :name "ingredient1" :placeholder "ingredient" }]
         [:p.help-block.text-danger]]]

        [:div.row.control-group
        [:div.form-group.col-xs-12.floating-label-form-group.controls
         [:label "ingredient "]
         [:input.form-control {:type "text" :id "ingredient2" :name "ingredient2" :placeholder "ingredient" }]
         [:p.help-block.text-danger]]]

       [:div.row.control-group
        [:div.form-group.col-xs-12.floating-label-form-group.controls
         [:label "Text"]
         [:input.form-control {:type "text" :id "text" :name "text" :placeholder "text" :required "required" :data-validation-required-message "Please recipie text."}]
         [:p.help-block.text-danger]]]

       [:div.row.control-group
        [:div.form-group.col-xs-12.floating-label-form-group.controls
         [:label "rating"]
         [:input.form-control {:type "rating" :id "rating" :name "rating" :placeholder "rating" :required "required" :data-validation-required-message "Please rate this recipie"}]
         [:p.help-block.text-danger]]]

       [:br]
       [:div#success]
       [:div.row
        [:div.form-group.col-xs-12.floating-label-form-group.controls
         [:button.btn.btn-default.submit.add "Add new cocktail!"]]]
       ]

      ]

     ]]

   ))




(defn add-new-cocktail [name ingredient ingredient1 ingredient2 rating text]
  (db/save-cocktail name ingredient ingredient1 ingredient2 rating text)
  (addCocktail "You added your recepie!")
  )

(defroutes addCocktail-routes
  (GET "/addCocktail" []

      ;; (addNew-page)
       (addCocktail)
       )
  (POST "/addCocktail" [name ingredient ingredient1 ingredient2 rating text] (add-new-cocktail name ingredient ingredient1 ingredient2 rating text))
  )

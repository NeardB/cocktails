(ns blog.routes.addNew
  (:require [compojure.core :refer :all]
            [blog.views.layout :as layout]
            [hiccup.form :refer :all]
            [blog.models.db :as db]
            [noir.session :as session]
            [blog.routes.helper :as helper]
            ;;validacija
            [noir.validation :refer [rule errors? has-value? on-error]]
            ;;redirect
            [noir.response :refer [redirect]]))


(defn addNew-page [& [success]]
  (layout/common
   (navbar)
   (helper/header "Admin Panel" "Add your awesome Blog" "img/bg.jpg")
   [:div.container
    [:div.row
     [:div.col-lg-8.col-lg-offset-2.col-md-10.col-md-offset-1
      (cond
       (has-value? success)
        [:div.alert.alert-success success])
      [:form#BlogForm {:action "addNew" :method "POST" :novalidate ""}
       [:div.row.control-group
        [:div.form-group.col-xs-12.floating-label-form-group.controls
         [:label "Username "]
         [:input.form-control {:type "text" :id "UserName" :name "UserName" :placeholder "Username" :required "required" :data-validation-required-message "Please enter username."}]
         [:p.help-block.text-danger]]]
       [:div.row.control-group
        [:div.form-group.col-xs-12.floating-label-form-group.controls
         [:label "Pass"]
         [:input.form-control {:type "text" :id "Pass" :name "Pass" :placeholder "Pass" :required "required" :data-validation-required-message "Please enter Pass."}]
         [:p.help-block.text-danger]]]

       [:br]
       [:div#success]
       [:div.row
        [:div.form-group.col-xs-12.floating-label-form-group.controls
         [:button.btn.btn-default.submit "Add new user!"]]]
       ]

      ]

     ]]

   ))


(defn add-new-user [UserName Pass]
  (db/save-user UserName Pass)
  (addNew-page "You added new user!")
  )

(defroutes addNew-routes
  (GET "/addNew" []

       (addNew-page))
  (POST "/addNew" [UserName  Pass] (add-new-user UserName Pass))

(ns cocktails.routes.register
  (:require [compojure.core :refer :all]
            [cocktails.views.layout :as layout]
            [hiccup.form :refer :all]
            [cocktails.models.db :as db]
            [noir.session :as session]
            [cocktails.routes.shared :as shared]
            ;;validacija
            [noir.validation :refer [rule errors? has-value? on-error]]
            ;;redirect
            [noir.response :refer [redirect]]))


(defn addNew [& [success]]
  (layout/common

  [:div {:class "jumbotron page-header text-center lead"}[:h3 "Register here"]]

   [:div.container
    [:div.row
     [:div.col-lg-8.col-lg-offset-2
      (cond
       (has-value? success)
        [:div.alert.alert-success success])
      [:form#Form {:action "addNew" :method "POST" :novalidate ""}
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
       [:div.row.control-group
        [:div.form-group.col-xs-12.floating-label-form-group.controls
         [:label "Repeate Pass"]
         [:input.form-control {:type "text" :id "PassRep" :name "PassRep" :placeholder "Repeate Pass" :required "required" :data-validation-required-message "Please repeate Pass."}]
         [:p.help-block.text-danger]]]

       [:br]
       [:div#success]
       [:div.row
        [:div.form-group.col-xs-12.floating-label-form-group.controls
         [:button.btn.btn-default.submit.add "Register!"]]]
       ]]]]

   ))




(defn add-new-user [UserName Pass]
  (db/save-user UserName Pass)
  (addNew "You successful register!")
  )

(defroutes register-routes
  (GET "/addNew" []

      ;; (addNew-page)
       (addNew)
       )
  (POST "/addNew" [UserName  Pass] (add-new-user UserName Pass))
  )

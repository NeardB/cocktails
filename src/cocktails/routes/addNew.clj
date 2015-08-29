(ns cocktails.routes.addNew
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


(defn addNew [& [success]]
  (layout/common
   (helper/navbar)
    (helper/header "About " "This is what I do" "img/a.jpg")
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
         [:button.btn.btn-default.submit.add "Add new user!"]]]
       ]

      ]

     ]]

   ))


;; (defn addNew []
;;   (layout/common
;;     (helper/navbar)
;;     (helper/header "About " "This is what I do" "img/a.jpg")
;;   ;;main
;;   [:div.container
;;     [:div.row
;;      [:div.col-lg-8.col-lg-offset-2.col-md-10.col-md-offset-1
;;       (helper/about-content)]]]

;;     [:footer
;;      [:div.container
;;       [:div.row
;;        [:div.col-lg-8.col-lg-offset-2.col-md-10.col-md-offset-1
;;         [:ul.list-inline.text-center
;;          (helper/social-button)]
;;         [:p.copyright.text-muted "Copyright &copy; www.cocktails.bojana"]]]]]
;;      )

;;     )

(defn add-new-user [UserName Pass]
  (db/save-user UserName Pass)
  (addNew "You added new user!")
  )

(defroutes addNew-routes
  (GET "/addNew" []

      ;; (addNew-page)
       (addNew)
       )
 ;; (POST "/addNew" [UserName  Pass] (add-new-user UserName Pass))
  )

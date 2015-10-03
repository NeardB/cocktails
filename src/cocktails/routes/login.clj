(ns cocktails.routes.login
  (:require [compojure.core :refer [defroutes GET POST]]
            ;;[cocktails.views.admin :as admin]
            [cocktails.views.layout :as layout]
            [hiccup.form :refer
            [form-to label text-field password-field submit-button]]
            [noir.response :refer [redirect]]
            [noir.session :as session]

            [cocktails.routes.shared :as shared]
            ;;validacija
            [noir.validation :refer [rule errors? has-value? on-error]]
            ;;enkripcija
            [noir.util.crypt :as crypt]
            ;;baza
            [cocktails.models.db :as db]
             [cocktails.routes.top20 :as top20]
            [cocktails.routes.search :as search]
            [hiccup.page :as hic-p]))


(defn login-page
  "Function which generates login-page"
  []
  (layout/common
   ;; (hic-p/include-js "/login.js")
;; (gen-page-head "Login")
   [:div {:class "col-md-4"}]
   [:div {:class "login  col-md-4"}
    [:form {:method "POST" :action "login"}
     [:h2 "Login"]
     [:div {:class "form-group has-feedback"}
     [:label {:class "control-label"}]
     [:input {:type "text" :name "UserName" :class "form-control " :required "required" :placeholder "User Name"}]
     [:i {:class "glyphicon glyphicon-user form-control-feedback"}]]
     [:div {:class "form-group has-feedback"}
     [:input {:type "password" :name "Pass" :class "form-control" :placeholder "Password"}]
     [:i {:class "glyphicon glyphicon-movk form-control-feedback "}]]
     [:form {:action "/login"}
     [:button.btn.btn-info.submit.login {:style "margin-bottom: 10px" :id "btn-login"} "Login" ]]]
     [:p [:i "Need an account? Register now!"]]
     [:form {:action "/addNew"}
     [:button.btn.btn-primary.submit "Register" ]]
     [:div {:class "col-md-4"}]]))



(defroutes login-routes

   (GET "/login"
       []
       (login-page))

 (POST "/login"
       [UserName Pass]
        (let [countUser (db/login UserName Pass)]
       (if (> (:total countUser) 0)
         (top20/top20)
         (search/search))))

  )


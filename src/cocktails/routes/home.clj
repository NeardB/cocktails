(ns cocktails.routes.home
  (:require [compojure.core :refer :all]
            [cocktails.views.layout :as layout]
            [cocktails.views.layoutH :as layoutH]
            [hiccup.form :refer :all]
            [cocktails.models.db :as db]
            [noir.session :as session]
            ;;validacija
            [noir.validation :refer [rule errors? has-value? on-error]]

            [cocktails.routes.helper :as helper]))



(defn home [& [flag]]
(layoutH/common
    (helper/navbar)
    (helper/home-header)))



(defroutes home-routes
  (GET "/" [] (home))

  )



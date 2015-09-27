(ns cocktails.routes.home
  (:require [compojure.core :refer :all]
            [cocktails.views.layout :as layout]
            [cocktails.views.homeLayout :as homeLayout]
            [hiccup.form :refer :all]
            [cocktails.models.db :as db]
            [noir.session :as session]
          ;validacija
            [noir.validation :refer [rule errors? has-value? on-error]]

            [cocktails.routes.shared :as shared]))



(defn home [& [flag]]
(homeLayout/common
    (shared/navbar)
    (shared/home-header)))



(defroutes home-routes
  (GET "/" [] (home))

  )



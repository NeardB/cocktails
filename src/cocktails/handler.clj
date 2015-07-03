(ns cocktails.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [compojure.handler :as handler]
           ;; [cocktails.db as db]
            [cocktails.views :as views]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defroutes app-routes
  (GET "/"
       []
       (views/home-page))
  (GET "/all-cocktails"
      []
      (views/all-cocktails-page))
  ;;(GET "/similar-recipes"
    ;;   []
      ;; (views/similar-recipes-page))



  (GET "/search-recipes"
      []
      (views/search-recipes-page))
  (GET "/login"
       []
       (views/login-page))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))

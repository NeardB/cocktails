(ns cocktails.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [compojure.handler :as handler]
           ;; [cocktails.db as db]
            [cocktails.views :as views]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
;;             [ring.middleware.anti-forgery]
;;             [ring.middleware.session]
            ))

;; (use 'ring.middleware.anti-forgery
;;      'ring.middleware.session)




(defroutes app-routes
  (GET "/"
       []
       (views/home-page))

  (GET "/all-cocktails"
      []
      (views/all-cocktails-page))

  (POST "/ajaxcall"
        {params :params}
        (views/cocktails-ingridiant-list params))


  (GET "/search-recipes"
      []
      (views/search-recipes-page))

  (GET "/login"
       []
       (views/login-page))

   (GET "/register"
       []
       (views/register-page))

  (POST "/register-user"
       {params :params}
       (views/register-user params))

   (POST "/check-user-login"
       {params :params}
       (views/check-user-login params))

  (route/resources "/")
  (route/not-found "Not Found"))

;; (defn get-custom-token [request]
;;   (get-in request [:headers "x-forgery-token"]))

;; (def app
;;   (-> handler
;;       (wrap-anti-forgery {:read-token get-custom-token})
;;       ))

  (def app
  (wrap-defaults app-routes nil))



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
  (route/resources "/")
  (route/not-found "Not Found"))

  (def app
  (wrap-defaults app-routes site-defaults))


  ;;(GET "/similar-recipes"
    ;;   []
      ;; (views/similar-recipes-page))

;;  (GET "/loginBojana"
;;       []
;;       (views/loginBojana)
;;       )

;;   (GET "/admin" []
;;        (if (= "admin" (session/get :user))
;;        (admin-page)
;;        (redirect "/login")))
;;  ;; (POST "/admin" [title subtitle content] (add-blog title subtitle content))
;;   (GET "/logout" [] (logout))


;;   (defn logout []
;;   (session/clear!)
;;   (redirect "/"))

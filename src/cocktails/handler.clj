(ns cocktails.handler
  (:require[compojure.core :refer [defroutes routes]]
            [ring.middleware.resource :refer [wrap-resource]]
            [ring.middleware.file-info :refer [wrap-file-info]]
            [hiccup.middleware :refer [wrap-base-url]]
            [compojure.handler :as handler]
            [compojure.route :as route]
            ;;routes
            [cocktails.routes.home :refer [home-routes]]
            [cocktails.routes.search :refer [search-routes]]
            [cocktails.routes.top20 :refer [top20-routes]]
            [cocktails.routes.addCocktail :refer [addCocktail-routes]]
            [cocktails.routes.login :refer [login-routes]]
            [cocktails.routes.register :refer [register-routes]]
            [cocktails.routes.recommendation :refer [recommendation-routes]]
            ;;session
            [noir.session :as session]
            [ring.middleware.session.memory :refer [memory-store]]
            ;;model
            [cocktails.models.db :as db]
            ;;validacija
            [noir.validation :refer [wrap-noir-validation]]))

(defroutes app-routes
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (->
   (handler/site
   (routes
      search-routes
       recommendation-routes
       home-routes
       top20-routes
       addCocktail-routes
       login-routes
       register-routes
       app-routes
    ))
    (session/wrap-noir-session
     {:store (memory-store)})
   (wrap-noir-validation)))

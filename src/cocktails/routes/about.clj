(ns cocktails.routes.about
  (:require [compojure.core :refer [defroutes GET POST]]
            [compojure.core :refer :all]
            [cocktails.views.layout :as layout]
            [hiccup.form :refer :all]
            [noir.session :as session]
            [cocktails.routes.helper :as helper]))


(defn about []
  (layout/common
    (helper/navbar)
    (helper/header "About " "This is what I do" "img/a.jpg")
  ;;main
  [:div.container
    [:div.row
     [:div.col-lg-8.col-lg-offset-2.col-md-10.col-md-offset-1
      (helper/about-content)]]]

    [:footer
     [:div.container
      [:div.row
       [:div.col-lg-8.col-lg-offset-2.col-md-10.col-md-offset-1
        [:ul.list-inline.text-center
         (helper/social-button)]
        [:p.copyright.text-muted "Copyright &copy; www.cocktails.bojana"]]]]]
     )

    )

(defroutes about-routes
  (GET "/about" [] (about))
)

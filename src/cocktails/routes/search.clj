(ns cocktails.routes.search
  (:require [compojure.core :refer [defroutes GET POST]]
            [compojure.core :refer :all]
            [cocktails.views.layout :as layout]
            [cocktails.models.db :as db]
            [hiccup.form :refer :all]
            [noir.session :as session]
            [hiccup.page :as hic-p]
            [cocktails.routes.shared :as shared]))



(defn search []
  (layout/common
    (shared/navbar)
    (shared/header " " "Search" "img/a.jpg")

  [:div.container
    [:div.row
     [:div.col-lg-8.col-lg-offset-2.col-md-10.col-md-offset-1
      (shared/search-content)
(let [all (db/list-ingredient)]
       [:div {:class "well carousel-search hidden-sm " :id "combobox"}
   [:div {:class "btn-group"} [:a {:class "btn btn-default dropdown-toggle btn-select" :data-toggle "dropdown" :href "#"} "Select ingrediant"
                             [:span {:class "caret"}]]
  [:ul {:class "dropdown-menu" }
 (for [loc all]

      [:li [:a {:href "#" }(:ingredient loc)]])
      [:li {:class "divider"}]
      [:li [:a {:href "#"} [:span {:class "glyphicon glyphicon-star"}] "Other"]]
   ]]])

 [:form { :method "POST"  :action "/search" :class "form"}
      ;;[:input  {:type "submit" :class "btn btn-info btn-md " :value  "Search" :id "search"}
      ;; [:span {:class "glyphicon glyphicon-search"}]]
  [:a {:href "#" :class "btn btn-info btn-md " :id "search" } [:span {:class "glyphicon glyphicon-search"}] "Search"]

  ]

      [:div {:id "div1"} [:div {:id "loaderdiv" :class "windows8" :style "display: none;"}]]
      ]]]

    [:footer
     [:div.container
      [:div.row
       [:div.col-lg-8.col-lg-offset-2.col-md-10.col-md-offset-1
        [:ul.list-inline.text-center
         (shared/social-button)]
        [:p.copyright.text-muted "Copyright &copy; www.cocktails.bojana"]]]]]
     ))

(defroutes search-routes
  (GET "/search" [] (search))
  ;;(POST "/search" [ingredient] (select-specific-cocktail ingredient))
)

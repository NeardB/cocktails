(ns cocktails.routes.top20
  (:require ;;[compojure.core :refer [defroutes GET POST]]
            [compojure.core :refer :all]
            [cocktails.views.layout :as layout]
            [cocktails.views.homeLayout :as homeLayout]
            [hiccup.form :refer :all]
         ;;   [noir.session :as session]
            [cocktails.routes.shared :as shared]
            [cocktails.models.db :as db]
            [hiccup.page :as hic-p]
            ))





(defn top20 []
  (layout/common
    (shared/navbar)
    (shared/header "Cocktails" "Here are top 20 cocktails" "img/g.jpg")

   [:div.container
    [:div.row
     [:div.col-lg-8.col-lg-offset-2.col-md-10.col-md-offset-1
    (let [all-locs (db/list-cocktails)]
     [:div {:class "containerTable"}
     [:h1 "Top 20 cocktails"]
     [:table {:class "table"}
      [:tr [:th "Name"] [:th "Rating"] [:th "Votes"] ]
      (for [loc all-locs]
        [:tr [:td (:name loc)] [:td (:rating loc)] [:td (:votes loc)]])]])]]]

    [:footer
     [:div.container
      [:div.row
       [:div.col-lg-8.col-lg-offset-2
        [:ul.text-center
         (shared/social-button)]
        [:p.copyright "Copyright &copy; www.cocktails.rs"]]]]]
     ))




(defroutes top20-routes
  (GET "/top20" [] (top20))
)

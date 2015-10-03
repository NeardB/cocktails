(ns cocktails.routes.recommendation
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


(defn recommendation []
  (layout/common
    (shared/navbar)
    (shared/header "Recommendation" "These recipes are similar to yours" "img/g.jpg")

   [:div.container
    [:div.row
     [:div.col-lg-8.col-lg-offset-2.col-md-10.col-md-offset-1
    (let [all-locs (db/recommended-cocktail)]
     [:div {:class "containerTable"}
     [:h1 "Recommended cocktails"]
     [:table {:class "table"}
      [:tr [:th "Name"] [:th "Rating"] [:th "Votes"] [:th "Ingridient"] [:th "Ingridient"][:th "Ingridient"][:th "Ingridient"]]
      (for [loc all-locs]
        [:tr [:td (:name loc)] [:td (:rating loc)] [:td (:votes loc)][:td (:ingredient loc)][:td (:ingredient1 loc)][:td (:ingredient2 loc)] [:td (:ingredient3 loc)]])]]
      )

      ]]]

    [:footer
     [:div.container
      [:div.row
       [:div.col-lg-8.col-lg-offset-2
        [:ul.text-center
         (shared/social-button)]
        [:p.copyright "Copyright &copy; www.cocktails.rs"]]]]]
     ))




(defroutes recommendation-routes
  (GET "/recommendation" [] (recommendation))
)

(ns cocktails.routes.top20
  (:require [compojure.core :refer [defroutes GET POST]]
            [compojure.core :refer :all]
            [cocktails.views.layout :as layout]
             [cocktails.views.layoutH :as layoutH]
            [hiccup.form :refer :all]
            [noir.session :as session]
            [cocktails.routes.helper :as helper]
            [cocktails.models.db :as db]
             [hiccup.page :as hic-p]
            ))





(defn top20 []
  (layout/common
    (helper/navbar)
    (helper/header "Cocktails" "" "img/g.jpg")

   [:div.container
    [:div.row
     [:div.col-lg-8.col-lg-offset-2.col-md-10.col-md-offset-1
(let [all-locs (db/list-cocktails)]
;;     (hic-p/html5
     [:div {:class "containerTable"}
     [:h1 "Top 20 cocktails"]
     [:table {:class "table"}
      [:tr [:th "Name"] [:th "Rating"] [:th "Votes"] ]
      (for [loc all-locs]
        [:tr [:td (:name loc)] [:td (:rating loc)] [:td (:votes loc)]])]])]]]
    [:hr]

    [:footer
     [:div.container
      [:div.row
       [:div.col-lg-8.col-lg-offset-2.col-md-10.col-md-offset-1
        [:ul.list-inline.text-center
         (helper/social-button)]
        [:p.copyright.text-muted "Copyright &copy; www.cocktails.bojana"]]]]]
     )

   )





;;      [:div {:class "containerTable"}
;;      [:h1 "Top 20 cocktails"]
;;      [:table {:class "table"}
;;       [:tr [:th "Name"] [:th "Rating"] [:th "Votes"] ]
;;       (for [loc all-locs]
;;         [:tr [:td (:name loc)] [:td (:rating loc)] [:td (:votes loc)]])]]))
;;       ]]

;;     [:footer
;;      [:div.container
;;       [:div.row
;;        [:div.col-lg-8.col-lg-offset-2.col-md-10.col-md-offset-1
;;         [:ul.list-inline.text-center
;;          (helper/social-button)]
;;         [:p.copyright.text-muted "Copyright &copy; www.cocktails.bojana"]]]]]
;;      )

;;     )




;; (defn all-cocktails-page
;;   "Function which generates cocktails-page"
;;   []
;;   (let [all-locs (db/list-cocktails)]
;;     (hic-p/html5
;;      (gen-page-head "Top 20 cocktails")
;;      navbar
;;      [:div {:class "containerTable"}
;;      [:h1 "Top 20 cocktails"]
;;      [:table {:class "table"}
;;       [:tr [:th "Name"] [:th "Rating"] [:th "Votes"] ]
;;       (for [loc all-locs]
;;         [:tr [:td (:name loc)] [:td (:rating loc)] [:td (:votes loc)]])]])))



(defroutes top20-routes
  (GET "/top20" [] (top20))
)

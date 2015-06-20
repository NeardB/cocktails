(ns cocktails.views
(:require
            [cocktails.db :as db]
            [clojure.string :as str]
            [hiccup.page :as hic-p]

          ))

(defn gen-page-head
  "Generates page headers"
  [title]
  [:head
   [:title title]
   (hic-p/include-css "/bootstrap.min.css")
   (hic-p/include-js "https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js")
   (hic-p/include-js "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js")
   (hic-p/include-css "/style.css")
   ])




(def navbar
  "Generates a navbar "
  [:nav {:class " -default -fixed-top"}
   [:div {:class "container"}
   [:div {:class "-header"}
   [:button {:type "button" :class "-toggle collapsed" :dat-target "#"}
    [:span {:class "sr-only"}]
    [:span {:class "icon-bar"}]
    [:span {:class "icon-bar"}]
    [:span {:class "icon-bar"}]]
    [:a {:class "-brand" :href "#"} [:img {:src "/clojure-icon.gif" :style "height: 30px; float: left; margin-right: 5px"}] "Clojure"]]
    [:div {:id "" :class "collapse -collapse"}
    [:ul {:class "nav -nav"}
     [:li [:a {:href "/"} "Home"]]
     [:li [:a {:href "/all-cocktails"} "View all recipes "]]
     [:li [:a {:href "/similar-recipes"} "View recommended recipes "]]
     [:li [:a {:href "/search-recipes"} "Search recipes "]]
     [:li [:a {:href "/login"} "Log in "]]]]]])

(defn home-page
  "Function which generates home-page"
  []
  (hic-p/html5
   (gen-page-head "Home")
   navbar
   [:h1 "Home"]
   [:p "Wellcome."]))


(defn login-page
  "Function which generates login-page"
  []
  (hic-p/html5
   (gen-page-head "login")
  [:div {:class "OmotacLogina"}
   [:h1 "Login"]

   ]))






;; (defn all-cocktails-page
;;   "Function which generates cocktails-page"
;;   []
;;   (let [all-locs (db/list-cocktails)]
;;     (hic-p/html5
;;      (gen-page-head "Kokteli")
;;      navbar
;;      [:div {:class "container"}
;;      [:h1 "All kokteli"]
;;      [:table {:class "table"}
;;       [:tr [:th "id"] [:th "ime"] ]
;;       (for [loc all-locs]
;;         [:tr [:td (:id loc)] [:td (:ime loc)]])]])))

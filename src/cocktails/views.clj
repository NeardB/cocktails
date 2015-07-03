(ns cocktails.views
(:require
            [cocktails.db :as db]
            [clojure.string :as str]
            [hiccup.page :as hic-p]
          ;;  [clojure.data.json :as json]
            [net.cgrand.enlive-html :as html]
          ))

(defn gen-page-head
  "Generates page headers"
  [title]
  [:head
   [:title title]

   (hic-p/include-js "https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js")
   (hic-p/include-css "/style.css")
   (hic-p/include-css "bootstrap/css/bootstrap.min.css")
   (hic-p/include-js "bootstrap/js/bootstrap.min.js")
   (hic-p/include-js "/ajax.js")

   ])



(def navbar
  "Generates a navbar "
  [:nav {:class "navbar navbar-default navbar-fixed-top"}
   [:div {:class "container"}
   [:div {:class "navbar-header"}
    [:button {:type "button" :class "navbar-toggle collapsed" :dat-target "#navbar"}    [:span {:class "sr-only"}]
    [:span {:class "sr-only"}]
    [:span {:class "icon-bar"}]
    [:span {:class "icon-bar"}]
    [:span {:class "icon-bar"}]]
     [:a {:class "navbar-brand" :href "#"} [:img {:src "/clojure-icon.gif" :style "height: 20px; float: left; margin-right: 5px"}] "Clojure"]]
    [:div {:id "navbar" :class "collapse navbar-collapse"}
    [:ul {:class "nav navbar-nav"}
     [:li [:a {:href "/"} "Home"]]
     [:li [:a {:href "/all-cocktails"} "Top 20 recipes "]]
     [:li [:a {:href "/similar-recipes"} "Recommended recipes "]]
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
   [:div {:class "login"}


     [:div {:class "form-group has-feedback"}
     [:label {:class "control-label"}]
     [:input {:type "text" :class "form-control" :placeholder "Username"}]
     [:i {:class "glyphicon glyphicon-user form-control-feedback"}]]
     [:div {:class "form-group has-feedback"}
     [:input {:type "password" :class "form-control" :placeholder "Password"}]

     [:i {:class "glyphicon glyphicon-lock form-control-feedback "}]]

     [:br]
     [:button  {:type "button" :class "btn-success btn-md" :method="post" :enctype="/" :id "btn-login"}  "Login"]
     [:span]
     [:button  {:type "button" :class "btn btn-primary btn-md " :href "/all-cocktails" :id "btn-register"} "Register"]]]))


(defn all-cocktails-page
  "Function which generates cocktails-page"
  []
  (let [all-locs (db/list-cocktails)]
    (hic-p/html5
     (gen-page-head "Top 20 cocktails")
     navbar
     [:div {:class "container"}
     [:h1 "Top 20 cocktails"]
     [:table {:class "table"}
      [:tr [:th "Name"] [:th "Rating"] [:th "Votes"] ]
      (for [loc all-locs]
        [:tr [:td (:name loc)] [:td (:rating loc)] [:td (:votes loc)]])]])))




 (defn search-recipes-page
  "Function which generates search-recipes-page"
  []
  (let [all (db/list-ingredient)]
  (hic-p/html5
   (gen-page-head "Search")
  navbar
[:div {:class "well carousel-search hidden-sm " :id "combobox"}
 [:div {:class "btn-group"} [:a {:class "btn btn-default dropdown-toggle btn-select" :data-toggle "dropdown" :href "#"} "Select ingrediant"
                             [:span {:class "caret"}]]
  [:ul {:class "dropdown-menu"}
    (for [loc all]
     [:li [:a {:href "#"}(:ingredient loc)]])
     [:li {:class "divider"}]
     [:li [:a {:href "#"} [:span {:class "glyphicon glyphicon-star"}] "Other"]]]]
     [:a {:class "btn btn-info btn-md " :id "search" :href "#"}  [:span {:class "glyphicon glyphicon-search"}] " Search"]]

    (hic-p/include-js "/ajax.js")
     )
    ))

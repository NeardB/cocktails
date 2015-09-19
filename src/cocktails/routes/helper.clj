(ns cocktails.routes.helper
  (:require [compojure.core :refer :all]
            [hiccup.form :refer :all]
            [cocktails.models.db :as db]
            ;;validacija
            [noir.validation :refer [rule errors? has-value? on-error]]
            [noir.session :as session]))

(defn navbar []
  (list [:nav.navbar.navbar-default.navbar-custom.navbar-fixed-top
   [:div.container-fluid
     [:div.navbar-header.page-scroll
     [:button.navbar-toggle {:type "button" :data-toggle "collapse" :data-target "#bs-example-navbar-collapse-1"}
     (take 3 (repeat [:span.icon-bar]))]
     [:a.navbar-brand {:href "http://clojure.org/"} "Clojure"]]
    ;;nav linkovi
    [:div.collapse.navbar-collapse {:id "bs-example-navbar-collapse-1"}
     [:ul.nav.navbar-nav.navbar-right
      [:li [:a {:href "/"} "Home"]]
      [:li [:a {:href "/top20"} "Top 20 cocktails"]]
      [:li [:a {:href "/search"} "Search"]]
     ;; [:li [:a {:href "/addNew"} "Add new user"]]
       [:li [:a {:href "/addCocktail"} "Add recipe"]]
      [:li [:a {:href "/contact"} "Contact"]]
     ;; [:li [:a {:href "/addNew-page"} "Add"]]
      [:li [:a {:href "/login"} "Login"]]]]
    ]

   ]))

(defn header [head subheading url]
  [:header.intro-header {:style (str "background-image:url('"url"')")}
   [:div.container
    [:div.row
     [:div.col-lg-8.col-lg-offset-2.col-md-10.col-md-offset-1
      [:div.site-heading
       [:h1 head]
       [:hr.small]
       [:span.subheading subheading]]]]]
   ])





(defn home-header []
  [:header {:id "top" :class "header"}
 [:div {:class "text-vertical-center"}
  [:h1 "Welcome"]
  [:h3 {:class "title"} "This is the best aplication to share your drink recipes and browse others recipes"]
  [:a {:href "/search" :class "btn btn-dark btn-lg"} "Browse!"]]])



(defn search-content []
 [:div {:id "div"} "Select ingridient to search for cocktail"]
  )




(defn social-button
   "Returns list of social buttons created using Bootstrap"
  []

  [:div {:class "col-md-12"}

   [:ul {:class "social-network social-circle"}
    [:li [:a {:href "" :class "icoRss link" :title "Rss"}
          [:i {:class "fa fa-rss"}]]]
    [:li [:a {:href "https://www.facebook.com/bojana.rakic.16" :class "icoFacebook link" :title "Facebook"}
          [:i {:class "fa fa-facebook"}]]]
    [:li [:a {:href "" :class "icoTwitter link" :title "Twitter"}
          [:i {:class "fa fa-twitter"}]]]
    [:li [:a {:href "" :class "icoGoogle link" :title "Google +"}
          [:i {:class "fa fa-google-plus"}]]]
    [:li [:a {:href "" :class "icoLinkedin link" :title "Linkedin"}
          [:i {:class "fa fa-linkedin"}]]]]])

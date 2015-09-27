(ns cocktails.routes.shared
  (:require [compojure.core :refer :all]
            [hiccup.form :refer :all]
            [cocktails.models.db :as db]
            ;;validacija
            [noir.validation :refer [rule errors? has-value? on-error]]
           [noir.session :as session]
            )
  )

(defn navbar []
  (list [:nav.navbar.navbar-default.navbar-custom.navbar-fixed-top
   [:div
     [:div {:class "navbar-header"}
     [:button {:class "navbar-toggle"} {:type "button" :data-toggle "collapse" :data-target "#bs-example-navbar-collapse-1"}]
     [:a.navbar-brand {:href "http://clojure.org/"} "Clojure"]]
    [:div.collapse.navbar-collapse {:id "bs-example-navbar-collapse-1"}
     [:ul.nav.navbar-nav.navbar-right
      [:li [:a {:href "/"} "Home"]]
      [:li [:a {:href "/top20"} "Top 20 cocktails"]]
      [:li [:a {:href "/search"} "Search"]]
      [:li [:a {:href "/addCocktail"} "Add recipe"]]
      [:li [:a {:href "/login"} "Login"]]
      ]]
    ]

   ]))

(defn header [heading subheading url]
  [:header.intro-header {:style (str "background-image:url('"url"')")}
   [:div {:class "container"}
    [:div {:class "row"}
     [:div {:class "col-lg-8 col-lg-offset-2"}
      [:div {:class "site-heading"}
       [:h1 heading]
       [:hr {:class "small"}]
       [:span {:class "subheading"} subheading]]]]]])





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

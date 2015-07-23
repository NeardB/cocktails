(ns cocktails.views.layout
  (:require [hiccup.page :refer [html5 include-css include-js]]))

(defn common [& body]
  (html5
    [:head
     [:title "Cocktails"]
     (include-css
                 ;; "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"
      "/css/bootstrap.min.css"
      "/css/cocktails.min.css"
      "/css/cocktails.css"
      "/css/socialmedia.css"
                  "/css/font-awesome.css")

     (include-js "/js/jquery.js"
                 "/js/bootstrap.min.js"
                 "/js/cocktails.min.js")

     ;;[:link {:href "//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" :rel "stylessheet" :type "text/css"}]
     [:link {:href "http://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic" :rel "stylessheet" :type "text/css"}]
     [:link {:href "http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800" :rel "stylessheet" :type "text/css"}]]
    [:body body]))

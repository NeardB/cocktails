(ns cocktails.views.layoutH
  (:require [hiccup.page :refer [html5 include-css include-js]]))

(defn common [& body]
  (html5
    [:head
     [:title "Home page"]
     (include-css
      "/css/bootstrap.min.css"
      "/css/home.css"
      "/css/font-awesome.css")

     (include-js "/js/jquery.js")

    ]
    [:body body]))

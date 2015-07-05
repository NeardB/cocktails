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
;;(hic-p/include-js "/validateEmail.js")
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
     [:li [:a {:href "/login"} "Log in "]]
     [:li [:a {:href "/register"} "Register "]]]]]])



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
     [:input {:type "text" :class "form-control" :placeholder "User Name"}]
     [:i {:class "glyphicon glyphicon-user form-control-feedback"}]]
     [:div {:class "form-group has-feedback"}
     [:input {:type "password" :class "form-control" :placeholder "Password"}]
     [:i {:class "glyphicon glyphicon-lock form-control-feedback "}]]
     [:form {:action "/"}
     [:input  {:type "submit" :class "btn-success btn-md " :value  "Login" :id "btn-login"} ]]
   ;;  [:button  {:type "button" :class "btn-success btn-md" :method="post" :enctype="/" :id "btn-login"}  "Login"]
     [:form {:action "/all-cocktails"}
     [:input  {:type "submit" :class "btn btn-primary btn-md " :value  "Register" :id "btn-register"} ]]]]))






;;       <label class="control-label" for="inputEmail">Email</label>
;;     <div class="controls">
;;       <input type="text" id="inputEmail" placeholder="Email">
;;     </div>
;;      <div class="control-group">
;;     <div class="controls">
;;       <label class="checkbox">
;;         <input type="checkbox"> Remember me
;;       </label>
;;       <button type="submit" class="btn">Sign in</button>
;;     </div>






;;     <div class="control-group">
;;   <label class="control-label" for="inputIcon">Email address</label>
;;   <div class="controls">
;;     <div class="input-prepend">
;;       <span class="add-on"><i class="icon-envelope"></i></span>
;;       <input class="span2" id="inputIcon" type="text">
;;     </div>
;;   </div>



(defn register-page
  "Function which generates register-page"
  []
  (hic-p/html5
   (gen-page-head "Register")
  [:div {:class "OmotacRegister"}
   [:h1 "Register"]
   [:div {:class "register"}



     [:form {:id "Username" :class "form-horizontal"}
     [:label {:class "col-xs-3  control-label"} "Username" ]
[:div {:class "col-xs-7"}
 [:input {:type "text" :class "form-control" :name "email" :placeholder "Username"}
  [:i {:class "glyphicon glyphicon-user form-control-feedback"}]]
 ]]

     [:form {:id "regexpEmailForm" :class "form-horizontal"}
     [:label {:class "col-xs-3  control-label"} "Email" ]
[:div {:class "col-xs-7"}
 [:input {:type "text" :class "form-control" :name "email" :placeholder "example@domain.com"}]
 [:i {:class "glyphicon glyphicon-envelope form-control-feedback"}]]]


    [:form {:id "pass" :class "form-horizontal "}
     [:label {:class "col-xs-3  control-label"} "Password" ]
    [:div {:class "col-xs-7"}
     [:input {:type "password" :class "form-control"  :placeholder "Password"}]
     [:i {:class "glyphicon glyphicon-lock form-control-feedback "}]]]


     [:br]
     [:span]
    [:form {:action "/"}
     [:input  {:type "submit" :class "btn btn-primary btn-md " :value  "Register" :id "register"} ]]]]))



(defn all-cocktails-page
  "Function which generates cocktails-page"
  []
  (let [all-locs (db/list-cocktails)]
    (hic-p/html5
     (gen-page-head "Top 20 cocktails")
     navbar
     [:div {:class "containerTable"}
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
  [:ul {:class "dropdown-menu" }
    (for [loc all]
     [:li [:a { }(:ingredient loc)]])
     [:li {:class "divider"}]
     [:li [:a {:href "#"} [:span {:class "glyphicon glyphicon-star"}] "Other"]]]]


;; [:a {:class "btn btn-info btn-md " :id "search" :href "/ajaxcall"}  [:span {:class "glyphicon glyphicon-search"}] " Search"]


 [:form { :method "POST"  :action "/ajaxcall" :class "form"}
     [:input  {:type "submit" :class "btn btn-info btn-md " :value  "Search" :id "search"}
      [:span {:class "glyphicon glyphicon-search"}]]]]

    (hic-p/include-js "/ajax.js")
     [:div {:id "div1"} [:div {:id "loaderdiv" :class "windows8" :style "display: none;"}]]
   (hic-p/include-js "/ajaxx.js"))))




;;  (defn select-user-page
;;   "Generates the single user page view"
;;   []
;;   (hic-p/html5
;;    (gen-page-head "Select a USER")
;;    navbar
;;    [:div {:class "container"}
;;    [:h1 "Select a User"]
;;    [:form {:action "/add-user" :method "POST" :class "form"}
;;     [:div {:class "form-group"}
;;     [:label "UserID: " ][:input {:type "text" :id "iduser" :name "name" :class "form-control"}]]
;;     [:p [:div {:class "btn btn-lg btn-success" :id "submituser"} "Submit User"]]]
;;     [:div {:id "div1"} [:div {:id "loaderdiv" :class "windows8" :style "display: none;"}
;;                         [:div {:class "wBall" :id "wBall_1"}
;;                          [:div {:class "wInnerBall"}]]

;;                         [:div {:class "wBall" :id "wBall_2"}
;;                          [:div {:class "wInnerBall"}]]

;;                         [:div {:class "wBall" :id "wBall_3"}
;;                          [:div {:class "wInnerBall"}]]

;;                         [:div {:class "wBall" :id "wBall_4"}
;;                          [:div {:class "wInnerBall"}]]

;;                         [:div {:class "wBall" :id "wBall_5"}
;;                          [:div {:class "wInnerBall"}]]
;;                         ]]]
;;     (hic-p/include-js "/ajax.js")))








 (defn list-HTML
  [ingredient]
  (hic-p/html5
   [:h2 "Songs listened to by the user"]
   [:table {:class "table"}
    [:tr [:th "name"] [:th "rating"] [:th "votes"] [:th "ingredient"]]
    (for [l ingredient]
      [:tr [:td (:name l)] [:td (:rating l)] [:td (:votes l)] [:td (:ingredient l)]])]
   [:br]))



(defn cocktails-ingridiant-list
  "Returns an HTML table"
  [ingredient]
  (let [results (db/select-specific-cocktail (ingredient :ingredient))
      ;;my-list (time (db/list-similar-users (ingredient :ingredient)))
        ;;data (time (db/recommended-recordset my-list))
        ]
    (str
     (time (list-HTML results))

     )))






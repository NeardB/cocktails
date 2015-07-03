(defproject cocktails "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.3.1"]
                 [ring/ring-defaults "0.1.2"]

                  [hiccup "1.0.2"]

                 [clj-enlive-template "0.0.1"]
                 ;;[noir "1.3.0"]
                 ;;[clj-json "0.5.3"]
                 [enlive "1.1.5"]

                 [org.clojure/java.jdbc "0.3.6"]
                 [mysql/mysql-connector-java "5.1.6"]

                 ]
  :plugins [[lein-ring "0.8.13"]]
  :ring {:handler cocktails.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring-mock "0.1.5"]]}})

(def ver "0.1.0")
(defproject org.bytescale/gozer ver

  :description "An authentication microservice built in Clojure"
  :url ""

  :dependencies [[org.clojure/clojure "1.7.0"]
                 [selmer "0.9.5"]
                 [markdown-clj "0.9.82"]
                 [environ "1.0.1"]
                 [metosin/ring-middleware-format "0.6.0"]
                 [metosin/ring-http-response "0.6.5"]
                 [bouncer "0.3.3"]
                 [org.clojure/tools.nrepl "0.2.12"]
                 [org.webjars/bootstrap "3.3.6"]
                 [org.webjars/jquery "2.1.4"]
                 [com.taoensso/tower "3.0.2"]
                 [com.taoensso/timbre "4.1.4"]
                 [com.fzakaria/slf4j-timbre "0.2.1"]
                 [compojure "1.4.0"]
                 [ring-webjars "0.1.1"]
                 [ring/ring-defaults "0.1.5"]
                 [ring-ttl-session "0.3.0"]
                 [ring "1.4.0" :exclusions [ring/ring-jetty-adapter]]
                 [mount "0.1.5"]
                 [buddy "0.8.2"]
                 [metosin/compojure-api "0.24.1"]
                 [metosin/ring-swagger-ui "2.1.3-4"]
                 [http-kit "2.1.19"]
                 [com.datomic/datomic-free "0.9.5327"]
                 [io.rkn/conformity "0.4.0"]]

  :uberjar-name (str "gozer-" ver ".jar")
  :jvm-opts ["-server"]

  :main gozer.core

  :plugins [[lein-environ "1.0.1"]]
  :profiles
  {:uberjar {:omit-source false
             :env {:production true}
             :aot :all
             :source-paths ["env/prod/clj"]}
   :dev           [:project/dev :profiles/dev]
   :test          [:project/test :profiles/test]
   :project/dev  {:dependencies [[prone "0.8.2"]
                                 [ring/ring-mock "0.3.0"]
                                 [ring/ring-devel "1.4.0"]
                                 [pjstadig/humane-test-output "0.7.1"]]


                  :source-paths ["env/dev/clj"]
                  :repl-options {:init-ns gozer.core}
                  :injections [(require 'pjstadig.humane-test-output)
                               (pjstadig.humane-test-output/activate!)]
                  ;;when :nrepl-port is set the application starts the nREPL server on load
                  :env {:dev        true
                        :port       3000
                        :nrepl-port 7000
                        :log-level  :trace}}
   :project/test {:env {:test       true
                        :port       3001
                        :nrepl-port 7001
                        :log-level  :trace}}
   :profiles/dev {:env {:log-level :trace}}
   :profiles/test {}})

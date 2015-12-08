(ns gozer.config
  (:require [selmer.parser :as parser]
            [taoensso.timbre :as timbre]
            [gozer.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (timbre/info "\n-=[gozer started successfully using the development profile]=-"))
   :middleware wrap-dev})

(ns gozer.config
  (:require [taoensso.timbre :as timbre]))

(def defaults
  {:init
   (fn []
     (timbre/info "\n-=[gozer started successfully]=-"))
   :middleware identity})

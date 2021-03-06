(ns gozer.security.authenticate
  (:require [gozer.db.core :as dbc]
            [datomic.api :as d]
            [buddy.core.hash :as hash]
            [buddy.core.codecs :refer [bytes->hex]]
            [taoensso.timbre :as timbre]))

(defn login
  "Authenticates a user by hashing the password provided and comparing
 with the one-way hashed password in the database to determine a match."
  [username password]
  (->> (d/pull (dbc/db ) '[*] [:user/username username])
       (println )
       ))

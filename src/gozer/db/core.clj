(ns gozer.db.core
  (:require [datomic.api :as d]
            [environ.core :refer [env]]
            [clojure.edn :as edn]
            [taoensso.timbre :as timbre]
            [gozer.db.schema-loader :as load]
            [gozer.db.users-schema :as us]))

(def conn (atom nil))

(defn connect [& {:keys [db-uri]
                  :or {db-uri (:database-uri env)}}]
  (swap! conn assoc :db-conn (try
                                (d/connect db-uri)
                                (catch Exception e
                                  (let [created (d/create-database db-uri)
                                        _ (timbre/info (str "database: " db-uri
                                                            ", created: " created))]
                                    (d/connect db-uri))))))
(defn db []
  (d/db (:db-conn @conn)))

(defn remove-db [& {:keys [db-uri]
                    :or {db-uri (:database-uri env)}}]
  (d/delete-database db-uri))

(defn apply-db-schema
  "Sets up the schema in datomic for the application."
  []
  (let [schemas (us/new-loader)]
    (doseq [s schemas]
      (load/load-schema s (:datomic-conn @conn)))))

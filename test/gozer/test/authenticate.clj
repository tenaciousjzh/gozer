(ns gozer.test.authenticate
  (:require [clojure.test :refer :all]
            [gozer.security.authenticate :refer :all]
            [gozer.db.core :as dbc]
            [datomic.api :as d]))

(deftest test-authenticate
  (testing "authenticate"
    (let [conn (:db-conn (dbc/connect))
          _ (println "conn: " conn)
          user-added (-> (d/transact
                          conn
                          [[:db/add (d/tempid :db.part/user) ]]))])))

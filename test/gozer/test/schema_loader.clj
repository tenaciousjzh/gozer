(ns gozer.test.schema-loader
  (:require [clojure.test :refer :all]
            [gozer.db.schema-loader :as load]
            [gozer.db.users-schema :as us]
            [gozer.db.core :as dbc]))

(deftest test-user-schema
  (testing "user schema"
    (let [user-loader (us/new-loader )]
      (is (not (nil? user-loader)))
      (is (satisfies? load/SchemaLoader user-loader)))))

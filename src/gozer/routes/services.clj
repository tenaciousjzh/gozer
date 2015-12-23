(ns gozer.routes.services
  (:require [ring.util.http-response :refer :all]
            [compojure.api.sweet :refer :all]
            [schema.core :as s]))

(s/defschema Credentials {:username String
                          :password String
                          :gen-session Boolean})

(s/defschema AuthenticationResult {:username String
                                   :is-authenticated Boolean
                                   :reason String})

(defapi service-routes
  (ring.swagger.ui/swagger-ui
   "/swagger-ui")
  ;JSON docs available at the /swagger.json route
  (swagger-docs
    {:info {:title "Sample api"}})
  (context* "/credentials" []
            :tags ["credentials"]

            (POST* "/authenticate" []
                   :summary "Authenticates a user with the credentials provided"
                   :body [credentials Credentials]
                   :return AuthenticationResult
                   (ok true))

            (POST* "/add" []
                   :return      Long
                   :body-params [x :- Long, y :- Long]
                   :summary     "x-y with body-parameters."
                   (ok (- x y)))

            (DELETE* "/remove/:id" []
                  :return      Long
                  :path-params [x :- Long, y :- Long]
                  :summary     "x*y with path-parameters"
                  (ok (* x y)))

            )

  (context* "/permissions" []
            :tags ["permissions"]
            :summary "summary inherited from context"
            (context* "/:kikka" []
                      :path-params [kikka :- s/Str]
                      :query-params [kukka :- s/Str]
                      (GET* "/:kakka" []
                            :path-params [kakka :- s/Str]
                            (ok {:kikka kikka
                                 :kukka kukka
                                 :kakka kakka})))))

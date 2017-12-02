(ns isitlit-server.handler
  (:require [compojure.api.sweet :refer :all]
            [ring.util.http-response :refer :all]
            [schema.core :as s]
            [isitlit-server.db :as db]))

(s/defschema lit-hit
  {:uuid Long
   :longitude Double
   :latitude Double})

(def app
  (api
    {:swagger
     {:ui "/"
      :spec "/swagger.json"
      :data {:info {:title "Isitlit-server"
                    :description "Compojure Api example"}
             :tags [{:name "api", :description "some apis"}]}}}

    (context "/api" []
      :tags ["api"]

      (GET "/get-litness" []
        :return [[Double]]
        :summary "gets litness"
        (ok {:result [[Double]]))

      (POST "/add-point" []
        :return lit-hit
        :body [lithit lit-hit]
        :summary "echoes a Pizza"
        (ok lithit)))))

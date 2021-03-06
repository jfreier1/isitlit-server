(ns isitlit-server.handler
  (:require [isitlit-server.db :as db]
            [compojure.core :refer :all]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [ring.middleware.json :as middleware]))

(defroutes app-routes
  (GET "/get-litness" []
    {:status 200
     :body (db/get-lit-hits)})

  (GET "/get-id" []
    {:status 200
     :body (db/first-insert)})

  (POST "/add-point" request
    (let [body (:body request)
          id (:id body)
          longitude (:longitude body)
          latitude (:latitude body)]
          (db/hit-lit id longitude latitude)
          {:status 200})))

(def app (-> (handler/site app-routes)
         (middleware/wrap-json-body {:keywords? true})
         middleware/wrap-json-response))

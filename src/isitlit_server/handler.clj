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

  (POST "/add-point" request
    (let [body (:body request)
          id (:id body)
          lat (:latitude body)
          long (:longitude body)]
          {:status 200})))

(def app (-> (handler/site app-routes)
         (middleware/wrap-json-body {:keywords? true})
         middleware/wrap-json-response))

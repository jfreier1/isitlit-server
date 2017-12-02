(ns isitlit-server.db
  (:require [korma.db :refer :all]
            [korma.core :refer :all]
            [clj-time.core :as t]
            [clj-time.format :as f]
            [clj-time.jdbc]))

(def DB "isitlit")
(def USER "root")
(def PASSWORD "password")
(default-connection
  (get-connection
    (defdb db (mysql {:db DB
                       :user USER
                       :password PASSWORD}))))

(declare id longitude latitude time currently_lit)
(defentity isitlit
  (pk :id)
  (table :isitlit)
  (entity-fields :id :longitude :latitude :time :currently_lit)
  (has-one id)
  (has-one longitude)
  (has-one latitude)
  (has-one time)
  (has-one currently_lit))
(defentity id
  (belongs-to isitlit))
(defentity longitude
  (belongs-to isitlit))
(defentity latitude
  (belongs-to isitlit))
(defentity time
  (belongs-to isitlit))
(defentity currently_lit
  (belongs-to isitlit))

;; insert returns the last inserted unique id
(defn first-insert
  []
  (insert isitlit
    (values {:longitude 0 :latitude 0 :time (t/now) :currently_lit false})))

(defn hit-lit
  [id longitude latitude]
  (update isitlit
    (where {:id id})
    (set-fields {:longitude longitude :latitude latitude :time (t/now) :currently_lit true})))

(defn get-lit-hits
  []
  (mapv #(select-keys % [:longitude :latitude])
    (select isitlit
      (where {:currently_lit true})
      (fields :longitude :latitude))))

(ns isitlit-server.db
  (:require [korma.db :refer :all]
            [korma.core :refer :all]))

(def DB "isitlit")
(def USER "root")
(def PASSWORD "password")
(defdb db (mysql {:db DB
                     :user USER
                     :password PASSWORD}))

(declare id longitude latitude time currently_lit)
(defentity isitlit
  (pk :id)
  (table :isitlit)
  (database db)
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
    (values {:longitude 0 :latitude 0 :time 0 :currently_lit false})))

(defn hit-lit
  [id])

(defn get-lit-hits
  []
  (select isitlit
    (where {:currently_lit true})))

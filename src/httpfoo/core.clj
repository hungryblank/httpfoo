(ns httpfoo.core
  (:require
  [httpfoo
   [diagram :as diagram]]
  [clojure.pprint :as pp]))

(defn foo
  "I don't do a whole lot."
  []
  (pp/pprint diagram/state-flow))

;;(defn interpolate
;;  "take one section of diagram and test it"
;;  [list]
;;  (interpolate-line (first list) (rest list)))
;; 
;;(defn interpolate-line
;;  [current-line following-lines]
;;  (cons (first current-line))

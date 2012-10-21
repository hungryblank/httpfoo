(ns httpfoo.core
  (:require
  [httpfoo
   [diagram :as diagram]]
  [clojure.pprint :as pp]))

;;
;;(defn foo
;;  "I don't do a whole lot."
;;  []
;;  (pp/pprint diagram/state-flow))

(defn foo
  "I don't do a whole lot."
  []
  (def first-row
    (first (get diagram/state-flow :root)))
  (pp/pprint first-row)
  (pp/pprint ( diagram/interpolate first-row (get diagram/state-flow :root) ())))

;(defn interpolate
;  "take one section of diagram and test it"
;  [list]
;  (interpolate-line (first list) (rest list)))
; 
;(defn interpolate-line
;  [current-line following-lines]
;  (cons (first current-line))

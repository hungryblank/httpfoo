(ns httpfoo.decision.response-maybe-including-entity
  (:use httpfoo.macros
        httpfoo.protocols)
  (:require [httpfoo.decision.maybe-multiple-representations :as maybe-multiple-representations] ))

(question response-include-an-entity? (follow :maybe-multiple-representations) (terminate 204))
(def start
  ask-response-include-an-entity?)

(ns httpfoo.decision.post-on-missing-resource
  (:use httpfoo.macros
        httpfoo.protocols)
  (:require [httpfoo.decision.new-resource :as new-resource]))

(question redirect? (terminate 303) (follow :new-resource))

(def start
  ask-redirect?)

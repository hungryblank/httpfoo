(ns httpfoo.decision.new-resource
  (:use httpfoo.macros
        httpfoo.protocols)
  (:require [httpfoo.decision.response-maybe-including-entity :as response-maybe-including-entity]))

(first-question new-resource? (terminate 201) (follow :response-maybe-including-entity))

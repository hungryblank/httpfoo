(ns httpfoo.graph.handle-unmodified
  (:use httpfoo.macros
        httpfoo.protocols)
  (:require [httpfoo.graph.response-maybe-including-entity :as response-maybe-including-entity]))

(question new-resource? (terminate 201) (follow :response-maybe-including-entity))

(ns httpfoo.graph.response-maybe-including-entity
  (:use httpfoo.macros
        httpfoo.protocols)
  (:require [httpfoo.graph.maybe-multiple-representations :as maybe-multiple-representations] ))

(question reponse-include-an-entity? (follow :maybe-multiple-representations) (terminate 204))

(ns httpfoo.graph.post-on-missing-resource
  (:use httpfoo.macros
        httpfoo.protocols)
  (:require [httpfoo.graph.new-resource :as new-resource]))

(question redirect? (terminate 303) (follow :new-resource))

(ns httpfoo.decision.maybe-multiple-representations
  (:use httpfoo.macros
        httpfoo.protocols))

(question multiple-representation? (terminate 300) (terminate 200))

(def start
  ask-multiple-representation?)

(ns httpfoo.graph.maybe-multiple-representations
  (:use httpfoo.macros
        httpfoo.protocols))

(question multiple-representation? (terminate 300) (terminate 200))
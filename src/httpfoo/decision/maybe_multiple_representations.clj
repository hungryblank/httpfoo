(ns httpfoo.decision.maybe-multiple-representations
  (:use httpfoo.macros
        httpfoo.protocols))

(first-question multiple-representation? (terminate 300) (terminate 200))

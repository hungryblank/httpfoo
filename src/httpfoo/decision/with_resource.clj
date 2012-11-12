(ns httpfoo.decision.with-resource
  (:use httpfoo.macros
        httpfoo.protocols)
   (:require [httpfoo.decision.handle-unmodified :as handle-unmodified]))

(declare ask-if-match-*)
(declare ask-etag-in-if-match?)

(first-question if-match? (ask if-match-*) (follow :handle-unmodified))
(question if-match-* (follow :handle-unmodified) (ask etag-in-if-match?))
(question etag-in-if-match? (follow :handle-unmodified) (terminate 412))

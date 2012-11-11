(ns httpfoo.decision.without-resource
  (:use httpfoo.macros
        httpfoo.protocols)
  (:require [httpfoo.decision.without-resource-didnt-exist :as without-resource-didnt-exist]
            [httpfoo.decision.without-resource-existed :as without-resource-existed]
            [httpfoo.decision.new-resource :as new-resource]))

(declare ask-put?)
(declare ask-apply-to-different-uri?)
(declare ask-conflict?)
(declare ask-resource-previously-existed?)

(question if-match-* (ask put?) (terminate 412))
(question put? (ask apply-to-different-uri?) (ask resource-previously-existed?))
(question apply-to-different-uri? (terminate 301) (ask conflict?))
(question conflict? (terminate 409) (follow :new-resource))
(question resource-previously-existed? (follow :without-resource-existed) (follow :without-resource-didnt-exist))

(def start
  ask-if-match-*)

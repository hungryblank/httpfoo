(ns httpfoo.decision.without-resource-didnt-exist
  (:use httpfoo.macros
        httpfoo.protocols)
  (:require [httpfoo.decision.post-on-missing-resource :as post-on-missing-resource]))

(declare ask-post-to-missing-resources-allowed?)

(first-question post? (ask post-to-missing-resources-allowed?) (terminate 404))
(question post-to-missing-resources-allowed? (follow :post-on-missing-resource) (terminate 404))

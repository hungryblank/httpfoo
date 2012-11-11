(ns httpfoo.graph.without-resource-existed
  (:use httpfoo.macros
        httpfoo.protocols)
  (:require [httpfoo.graph.post-on-missing-resource :as post-on-missing-resource]))

(declare ask-resource-moved-temporarily?)
(declare ask-post?)
(declare ask-post-to-missing-resources-allowed?)

(question resource-moved-permanently? (terminate 301) (ask resource-moved-temporarily?))
(question resource-moved-temporarily? (terminate 307) (ask post?))
(question post? (ask post-to-missing-resources-allowed?) (terminate 410))
(question post-to-missing-resources-allowed? (follow :post-on-missing-resource) (terminate 410))

(def start
  ask-resource-moved-permanently?)

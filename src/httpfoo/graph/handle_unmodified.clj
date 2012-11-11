(ns httpfoo.graph.handle-unmodified
  (:use httpfoo.macros
        httpfoo.protocols)
  (:require
            [httpfoo.graph.response-maybe-including-entity :as response-maybe-including-entity]
            [httpfoo.graph.post-on-missing-resource :as post-on-missing-resource]
            [httpfoo.graph.maybe-multiple-representations :as maybe-multiple-representations]
            [httpfoo.graph.new-resource :as new-resource]))

(declare ask-if-unmodified-since-valid-date?)
(declare ask-last-modified-gt-if-unmodified-since?)
(declare ask-if-none-match-exists?)
(declare ask-if-none-match-*)
(declare ask-get-or-head?)
(declare ask-etag-in-if-none-match?)
(declare ask-if-modified-since-exists?)
(declare ask-if-modified-since-valid-date?)
(declare ask-if-modified-since-gt-now?)
(declare ask-last-modified-gt-if-modified-since?)
(declare ask-delete?)
(declare ask-delete-enacted?)
(declare ask-post?)
(declare ask-put?)
(declare ask-conflict?)

(question if-unmodified-since? (ask if-unmodified-since-valid-date?) (ask if-none-match-exists?))
(question if-unmodified-since-valid-date? (ask last-modified-gt-if-unmodified-since?) (ask if-none-match-exists?))
(question last-modified-gt-if-unmodified-since? (terminate 412) (ask if-none-match-exists?))
(question if-none-match-exists? (ask if-none-match-*) (ask if-modified-since-exists?))
(question if-none-match-* (ask get-or-head?) (ask etag-in-if-none-match?))
(question get-or-head? (terminate 304) (terminate 412))
(question etag-in-if-none-match? (ask get-or-head?) (ask if-modified-since-exists?))
(question if-modified-since-exists? (ask if-modified-since-valid-date?) (ask delete?))
(question if-modified-since-valid-date? (ask if-modified-since-gt-now?) (ask delete?))
(question if-modified-since-gt-now? (ask delete?) (ask last-modified-gt-if-modified-since?))
(question last-modified-gt-if-modified-since? (ask delete?) (terminate 304))
(question delete? (ask delete-enacted?) (ask post?))
(question delete-enacted? (follow :response-maybe-including-entity) (terminate 202))
(question post? (follow :post-on-missing-resource) (ask put?))
(question put? (ask conflict?) (follow :maybe-multiple-representations))
(question conflict? (terminate 409) (follow :new-resource))

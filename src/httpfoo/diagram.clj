(ns httpfoo.diagram
  (:require [clojure.pprint :as pp]))

(def state-flow
{ :root '(
  (available? (ask known-method?) (terminate 503))
  (known-method? (ask uri-too-long?) (terminate 501))
  (uri-too-long? (terminate 414) (ask method-allowed?))
  (method-allowed? (ask malformed?) (terminate 405))
  (malformed? (terminate 400) (ask authorized?))
  (authorized? (ask forbidden?) (terminate 401))
  (forbidden? (terminate 403) (ask content-header-unknown?))
  (content-header-unknown? (terminate 501) (ask content-type-unknown?))
  (content-type-unknown? (terminate 415) (ask request-entity-too-large?))
  (request-entity-too-large? (terminate 413) (ask options?))
  (options? (terminate 200) (ask accept-exists?))
  (accept-exists? (ask acceptable-media-type-available?) (ask accept-language-exists?))
  (acceptable-media-type-available? (ask accept-language-exists?) (terminate 406))
  (accept-language-exists? (ask acceptable-language-available?) (ask accept-charset-exists?))
  (acceptable-language-available? (ask accept-charset-exists?) (terminate 406))
  (accept-charset-exists? (ask acceptable-charset-available?) (ask accept-encoding-exists?))
  (acceptable-charset-available? (ask accept-encoding-exists?) (terminate 406))
  (accept-encoding-exists? (ask acceptable-encoding-available?) (ask resource-exists?))
  (acceptable-encoding-available? (ask resource-exists?) (terminate 406))
  (resource-exists? (follow :with-resource) (follow :without-resource))
  )

:without-resource
  '((if-match-* (ask put?) (terminate 412))
  (put? (ask apply-to-different-uri?) (ask resource-previously-existed?))
  (apply-to-different-uri? (terminate 301) (ask conflict?))
  (conflict? (terminate 409) (follow :new-resource))
  (resource-previously-existed? (follow :without-resource-existed)
    (follow :without-resource-didnt-exist)))

:without-resource-existed
  '((resource-moved-permanently? (terminate 301) (ask resource-moved-temporarily?))
  (resource-moved-temporarily? (terminate 307) (ask post?))
  (post? (ask post-to-missing-resources-allowed?) (terminate 410))
  (post-to-missing-resources-allowed? (follow :post-on-missing-resource)
    (terminate 410)))

:without-resource-didnt-exist
  '((post? (ask post-to-missing-resources-allowed?) (terminate 404))
  (post-to-missing-resources-allowed? (follow :post-on-missing-resource)
    (terminate 404)))

:post-on-missing-resource
  '((redirect? (terminate 303) (follow :new-resource)))

:new-resource
  '((new-resource? (terminate 201) (follow :response-maybe-including-entity)))

:response-maybe-including-entity
  '((reponse-include-an-entity? (follow :maybe-multiple-representations) (terminate 204)))
:maybe-multiple-representations
  '((multiple-representation? (terminate 300) (terminate 200)))

:with-resource
  '((if-match? (ask if-match-*) (follow :handle-unmodified))
  (if-match-* (follow :handle-unmodified) (ask etag-in-if-match?))
  (etag-in-if-match? (follow :handle-unmodified) (terminate 412)))

:handle-unmodified
  '((if-unmodified-since? (ask if-unmodified-since-valid-date?) (ask if-none-match-exists?))
  (if-unmodified-since-valid-date? (ask last-modified-gt-if-unmodified-since?) (ask if-none-match-exists?))
  (last-modified-gt-if-unmodified-since? (terminate 412) (ask if-none-match-exists?))
  (if-none-match-exists? (ask if-none-match-*) (ask if-modified-since-exists?))
  (if-none-match-* (ask get-or-head?) (ask etag-in-if-none-match?))
  (get-or-head? (terminate 304) (terminate 412))
  (etag-in-if-none-match? (ask get-or-head?) (ask if-modified-since-exists?))
  (if-modified-since-exists? (ask if-modified-since-valid-date?) (ask delete?))
  (if-modified-since-valid-date? (ask if-modified-since-gt-now?) (ask delete?))
  (if-modified-since-gt-now? (ask delete?) (ask last-modified-gt-if-modified-since?))
  (last-modified-gt-if-modified-since? (ask delete?) (terminate 304))
  (delete? (ask delete-enacted?) (ask post?))
  (delete-enacted? (follow :response-maybe-including-entity) (terminate 202))
  (post? (follow :post-on-missing-resource) (ask put?))
  (put? (ask conflict?) (follow :maybe-multiple-representations))
  (conflict? (terminate 409) (follow :new-resource)))})

;; take a triple
;; take the 2 last child
;; map them to their definition
;; return a new list
(declare interpolate)

(defn interpolate-flat
  "get a key"
  [list]
  (interpolate (first list) (rest list) ()))

(defn rezolve
  "if joining is needed to it"
  [element]
  (if (= (first element) 'follow)
    (pp/pprint [(second  element) "foooooo"]))
  (if (= (first element) 'follow)
    (interpolate-flat (get state-flow (second element)))
    element))

(defn lookup
  "given a key and "
  [element list]
  (if (list? element)
    (rezolve element)
    (interpolate
     (first (filter (fn [x] (= element (first x))) list ))
     list
     ())))

(defn interpolate
  "interpolates a triple with other definitions"
  [current list global]
  (cons (first current)
   (map (fn [element] (lookup element list)) (rest current))))

(defn all
  "all functions a request should respond to"
  []
  (distinct
   (flatten
    (map
     (fn [x]
       (map
        (fn [y]
          (filter
           (fn [z]
             (not (list? z)))y)) x) )
         (vals state-flow)))))

(defn triple-to-fun
  "makes a function out of a triple"
  [triple]
  (let [mame (first triple)
        positive (second triple)
        negative (nnext triple)]
  (list 'defn mame
    ['response]
    (list 'if (list mame 'response)
      (list positive 'response)
      (list negative 'response)))))

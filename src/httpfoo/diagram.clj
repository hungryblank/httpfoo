(ns httpfoo.diagram)

(def state-flow
{ :root '(
  (available? known-method? (terminate 503))
  (known-method? uri-too-long? (terminate 501))
  (uri-too-long? (terminate 414) method-allowed?)
  (method-allowed? malformed? (terminate 405))
  (malformed? (terminate 400) authorized?)
  (authorized? forbidden? (terminate 401))
  (forbidden? (terminate 403) content-header-unknown?)
  (content-header-unknown? (terminate 501) content-type-unknown?)
  (content-type-unknown? (terminate 415) request-entity-too-large?)
  (request-entity-too-large? (terminate 413) options?)
  (options? (terminate 200) accept-exist?)
  (accept-exist? acceptable-media-type-available? accept-language-exists?)
  (acceptable-media-type-available? accept-language-exists? (terminate 406))
  (accept-language-exists? acceptable-language-available? accept-charset-exist?)
  (acceptable-language-available? accept-charset-exists? (terminate 406))
  (accept-charset-exist? acceptable-charset-available? accept-encoding-exist?)
  (acceptable-charset-available? accept-encoding-exist? (terminate 406))
  (accept-encoding-exist? acceptable-encoding-available? resource-exist?)
  (acceptable-encoding-available? resource-exist? (terminate 406))
  (resource-exist? (recur with-resource) (recur without-resource)))

:without-resource
  '((if-match-* put? (terminate 412))
  (put? apply-to-different-uri? resource-previously-existed?)`
  (apply-to-different-uri? (terminate 301) conflict?)
  (conflict? terminate(409) (recur new-resource))
  (resource-previously-existed? (recur without-resource-existed)
    (recur without-resource-didnt-exist)))

:without-resource-existed
  '((resource-moved-permanently? (terminate 301) resource-moved-temporarily?)
  (resource-moved-temporarily? (terminate 307) post?)
  (post? post-to-missing-resources-allowed? (terminate 410))
  (post-to-missing-resources-allowed? (recur post-on-missing-resource)
    terminate(410)))

:without-resource-didnt-exist
  '((post? post-to-missing-resources-allowed? (terminate 404))
  (post-to-missing-resources-allowed? (recur post-on-missing-resource)
    (terminate 404)))

:post-on-missing-resource
  '((redirect? (terminate 303) (recur new-resource)))

:new-resource
  '((new-resource? terminate(201) (recur response-maybe-including-entity)))

:response-maybe-including-entity
  '((reponse-include-an-entity? (recur maybe-multiple-representations) (terminate 204)))
:maybe-multiple-representations
  '((multiple-representation? (terminate 300) (terminate 200)))

:with-resource
  '((if-match? if-match-* (recur handle-unmodified))
  (if-match-* (recur handle-unmodified) etag-in-if-match?)
  (etag-in-if-match? (recur handle-unmodified) (terminate 412)))

:handle-unmodified
  '((if-unmodified-since? if-unmodified-since-valid-date? if-none-match-exists?)
  (if-unmodified-since-valid-date? last-modified-gt-if-unmodified-since? if-none-match-exists?)
  (last-modified-gt-if-unmodified-since? (terminate 412) if-none-match-exists?)
  (if-none-match-exists? if-none-match-* if-modified-since-exists?)
  (if-none-match-* get-or-head? etag-in-if-non-match?)
  (get-or-head? (terminate 304) (terminate 412))
  (if-modified-since-exists? if-modified-since-valid-date? delete?)
  (if-modified-since-valid-date? if-modified-since-gt-now? delete?)
  (if-modified-since-gt-now? delete? last-modified-gt-if-modified-since?)
  (last-modified-gt-if-modified-since? delete? (terminate 304))
  (delete? delete-enacted? post?)
  (delete-enacted? (recur response-maybe-including-entity) (terminate 202))
  (post? (recur post-on-missing-resource) put?)
  (put? conflict? (recur maybe-multiple-representations))
  (conflict? (terminate 409) (recur new-resource)))})

;; take a triple
;; take the 2 last child
;; map them to their definition
;; return a new list
(declare interpolate)

(defn lookup
  "given a key and "
  [element list]
  (if (list? element)
    element
    (interpolate
     (first (filter (fn [x] (= element (first x))) list ))
     list
     ())))

(defn interpolate
  "interpolates a triple with other definitions"
  [current list global]
  (cons (first current)
   (map (fn [element] (lookup element list)) (rest current))))

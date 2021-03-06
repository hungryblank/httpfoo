(ns httpfoo.protocols)

(defprotocol Handler
  "hander protocol
   all functions expect a context as a first argument, this context
   represents the complex of request/resource/response depending by the
   stage of the handling.
   all functions ending with a ? must return true or false"
  (available? [context])
  (known-method? [context])
  (uri-too-long? [context])
  (method-allowed? [context])
  (malformed? [context])
  (authorized? [context])
  (forbidden? [context])
  (content-header-unknown? [context])
  (content-type-unknown? [context])
  (request-entity-too-large? [context])
  (options? [context])
  (accept-exists? [context])
  (acceptable-media-type-available? [context])
  (accept-language-exists? [context])
  (acceptable-language-available? [context])
  (accept-charset-exists? [context])
  (acceptable-charset-available? [context])
  (accept-encoding-exists? [context])
  (acceptable-encoding-available? [context])
  (resource-exists? [context])
  (if-match? [context])
  (if-match-* [context])
  (etag-in-if-match? [context])
  (post? [context])
  (post-to-missing-resources-allowed? [context])
  (response-include-an-entity? [context])
  (multiple-representation? [context])
  (redirect? [context])
  (new-resource? [context])
  (resource-moved-permanently? [context])
  (resource-moved-temporarily? [context])
  (if-unmodified-since? [context])
  (if-unmodified-since-valid-date? [context])
  (last-modified-gt-if-unmodified-since? [context])
  (if-none-match-exists? [context])
  (if-none-match-* [context])
  (get-or-head? [context])
  (etag-in-if-none-match? [context])
  (if-modified-since-exists? [context])
  (if-modified-since-valid-date? [context])
  (if-modified-since-gt-now? [context])
  (last-modified-gt-if-modified-since? [context])
  (delete? [context])
  (delete-enacted? [context])
  (put? [context])
  (conflict? [context])
  (apply-to-different-uri? [context])
  (resource-previously-existed? [context])
  (finish [context status-code]))

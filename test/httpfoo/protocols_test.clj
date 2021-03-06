(ns httpfoo.protocols-test
  (:use clojure.test
        httpfoo.core
        httpfoo.protocols)
  (:require [httpfoo.decision.root :as decision-root]))

(defrecord Dummy []
  Handler
  (available? [context] true)
  (known-method? [context] true)
  (uri-too-long? [context] false)
  (method-allowed? [context] true)
  (malformed? [context] false)
  (authorized? [context] true)
  (forbidden? [context] false)
  (content-header-unknown? [context] false)
  (content-type-unknown? [context] false)
  (request-entity-too-large? [context] false)
  (options? [context] false)
  (accept-exists? [context] true)
  (acceptable-media-type-available? [context] true)
  (accept-language-exists? [context] true)
  (acceptable-language-available? [context] true)
  (accept-charset-exists? [context] true)
  (acceptable-charset-available? [context] true)
  (accept-encoding-exists? [context] true)
  (acceptable-encoding-available? [context] true)
  (resource-exists? [context] true)
  (if-match? [context] true)
  (if-match-* [context] true)
  (etag-in-if-match? [context] true)
  (post? [context] true)
  (post-to-missing-resources-allowed? [context] true)
  (response-include-an-entity? [context] true)
  (multiple-representation? [context] true)
  (redirect? [context] true)
  (new-resource? [context] true)
  (resource-moved-permanently? [context] true)
  (resource-moved-temporarily? [context] true)
  (if-unmodified-since? [context] true)
  (if-unmodified-since-valid-date? [context] true)
  (last-modified-gt-if-unmodified-since? [context] true)
  (if-none-match-exists? [context] true)
  (if-none-match-* [context] true)
  (get-or-head? [context] true)
  (etag-in-if-none-match? [context] true)
  (if-modified-since-exists? [context] true)
  (if-modified-since-valid-date? [context] true)
  (if-modified-since-gt-now? [context] true)
  (last-modified-gt-if-modified-since? [context] true)
  (delete? [context] true)
  (delete-enacted? [context] true)
  (put? [context] true)
  (conflict? [context] true)
  (apply-to-different-uri? [context] true)
  (resource-previously-existed? [context] true)
  (finish [context status-code] status-code))

(deftest dummy-handler-test
  (testing "a dummy handler should return a predictable status code")
  (let [req (Dummy.)]
    (is (= (decision-root/start req) 412) )))

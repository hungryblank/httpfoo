(ns httpfoo.diagram-test
  (:use clojure.test
        httpfoo.diagram))



;(deftest print-test
;  (testing "FIXME, I fail."
;    (is (= (httpfoo.diagram/interpolate '(foo bar (terminate 300)) '((bar qux qua)) ())
;           '(foo (bar (nil) (nil)) (terminate 300))) )))

(deftest print-fun-test
  (testing "is a fun"
    (is (= (triple-to-fun (first (get state-flow :root))) 1))))

(deftest all-test
  (testing "is a fun"
    (is (= (all) 1))))

;;
;;(deftest print-fun-test
;;  (testing "is a fun"
;;    (is (= (triple-to-fn-macro :root, (first (get state-flow :root)))
;;           '(defn root-available?
;;              [request]
;;              (if (available? request)
;;                (root-known-method? request)
;;                (terminate 503 request)))))))
;;
;;(deftest print-test
;;  (testing "FIXME, I fail."
;;    (is (= (httpfoo.diagram/interpolate '(foo bar (terminate 300)) '((bar qux qua)) ())
;;           '(foo (bar (nil) (nil)) (terminate 300)) ) )))

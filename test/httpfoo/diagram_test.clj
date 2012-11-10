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

(defn member [x sq]
  (if (seq sq)
    (if (= x (first sq))
      true
      (recur x (rest sq)))))

(defn good-triple
  [triple]
  (let [known-verbs '(ask terminate follow)]
    (doseq [action (map first (rest triple))]
      (is (member action known-verbs) "doh!!"))))

(deftest well-defined-test
  (testing "all positive/negative are lists with known verbs"
      (doseq [[k, v] state-flow]
        (doseq [triple, v]
          (good-triple triple)
          ))))

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

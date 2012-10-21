(ns httpfoo.diagram-test
  (:use clojure.test
        httpfoo.diagram))


(deftest print-test
  (testing "FIXME, I fail."
    (is (= (httpfoo.diagram/interpolate '(foo bar (terminate 300)) '((bar qux qua)) ())
           '(foo (bar (nil) (nil)) (terminate 300))) )))

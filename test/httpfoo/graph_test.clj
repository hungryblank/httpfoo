;;
(ns httpfoo.decision-test
  (:use clojure.test
        httpfoo.decision.root)

(deftest fail-test
  (testing "FIXME, I fail."
    (is (= 1 1))))

(deftest fooff-test
  (testing "FIXME, I fail."
    (is (= (ask-available? 1) 1))))

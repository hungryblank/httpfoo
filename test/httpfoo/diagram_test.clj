(ns httpfoo.diagram-test
  (:use clojure.test
        httpfoo.diagram))



(deftest all-questions-test
  (testing "is a fun"
    (is (= (count (all-questions)) 48))))

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

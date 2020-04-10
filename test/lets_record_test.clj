(ns lets-record-test
  (:require [clojure.test :refer :all]
            [lets-record :refer [letr]]))

(deftest letr-test
  (testing "A map of values is created and added to the atom"
    (let [v (atom [])
          r (letr v [a 1
                     b (+ a 1)
                     c (* b 2)]
                  (+ a b c))]
      (is (= 6 r))
      (is (= [{:a 1 :b 2 :c 4}] @v)))))

(ns statute.facts-test
  (:require [kotoba.lang.text :as str]
            [clojure.test :refer [deftest is]]
            [statute.facts :as facts]))

(deftest ind-has-spec-basis
  (let [sb (facts/spec-basis "IND")]
    (is (= 2 (count sb)))
    (is (every? #(str/starts-with? (:statute/url %) "https://prsindia.org/") sb))
    (is (every? :statute/law-number sb))))

(deftest unknown-jurisdiction-has-no-spec-basis
  (is (nil? (facts/spec-basis "ATL")))
  (is (nil? (facts/spec-basis "ZZZ"))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["IND" "JPN" "ATL"])]
    (is (= 3 (:requested c)))
    (is (= 1 (:covered c)))
    (is (= ["ATL" "JPN"] (:missing-jurisdictions c)))))

(deftest by-topic-filters
  (is (= ["ind.digital-personal-data-protection-act-2023"]
         (mapv :statute/id (facts/by-topic "IND" :privacy))))
  (is (empty? (facts/by-topic "IND" :labor)))
  (is (empty? (facts/by-topic "ATL" :privacy))))

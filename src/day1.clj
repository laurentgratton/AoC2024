(ns day1
    (:require [clojure.string :as str]))

(def input (->> "data/day1.txt"
                slurp
                str/split-lines))

(def firstList  (sort (map #(Integer/parseInt (first (str/split % #"   "))) input)))
(def secondList (sort (map #(Integer/parseInt (second (str/split % #"   "))) input)))

(defn q1 [a b]
             (map #(Math/abs (- %1 %2)) a b))

(defn q2 [a b] (map #(* % (->>
                            b
                            flatten
                            (filter #{%})
                            count)) a))
(defn run [_]
      (println "q1: " (apply + (q1 firstList secondList)))
      (println "q2: " (apply + (q2 firstList secondList))))
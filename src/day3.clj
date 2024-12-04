(ns day3
    (:require [clojure.string :as str]))

(def mulfinder #"(mul\(\d*,\d*\))")
(def digitsfinder #"mul\((\d*),(\d*)\)")
(def input (str/replace (->> "data/day3.txt"
                slurp) #"\n" ""))
(def matcher (re-seq mulfinder input))
(def matches (map first matcher))

(defn parseMult [mult]
      (apply * (map #(Integer/parseInt %) (rest (re-find #"mul\((\d*),(\d*)\)" mult)))))

(def input2 (str/replace input #"don't\(\).*?do\(\)" ""))
(def matcher2 (re-seq mulfinder input2))
(def matches2 (map first matcher2))

(defn run [_] (println "q1: " (apply + (map parseMult matches)))
      (println "q2: " (apply + (map parseMult matches2))))
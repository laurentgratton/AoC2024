(ns day2
    (:require
      [clojure.string :as str]))

(defn toVecOfInt [line]
      (map #(Integer/parseInt %) (str/split line #" ")))

(def input (->> "data/day2.txt"
                slurp
                str/split-lines
                (map toVecOfInt)))

(defn isSequential? [sequence]
      (and (or (apply < sequence) (apply > sequence))))

(defn validIncrements? [sequence]
      (every? #(> 4 %) (map #(Math/abs (- %1 %2)) (butlast sequence) (rest sequence))))

(defn isValidSeq? [sequence]
      (and (isSequential? sequence) (validIncrements? sequence)))

(def invalidInputs (filter #(not (isValidSeq? %)) input))

(defn drop-nth [n coll]
      (concat
        (take n coll)
        (drop (inc n) coll)))

(defn testRemovingSingle [sequence]
       (not (empty? (filter true? (map #(isValidSeq? (drop-nth % sequence)) (range (count sequence)))))))

(defn run [_] (println "q1: " (count (filter isValidSeq? input)))
      (println "q2: " (+ (count (filter isValidSeq? input)) (count (filter testRemovingSingle invalidInputs)))))
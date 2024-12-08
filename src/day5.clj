(ns day5
    (:require [clojure.string :as str]))

(def inputs (-> "data/day5.txt"
                slurp
                (str/split #"\n\n")))
(defn split-into-int [row]
      (map #(Integer/parseInt %) (str/split row #",")))
(def rules (map #(vec [(Integer/parseInt (first %)) (Integer/parseInt (second %))]) (map #(str/split % #"\|") (str/split-lines (str/trim (first inputs))))))
(def pages (map split-into-int (str/split-lines (second inputs))))
(defn get-rules [rules value]
      (vec (map first (filter #(= value (second %)) rules))))
(defn is-valid-page? [rules page]
      (loop [step (first page) remainder (rest page)]
            (if (nil? step)
              true
              (if (reduce #(or %1 (.contains (get-rules rules step) %2)) false remainder)
                false
                (recur (first remainder) (rest remainder))))))

(defn middle-value [vect]
      (when-not (empty? vect)
                (vect (quot (count vect) 2))))
(defn sort-page [a b]
      (cond (.contains (get-rules rules a) b) -1
            (.contains (get-rules rules b) a) 1
            :else 0))

(defn run [_] (println "q1: " (apply + (map #(middle-value (vec %)) (filter #(is-valid-page? rules %)  pages))))
      (println "q2: " (apply + (map #(middle-value (vec (sort sort-page %))) (filter #(not (is-valid-page? rules %)) pages)))))
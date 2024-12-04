(ns day4
    (:require [clojure.string :as str]))

(def directions [[[0 -1] [0 -2] [0 -3]]
                 [[0 1] [0 2] [0 3]]
                 [[1 0] [2 0] [3 0]]
                 [[-1 0] [-2 0] [-3 0]]
                 [[1 -1] [2 -2] [3 -3]]
                 [[-1 -1] [-2 -2] [-3 -3]]
                 [[1 1] [2 2] [3 3]]
                 [[-1 1] [-2 2] [-3 3]]])
(def match '[\M \A \S])

(def input (->> "data/day4.txt"
                slurp
                str/split-lines))
(defn parseRow [rowNum row]
      (map #(vector rowNum (first %)) (filter #(= (second %) \X) (map-indexed vector row))))

(defn findStarts [grid]
      (concat (map-indexed parseRow grid)))

(defn expandCoords [coords direction]
      (map #(map + coords %) direction))

(defn paths [coords directions]
      (map #(expandCoords coords %) directions))

(defn getInput [coords grid]
      (map #(get-in grid %) coords))

(defn validCoord? [[x y] gridx gridy] (and (<= 0 x (dec gridx)) (<= 0 y (dec gridy))))

(defn checkPath [path gridx gridy]
      (every? #(validCoord? % gridx gridy) path))

(defn filterPaths [paths grid]
       (filter #(checkPath % (count (first grid)) (count grid)) paths))

(defn getEntries [coord grid]
      (count (filter #(= '[\M \A \S] %) (map #(getInput % grid) (filterPaths (paths coord directions) grid)))))

(defn processRow [row grid]
      (apply + (map #(getEntries % grid) row)))

(def q2directions '[
                    [[-1 -1] [1 1] [-1 1] [1 -1]]])
(defn q2parseRow [rowNum row]
      (map #(vector rowNum (first %)) (filter #(= (second %) \A) (map-indexed vector row))))
(defn q2findStarts [grid]
      (concat (map-indexed q2parseRow grid)))
(defn q2getEntries [coord grid]
      (count (filter #(or (= '[\M \S \S \M] %) (= '[\M \S \M \S] %) (= '[\S \M \S \M] %) (= '[\S \M \M \S] %)) (map #(getInput % grid) (filterPaths (paths coord q2directions) grid)))))
(defn q2processRow [row grid]
      (apply + (map #(q2getEntries % grid) row)))

(defn run [_]
      (println "q1: " (apply + (map #(processRow % input) (findStarts input))))
      (println "q2: " (apply + (map #(q2processRow % input) (q2findStarts input)))))
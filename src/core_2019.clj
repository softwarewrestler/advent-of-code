(ns aoc.19 (:require [clojure.string :as str]))


;--- Day 1: The Tyranny of the Rocket Equation ---
(defn string->int [string] (Integer/parseInt  string))

(defn read-int-input [file]
  (map string->int (str/split-lines (slurp file))))

(defn fuel-for-mass [mass] (- (quot mass 3) 2))

(defn fuel-for-fuel [fuel]
  (loop [fuel-left fuel
         acc 0]
    (if (<= fuel-left 0)
      acc

        (recur (fuel-for-mass fuel-left) (+ acc fuel-left)))))

(defn fuel-for-everything [mass]
  (fuel-for-fuel (fuel-for-mass mass)))

(defn fuel-requirements [f input] (reduce #(+ %1 (f %2)) 0 input))

(def input (read-int-input "resources/2019-day-01-input.txt"))
;--- part 1
;def total-weight-p1 (reduce #(+ %1 (fuel-for-mass %2)) 0 input))
(def total-weight-p1 (fuel-requirements fuel-for-mass input))
(prn total-weight-p1)
;--- part 2
(def total-weight-p2 (fuel-requirements fuel-for-everything input))
(prn total-weight-p2)
;(def total-weight (reduce #(+ %1 (fuel-for-everything %2)) 0 input))




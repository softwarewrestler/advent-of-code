(ns aoc-2019.day-3
  (:require [clojure.string :as str]
            [clojure.test :refer [deftest is are]]))

(defn string->int [string] (Integer/parseInt  string))
;--- Day 3: Crossed Wires ---
(defn go-right [[x y] step-count]
  (for [x-pos (range x (+ x step-count 1))] [x-pos y]))

(defn go-left [[x y] step-count]
  (for [x-pos (reverse (range (- x step-count) x))] [x-pos y]))

(defn go-up [[x y] step-count]
  (for [y-pos (range y (+ y step-count 1))] [x y-pos]))

(defn go-down [[x y] step-count]
  (for [y-pos (reverse (range (- y step-count) y))] [x y-pos]))

(def direction-map {:R go-right :L go-left :U go-up :D go-down})

(defn split-direction [direction]
  (let [[_ dir steps] (re-matches #"(\D)(\d+)" direction)]
    [(keyword dir) (string->int steps)]))

(deftest splitting-directions
  (are [direction split] (= (split-direction direction) split)
    "R8" [:R 8]
    "L88" [:L 88]
    "U18" [:U 18]
    "D39" [:D 39]))

(defn follow-direction [direction position]
  (let [[dir steps] (split-direction direction)]
    ((dir direction-map) position steps)))

(defn create-path [directions]
  (loop [directions directions
         point [0 0]
         path #{}]
    (if (empty? directions)
      path
      (let [steps (follow-direction (first directions) point)]
        (recur (rest directions) (last steps) (set (clojure.set/union path steps)))))))

(defn calculate-distance [[x y]]
  (+ (Math/abs x) (Math/abs y)))

(defn find-closest-intersection [wire-paths]

  )

;(deftest closest-intersection
;  (are [path distance] (= (find-closest-intersection path) distance)
;       "R8,U5,L5,D3\nU7,R6,D4,L4" 6
;       )
;  )

; need to get 2 vectors from the input


(comment
  (defn parse-path [path]
    (str/split (str/trim path) #","))
  (defn parse-input [input]
    (map parse-path
         (str/split-lines input)))
    ;(parse-path input))
  (defn read-file [file]
    (parse-input (slurp file)))

  (read-file "resources/2019/day-03-input.txt")
  (parse-input "R8,U5,L5,D3\nU7,R6,D4,L4"))





(ns aoc-2019.day-1 
  (:require [clojure.string :as str]
            [clojure.test :refer [deftest is are]]))

;--- Day 1: The Tyranny of the Rocket Equation ---
(defn string->int [string] (Integer/parseInt  string))

(defn read-int-input [file]
  (map string->int (str/split-lines (slurp file))))

(defn calculate-required-fuel [mass] 
  (let [divided (quot mass 3)]
    (if (< divided 2)
      divided
      (- divided 2)
      )
    )
  (max (- (quot mass 3) 2) 0)
  )

(deftest fuel-equation
  (are [mass fuel] (= (calculate-required-fuel mass) fuel)
       12 2
       14 2
       1969 654
       100756 33583
       ))





(comment
  (def file "resources/2019/day-01-input.txt")
  (read-int-input file)
  (launch-fuel-requirement file)
)
;--- part 1
(defn launch-fuel-requirement [file]
  (reduce (fn [acc mass] (+ acc (calculate-required-fuel mass))) 0 (read-int-input file)))


;--- part 2
(defn calculate-fuel-for-fuel [mass] 
  (loop [fuel (calculate-required-fuel mass)
         total-fuel 0 ]
    (if (= fuel 0)
      total-fuel
    (recur (calculate-required-fuel fuel) (+ fuel total-fuel))
    ) ))

(defn launch-fuel-requirement-2 [file]
  (reduce (fn [acc mass] 
            (+ acc (calculate-fuel-for-fuel mass)
               )) 0 (read-int-input file)))



(deftest fuel-for-fuel-equation
  (are [mass fuel] (= (calculate-fuel-for-fuel mass) fuel)
       12 2
       14 2
       1969 966
       100756 50346
       ))

(comment
  (def file "resources/2019/day-01-input.txt")
  (read-int-input file)
  (launch-fuel-requirement-2 file)
  )

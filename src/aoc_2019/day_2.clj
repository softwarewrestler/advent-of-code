(ns aoc-2019.day-2
  (:require [clojure.string :as str]
            [clojure.test :refer [deftest is are]]))

;--- Day 2: 1202 Program Alarm ---


(defn string->int [string] (Integer/parseInt  string))

(defn read-input [file]
  (map string->int
       (->
        (slurp file)
        (str/trim)
        (str/split #","))))

(defn prepare-input [input noun verb]
  (assoc (assoc input 1 noun) 2 verb))



;opcode src1 src2 dest
; opcode 1=>+, 2=>*, 99 halt


(def opcode-map {1 + 2 *})

(defn opcode-processor [[opcode src1 src2 dest] memory]
  (let [op1 (get memory src1)
        op2 (get memory src2)
        value ((opcode-map opcode) op1 op2)]
   ; (do
      ;(println "op1: " op1)
      ;(println "op2: " op2)
      ;(println "value:" value)
   ; )
    (assoc memory dest value)))


(defn int-code-processor [int-code]
  (loop [instruction-set int-code
         memory int-code]
    (let [instruction (take 4 instruction-set)
          opcode (first instruction)]
      (if (= opcode 99)
        (get memory 0)
        (recur (drop 4 instruction-set) (opcode-processor instruction memory))))))


(comment
  (def file "resources/2019/day-02-input.txt")
  (read-int-input file)
  (def file-input (vec (read-input file)))
  (def input (prepare-input file-input 12 2))
  (int-code-processor input)
  (def sample [1 9 10 3 2 3 11 0 99 30 40 50]))

;--- Part Two ---
; find noun and verb that produce 19690720 as the output
; prepare input for noun verb pair
; process input
; check result

(comment
  (def file "resources/2019/day-02-input.txt")
  (def file-input (vec (read-input file)))
  (def input (prepare-input file-input 12 2))
  (def options (for [noun (range 100) verb (range 100)] [noun verb])))
;

(defn bfa [clean-input options]
  (loop [options options]
  (let [[noun verb] (first options)
        input (prepare-input clean-input noun verb)
        output (int-code-processor input)]
(do 
  ;(println "NOUN: " noun "\tVERB: " verb)
  ;(println "output " output)
    (if (= 19690720 output) 
      (+ (* 100 noun) verb)
      (recur (rest options)))
) 
    ))
  )







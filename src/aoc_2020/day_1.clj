(ns aoc-2020.day-1 
  (:require [clojure.string :as str]
            [clojure.test :refer [deftest is are]]))

(defn string->int [string] (Integer/parseInt  string))

(defn read-int-input [file]
  (map string->int (str/split-lines (slurp file))))

(defn read-input [file]
  (str/split-lines (slurp file)))

(defn read-vec-input [file]
  (vec (read-input file)))

;--- Day 1: Report Repair ---
(defn find-complement [value sum collection]
  (some #{(- sum value)} collection))

(defn find-complements [sum collection]
  (loop [value (first collection)
         values (rest collection)] 
    (if (empty? values) 
      nil
      (if (find-complement value sum values)
        (* value (- sum value))
        (recur (first values) (rest values))))))

(defn day1-part1 [expense-report]
  (find-complements 2020 expense-report))

;--- Part Two ---
(defn day1-part2 [expense-report]
  (loop [value (first expense-report)
         collection (rest expense-report)]
    (let [product (find-complements (- 2020 value) collection)]
      (if product 
        (* product value) 
        (recur (first collection) (rest collection))))))

(deftest test-day1-part1
  (is (= 514579 (day1-part1 '(1721 979 366 299 675 1456)))))
(deftest test-day1-part1
  (is (= 241861950 (day1-part2 '(1721 979 366 299 675 1456)))))

(comment
  (def file "resources/2020/day-01-input.txt")
  (day1-part1 (read-int-input file))
  (day1-part2 (read-int-input file)))



;--- Day 2: Password Philosophy ---
(def password-regex (re-pattern #"^(\d+)-(\d+)\s+(\w):\s+(\w*)"))

(defn parse-line [line]
  (let [[_ s-min s-max letter password] (re-matches password-regex line)]
    [(string->int s-min) (string->int s-max) letter password]))

(defn in-range? [min-count max-count letter-count]
  (and (>= letter-count min-count) (<= letter-count max-count)))

(defn validate-password [min-count max-count letter password]
  (let [letter-count (or (get (into (frequencies password)) (.charAt letter 0)) 0)]
    (in-range? min-count max-count letter-count)))


(defn process-password-line [line]
  (let [[ min-count max-count letter password] (parse-line line)]
    (validate-password min-count max-count letter password)
    ))

(defn day2-part1 [input]
  (->>
    (map process-password-line input)
    (filter true?)
    (count)))

(comment
  (def file "resources/2020/day-02-input.txt")
  (day2-part1 (read-input file)))
;--- Part Two ---
(defn only-one [bool1 bool2]
  (and 
    (or bool1 bool2) 
    (not (and bool1 bool2))))

(defn validate-password [index1 index2 letter password]
  (let [letter1 (get password (- index1 1))
        letter2 (get password (- index2 1))]
    (only-one (= (.charAt letter 0) letter1) (= (.charAt letter 0) letter2))))


(defn day2-part2 [input]
  (->>
    (map process-password-line input)
    (filter true?)
    (count)))

(deftest d2p2-validate-password
  (are [line result] (= (process-password-line line) result)
      "1-3 a: abcde" true
      "1-3 b: cdefg" false
      "2-9 c: ccccccccc" false
      ))

(def d2p2-test-input 
  '("1-3 a: abcde" "1-3 b: cdefg" "2-9 c: ccccccccc"))

(deftest test-d2p2
  (is (= 1 (day2-part2 d2p2-test-input))))

(comment
  (def file "resources/2020/day-02-input.txt")
  (day2-part2 (read-input file)))

;--- Day 3: Toboggan Trajectory ---
(defn get-character [line-number line]
  (let [index (* 3 line-number) 
        length (.length line)]
    (.toString (get line (mod index length)))))

(defn d3p1 [input]
  (->>
    (map-indexed get-character input)
    (rest)
    (filter #(= "#" %))
    (count)))

;(rest (map-indexed get-character d3p1-test-input))

(def d3p1-test-input [
"..##......."
"#...#...#.."
".#....#..#."
"..#.#...#.#"
".#...##..#."
"..#.##....."
".#.#.#....#"
".#........#"
"#.##...#..."
"#...##....#"
".#..#...#.#"])

(deftest d3p1-test (is [input] (= 7 (d3p1 input))))
(deftest d3p1-get-character
  (are [line-number line result] (= (get-character line-number line) result)
       1 (get d3p1-test-input 1) "."
       2 (get d3p1-test-input 2) "#"
       3 (get d3p1-test-input 3) "."
       4 (get d3p1-test-input 4) "#"
       5 (get d3p1-test-input 5) "#"
       6 (get d3p1-test-input 6) "."
       7 (get d3p1-test-input 7) "#"
       8 (get d3p1-test-input 8) "#"
       9 (get d3p1-test-input 9) "#"
       10 (get d3p1-test-input 10) "#"))

(comment
  (def file "resources/2020/day-03-input.txt")
  (d3p1 (read-vec-input file)))

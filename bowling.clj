(defmulti calculate-special
    (fn [one two coll]
        (cond
            (= 10 one) :strike
            (= 10 (+ one two)) :spare
            :else :regular)))
(defmethod calculate-special :regular [one two coll] (+ one two))
(defmethod calculate-special :spare [one two coll] (+ one two (first coll)))
(defmethod calculate-special :strike [one two coll] (+ one two (first coll)))

(defmulti pins-in-next-frame (fn [coll] (= 10 (first coll))))
(defmethod pins-in-next-frame true [coll] 10)
(defmethod pins-in-next-frame false [coll] (+ (first coll) (-> coll rest first)))

(defmulti calculate-score
    (fn [coll]
        (cond
            (= 10 (first coll)) :strike
            (= [] coll) :empty
            :else :regular)))
(defmethod calculate-score :empty [coll] 0)
(defmethod calculate-score :strike [coll]
    (let [tail (rest coll)]
        (+ 10 (pins-in-next-frame tail) (calculate-score tail))))
(defmethod calculate-score :regular [coll]
    (let [one (first coll)
          two (-> coll rest first)
          tail (-> coll rest rest)]
         (+ (calculate-special one two tail) (calculate-score tail))))

(println (calculate-score (read-string (first *command-line-args*))))

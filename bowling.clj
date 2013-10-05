(defmulti calculate-score
    (fn [coll]
        (let [one (first coll)
              two (-> coll rest first)
              tail (-> coll rest rest)]
              (cond
                  (= [] coll) :empty
                  (= 3 (count coll)) :irregular-tenth
                  (= 10 one) :strike
                  (= 10 (+ one two)) :spare
                  :else :regular))))

(defmethod calculate-score :empty [coll] 0)
(defmethod calculate-score :irregular-tenth [coll] (reduce + coll))
(defmethod calculate-score :strike [coll]
    (let [tail (rest coll)]
        (+ 10 (first tail) (-> tail rest first) (calculate-score tail))))
(defmethod calculate-score :spare [coll]
    (let [tail (-> coll rest rest)]
        (+ 10 (first tail) (calculate-score tail))))
(defmethod calculate-score :regular [coll]
    (let [one (first coll)
          two (-> coll rest first)
          tail (-> coll rest rest)]
         (+ one two (calculate-score tail))))

(println (calculate-score (read-string (first *command-line-args*))))

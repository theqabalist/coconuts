(defmulti calculate-special
    (fn [one two coll]
        (cond
            (= 10 (+ one two)) :spare
            :else :regular)))
(defmethod calculate-special :regular [one two coll] (+ one two))
(defmethod calculate-special :spare [one two coll] (+ one two (first coll)))

(defmulti calculate-score (fn [coll] coll))
(defmethod calculate-score [] [coll] 0)
(defmethod calculate-score :default [coll]
    (let [one (first coll)
          two (-> coll rest first)
          tail (-> coll rest rest)]
         (+ (calculate-special one two tail) (calculate-score tail))))

(println (calculate-score (read-string (first *command-line-args*))))

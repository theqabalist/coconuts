(defmulti calculate-score
    (fn [coll]
        (let [one (first coll)
              two (-> coll rest first)
              tail (-> coll rest rest)]
              (cond
                  (= [] coll) :empty
                  (= 1 (count coll)) :singlet
                  (and (= 10 (+ one two)) (= [] tail)) :partial-spare
                  (= 3 (count coll)) :irregular-tenth
                  (and (= 10 one) (= 0 (count tail))) :partial-strike
                  (= 10 one) :strike
                  (= 10 (+ one two)) :spare
                  :else :regular))))

(defmethod calculate-score :empty [coll] 0)
(defmethod calculate-score :singlet [coll] (first coll))
(defmethod calculate-score :irregular-tenth [coll] (reduce + coll))
(defmethod calculate-score :partial-strike [coll] (+ 10 (first coll)))
(defmethod calculate-score :strike [coll]
    (let [tail (rest coll)]
        (+ 10 (first tail) (-> tail rest first) (calculate-score tail))))
(defmethod calculate-score :partial-spare [coll] 10)
(defmethod calculate-score :spare [coll]
    (let [tail (-> coll rest rest)]
        (+ 10 (first tail) (calculate-score tail))))
(defmethod calculate-score :regular [coll]
    (let [one (first coll)
          two (-> coll rest first)
          tail (-> coll rest rest)]
         (+ one two (calculate-score tail))))

(defn mainloop [scores]
    (let [newscores (conj scores (-> (read-line) read-string))]
        (println newscores)
        (println (calculate-score newscores))
        (recur newscores)))

(if (nil? (first *command-line-args*))
    (mainloop [])
    (println (calculate-score (read-string (first *command-line-args*)))))

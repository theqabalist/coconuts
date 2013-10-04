(defn calculate-score [coll]
    (reduce + coll))

(println (calculate-score (read-string (first *command-line-args*))))

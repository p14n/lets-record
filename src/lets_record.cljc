(ns lets-record)

(defmacro map-vars
  "Create a map with keys matching parameter names and values matching parameter values"
  [& args]
  (let [p (cons 'list (map (juxt (comp keyword name) identity) args))]
    `(into {} ~p)))

(defmacro letr
  "Matches the let macro, but passes a map of the generated values to the atom collection passed in as the first param"
  [atom-coll bindings & body]
  (let [b (destructure bindings)
        c `(swap! ~atom-coll #(conj % (map-vars ~@(take-nth 2 b))))]
    `(let* ~b (do ~c
                  ~@body))))
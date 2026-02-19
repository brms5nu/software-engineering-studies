(ns studies.basics)

(defn greet [name]
  (str "Hello, " name "! Welcome to Clojure."))

(comment
  ;; Rich comment block — evaluate these forms in the REPL
  (greet "Bruno")
  (+ 1 2 3)
  (map inc [1 2 3 4 5]))

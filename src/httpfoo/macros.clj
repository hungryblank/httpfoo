(ns httpfoo.macros)

(defmacro follow  [namespace req]  `(~(ns-resolve (symbol (str "httpfoo.decision." (name namespace))) 'start) ~req))

(defmacro ask
  [question arg]
  `(~(symbol (str "ask-" question)) ~arg))

(defmacro terminate
  [code arg]
  `(~'finish ~arg ~code))

(defmacro question
  [name positive-clause negative-clause]
  `(defn ~(symbol (str "ask-" name))
     [request#]
     (if (~name request#)
       (~@positive-clause request#)
       (~@negative-clause request#))))

(defmacro first-question
  [name positive-clause negative-clause]
  `(def ~'start
      (question ~name ~positive-clause ~negative-clause)))

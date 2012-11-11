(ns httpfoo.macros)

(defmacro follow
  [namespace request]
  `((ns-resolve (symbol (name ~namespace)) '~'start) ~request))

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

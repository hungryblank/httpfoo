(ns httpfoo.ball)

(defprotocol Moves
  (bounce [thing] "bounces")
  (roll [thing] "rolls"))

(defn bounce
  [ball]
  (bounce ball))

(defmacro question [scope name positive negative]
  `(defn ~(symbol (str scope "-" name))
     [request#]
     (if (~name request#)
       (~@positive request#)
       (~@negative request#))))

(question 'foobar 'foobaz 'poz 'foox)

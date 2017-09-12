(ns code-hoard.graphics)

(defn search-methods [type methodName]
  (def nameRei (java.util.regex.Pattern/compile methodName))
  (for [methods(.getMethods type)
        :let [name (.getName methods)]
        :when (re-find nameRei name)]
    name))

(search-methods java.awt.Frame "toggle")


(defn f-grid [f max-x max-y]
  (for [x (range max-x) y (range max-y)]
    [x y (rem (f x y) 256)]))

(defn clear [gfx size-x size-y]
  (.clearRect gfx 0 0 size-x size-y))

(defn f-draw [f gfx size-x size-y]
  (clear gfx size-x size-y)
  (doseq [[x y res] (f-grid f size-x size-y)]
    (.setColor gfx (java.awt.Color. res res res))
    (.fillRect gfx x y 1 1)))

;; Create frame and get graphics context
(def frame (java.awt.Frame.))
(clear gfx grid-size)
(.setVisible frame true)
(.setSize frame (java.awt.Dimension. grid-size grid-size))
(def gfx (.getGraphics frame))

;;Test it out
(def grid-size 400)
(.setSize frame (java.awt.Dimension. grid-size grid-size))
(f-draw bit-xor gfx grid-size grid-size)
(f-draw + gfx grid-size grid-size)


(.setVisible frame false)

;; (.setColor gfx (java.awt.Color. 255 128 0))
;; (.fillRect gfx 100 100 50 75)
;;

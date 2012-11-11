(ns httpfoo.graph.root
  (:use httpfoo.macros
        httpfoo.protocols)
  (:require [httpfoo.graph.with-resource :as with-resource]
            [httpfoo.graph.without-resource :as without-resource]))

(declare ask-known-method?)
(declare ask-uri-too-long?)
(declare ask-method-allowed?)
(declare ask-malformed?)
(declare ask-authorized?)
(declare ask-forbidden?)
(declare ask-content-header-unknown?)
(declare ask-content-type-unknown?)
(declare ask-request-entity-too-large?)
(declare ask-options?)
(declare ask-accept-exists?)
(declare ask-acceptable-media-type-available?)
(declare ask-accept-language-exists?)
(declare ask-acceptable-language-available?)
(declare ask-accept-charset-exists?)
(declare ask-acceptable-charset-available?)
(declare ask-accept-encoding-exists?)
(declare ask-acceptable-encoding-available?)
(declare ask-resource-exists?)




(question available? (ask known-method?) (terminate 503))
(prn (macroexpand '( question available? (ask known-method?) (terminate 503))))
(question known-method? (ask uri-too-long?) (terminate 501))
(question uri-too-long? (terminate 414) (ask method-allowed?))
(question method-allowed? (ask malformed?) (terminate 405))
(question malformed? (terminate 400) (ask authorized?))
(question authorized? (ask forbidden?) (terminate 401))
(question forbidden? (terminate 403) (ask content-header-unknown?))
(question content-header-unknown? (terminate 501) (ask content-type-unknown?))
(question content-type-unknown? (terminate 415) (ask request-entity-too-large?))
(question request-entity-too-large? (terminate 413) (ask options?))
(question options? (terminate 200) (ask accept-exists?))
(question accept-exists? (ask acceptable-media-type-available?) (ask accept-language-exists?))
(question acceptable-media-type-available? (ask accept-language-exists?) (terminate 406))
(question accept-language-exists? (ask acceptable-language-available?) (ask accept-charset-exists?))
(question acceptable-language-available? (ask accept-charset-exists?) (terminate 406))
(question accept-charset-exists? (ask acceptable-charset-available?) (ask accept-encoding-exists?))
(question acceptable-charset-available? (ask accept-encoding-exists?) (terminate 406))
(question accept-encoding-exists? (ask acceptable-encoding-available?) (ask resource-exists?))
(question acceptable-encoding-available? (ask resource-exists?) (terminate 406))
(question resource-exists? (follow :with-resource) (follow :without-resource))

(def start
  ask-available?)

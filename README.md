# httpfoo

[![Build Status](https://travis-ci.org/hungryblank/httpfoo.png)](https://travis-ci.org/hungryblank/httpfoo)

An attempt to render the decision engine of
[webmachine](https://github.com/basho/webmachine) in clojure.

## Rationale

Webmachine implements a decision engine to handle any HTTP request that
implements the decision tree described in this
[flow chart](https://raw.github.com/wiki/basho/webmachine/images/http-headers-status-v3.png).

## Usage

An implementation of the decision engine is provided, also a protocol
that must be implemented by an httpfoo handler is given, implementing
this protocol should result in the ability of handling properly all HTTP
requests that handle REST resource

## Notes

This is my first clojure learning project and as such s guaranteed to be
wrong in many ways.

## Status

Largely incomplete

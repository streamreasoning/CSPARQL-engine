# Change Log
All notable changes to this project will be documented in this file.
This document adheres to [keep-a-changelog].

## [0.9.5.1-modaclouds] - 2015-05-14

### Added
- api for evaluating general sparql queries against the static kb when using the jena engine 

### Fixed
- performance issue caused by the garbage collection call at each triple that is fed
- RDFTable.isGraph() is now implemented for both jena and sesame engine

[0.9.5.1-modaclouds]: https://github.com/streamreasoning/CSPARQL-engine/compare/0.9.5.1...0.9.5.1-modaclouds
[keep-a-changelog]: https://github.com/olivierlacan/keep-a-changelog

## [0.6.3](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.6.2...v0.6.3) (2021-05-26)


### Bug Fixes

* **application:** Fix Docker problems ;) ([1b8d9bb](https://github.com/wuespace/telestion-project-daedalus2/commit/1b8d9bb6e445c481f8177410a9cdb87e9d826b6b))



## [0.6.2](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.6.1...v0.6.2) (2021-05-26)


### Bug Fixes

* Try to fix Docker issues with host-system host alias. This will require binding the host's network to the docker image's network. To do this, use `--net=host` (Linux-only!) ([495ed15](https://github.com/wuespace/telestion-project-daedalus2/commit/495ed156cf13be1f5af11552c05d7d6c71623ec4))



## [0.6.1](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.6.0...v0.6.1) (2021-05-26)


### Bug Fixes

* Fix config problem (and database configuration) in a quick and dirty manner ([3ef2cbf](https://github.com/wuespace/telestion-project-daedalus2/commit/3ef2cbf5fdbdced603b0840c688536932e1205be))



# [0.6.0](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.5.0...v0.6.0) (2021-05-26)


### Features

* Update Backstein DB config for application ([2e6e1dc](https://github.com/wuespace/telestion-project-daedalus2/commit/2e6e1dcbc8afec3e5bed6ea168545b7dae4d7235))



# [0.5.0](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.4.0...v0.5.0) (2021-05-24)


### Features

* **application:** Update application config for integration test ([6afd410](https://github.com/wuespace/telestion-project-daedalus2/commit/6afd4101d0d4c46b8645fd87c2a9af4f49507899))



# [0.4.0](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.3.0...v0.4.0) (2021-05-24)


### Bug Fixes

* **application:** Fix and test reading, parsing, and dumping (to a file) MavLink messages from a (virtual) serial device ([52d99e0](https://github.com/wuespace/telestion-project-daedalus2/commit/52d99e003acc3f90ca1e8a71150f234599175872))
* **client:** Adjust to updated aggregation data sample type from backend and listen to "real" addresses ([d76afbd](https://github.com/wuespace/telestion-project-daedalus2/commit/d76afbdc8286202728168ccfaa45ce1f2731b874))
* **client:** Use new event bus addresses for the fake data in the current values configuration ([cc174e1](https://github.com/wuespace/telestion-project-daedalus2/commit/cc174e183eb1ef13548058a281ba85ee5d3d0681))
* **database:** Fix database config and implementation to get it working ([0475e1b](https://github.com/wuespace/telestion-project-daedalus2/commit/0475e1bed776d550f326138de0d51e98729b197f))
* Add missing default constructor for MavlinkRegistrar ([fe9121b](https://github.com/wuespace/telestion-project-daedalus2/commit/fe9121b6139edc7dd465b955756974732bd8161d))
* Register MAVLink Registrar ([962a8ab](https://github.com/wuespace/telestion-project-daedalus2/commit/962a8ab747147d04c002d4485cb8ed69021db8b0))


### Features

* **database:** added working database implementation ([4ebf9b9](https://github.com/wuespace/telestion-project-daedalus2/commit/4ebf9b909e9950876b11aada5585c5d99a48b474))
* **widget:** Add placeholder widget ([82e9a49](https://github.com/wuespace/telestion-project-daedalus2/commit/82e9a494b719198165a7516c5f410fce84dbb29a))
* **widget:** Implement current values widget ([0b13d67](https://github.com/wuespace/telestion-project-daedalus2/commit/0b13d6768b3e47bd3c2106c951170a08e0f8d4f2))
* **widget:** Implement delay status and context based text color in current-values widget ([f0dab65](https://github.com/wuespace/telestion-project-daedalus2/commit/f0dab65bdb5e25c71956b737f54e1a53208223d7))
* **widget:** Initialize current values widget ([d6c40bd](https://github.com/wuespace/telestion-project-daedalus2/commit/d6c40bd6be8180dbdee8c42cde0a0f69e7ad82d9))
* Add MAVLink Message registrar for the parser to work ([ac761a3](https://github.com/wuespace/telestion-project-daedalus2/commit/ac761a37e2f6e6bf18728034fe37e4f7398ed17b))



# [0.3.0](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.2.1...v0.3.0) (2021-05-08)


### Bug Fixes

* **mavlink-messages:** Convert `SeedSystemT.java` encoding to UTF-8 ([e88b437](https://github.com/wuespace/telestion-project-daedalus2/commit/e88b4376157826e63224222489dc4c0fe69f4010))
* **mavlink-messages:** Fix wrong `int` types in Mavlink message record ([3596421](https://github.com/wuespace/telestion-project-daedalus2/commit/3596421dd6a12b63dcfd9b88ce10ac3637ec74cb))
* **message-splitter:** Use "reference" `byte` type for `ArrayList` ([5107644](https://github.com/wuespace/telestion-project-daedalus2/commit/5107644c14666192ff7f138ba0aed16409288642))


### Features

* **client:** Apply patches ([7cc41e9](https://github.com/wuespace/telestion-project-daedalus2/commit/7cc41e9de4be5007a81bda70bb9e6582120c1bd0))
* **message-splitter:** Implement a basic message splitter to extract potential MavLink packages from the "raw" input byte stream and re-publish those messages ([271d6ea](https://github.com/wuespace/telestion-project-daedalus2/commit/271d6ea7b1a94193d44a97141787a8b680307487))
* **message-transformer:** Add initial implementation for the `MessageTransformer` verticle ([554c81a](https://github.com/wuespace/telestion-project-daedalus2/commit/554c81a960dfaaf7468e229570e1d5efefb24785))
* **message-transformer:** Implement "beautiful" messages and republish `de.wuespace.telestion.project.daedalus2.mavlink.Drehtest` to the database in well-wrapped forms (unwrapping bit sequences) ([1d8f359](https://github.com/wuespace/telestion-project-daedalus2/commit/1d8f3595e9bb88146a16e0a0868ef584cdb8341c))
* **message-transformer:** Implement new `SystemT` message ([a035599](https://github.com/wuespace/telestion-project-daedalus2/commit/a0355990730e3fe3c612d91c08e3d2fa598aa37b))
* **message-transformer:** Rename `Status` to more precise `AvailableStatus` in preparation for `SystemStatus` implementation ([6c14c36](https://github.com/wuespace/telestion-project-daedalus2/commit/6c14c3674b85ce1e29371aa540518b6ce787bd32))
* Add MAVLink definitions and autogenerated files ([fa11ca4](https://github.com/wuespace/telestion-project-daedalus2/commit/fa11ca4d54e94134500c20402d6c2ac22f8bfdbb))



## [0.2.1](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.2.0...v0.2.1) (2021-05-06)


### Bug Fixes

* **application:** Docker binary working directory ([4611cdf](https://github.com/wuespace/telestion-project-daedalus2/commit/4611cdfe95e8e07c41cc1e45c7467cac5eae978a))



# [0.2.0](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.1.7...v0.2.0) (2021-05-06)


### Bug Fixes

* **application:** Verticle classnames matching new telestion-core package structure ([d724657](https://github.com/wuespace/telestion-project-daedalus2/commit/d7246578c7466abec7ff72832170502d19a9977e))
* **deps:** Fix java maven dependencies ([94e3165](https://github.com/wuespace/telestion-project-daedalus2/commit/94e3165a997d121e3d07eca4fe3e9220aa9fe3e3))
* **gradle:** Fix main class name ([0e649df](https://github.com/wuespace/telestion-project-daedalus2/commit/0e649df6b9d5a7aa5299d5b1d831d5d9cf02adc3))


### Features

* Add mavlink definitions and current Drehtest-Record ([baa1b52](https://github.com/wuespace/telestion-project-daedalus2/commit/baa1b52c2e9e5475784502cbf4f28008cffa1bef))



## [0.1.7](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.1.6...v0.1.7) (2021-05-02)


### Bug Fixes

* Docker images tags ([f276892](https://github.com/wuespace/telestion-project-daedalus2/commit/f27689221326d9db9d6ed1c7ecb7c0b38482fc15))



## [0.1.6](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.1.5...v0.1.6) (2021-05-02)


### Bug Fixes

* Add more tags to published docker images ([62a91f2](https://github.com/wuespace/telestion-project-daedalus2/commit/62a91f22f80b47f306568c2a3b3b64663876ed46))
* Client Build jobs don't receive the latest commits ([70f7aca](https://github.com/wuespace/telestion-project-daedalus2/commit/70f7aca4ee3cb33cf4d0f0b8260fb3bbcb6a79ed))



## [0.1.5](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.1.4...v0.1.5) (2021-05-02)


### Bug Fixes

* Docker build and publish path ([ad6d373](https://github.com/wuespace/telestion-project-daedalus2/commit/ad6d37321dab456f11c3d8c9c5f6fa6d2dc82561))
* Publish Client workflow updates package version ([50d4d0d](https://github.com/wuespace/telestion-project-daedalus2/commit/50d4d0d5071bbf26a9151af347e456ae084142fb))



## [0.1.4](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.1.3...v0.1.4) (2021-05-01)



## [0.1.3](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.1.2...v0.1.3) (2021-05-01)


### Bug Fixes

* Paths in workflows ([1ad8f9e](https://github.com/wuespace/telestion-project-daedalus2/commit/1ad8f9e5b9a7393fd4f4c0fc8c864ac63b681dcf))



## [0.1.2](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.1.1...v0.1.2) (2021-05-01)


### Bug Fixes

* Explicit set Dockerfile on build and push ([044ba2d](https://github.com/wuespace/telestion-project-daedalus2/commit/044ba2dca3fbc0f0e02d77e75c440120ffbfe145))
* Publish client binaries workflow ([4773d57](https://github.com/wuespace/telestion-project-daedalus2/commit/4773d5758029aa33830cff26ee2d39e7921dd6dd))



## [0.1.1](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.1.0...v0.1.1) (2021-05-01)


### Bug Fixes

* Workflow syntax and build-env file ([552df47](https://github.com/wuespace/telestion-project-daedalus2/commit/552df475cede2ee39c395e8944cc6567b5e4f86a))



# [0.1.0](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.0.5-test...v0.1.0) (2021-05-01)


### Features

* **client:** Initialize client using tc-cli ([757480e](https://github.com/wuespace/telestion-project-daedalus2/commit/757480e654643dbce38e44516ffa282b161e705d))
* Initialize project ([6f06987](https://github.com/wuespace/telestion-project-daedalus2/commit/6f0698706fb959bf53369b71aa3d6ce6781c03ed))
* Re-initialize Telestion project ([ce7ae31](https://github.com/wuespace/telestion-project-daedalus2/commit/ce7ae3153593d95a7a879848cec37301527b71f0))



## [0.0.5-test](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.0.4-test...v0.0.5-test) (2021-04-20)



## [0.0.4-test](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.0.3-test...v0.0.4-test) (2021-04-20)



## [0.0.3-test](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.0.3...v0.0.3-test) (2021-04-20)



## [0.0.2-test](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.0.1-test...v0.0.2-test) (2021-04-19)




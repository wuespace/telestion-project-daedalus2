# [1.7.0](https://github.com/wuespace/telestion-project-daedalus2/compare/v1.6.0...v1.7.0) (2023-02-12)


### Features

* **client/widget:** Add focus selector to Iridium map widget ([5dc1604](https://github.com/wuespace/telestion-project-daedalus2/commit/5dc1604c1fd86861241b285812513519ad5c3ffd))
* **client/widget:** Add legend to Iridium map widget ([e4fb049](https://github.com/wuespace/telestion-project-daedalus2/commit/e4fb04947ef286a57d20e86e110388bbabf97ebd))
* **client:** Increase Iridium packet timeouts further ([624bc66](https://github.com/wuespace/telestion-project-daedalus2/commit/624bc66c5ab3939e8ac29256b9aba70708e1bd73))
* **client:** Update format delta time to accept undefined and null values ([c738379](https://github.com/wuespace/telestion-project-daedalus2/commit/c738379494087cb788e66de96b9409d2e697e9fa))



# [1.6.0](https://github.com/wuespace/telestion-project-daedalus2/compare/v1.5.0...v1.6.0) (2023-02-11)


### Bug Fixes

* **client/widget:** Fix heater temps calculating wrong temperature ([978c08c](https://github.com/wuespace/telestion-project-daedalus2/commit/978c08cde5c22d8e503313d3da5b6c37e5c4b8d0))


### Features

* **client:** Update format delta time to show 'now' even if the received packet is 10 seconds from the future ([85e0d00](https://github.com/wuespace/telestion-project-daedalus2/commit/85e0d00a09890ff848b7e77c35480221e0bd8f05))



# [1.5.0](https://github.com/wuespace/telestion-project-daedalus2/compare/v1.4.0...v1.5.0) (2023-02-11)


### Bug Fixes

* **application:** Always reply to requests in Redis request handlers (even if no value is available for the requested storage slot) ([ca86e33](https://github.com/wuespace/telestion-project-daedalus2/commit/ca86e3312f42f000ce18f4b9abf842a86f9873d3))
* **client/widget:** Fix wrong delay time in Iridium map widget ([6d9b297](https://github.com/wuespace/telestion-project-daedalus2/commit/6d9b297b468acc0f9ce400ba7d73b5494abdd4be))


### Features

* **client/widget:** Display heater temperature if heater is not faulty in seed ECAM widget ([55b7f21](https://github.com/wuespace/telestion-project-daedalus2/commit/55b7f210ea421cff574efe0514103feaa9890987))
* **client/widget:** Increase abnormal timeout in Iridium timeline widget ([3278158](https://github.com/wuespace/telestion-project-daedalus2/commit/3278158b01f0835b90e9d9e35b853463c81b0357))
* **client/widget:** Remove invalid/possible dangerous state from state change widget ([7f4558e](https://github.com/wuespace/telestion-project-daedalus2/commit/7f4558e7f3c35913c9764ca7f4a82c5fdca3fb5c))
* **client:** Increase timeouts in connection status panel in Iridium mode ([3cecb85](https://github.com/wuespace/telestion-project-daedalus2/commit/3cecb8589cdca8f69f6fcbfc162bc93351a09416))



# [1.4.0](https://github.com/wuespace/telestion-project-daedalus2/compare/v1.3.0...v1.4.0) (2023-02-10)


### Features

* Trigger release pipeline ([884f213](https://github.com/wuespace/telestion-project-daedalus2/commit/884f213b83cb25cf778b7be6b0f25b0a38b81ced))



# [1.3.0](https://github.com/wuespace/telestion-project-daedalus2/compare/v1.2.1...v1.3.0) (2023-02-10)


### Features

* **application:** Update Iridium message injector to send both latest and valid positions ([85a1444](https://github.com/wuespace/telestion-project-daedalus2/commit/85a14441a942fb7e591d65521f835b753b413d53))
* **application:** Update iridium message types to match iridium-sbd-receiver and update message mapper to represent new document structure ([715ec54](https://github.com/wuespace/telestion-project-daedalus2/commit/715ec54df46941a9511ed9dfa0d5873c9f8685f1))
* **client/widget:** Add Iridium map widget ([f783a60](https://github.com/wuespace/telestion-project-daedalus2/commit/f783a6049504b6b7933855fcd7741e5df79e5e0a))
* **client/widget:** Add Iridium state widget derived from the original state widget ([62b5da9](https://github.com/wuespace/telestion-project-daedalus2/commit/62b5da9ae3fbce92f23b89919e02b94262b70918))
* **client/widget:** Add Iridium timeline widget derived from the original timeline widget ([2eba165](https://github.com/wuespace/telestion-project-daedalus2/commit/2eba1657d35d19a0b1d59d5fcc1cbf6e51b27da6))
* **client:** Add Iridium widgets to recovery dashboards and make them available via the `iridium` user ([ed9be1d](https://github.com/wuespace/telestion-project-daedalus2/commit/ed9be1da966d2ce64ca6b9f39c2acd395f3bf365))



## [1.2.1](https://github.com/wuespace/telestion-project-daedalus2/compare/v1.2.0...v1.2.1) (2023-02-03)


### Bug Fixes

* **client:** Remove payload appendix in recovery dashboard ([#399](https://github.com/wuespace/telestion-project-daedalus2/issues/399)) ([a47317d](https://github.com/wuespace/telestion-project-daedalus2/commit/a47317debf3af2ef0f2e22eba9f02fd8a5b97912))



# [1.2.0](https://github.com/wuespace/telestion-project-daedalus2/compare/v1.1.1...v1.2.0) (2023-02-03)


### Features

* **application:** Add Iridium Message Injector verticle for testing purposes to mock incoming Iridium data ([aebf98a](https://github.com/wuespace/telestion-project-daedalus2/commit/aebf98ace48acf2792582408ee4430f99cd1c04c))
* **client/widget:** Add Map widget and pin down dependencies ([c43055e](https://github.com/wuespace/telestion-project-daedalus2/commit/c43055e901d3fd65fe5a318d660b408ad98f8b0d))
* **client:** Add dockerfile to package client static html page and a release workflow that builds and pushes the docker image to the GitHub container registry ([2ff5f08](https://github.com/wuespace/telestion-project-daedalus2/commit/2ff5f08256cf42559b23681ab482308f609fdbd3))
* **client:** Add property to `config.json` to configure the UI based on information target (iridium/local) ([1c698ef](https://github.com/wuespace/telestion-project-daedalus2/commit/1c698efa8e128e51ed199aa82887459f728e0bc1))
* **client:** Add recovery dashboard and iridium user ([37510c0](https://github.com/wuespace/telestion-project-daedalus2/commit/37510c00743e77c3848e9805754b872395df4e64))



## [1.1.1](https://github.com/wuespace/telestion-project-daedalus2/compare/v1.1.0...v1.1.1) (2023-02-01)


### Bug Fixes

* **client/widget:** Fix styling issues ([13f4bda](https://github.com/wuespace/telestion-project-daedalus2/commit/13f4bdaa187c708679a9a22484c709c7738fa6a2))



# [1.1.0](https://github.com/wuespace/telestion-project-daedalus2/compare/v1.0.0...v1.1.0) (2023-02-01)


### Features

* **client/widget:** Add support for heater in Seed ECAM Widget ([03a523e](https://github.com/wuespace/telestion-project-daedalus2/commit/03a523ecfedb738c8e9c276f2c861e1e1b74fb7f))
* **model:** Remove checklist from electrical dashboard ([13cc963](https://github.com/wuespace/telestion-project-daedalus2/commit/13cc96302de5dc569f75eb17788201aaa848bb79))
* **model:** Remove checklist from overview dashboard ([60f1cb3](https://github.com/wuespace/telestion-project-daedalus2/commit/60f1cb34509044ffe04aab9e7a3e8055c671fbc0))
* Show elapsed time in seconds for 300s before switching to Minutes ([cf1f792](https://github.com/wuespace/telestion-project-daedalus2/commit/cf1f7924d193968135a81940e352dd738c40f1ff))
* Update Mavlink autogenerated code ([132e048](https://github.com/wuespace/telestion-project-daedalus2/commit/132e048b08f4413203a46ee5f2c46067b71f6f63))



# [1.0.0](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.31.1...v1.0.0) (2023-01-03)


* feat!: Bump to stable version ([7b44065](https://github.com/wuespace/telestion-project-daedalus2/commit/7b44065c2be35c4ac6e6dd87616358adb187402b))


### BREAKING CHANGES

* Bump to stable version



## [0.31.1](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.31.0...v0.31.1) (2023-01-03)



# [0.31.0](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.30.0...v0.31.0) (2022-03-10)


### Bug Fixes

* **application/tc-counter:** Fix using wrong configuration in `TelestionCounter` for reset address ([f11a5ca](https://github.com/wuespace/telestion-project-daedalus2/commit/f11a5cae50c2ec5c79b0ced44812dd363f0c10f2))


### Features

* **application:** Add `RawFileLogger` Verticle ([4e75f0e](https://github.com/wuespace/telestion-project-daedalus2/commit/4e75f0e961b4d39197fb6955ed1ff73b5868f9e4))
* **application:** Add reset option to TC counter verticle ([a7af10d](https://github.com/wuespace/telestion-project-daedalus2/commit/a7af10d34e039a322ce64bb1f86cdb18dfd3b3cc))
* **client:** Add TC Counter types ([435880a](https://github.com/wuespace/telestion-project-daedalus2/commit/435880a7b35acd125f4275e1d76a2fa5a4ef585c))



# [0.30.0](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.29.2...v0.30.0) (2022-01-15)


### Features

* **client:** Add timeline widget to all dashboards ([cdb8b09](https://github.com/wuespace/telestion-project-daedalus2/commit/cdb8b093f0ccff14ef338a6c8a832b1cb1d87e3e))
* **widget:** Add timeline widget ([cfe4fa4](https://github.com/wuespace/telestion-project-daedalus2/commit/cfe4fa46e0b14bbdaee0924ffa21e712479c6b2d))



## [0.29.2](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.29.1...v0.29.2) (2022-01-15)


### Bug Fixes

* **widget:** Fix log messages splitter in TC console widget ([559374f](https://github.com/wuespace/telestion-project-daedalus2/commit/559374f89b9942eb77e6e624536ca4b6cb6c43f0))



## [0.29.1](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.29.0...v0.29.1) (2022-01-14)


### Bug Fixes

* **widget:** Fix an error that happens when no log messages are present for a source in TC console widget ([d940b05](https://github.com/wuespace/telestion-project-daedalus2/commit/d940b055fe8a06177251d1655fe8b1d217c12aec))



# [0.29.0](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.28.0...v0.29.0) (2022-01-14)


### Bug Fixes

* **application:** Fix in addresses in configuration for Redis and MongoDB verticles not capturing the received log messages via MavLink ([51909ec](https://github.com/wuespace/telestion-project-daedalus2/commit/51909ec18663defb2ec8c90469cc002c44c77754))


### Features

* **application:** Send out all log information separately in TC console verticle ([51d9f45](https://github.com/wuespace/telestion-project-daedalus2/commit/51d9f45ee9025c5a58310ee33968467917ee6e4a))
* **widget:** Update TC console widget now handling the rendering of the message and support for show all boots ([964586b](https://github.com/wuespace/telestion-project-daedalus2/commit/964586bb17dd8424f42d5cedfc82e598bffeb8e1))



# [0.28.0](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.27.0...v0.28.0) (2022-01-13)


### Features

* **widget:** Add auto-scroll checkbox to switch scroll-to-bottom on and off ([5dede8a](https://github.com/wuespace/telestion-project-daedalus2/commit/5dede8a22c0891e555324212e3dcd5e4f64b8c72))



# [0.27.0](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.26.1...v0.27.0) (2022-01-13)


### Features

* **application:** Add receive time and time_local to log message in telecommand console ([412a143](https://github.com/wuespace/telestion-project-daedalus2/commit/412a143c988313ae310988bc0d5409476b2ec521))
* **application:** Trim received log message before publishing in Mavlink TM parser ([019769d](https://github.com/wuespace/telestion-project-daedalus2/commit/019769dd460fef1aa49ab0a98a9e8ff8f421db1e))
* **widget:** Allow multiple sending of the same telecommand in TCSendButton ([503500a](https://github.com/wuespace/telestion-project-daedalus2/commit/503500afd9aacf0e1020801122181f43b161ae89))
* **widget:** Enable smooth scrolling in TM/TC window ([65cc54b](https://github.com/wuespace/telestion-project-daedalus2/commit/65cc54b65564e2e3769eaebc7aa256e45bee0e0a))



## [0.26.1](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.26.0...v0.26.1) (2022-01-12)


### Bug Fixes

* **widget:** Fix display `N/A` on `0` telecommands in TC counter bar in TC console widget ([83451a1](https://github.com/wuespace/telestion-project-daedalus2/commit/83451a17a0c928922c7af5704f4655373fae12eb))



# [0.26.0](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.25.0...v0.26.0) (2022-01-12)


### Features

* **application:** Add clear and clear all support in Telecommand console ([d08ca37](https://github.com/wuespace/telestion-project-daedalus2/commit/d08ca37309c4d92d3b6891dbe34473e9bce0c4a6))
* **widget:** Add clear and clear all support in TC console widget ([61e90dc](https://github.com/wuespace/telestion-project-daedalus2/commit/61e90dc5caf37bcf85e9ff517434878e3dfc9eb0))



# [0.25.0](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.24.0...v0.25.0) (2022-01-12)


### Bug Fixes

* **application:** Add TC Console addresses to permitted inbound and unbound addresses via EventbusTcpBridge ([76af0dd](https://github.com/wuespace/telestion-project-daedalus2/commit/76af0dd061a28d0f838318c0133ab849b88b5e3b))
* **application:** Encode and decode String array manually because Vert.x shared data storage cannot ([aa4cdad](https://github.com/wuespace/telestion-project-daedalus2/commit/aa4cdada845f1cf507d0c6595fc8b156e0073ed3))
* **application:** Move `JsonMessage.on` into one register call because send will use only one registered handler regardless which verticle and which handler. (see https://vertx.io/docs/vertx-core/java/#_sending_messages) ([979ac4c](https://github.com/wuespace/telestion-project-daedalus2/commit/979ac4cfcbb68e3005c249b9c31de184f2b689d0))
* **client:** Fix Telecommand class name due to previous refactor (see v0.6.2 integration) ([35e446a](https://github.com/wuespace/telestion-project-daedalus2/commit/35e446a1fae3abec2d20cd0c9157c6092158e1c3))


### Features

* **application:** Add log messages to D2 TM simulator ([09375d1](https://github.com/wuespace/telestion-project-daedalus2/commit/09375d15b99b6f2e764a563bc02418506035f14d))
* **application:** Add Telecommand console to config.json ([035dbf4](https://github.com/wuespace/telestion-project-daedalus2/commit/035dbf4440296f7aae2fb622b724a3bf483be7bd))
* **application:** Implement Telecommand console verticle and message types ([e1c7190](https://github.com/wuespace/telestion-project-daedalus2/commit/e1c7190f8a68d3aa6e1b7b593cf31b577a96fb00))
* **client:** Add client side type definitions for message types ([3d42ced](https://github.com/wuespace/telestion-project-daedalus2/commit/3d42ced7a9d7232b2326b5f579429a02d4c6891e))
* **client:** Add core-utils from `telestion-client-core` ([65fc385](https://github.com/wuespace/telestion-project-daedalus2/commit/65fc3857ca7ba3d50ed8187dbe2993eb9eabd198))
* **client:** Add some utility functions to `tc-console.ts` ([c84a0c3](https://github.com/wuespace/telestion-project-daedalus2/commit/c84a0c3f81a006e856bc82d94953cc062e1755ae))
* **client:** Add TC console widget ([784dffc](https://github.com/wuespace/telestion-project-daedalus2/commit/784dffcf45fbaae1a707a90951118bcfb4fcab0b))
* **client:** Clean up telecommand dashboards ([e7d1f93](https://github.com/wuespace/telestion-project-daedalus2/commit/e7d1f93a7e6b6ce88c69cff4bb2e8d4fa79eea7f))
* **client:** Update connection status panel timeouts ([f690a15](https://github.com/wuespace/telestion-project-daedalus2/commit/f690a1574334f8767f651d4e784488d00dc32dbd))
* Migrate project to Telestion-Core v0.6.2 ([b3fc79a](https://github.com/wuespace/telestion-project-daedalus2/commit/b3fc79a99088b521203130883ddd9edcd1e46fe7))
* Update MavLink XML definition and generated sources for Java and python ([d8bba27](https://github.com/wuespace/telestion-project-daedalus2/commit/d8bba27eda856a659206d92b5ddc070503ea63f9))
* **widget:** Finalize TC console widget ([0c58686](https://github.com/wuespace/telestion-project-daedalus2/commit/0c58686635a1bc542fb90526cfa1068c882612e8))



# [0.24.0](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.23.0...v0.24.0) (2022-01-02)


### Features

* **application:** Update configuration which adds telecommands to checklist ([f9f319b](https://github.com/wuespace/telestion-project-daedalus2/commit/f9f319b4cf4d19007f9e762925750a7813e9416a))
* **client:** Add connection status panel to the left side of the header which displays the connection status of Seed A, Seed B and Ejector ([81dbb42](https://github.com/wuespace/telestion-project-daedalus2/commit/81dbb4297c1017acea81a5ad6e86fb671ca094dc))
* **d2-sim:** Clean up telemetry and add some basic functionality like automatic state cycling and correct `time_local` and `d2time` ([51178d6](https://github.com/wuespace/telestion-project-daedalus2/commit/51178d69ba660ac4a6caf66cfb21ee2db34ecb18))
* **widget:** Update `state-widget` which now displays the last received time and state id next to the state name. The state name now comes without a "k" prefix. Ejector and Seed states are now separated. The state change widget selection now depends on the target. (ejector or seed) ([95b9757](https://github.com/wuespace/telestion-project-daedalus2/commit/95b97574fb97755cafdf96d433e0c52c6e4eba91))



# [0.23.0](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.22.2...v0.23.0) (2021-12-17)


### Features

* **client:** Add configuration that loads synchronous with the initial page and reads the `config.json` in the webroot to initially configure the web client ([f82a10d](https://github.com/wuespace/telestion-project-daedalus2/commit/f82a10d55a569c4714285d5cfa897a7b4f9cd833))



## [0.22.2](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.22.1...v0.22.2) (2021-12-17)


### Bug Fixes

* **ci:** Add `docker-compose` postfix to published setup archive ([8ae351f](https://github.com/wuespace/telestion-project-daedalus2/commit/8ae351f36a51b6813b9a59f18ce6fadf5272ba2f))



## [0.22.1](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.22.0...v0.22.1) (2021-12-16)


### Bug Fixes

* **ci:** Fix upload issues in release actions ([2693d37](https://github.com/wuespace/telestion-project-daedalus2/commit/2693d3774a4a46be633d3367e394a051201f8265))



# [0.22.0](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.21.0...v0.22.0) (2021-12-16)


### Features

* **ci:** Add step to compress and upload client build as static webpage ([d186945](https://github.com/wuespace/telestion-project-daedalus2/commit/d1869453e84dc9e3376044d3dc3c135b1cbb8e7d))



# [0.21.0](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.20.1...v0.21.0) (2021-12-09)


### Features

* **application:** Add assist-now binary exporter to D2 TM simulator ([7870c81](https://github.com/wuespace/telestion-project-daedalus2/commit/7870c81766d8c493dab9d55e09360da93be2f904))
* **application:** Publish parser stats to redis in `mavlink/parser` section ([57f4b3e](https://github.com/wuespace/telestion-project-daedalus2/commit/57f4b3e528f6d43f5c66f8e78de6663ff919eb8a))
* **application:** Update checklist configuration ([5916673](https://github.com/wuespace/telestion-project-daedalus2/commit/5916673fecb1ec4f80febafd932dd9ae15009947))
* **client:** Add current values dashboard ([1daf155](https://github.com/wuespace/telestion-project-daedalus2/commit/1daf155ef0ec5d418ed7ae3cd89edd929e7e1efc))



## [0.20.1](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.20.0...v0.20.1) (2021-12-09)


### Bug Fixes

* **application:** Fix Telecommand Sender verticle correctly casting a signed byte array to an unsigned short array to successfully send Assist Now Upload Mavlink messages with valid payload ([050984a](https://github.com/wuespace/telestion-project-daedalus2/commit/050984a07f925ca3b333bd43804690dfcb45205c))



# [0.20.0](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.19.0...v0.20.0) (2021-12-08)


### Bug Fixes

* **application:** Regenerate Mavlink code again ([e61d356](https://github.com/wuespace/telestion-project-daedalus2/commit/e61d356e8e9f5ad4d1a1cec81a560b674248bebd))
* **client:** Fix widget ids in Telecommand dashboard ([b05377e](https://github.com/wuespace/telestion-project-daedalus2/commit/b05377eead0ed80edd43a2d05493ab525c8e6d9f))


### Features

* **application:** Print source id on telecommand in D2 TM simulator ([271131e](https://github.com/wuespace/telestion-project-daedalus2/commit/271131ee0ce7701f40e94db2c433bcf489c6c543))



# [0.19.0](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.18.0...v0.19.0) (2021-12-08)


### Bug Fixes

* **application:** Regenerate Mavlink code to fix generation from last version ([6df2998](https://github.com/wuespace/telestion-project-daedalus2/commit/6df299852e894c646f44304c070ac9022ab2a0c6))


### Features

* **application:** Log Mavlink parser stats after every received message chunk ([201201a](https://github.com/wuespace/telestion-project-daedalus2/commit/201201a4f07fc74e1e2e41250002283400457889))



# [0.18.0](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.17.0...v0.18.0) (2021-12-08)


### Bug Fixes

* **application:** Add Message Chunker information to Redis check list ([716bfa2](https://github.com/wuespace/telestion-project-daedalus2/commit/716bfa27852c673960b72b69b3104ef50dd65d18))


### Features

* **application:** Add Telecommand Generator verticle to generate a bunch of telecommands ([08ac4df](https://github.com/wuespace/telestion-project-daedalus2/commit/08ac4dfbd0817072ba63b019a7c968ab7eb50297))
* **application:** Update D2 TM simulator ([c6c50b0](https://github.com/wuespace/telestion-project-daedalus2/commit/c6c50b0ab318a146ff977db0ada7867326d7995b))
* **application:** Update mavlink definition file and regenerate python and Java files ([171eac8](https://github.com/wuespace/telestion-project-daedalus2/commit/171eac877dfa09019fef6f2edaa15389137d9bde))
* **client:** Add debug dashboard with Eventbus debug widget to user config ([4d4aff1](https://github.com/wuespace/telestion-project-daedalus2/commit/4d4aff12f852cec35ff50cd5942d7519106aa2a9))
* **client:** Add ejector camera widget for `camrec` and `camled` TCs to the ejector ([75b1ad5](https://github.com/wuespace/telestion-project-daedalus2/commit/75b1ad5c23dc4cf497434fe3e6a60726fb230868))
* **client:** Add radio silence and ejector camera widgets to the Telecommands dashboard ([65d27d5](https://github.com/wuespace/telestion-project-daedalus2/commit/65d27d5b01226eb41499be737cbc1d93c735de93))
* **client:** Add radio silence widget to easily trigger and abort the radio silence sequence using buttons (with confirmation dialogs) ([d177a57](https://github.com/wuespace/telestion-project-daedalus2/commit/d177a5743404344b3c4409f713a9f3a4d5c6db43))
* **client:** Remove outdated/invalid default values for the current values and graph widget ([400bbbe](https://github.com/wuespace/telestion-project-daedalus2/commit/400bbbe302676f486c8dc34e2045eebd3dddaca0))
* **widget:** Filter out invalid and entry and exit states from state change widget ([3d54375](https://github.com/wuespace/telestion-project-daedalus2/commit/3d5437526c63ff0bdf0baee3a7dabc3a8f5ba4b5))



# [0.17.0](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.16.4...v0.17.0) (2021-12-02)


### Features

* Add updated MavLink definition for Daedalus2 ([d51bed6](https://github.com/wuespace/telestion-project-daedalus2/commit/d51bed639ea0681005d0626fec432359e36b255a))
* **application:** Add Message Chunker with updated configuration ([bc325bc](https://github.com/wuespace/telestion-project-daedalus2/commit/bc325bcf48654982c30cbec620b7aac0ade1cc79))



## [0.16.4](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.16.3...v0.16.4) (2021-12-01)


### Bug Fixes

* **ci:** Remove zip and upload part for generated client build because it does not work :trollface: ([1b29557](https://github.com/wuespace/telestion-project-daedalus2/commit/1b29557579cd13878a3012aaefcd8dfa7a2593e2))



## [0.16.3](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.16.2...v0.16.3) (2021-12-01)


### Bug Fixes

* **ci:** Fix release workflow by adding one step more ([762a731](https://github.com/wuespace/telestion-project-daedalus2/commit/762a731edd951ef264420dca5102a5fe58107e1b))



## [0.16.2](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.16.1...v0.16.2) (2021-12-01)


### Bug Fixes

* **ci:** Fix release workflow again ([b4dba66](https://github.com/wuespace/telestion-project-daedalus2/commit/b4dba665994e458aa4c809c9d539e16c502cdaae))



## [0.16.1](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.16.0...v0.16.1) (2021-12-01)


### Bug Fixes

* **ci:** Fix invalid replace pattern in client release workflow ([b6174eb](https://github.com/wuespace/telestion-project-daedalus2/commit/b6174eb2f6e898322e65626b5f5ffe8350bb5e81))



# [0.16.0](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.15.1...v0.16.0) (2021-12-01)


### Bug Fixes

* **application:** Fix bug where uploaded data which is not Base64 encoded throws and breaks the verticle ([8945c66](https://github.com/wuespace/telestion-project-daedalus2/commit/8945c666fb8247647aaf7961759c9a8cb1dabda8))
* **application:** Fix Iridium Message Mapper not sending receive time in header resulting in errors in Redis Saver ([c98a29b](https://github.com/wuespace/telestion-project-daedalus2/commit/c98a29b96f4a586d891b012dd605785ef551b543))
* **application:** Increase size of integer elements in IE header to prevent unsigned integer overflow ([1da78b7](https://github.com/wuespace/telestion-project-daedalus2/commit/1da78b79ac6be7580b0db70a4fbbfc8cbeceedd6))
* **client:** Add Client side implementation to handle uploaded data which can be invalid ([3903377](https://github.com/wuespace/telestion-project-daedalus2/commit/3903377ab78960c65b44cc8b183d1b8c0f893c5f))
* **client:** Fix several small visual issues in the Seed ECAM Widget ([c2c9f61](https://github.com/wuespace/telestion-project-daedalus2/commit/c2c9f6102f8faf2b29a741825fd36128fbb52fd6))
* **widget:** Fix title overflow in A-GPS Upload widget ([c98d408](https://github.com/wuespace/telestion-project-daedalus2/commit/c98d408b765fff517a0e4f61eef9de300919ba91))


### Features

* **application:** Add Assist Now generator script ([4159e7c](https://github.com/wuespace/telestion-project-daedalus2/commit/4159e7cd10ba7d3f04f72325e34322efa4f14e32))
* **application:** Add iridium message channels to Redis and Mongo saver ([0de6e4f](https://github.com/wuespace/telestion-project-daedalus2/commit/0de6e4f71b5163ca233e789275c8cb273f2e030b))
* **application:** Add message mapper verticle to map iridium messages to redis readable data ([9a95452](https://github.com/wuespace/telestion-project-daedalus2/commit/9a954523019129c79f528e06d8c4423699688d03))
* **application:** Add Telecommand Counter verticle ([d535a90](https://github.com/wuespace/telestion-project-daedalus2/commit/d535a9018bae8916235090a52e03d80adabcd03e))
* **application:** Partially rewrite TelecommandSender verticle to send different MAVLink messages based on Telecommand Messages ([f3f4598](https://github.com/wuespace/telestion-project-daedalus2/commit/f3f459842233316f29ae0ae96ad05cb2b4199544))
* **client:** Add current D2 mission time in header ([72a828d](https://github.com/wuespace/telestion-project-daedalus2/commit/72a828ddbbbf65e761f259bf6774f863d50f8055))
* **client:** Add State change widget ([de12b15](https://github.com/wuespace/telestion-project-daedalus2/commit/de12b15a76a81925ccb71ab403f2f5f4961c749f))
* **client:** Move and refactor states from State widget to model ([38ed96c](https://github.com/wuespace/telestion-project-daedalus2/commit/38ed96c272817352c34eb2a42bd3cd194d23376c))
* **client:** Refactor and clean up state widget and make use of the new state definitions ([be5cded](https://github.com/wuespace/telestion-project-daedalus2/commit/be5cded73d03f8028b3c2955b964b10cd29ecadb))



## [0.15.1](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.15.0...v0.15.1) (2021-10-30)


### Bug Fixes

* **application:** Send time in seconds instead of milliseconds in TimeSync TC ([9b7a8af](https://github.com/wuespace/telestion-project-daedalus2/commit/9b7a8af1ccaf271a70d8b64d1caa15952465f1ec))
* **client:** Adjust Seed ECAM widget to new data structure ([254d571](https://github.com/wuespace/telestion-project-daedalus2/commit/254d5711054ea1715a6fc88b7cfde20efc0939b1))



# [0.15.0](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.14.3...v0.15.0) (2021-10-29)


### Bug Fixes

* **client:** Fix critical bug where Seed ECAM widget wouldn't load if the main bus voltage was `0` ([2b18a3d](https://github.com/wuespace/telestion-project-daedalus2/commit/2b18a3d7b90548d149a8558a590b623ac4e4f242))


### Features

* **application:** Add Iridium Payload type definitions ([b6422e5](https://github.com/wuespace/telestion-project-daedalus2/commit/b6422e54bfec39e55d4daa132bc6403ae1bd8d9f))
* **application:** Integrate new MavLink definitions ([aaa5e0e](https://github.com/wuespace/telestion-project-daedalus2/commit/aaa5e0e1c95fe63fc5d2cb5306c207b28a432284))
* **client:** Add battery heating TC widget that sends the `batheattarget <celsius>` CON_CMD to either of the seeds ([fa56fe5](https://github.com/wuespace/telestion-project-daedalus2/commit/fa56fe5d1fe489ee6067d76cdfbba4098100ebbd))
* **client:** Add Iridium Payload type definitions ([9c9a189](https://github.com/wuespace/telestion-project-daedalus2/commit/9c9a189d6c68703937d2793eaff127c6580ba55f))



## [0.14.3](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.14.2...v0.14.3) (2021-10-12)


### Bug Fixes

* **client:** Use SYSTEM_T instead of HEARTBEAT for standard widget config in preparation for the test on the 14th of October 2021 ([78be104](https://github.com/wuespace/telestion-project-daedalus2/commit/78be104a33c3b143fe740dc7c231721ff5978f66))



## [0.14.2](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.14.1...v0.14.2) (2021-10-12)


### Bug Fixes

* **application:** Remove profiles from production docker-compose configuration ([1b74879](https://github.com/wuespace/telestion-project-daedalus2/commit/1b748793002d1b3b5de4ab615f03c6cb18f53c00))



## [0.14.1](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.14.0...v0.14.1) (2021-10-12)


### Bug Fixes

* Add publish protection on skipped releases ([5d50eca](https://github.com/wuespace/telestion-project-daedalus2/commit/5d50ecab5d2b5252101811cb7c5b4d707575e54e))



# [0.14.0](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.13.1...v0.14.0) (2021-10-12)


### Bug Fixes

* **application:** Export locally TCP server for incoming Iridium messages ([7ba70bc](https://github.com/wuespace/telestion-project-daedalus2/commit/7ba70bcb5a01178162f7847ff5ede6b96e830504))
* **application:** Fix problems in `RedisRequestHandler` verticle ([cc2be0f](https://github.com/wuespace/telestion-project-daedalus2/commit/cc2be0fbe4a2c71c87a121d981f547957d0fde07))
* **application:** Use default configurations in `RedisVerticle` ([816841e](https://github.com/wuespace/telestion-project-daedalus2/commit/816841e06c830b837e60a6fe0ea9e1d6b0505dfd))
* **application:** Use the right event bus channels and catch more exceptions ([096177e](https://github.com/wuespace/telestion-project-daedalus2/commit/096177e92aabbeb93c9c741e4a650bf34e3f5677))
* **client:** Add missing `key` props in various widgets to avoid console warnings ([5fbce60](https://github.com/wuespace/telestion-project-daedalus2/commit/5fbce60e210f7616ae26f021e5d000efe81d2a20))
* **client:** Fix further potential issues in Redis hooks ([63d1b80](https://github.com/wuespace/telestion-project-daedalus2/commit/63d1b808a344a25f46e985826c77b6df8a3e67ef))
* **client:** Fix issue where checkbox widget navigation wouldn't register clicks (sometimes) ([e73551f](https://github.com/wuespace/telestion-project-daedalus2/commit/e73551f5e70a035dd449bb2579e9010de8f045a3))
* **client:** Fix issues with various React Spectrum components, most notably the ComboBox in the graph configuration. ([68620bd](https://github.com/wuespace/telestion-project-daedalus2/commit/68620bde599e1a69ce644234e235f89782beef6f))
* **client:** Fix layout and user experience ([f719698](https://github.com/wuespace/telestion-project-daedalus2/commit/f719698936a86db0b6695c1e03a4716666d28a47))
* **client:** Fix various minor issues in Redis hooks ([65a2714](https://github.com/wuespace/telestion-project-daedalus2/commit/65a2714313301254fd459cf4b037577f66297328))
* **client:** Fixed a bug where the Seed ECAM widget would crash after a "once connected" event bus got disconnected, requiring a Widget reload (in the standard widget error screen) to reload the widget ([3db883a](https://github.com/wuespace/telestion-project-daedalus2/commit/3db883acab460c0177276eb85eb9f1bb5be0f97d))
* **client:** Make return type of `useCacheLatest` "undefinable" ([811f531](https://github.com/wuespace/telestion-project-daedalus2/commit/811f53135980e240cac159fb0e9c94e383543ac9))
* **client:** Make state widget columns the same width ([d525a45](https://github.com/wuespace/telestion-project-daedalus2/commit/d525a4557852659c4738ecb46873eb8be1f1f21c))


### Features

* **application:** Add "introspection" feature to `RedisRequestHandler`, allowing to ask for available Redis keys ([6235ea8](https://github.com/wuespace/telestion-project-daedalus2/commit/6235ea81191c191a1bd38cf4116177439fcfd6f7))
* **application:** Add `RedisRequestHandler` verticle to request both aggregations (time series) and latest (key-value-based) data from the Redis DB ([4c844f6](https://github.com/wuespace/telestion-project-daedalus2/commit/4c844f612768a971c476c90e5f130a3a464b78cf))
* **application:** Add `RedisSaver` verticle to save arbitrary data in the Redis DB ([bce6456](https://github.com/wuespace/telestion-project-daedalus2/commit/bce64560a8df2fb080fc7103c40966bed4922ed3))
* **application:** Add `TimeSync` verticle for handling time sync TCs ([4309084](https://github.com/wuespace/telestion-project-daedalus2/commit/430908476f4d4d1fbcd9cb28d55282e96a128b75))
* **application:** Add a `ChecklistManager` verticle ([e9affc6](https://github.com/wuespace/telestion-project-daedalus2/commit/e9affc64f588a7654823f10626decaed39dd49b7))
* **application:** Add A-GPS transmitter verticle ([54703c7](https://github.com/wuespace/telestion-project-daedalus2/commit/54703c7f13c5fda7862166bed1675de38cc5c762))
* **application:** Add A-GPS Transmitter verticle to default configuration ([123ea19](https://github.com/wuespace/telestion-project-daedalus2/commit/123ea199ab0450855cc72afcda2ecda6f161de43))
* **application:** Add initial Redis setup ([3dbf035](https://github.com/wuespace/telestion-project-daedalus2/commit/3dbf035299b0921c9d8b934475c7ebd22cda0026))
* **application:** Add Iridium message parser verticle ([b42c723](https://github.com/wuespace/telestion-project-daedalus2/commit/b42c723e0df1445923491ff69d89c657183efcca))
* **application:** Add message definitions for Iridium messages ([ae9bb6c](https://github.com/wuespace/telestion-project-daedalus2/commit/ae9bb6c865ee1f0b865795b0e2919fb7d878580f))
* **application:** Add message request and response definitions for the A-GPS verticle ([bd32de9](https://github.com/wuespace/telestion-project-daedalus2/commit/bd32de92a0ffedab41240d8dc5a63a9ae1dc578e))
* **application:** Add raw telecommand message and handle raw telecommands in TelecommandSender verticle ([6212ddd](https://github.com/wuespace/telestion-project-daedalus2/commit/6212dddc9245951c8484a4e6a95f1270f08a42f0))
* **application:** Add redis to production docker-compose file ([43842e5](https://github.com/wuespace/telestion-project-daedalus2/commit/43842e5a0eee8947441fdc81a5717e714a764ecf))
* **application:** Adjust D2 Simulator to also print receives TCs to the console ([5e98174](https://github.com/wuespace/telestion-project-daedalus2/commit/5e9817497d8d23f19f65bbe5ac38b0dd18a01f95))
* **application:** Adjust MongoDB configuration and verticles ([5ca0eb7](https://github.com/wuespace/telestion-project-daedalus2/commit/5ca0eb716e3844717ea3bfced2b21c293ab4ccda))
* **application:** Change restart rules to `unless-stopped` ([a3022f5](https://github.com/wuespace/telestion-project-daedalus2/commit/a3022f568187fe712289f29f5f6c36c03acd5672))
* **application:** Integrate new MavLink definitions ([0e46539](https://github.com/wuespace/telestion-project-daedalus2/commit/0e465392e0b6358bd51f2018e3153dcfbf0f338b))
* **application:** Log warnings if a Redis error occurs ([8a83fed](https://github.com/wuespace/telestion-project-daedalus2/commit/8a83fedcef3caec33b94b179900e7b63414bc36b))
* **application:** Make `RedisSaver` verticle compatible with listening to different addresses for different message types ([71d4fa9](https://github.com/wuespace/telestion-project-daedalus2/commit/71d4fa938e75ab2058e5edf71ea7b1f5ce94bb5b))
* **application:** Optimize log output for `TelecommandSender` ([3041bfd](https://github.com/wuespace/telestion-project-daedalus2/commit/3041bfdd64d0dac92ecab9172fa207b6cbd0f021))
* **application:** Regenerate MavLink mappings with more recent generator version ([85648a4](https://github.com/wuespace/telestion-project-daedalus2/commit/85648a47166bbdd8de1d1a35ca747ea0125f5aff))
* **application:** Release production docker-compose with full image tag instead of `latest` ([3f93437](https://github.com/wuespace/telestion-project-daedalus2/commit/3f93437a0e228755bd999d310f458d3bb0e030b2))
* **application:** Reply on success or failure in `TelecommandSender` ([c35a0e3](https://github.com/wuespace/telestion-project-daedalus2/commit/c35a0e329043cfbfc7c89da4c38893251f7fea2e))
* **application:** Update MavLink implementations ([c4c6abc](https://github.com/wuespace/telestion-project-daedalus2/commit/c4c6abc8c37562a856ef4309398259fd95357fe5))
* **application:** Update verticle configuration to handle Iridium messages ([20548f0](https://github.com/wuespace/telestion-project-daedalus2/commit/20548f0ba65e7668b875bc8aad3905aa2e0e6a17))
* **client:** Add `useRequestRedisTimeSeries`, `useRequestRedisTimeSeriesState` and `useRequestLatest` hooks to query the Redis DB ([5b027e2](https://github.com/wuespace/telestion-project-daedalus2/commit/5b027e213837fe1454cf2c1c8c45787abe029b5c))
* **client:** Add Checklist widget ([0ccca8b](https://github.com/wuespace/telestion-project-daedalus2/commit/0ccca8b9d470cff100fd8e465038a54fd50dc843))
* **client:** Add client-side of A-GPS data transmission ([031d6e5](https://github.com/wuespace/telestion-project-daedalus2/commit/031d6e5819a8751669e629eeb82990ab44f27c8b))
* **client:** Add initial Seed ECAM widget implementation. ([1a7ec75](https://github.com/wuespace/telestion-project-daedalus2/commit/1a7ec7569a2f0b4cf1c04c5a1ae31f2f181c5703))
* **client:** Add Iridium mongo collection message types ([b186391](https://github.com/wuespace/telestion-project-daedalus2/commit/b1863914df08cce72002d89f190e6951bb4fdc37))
* **client:** Add manual time series request widget ([b284288](https://github.com/wuespace/telestion-project-daedalus2/commit/b2842889bfe4485755588b6c78fcea45e8c06bc6))
* **client:** Add time sync widget for time sync TCs ([a70f092](https://github.com/wuespace/telestion-project-daedalus2/commit/a70f092b6e4357424d3523d1e92cd19b253b22a4))
* **client:** Add user feedback for sending telecommands in the `freitext-tc-widget`. ([38c8c0d](https://github.com/wuespace/telestion-project-daedalus2/commit/38c8c0d5e7d6058b73acef5c73384d519d2a76c5))
* **client:** Add user feedback to button telecommands ([c40a349](https://github.com/wuespace/telestion-project-daedalus2/commit/c40a34976588c5a39206e1e7a6a109fb71393d84))
* **client:** Connect Seed ECAM widget to "real" TM data ([5684b51](https://github.com/wuespace/telestion-project-daedalus2/commit/5684b5111cc5d80d579160e44051293bea115769))
* **client:** Enhance `current-values-widget` configuration UX ([a1f5176](https://github.com/wuespace/telestion-project-daedalus2/commit/a1f5176ad3659db2b3a1cc653463a863501076c0))
* **client:** Implement Redis-optimized alternative Graph widget `simple-graph-widget` based on `react-vis` ([9d05fac](https://github.com/wuespace/telestion-project-daedalus2/commit/9d05facae46cd287011197c657066b4d9cdb8071))
* **client:** Make dark theme the default and save preference ([9a45de6](https://github.com/wuespace/telestion-project-daedalus2/commit/9a45de6b4a3ef78129533c5c96ebb5261f91f7e5))
* **client:** Migrate `current-values-widget` to use the Redis DB ([488fae8](https://github.com/wuespace/telestion-project-daedalus2/commit/488fae8ad81f18c7ee133d5da06d33a02da15935))
* **client:** Migrate the state machine widget to Redis-based data requests, adjust it for two seeds, and add data source configurability ([c8c1cbb](https://github.com/wuespace/telestion-project-daedalus2/commit/c8c1cbb1cd6eb18d0b02f4f594c1181cd579e4d4)), closes [#108](https://github.com/wuespace/telestion-project-daedalus2/issues/108)
* **client:** Update and add more dashboards with preconfigured widgets ([48be14e](https://github.com/wuespace/telestion-project-daedalus2/commit/48be14e1a8e3b80095dbade17869168da269b927))
* **d2-sim:** Clean up D2 Simulator ([b78e030](https://github.com/wuespace/telestion-project-daedalus2/commit/b78e0302549372f68c9e387ea358a888c3f75bd3))



## [0.13.1](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.13.0...v0.13.1) (2021-08-20)


### Bug Fixes

* **client:** Fix package version in `package.json` ([1dfbf3e](https://github.com/wuespace/telestion-project-daedalus2/commit/1dfbf3e0fb241decd7795014f2e9eeac95be3bca))



# [0.13.0](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.12.0...v0.13.0) (2021-08-20)


### Features

* **client:** Add performance action ([4be6388](https://github.com/wuespace/telestion-project-daedalus2/commit/4be6388246b7b31bb075447fef135612341e9e2b))



# [0.12.0](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.11.2...v0.12.0) (2021-08-15)


### Features

* Add nextState (replace the place holders) ([54cc952](https://github.com/wuespace/telestion-project-daedalus2/commit/54cc952533ed40ecbba6331c0eac4aa9db6b2bbb))
* Add state machine widget ([6455c9b](https://github.com/wuespace/telestion-project-daedalus2/commit/6455c9bcc97a7de250a59dd90e2b7ce4c9bd2057))
* Add state machine widget ([70dc165](https://github.com/wuespace/telestion-project-daedalus2/commit/70dc1658959026de4faa9dab07cf391282567c92))
* **application:** Add LatestDataAggregator to launch configuration to publish the system state in regular intervals ([6bfa95e](https://github.com/wuespace/telestion-project-daedalus2/commit/6bfa95e8201cab9d8c1b235091830654e00f1d36))
* **application:** Add LatestDataAggregator verticle to always publish the latest data in a regular interval onto the eventbus ([070619a](https://github.com/wuespace/telestion-project-daedalus2/commit/070619a013ed979b88592114c20e12bd6ca00c43))
* **client:** Add full SystemT mongo collection type ([377bad8](https://github.com/wuespace/telestion-project-daedalus2/commit/377bad83c4a85f766386559a1c377f0ecb115d5f))
* **client:** Add JavaMessage type to model messages that are received from and sent to the Java backend. (aka className) ([6eb6a10](https://github.com/wuespace/telestion-project-daedalus2/commit/6eb6a103f9e5ef9087a5682a8bc5f270181cfe48))
* **client:** Add MongoCollection type to model messages stored in mongo collections and are received from and sent to the Java backend. (aka className) ([dabcfd4](https://github.com/wuespace/telestion-project-daedalus2/commit/dabcfd4e3eff13cc0c43c02701078774deae0768))
* **client:** Add the latest-state channel name and type to the project ([ae3dc48](https://github.com/wuespace/telestion-project-daedalus2/commit/ae3dc48a40c547a64692d4b4533afd579063f2a9))
* **client:** Update state widget to log via a component logger ([3d5e95f](https://github.com/wuespace/telestion-project-daedalus2/commit/3d5e95fe32d8ab1132130b032cffd30653efa241))
* **client:** Update state widget to use the new types and channel names to connect to the backend ([88c89e8](https://github.com/wuespace/telestion-project-daedalus2/commit/88c89e88f738cc694bb301b53222bbab35c6bdc9))
* Remove MongoDB project sources ([6803d10](https://github.com/wuespace/telestion-project-daedalus2/commit/6803d103e4d753945b6cc2e9ab894e3d73322ef3))
* **stateWidget:** Add states ([81167d4](https://github.com/wuespace/telestion-project-daedalus2/commit/81167d47959e19ca315fe1b252be2ffacab25271))
* Use MongoDB extension instead of project sources ([0b41183](https://github.com/wuespace/telestion-project-daedalus2/commit/0b41183e3cf68b60bc6e733923645af10d950729))
* **widget:** Make state widget compatible with Telestion Client Library v0.16 ([ec397d4](https://github.com/wuespace/telestion-project-daedalus2/commit/ec397d4f503006a1846f5ddaf5d5efda21e6fb35))



## [0.11.2](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.11.1...v0.11.2) (2021-07-08)


### Bug Fixes

* **application:** Update telestion-extension-mavlink dependency ([2008d76](https://github.com/wuespace/telestion-project-daedalus2/commit/2008d76b3e18dc4831426da5c077417e453cf743))



## [0.11.1](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.11.0...v0.11.1) (2021-07-08)


### Bug Fixes

* Fix the publish setup package containing root folder structure ([fed9291](https://github.com/wuespace/telestion-project-daedalus2/commit/fed9291d40e42f048807bc4f04930f022639b62f))



# [0.11.0](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.10.0...v0.11.0) (2021-07-07)


### Features

* **application:** Add a default configuration to the MongoDatabaseService verticle ([6265f6e](https://github.com/wuespace/telestion-project-daedalus2/commit/6265f6e79c97588f2ddf733077e86df0982b24c8))
* **application:** Add Broadcast TCP Server which sends incoming messages to all connected clients. ([6f7b383](https://github.com/wuespace/telestion-project-daedalus2/commit/6f7b38332df2bf6f9a20dbff2e18164b0db593a4))
* **application:** Clean up Dockerfile for leaner Docker images ([8e4a9e0](https://github.com/wuespace/telestion-project-daedalus2/commit/8e4a9e0445bb58eac543a8820178cd65f144a518))
* **application:** Introduce a Docker Compose configuration to configure and handle interconnections between database and application Docker containers. The MongoDB database now runs in its own Docker container which greatly decreases the setup effort for this Telestion project. ([16aae7d](https://github.com/wuespace/telestion-project-daedalus2/commit/16aae7df7d4129d9f12a1bc84823781dfd512969))
* **application:** The serial connection now gets handled outside the Docker container and the packages are transmitted via a TCP connection from a server in the container to the client on the host system. ([fe36212](https://github.com/wuespace/telestion-project-daedalus2/commit/fe36212cc51974fee5eaf701521dc8611e74628d))
* **application:** Update sysId and compIds in MavLink messages ([23e703d](https://github.com/wuespace/telestion-project-daedalus2/commit/23e703dab4c9693809add949d5b5d05e200bc09e))
* Compress production folder on release ([2708b00](https://github.com/wuespace/telestion-project-daedalus2/commit/2708b00a9b697eecf5094afe65e9385ad2415e4c))



# [0.10.0](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.9.0...v0.10.0) (2021-06-26)


### Bug Fixes

* Hotfix SeedSystemT ([23862b6](https://github.com/wuespace/telestion-project-daedalus2/commit/23862b6cfbee121500016e2337172053481adbd9))


### Features

* **widget:** Add note widget ([128cc34](https://github.com/wuespace/telestion-project-daedalus2/commit/128cc3478aa199f8a00bc110df15ed0e31c665b0))



# [0.9.0](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.8.0...v0.9.0) (2021-06-26)


### Bug Fixes

* **application:** Include MavInfo details from ConCmd ([87d78e4](https://github.com/wuespace/telestion-project-daedalus2/commit/87d78e4d8a1a6851c9ca47466bee7631f9bcf09e))
* **application:** Make TelecommandSender network independent ([be32536](https://github.com/wuespace/telestion-project-daedalus2/commit/be32536d4a98fab9135fb3c60cbfa10e832d4f9e))
* **client:** Fix integration issues with application ([a83ed65](https://github.com/wuespace/telestion-project-daedalus2/commit/a83ed65839e64732973353222b430d99a54c7d90))
* **client:** Fix style issue in button and list labels and icons ([8c69541](https://github.com/wuespace/telestion-project-daedalus2/commit/8c69541ba61a1a6ff1fd0d4b4da91efd373d9e46))
* **client:** Fix value dependencies for Freitext TC widget's `onSubmit` ([ba015fe](https://github.com/wuespace/telestion-project-daedalus2/commit/ba015fe3d40510c9c86eb5e56e935ccd13fe0332))
* Fix bugs and implement requested changes ([51e192d](https://github.com/wuespace/telestion-project-daedalus2/commit/51e192dcee66f998e1390f4f4461e9ab4d6fd627))
* Remove spelling mistakes and bugs ([9d96480](https://github.com/wuespace/telestion-project-daedalus2/commit/9d96480997aa3c4fe1bbcd2943824b0ede3682b2))
* Send json and not the Object over the bus in MavlinkPacketCutter ([186285d](https://github.com/wuespace/telestion-project-daedalus2/commit/186285d162f90eac25634859a60b23f0f7bd97a2))


### Features

* Add ConCommand (new daedalus.xml) ([dbf7064](https://github.com/wuespace/telestion-project-daedalus2/commit/dbf7064ee089a37a4f3f0a0dc3d6fac1668cf8c7))
* Add new autogenerated MAVLink messages ([e99219c](https://github.com/wuespace/telestion-project-daedalus2/commit/e99219ca0976fe66178f38544e926c1540d7b508))
* **application:** Add Serial Printer verticle ([d5250ab](https://github.com/wuespace/telestion-project-daedalus2/commit/d5250ab291d71576215d608ab47b58ad16fa05d0))
* **application:** Re-import main configuration ([6507764](https://github.com/wuespace/telestion-project-daedalus2/commit/6507764f93444234ba647bc7ffd72f7e16e0189f))
* **application:** Set up telecommand sender in vert.x configuration ([1b2ab52](https://github.com/wuespace/telestion-project-daedalus2/commit/1b2ab5208ee8c775e21b3384c1652bb5302098fc))
* **application:** Specify connection details type ([4766a75](https://github.com/wuespace/telestion-project-daedalus2/commit/4766a755f4e31557e51f18bd5c84ae084d7eabc7))
* **application:** Update configuration to support telecommand encoding and sending ([89c047d](https://github.com/wuespace/telestion-project-daedalus2/commit/89c047d8102c150c07f56e61ca9e7fff7c47f418))
* **client:** Add Camera TC and Freitext TC widgets ([c50389f](https://github.com/wuespace/telestion-project-daedalus2/commit/c50389fd64c1d4b79a2781cb81012120e34e1184))
* **client:** Add dynamic styling for highlight.js containers based on current color scheme ([2e232d1](https://github.com/wuespace/telestion-project-daedalus2/commit/2e232d1497487ca1092eb8430cb76e50eeb3c180))
* **client:** Add highlighted config controls to Camera and FreitextTC widget ([4c2975d](https://github.com/wuespace/telestion-project-daedalus2/commit/4c2975df047f6731ad28d8908c5f7f6869699515))
* **client:** Update sources to work with Telestion-Client v0.16 ([2b04c29](https://github.com/wuespace/telestion-project-daedalus2/commit/2b04c293bd6dc115b318f1ce5287abdaed76e726))
* Cut messages from different senders ([99d336c](https://github.com/wuespace/telestion-project-daedalus2/commit/99d336c8f6ed98c469f62893e96bf6aee27873f0))
* Implement Telecommands ([2202bc3](https://github.com/wuespace/telestion-project-daedalus2/commit/2202bc37ddc8ad9e200b52c3f25cbc3e1133a022))



# [0.8.0](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.7.1...v0.8.0) (2021-06-03)


### Features

* Add application runner shell script ([56a3c12](https://github.com/wuespace/telestion-project-daedalus2/commit/56a3c12bdb424952b96dac9c12250f38bf3c94e9))
* **client:** Update to telestion-client v0.15 and clean up some code dependencies ([71d4adf](https://github.com/wuespace/telestion-project-daedalus2/commit/71d4adfc69f651285947f783111e4cdc37acced3))



## [0.7.1](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.7.0...v0.7.1) (2021-06-03)


### Bug Fixes

* **application:** Update host address for MongoDB ([5580281](https://github.com/wuespace/telestion-project-daedalus2/commit/558028144ac4460bbe5c283984edb25f2f11b275))



# [0.7.0](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.6.3...v0.7.0) (2021-06-03)


### Features

* **application:** Set baud rate in configuration ([30f92fe](https://github.com/wuespace/telestion-project-daedalus2/commit/30f92fe44d5e96b01652268b5481d38a12c25a90))



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

* Add missing default constructor for MavlinkRegistrar ([fe9121b](https://github.com/wuespace/telestion-project-daedalus2/commit/fe9121b6139edc7dd465b955756974732bd8161d))
* **application:** Fix and test reading, parsing, and dumping (to a file) MavLink messages from a (virtual) serial device ([52d99e0](https://github.com/wuespace/telestion-project-daedalus2/commit/52d99e003acc3f90ca1e8a71150f234599175872))
* **client:** Adjust to updated aggregation data sample type from backend and listen to "real" addresses ([d76afbd](https://github.com/wuespace/telestion-project-daedalus2/commit/d76afbdc8286202728168ccfaa45ce1f2731b874))
* **client:** Use new event bus addresses for the fake data in the current values configuration ([cc174e1](https://github.com/wuespace/telestion-project-daedalus2/commit/cc174e183eb1ef13548058a281ba85ee5d3d0681))
* **database:** Fix database config and implementation to get it working ([0475e1b](https://github.com/wuespace/telestion-project-daedalus2/commit/0475e1bed776d550f326138de0d51e98729b197f))
* Register MAVLink Registrar ([962a8ab](https://github.com/wuespace/telestion-project-daedalus2/commit/962a8ab747147d04c002d4485cb8ed69021db8b0))


### Features

* Add MAVLink Message registrar for the parser to work ([ac761a3](https://github.com/wuespace/telestion-project-daedalus2/commit/ac761a37e2f6e6bf18728034fe37e4f7398ed17b))
* **database:** added working database implementation ([4ebf9b9](https://github.com/wuespace/telestion-project-daedalus2/commit/4ebf9b909e9950876b11aada5585c5d99a48b474))
* **widget:** Add placeholder widget ([82e9a49](https://github.com/wuespace/telestion-project-daedalus2/commit/82e9a494b719198165a7516c5f410fce84dbb29a))
* **widget:** Implement current values widget ([0b13d67](https://github.com/wuespace/telestion-project-daedalus2/commit/0b13d6768b3e47bd3c2106c951170a08e0f8d4f2))
* **widget:** Implement delay status and context based text color in current-values widget ([f0dab65](https://github.com/wuespace/telestion-project-daedalus2/commit/f0dab65bdb5e25c71956b737f54e1a53208223d7))
* **widget:** Initialize current values widget ([d6c40bd](https://github.com/wuespace/telestion-project-daedalus2/commit/d6c40bd6be8180dbdee8c42cde0a0f69e7ad82d9))



# [0.3.0](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.2.1...v0.3.0) (2021-05-08)


### Bug Fixes

* **mavlink-messages:** Convert `SeedSystemT.java` encoding to UTF-8 ([e88b437](https://github.com/wuespace/telestion-project-daedalus2/commit/e88b4376157826e63224222489dc4c0fe69f4010))
* **mavlink-messages:** Fix wrong `int` types in Mavlink message record ([3596421](https://github.com/wuespace/telestion-project-daedalus2/commit/3596421dd6a12b63dcfd9b88ce10ac3637ec74cb))
* **message-splitter:** Use "reference" `byte` type for `ArrayList` ([5107644](https://github.com/wuespace/telestion-project-daedalus2/commit/5107644c14666192ff7f138ba0aed16409288642))


### Features

* Add MAVLink definitions and autogenerated files ([fa11ca4](https://github.com/wuespace/telestion-project-daedalus2/commit/fa11ca4d54e94134500c20402d6c2ac22f8bfdbb))
* **client:** Apply patches ([7cc41e9](https://github.com/wuespace/telestion-project-daedalus2/commit/7cc41e9de4be5007a81bda70bb9e6582120c1bd0))
* **message-splitter:** Implement a basic message splitter to extract potential MavLink packages from the "raw" input byte stream and re-publish those messages ([271d6ea](https://github.com/wuespace/telestion-project-daedalus2/commit/271d6ea7b1a94193d44a97141787a8b680307487))
* **message-transformer:** Add initial implementation for the `MessageTransformer` verticle ([554c81a](https://github.com/wuespace/telestion-project-daedalus2/commit/554c81a960dfaaf7468e229570e1d5efefb24785))
* **message-transformer:** Implement "beautiful" messages and republish `de.wuespace.telestion.project.daedalus2.mavlink.Drehtest` to the database in well-wrapped forms (unwrapping bit sequences) ([1d8f359](https://github.com/wuespace/telestion-project-daedalus2/commit/1d8f3595e9bb88146a16e0a0868ef584cdb8341c))
* **message-transformer:** Implement new `SystemT` message ([a035599](https://github.com/wuespace/telestion-project-daedalus2/commit/a0355990730e3fe3c612d91c08e3d2fa598aa37b))
* **message-transformer:** Rename `Status` to more precise `AvailableStatus` in preparation for `SystemStatus` implementation ([6c14c36](https://github.com/wuespace/telestion-project-daedalus2/commit/6c14c3674b85ce1e29371aa540518b6ce787bd32))



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




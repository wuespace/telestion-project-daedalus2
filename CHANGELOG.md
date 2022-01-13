# [0.28.0](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.27.0...v0.28.0) (2022-01-13)


### Features

* **widget:** Add auto-scroll checkbox to switch scroll-to-bottom on and off ([0e458b7](https://github.com/wuespace/telestion-project-daedalus2/commit/0e458b7d2b0dbada8b6f3231f38ef36080955f2d))



# [0.27.0](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.26.1...v0.27.0) (2022-01-13)


### Features

* **application:** Add receive time and time_local to log message in telecommand console ([dff8751](https://github.com/wuespace/telestion-project-daedalus2/commit/dff8751760f11a6b7d03cf0a85b1caee1e1adafd))
* **application:** Trim received log message before publishing in Mavlink TM parser ([e336d2b](https://github.com/wuespace/telestion-project-daedalus2/commit/e336d2be9222088b44ec87448e6b2a654ab3337f))
* **widget:** Allow multiple sending of the same telecommand in TCSendButton ([2499d34](https://github.com/wuespace/telestion-project-daedalus2/commit/2499d34fffe981578431846fbcbf00a3e597d181))
* **widget:** Enable smooth scrolling in TM/TC window ([0e77971](https://github.com/wuespace/telestion-project-daedalus2/commit/0e77971df168ded40bb0223b9bc3c30d08a84bc9))



## [0.26.1](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.26.0...v0.26.1) (2022-01-12)


### Bug Fixes

* **widget:** Fix display `N/A` on `0` telecommands in TC counter bar in TC console widget ([8dfc2b5](https://github.com/wuespace/telestion-project-daedalus2/commit/8dfc2b5ee4c721c4bb3656e4e4d0bcdfcb976996))



# [0.26.0](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.25.0...v0.26.0) (2022-01-12)


### Features

* **application:** Add clear and clear all support in Telecommand console ([8ef2b88](https://github.com/wuespace/telestion-project-daedalus2/commit/8ef2b886040bd43a71cf8d6e2129cc022c93ad93))
* **widget:** Add clear and clear all support in TC console widget ([14c3858](https://github.com/wuespace/telestion-project-daedalus2/commit/14c3858c9809c32242edbd3c41d75bc56d7a60c2))



# [0.25.0](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.24.0...v0.25.0) (2022-01-12)


### Bug Fixes

* **application:** Add TC Console addresses to permitted inbound and unbound addresses via EventbusTcpBridge ([5fc6dfe](https://github.com/wuespace/telestion-project-daedalus2/commit/5fc6dfe8a1fe10af7e64163b80678f54a413b66f))
* **application:** Encode and decode String array manually because Vert.x shared data storage cannot ([25248e6](https://github.com/wuespace/telestion-project-daedalus2/commit/25248e6d62e587587745d8daf49a0f1c0fb03df7))
* **application:** Move `JsonMessage.on` into one register call because send will use only one registered handler regardless which verticle and which handler. (see https://vertx.io/docs/vertx-core/java/#_sending_messages) ([e3d0979](https://github.com/wuespace/telestion-project-daedalus2/commit/e3d097902114827ff05161ec61994e0cb7015312))
* **client:** Fix Telecommand class name due to previous refactor (see v0.6.2 integration) ([7bf9299](https://github.com/wuespace/telestion-project-daedalus2/commit/7bf92998b7eca049077b03dbedef76a2b3c4e88b))


### Features

* **application:** Add log messages to D2 TM simulator ([98ec2a9](https://github.com/wuespace/telestion-project-daedalus2/commit/98ec2a9bb9697e1d1ed8ed43df20d1d9f4403f7f))
* **application:** Add Telecommand console to config.json ([7ef703d](https://github.com/wuespace/telestion-project-daedalus2/commit/7ef703d131e45953b2d6044990ede49dbbd52c70))
* **application:** Implement Telecommand console verticle and message types ([1eae9c0](https://github.com/wuespace/telestion-project-daedalus2/commit/1eae9c0e782469755181443ba2864d7cdcf44a61))
* **client:** Add client side type definitions for message types ([8fda822](https://github.com/wuespace/telestion-project-daedalus2/commit/8fda82293143f26547766e46eb0b831124b22c2c))
* **client:** Add core-utils from `telestion-client-core` ([2828b65](https://github.com/wuespace/telestion-project-daedalus2/commit/2828b65878111d18976e4b931a4d0b4b297576b0))
* **client:** Add some utility functions to `tc-console.ts` ([c422fd4](https://github.com/wuespace/telestion-project-daedalus2/commit/c422fd4482ba730994704a7394d0f62e93412301))
* **client:** Add TC console widget ([ea0cf59](https://github.com/wuespace/telestion-project-daedalus2/commit/ea0cf5972223ae46022a5f55a7067795998a47d5))
* **client:** Clean up telecommand dashboards ([79bb430](https://github.com/wuespace/telestion-project-daedalus2/commit/79bb43047b503c1d2234fc658f084e5ba704945f))
* **client:** Update connection status panel timeouts ([1f480ae](https://github.com/wuespace/telestion-project-daedalus2/commit/1f480ae325741ae19ed0c89ef3c2f8f13b1638e5))
* Migrate project to Telestion-Core v0.6.2 ([6e82865](https://github.com/wuespace/telestion-project-daedalus2/commit/6e828654b3a37fb10543b209a02e2e2d6c74dd5e))
* Update MavLink XML definition and generated sources for Java and python ([c2eae95](https://github.com/wuespace/telestion-project-daedalus2/commit/c2eae952e7b02175cdee46c0fcd953c3ce092d88))
* **widget:** Finalize TC console widget ([3ef7177](https://github.com/wuespace/telestion-project-daedalus2/commit/3ef7177fe8208cc6fa5ce763a2bd66ec132e4ea2))



# [0.24.0](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.23.0...v0.24.0) (2022-01-02)


### Features

* **application:** Update configuration which adds telecommands to checklist ([bbf966d](https://github.com/wuespace/telestion-project-daedalus2/commit/bbf966d5db7d77d0961f996c67c4beaddee326ae))
* **client:** Add connection status panel to the left side of the header which displays the connection status of Seed A, Seed B and Ejector ([b48e1f0](https://github.com/wuespace/telestion-project-daedalus2/commit/b48e1f06e7c7355a8e3f2efa2dc878cfc324363b))
* **d2-sim:** Clean up telemetry and add some basic functionality like automatic state cycling and correct `time_local` and `d2time` ([500f023](https://github.com/wuespace/telestion-project-daedalus2/commit/500f0239332a8d9e3e9467643b1ebbb0f38a61e2))
* **widget:** Update `state-widget` which now displays the last received time and state id next to the state name. The state name now comes without a "k" prefix. Ejector and Seed states are now separated. The state change widget selection now depends on the target. (ejector or seed) ([7d1fac5](https://github.com/wuespace/telestion-project-daedalus2/commit/7d1fac508757fad7200e4ef8b72263b4b014a554))



# [0.23.0](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.22.2...v0.23.0) (2021-12-17)


### Features

* **client:** Add configuration that loads synchronous with the initial page and reads the `config.json` in the webroot to initially configure the web client ([0b124dd](https://github.com/wuespace/telestion-project-daedalus2/commit/0b124dd525c9bc02166cfd0dc89f33ff48793ead))



## [0.22.2](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.22.1...v0.22.2) (2021-12-17)


### Bug Fixes

* **ci:** Add `docker-compose` postfix to published setup archive ([9779150](https://github.com/wuespace/telestion-project-daedalus2/commit/97791509766c62c211bd8b5eae0bed89ef765fdd))



## [0.22.1](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.22.0...v0.22.1) (2021-12-16)


### Bug Fixes

* **ci:** Fix upload issues in release actions ([74bb2bc](https://github.com/wuespace/telestion-project-daedalus2/commit/74bb2bc9e67c4f2c6d92ae75b787c0d91338a99d))



# [0.22.0](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.21.0...v0.22.0) (2021-12-16)


### Features

* **ci:** Add step to compress and upload client build as static webpage ([6520c6a](https://github.com/wuespace/telestion-project-daedalus2/commit/6520c6a8dd8adc86088e5cec8bf27994569aa699))



# [0.21.0](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.20.1...v0.21.0) (2021-12-09)


### Features

* **application:** Add assist-now binary exporter to D2 TM simulator ([28bbb87](https://github.com/wuespace/telestion-project-daedalus2/commit/28bbb87a1e8ad247e4b145a41b5af35cdc37d530))
* **application:** Publish parser stats to redis in `mavlink/parser` section ([47a6d53](https://github.com/wuespace/telestion-project-daedalus2/commit/47a6d5349a916178c8351784223bcab899e72806))
* **application:** Update checklist configuration ([9197194](https://github.com/wuespace/telestion-project-daedalus2/commit/9197194353b6e1025c38b83fc68520a6ff6ce0c5))
* **client:** Add current values dashboard ([0519248](https://github.com/wuespace/telestion-project-daedalus2/commit/05192481a6bbf0cd4f428e6063eaa194485b4e27))



## [0.20.1](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.20.0...v0.20.1) (2021-12-09)


### Bug Fixes

* **application:** Fix Telecommand Sender verticle correctly casting a signed byte array to an unsigned short array to successfully send Assist Now Upload Mavlink messages with valid payload ([016c554](https://github.com/wuespace/telestion-project-daedalus2/commit/016c554fae0627f619ce9dc1bc6270431fa7cffb))



# [0.20.0](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.19.0...v0.20.0) (2021-12-08)


### Bug Fixes

* **application:** Regenerate Mavlink code again ([4f09714](https://github.com/wuespace/telestion-project-daedalus2/commit/4f097146995a72f3a0a165a95393a88ea90cbcda))
* **client:** Fix widget ids in Telecommand dashboard ([b518bb0](https://github.com/wuespace/telestion-project-daedalus2/commit/b518bb0c7204ed5b5e172d9ca18d36add14c3b45))


### Features

* **application:** Print source id on telecommand in D2 TM simulator ([0c8855c](https://github.com/wuespace/telestion-project-daedalus2/commit/0c8855c3acbb389ec79b04d9feeac5297a445db1))



# [0.19.0](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.18.0...v0.19.0) (2021-12-08)


### Bug Fixes

* **application:** Regenerate Mavlink code to fix generation from last version ([2fb4132](https://github.com/wuespace/telestion-project-daedalus2/commit/2fb413203592e74ff8f7c12442433b5562b2782b))


### Features

* **application:** Log Mavlink parser stats after every received message chunk ([ab4c765](https://github.com/wuespace/telestion-project-daedalus2/commit/ab4c765258e898d8bb3b3184e80e15cd1fcd28f0))



# [0.18.0](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.17.0...v0.18.0) (2021-12-08)


### Bug Fixes

* **application:** Add Message Chunker information to Redis check list ([5eb0865](https://github.com/wuespace/telestion-project-daedalus2/commit/5eb0865c88b5ebc7883c6f922429f57c98da09f8))


### Features

* **application:** Add Telecommand Generator verticle to generate a bunch of telecommands ([a9751b8](https://github.com/wuespace/telestion-project-daedalus2/commit/a9751b8261422c5774b0438499acf746658baaef))
* **application:** Update D2 TM simulator ([cb3445c](https://github.com/wuespace/telestion-project-daedalus2/commit/cb3445cde15a2ee3d760c5b8de5f4064455be847))
* **application:** Update mavlink definition file and regenerate python and Java files ([96248b1](https://github.com/wuespace/telestion-project-daedalus2/commit/96248b1d14ea85e921b2c86876b23fa63e9aac27))
* **client:** Add debug dashboard with Eventbus debug widget to user config ([1d9c2aa](https://github.com/wuespace/telestion-project-daedalus2/commit/1d9c2aa1ac47fb77f7c0565d5286b0bd30c47c58))
* **client:** Add ejector camera widget for `camrec` and `camled` TCs to the ejector ([828f3f7](https://github.com/wuespace/telestion-project-daedalus2/commit/828f3f72a17824daf3b0358e707f4d0d8fe99792))
* **client:** Add radio silence and ejector camera widgets to the Telecommands dashboard ([49baf9d](https://github.com/wuespace/telestion-project-daedalus2/commit/49baf9dc086113afb71e82466af94ef7bb7f0727))
* **client:** Add radio silence widget to easily trigger and abort the radio silence sequence using buttons (with confirmation dialogs) ([4037ca7](https://github.com/wuespace/telestion-project-daedalus2/commit/4037ca700c08c0d16994bcb22444f548ee226844))
* **client:** Remove outdated/invalid default values for the current values and graph widget ([d4d752f](https://github.com/wuespace/telestion-project-daedalus2/commit/d4d752f66d9480f393d7cfa1da2e4267c79ba00e))
* **widget:** Filter out invalid and entry and exit states from state change widget ([0781f0c](https://github.com/wuespace/telestion-project-daedalus2/commit/0781f0cd30857cbb3e08e077d6304c5105606f58))



# [0.17.0](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.16.4...v0.17.0) (2021-12-02)


### Features

* Add updated MavLink definition for Daedalus2 ([80ef4d2](https://github.com/wuespace/telestion-project-daedalus2/commit/80ef4d2b027a2ad54a879dc262946d8f8d2e7b12))
* **application:** Add Message Chunker with updated configuration ([33b0b12](https://github.com/wuespace/telestion-project-daedalus2/commit/33b0b121e77b4b76a2293bbf5142d4924b940279))



## [0.16.4](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.16.3...v0.16.4) (2021-12-01)


### Bug Fixes

* **ci:** Remove zip and upload part for generated client build because it does not work :trollface: ([221a4db](https://github.com/wuespace/telestion-project-daedalus2/commit/221a4dbaa12d62f307a183be5e628d971a45ff05))



## [0.16.3](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.16.2...v0.16.3) (2021-12-01)


### Bug Fixes

* **ci:** Fix release workflow by adding one step more ([67fc8df](https://github.com/wuespace/telestion-project-daedalus2/commit/67fc8df1aa8ddb9df74344840714713d25cb8d7a))



## [0.16.2](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.16.1...v0.16.2) (2021-12-01)


### Bug Fixes

* **ci:** Fix release workflow again ([c2f2538](https://github.com/wuespace/telestion-project-daedalus2/commit/c2f2538810088f5d5b2dbdd117149e88e81bab9e))



## [0.16.1](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.16.0...v0.16.1) (2021-12-01)


### Bug Fixes

* **ci:** Fix invalid replace pattern in client release workflow ([60f76db](https://github.com/wuespace/telestion-project-daedalus2/commit/60f76db0532e11b4272e31e9e2e429f816010daf))



# [0.16.0](https://github.com/wuespace/telestion-project-daedalus2/compare/v0.15.1...v0.16.0) (2021-12-01)


### Bug Fixes

* **application:** Fix bug where uploaded data which is not Base64 encoded throws and breaks the verticle ([0a12988](https://github.com/wuespace/telestion-project-daedalus2/commit/0a129882e2c5f363e5f8be63d787327a1f0d8f97))
* **application:** Fix Iridium Message Mapper not sending receive time in header resulting in errors in Redis Saver ([66b0ca2](https://github.com/wuespace/telestion-project-daedalus2/commit/66b0ca216cd459f9f1f402b4f6f7c9f3d7cae86e))
* **application:** Increase size of integer elements in IE header to prevent unsigned integer overflow ([1da78b7](https://github.com/wuespace/telestion-project-daedalus2/commit/1da78b79ac6be7580b0db70a4fbbfc8cbeceedd6))
* **client:** Add Client side implementation to handle uploaded data which can be invalid ([4665c7e](https://github.com/wuespace/telestion-project-daedalus2/commit/4665c7e9ce2083acbffb5090190cee263d86cdbb))
* **client:** Fix several small visual issues in the Seed ECAM Widget ([2935e89](https://github.com/wuespace/telestion-project-daedalus2/commit/2935e891ca230f6b7d4921d901f8ff82569e6a01))
* **widget:** Fix title overflow in A-GPS Upload widget ([7c678fa](https://github.com/wuespace/telestion-project-daedalus2/commit/7c678fa3262ecd4ec71bb69dc8504aaedc35afd2))


### Features

* **application:** Add Assist Now generator script ([6cc21a4](https://github.com/wuespace/telestion-project-daedalus2/commit/6cc21a42fdf4492488eda6cb48d926822c7ef2c2))
* **application:** Add iridium message channels to Redis and Mongo saver ([f770756](https://github.com/wuespace/telestion-project-daedalus2/commit/f770756ed03c728eda1d853979f7167e7c3d9588))
* **application:** Add message mapper verticle to map iridium messages to redis readable data ([97d32c1](https://github.com/wuespace/telestion-project-daedalus2/commit/97d32c1bb3ce2f77fe46c7e34faaee15d8561f35))
* **application:** Add Telecommand Counter verticle ([9328e58](https://github.com/wuespace/telestion-project-daedalus2/commit/9328e58b7b55957fcdf12a3f1ed9a5b9c3f246eb))
* **application:** Partially rewrite TelecommandSender verticle to send different MAVLink messages based on Telecommand Messages ([01ff65b](https://github.com/wuespace/telestion-project-daedalus2/commit/01ff65beba6915256da97da53d09527e23dcd574))
* **client:** Add current D2 mission time in header ([58bb9b8](https://github.com/wuespace/telestion-project-daedalus2/commit/58bb9b8a6baaad13db2665d4b6c7ff9e1b5bbfa6))
* **client:** Add State change widget ([ba08b83](https://github.com/wuespace/telestion-project-daedalus2/commit/ba08b8399b51fb2b7961887a42e12c7a305429b1))
* **client:** Move and refactor states from State widget to model ([5daea49](https://github.com/wuespace/telestion-project-daedalus2/commit/5daea49ee7bfd602428e56da97a73425bd01bf49))
* **client:** Refactor and clean up state widget and make use of the new state definitions ([88693e7](https://github.com/wuespace/telestion-project-daedalus2/commit/88693e77daf2803e1993c1b02afeda6995180488))



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




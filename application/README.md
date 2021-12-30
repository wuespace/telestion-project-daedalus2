# Telestion Project Application

The application part of a Telestion Project.

## Prerequisites

You will need Java 16 and Docker with `docker-compose` set up and ready to go.
A text editor or IDE with auto-completion and gradle support is beneficial.

## Setup

To fetch the dependencies locally you need a GitHub personal access token with `packages:read` rights.
Either add your GitHub username and the token as `GITHUB_ACTOR` and `GITHUB_TOKEN` to your environment variables
or make a copy of the `gradle.properties.example` and name it `gradle.properties`.
Then insert your username and token into this file.
No worries, this file should not be committed. (it's git ignored)

Finally, re-sync gradle to download and set up the Telestion Core dependencies.

## Usage and Testing

### Native

Now you can run the application natively on your system.
Before you start, you need a MongoDB Server setup.
To start it, please type:

```sh
docker-compose --profile devel-native up -d
```

Now, the MongoDB server is running on port `27017`.
You can try to connect to the server if you installed the mongo-client-tools:

```sh
mongo
```

Let's start the application with gradle:

```sh
./gradlew run
```

or:

```sh
.\gradle.bat run
```

### In Docker

If you want to test your changes directly in docker, you can package it and run it beside the MongoDB container.

1. Please shut down any running containers and services:

   ```sh
   docker-compose --profile devel-native down
   ```

2. Create a distribution build with gradle:

   ```sh
   ./gradlew assembleDist
   ```

   or:

   ```bat
   .\gradle.bat assembleDist
   ```

3. Build the local image with `docker-compose`:

   ```sh
   docker-compose --profile devel-docker build
   ```

4. And create and start the containers:

   ```sh
   docker-compose --profile devel-docker up -d
   ```

That's it!

### Production mode

If you want to run the application in production mode using the latest release of the project, then you simply need to call:

```sh
docker-compose --profile prod up -d
```

to pull and start the required docker containers.

> Attention:
>
> Your local `config.json` will be used to configure the started application!

## Testing your work

### TCP MavLink simulator

We have written a small simulator which attaches to the exposed MavLink TCP server.
It sends status updates in regular intervals and can receive all MavLink telecommands from Telestion side.

To use it, please take a look inside the [`d2-tm-sim` folder](./d2-tm-sim/).

### Testing with serial ports

If you are using Linux as your host operating system you can open two linked serial ports with socat:

```sh
socat -d -d pty,raw,echo=0 pty,raw,echo=0
```

The output tells you the path to the opened sockets, e.g.:

```txt
2021/12/02 03:45:06 socat[13586] N PTY is /dev/pts/5
2021/12/02 03:45:06 socat[13586] N PTY is /dev/pts/6
2021/12/02 03:45:06 socat[13586] N starting data transfer loop with FDs [5,5] and [7,7]
```

It says, it has created two virtual serial devices on `/dev/pts/5` and on `/dev/pts/6`.

Now, connect the tcp_serial_redirect python script to one of the open ports:

```sh
python ./tcp_serial_redirect.py -c localhost:9871 /dev/pts/6 38400
```

And e.g. `hexdump` on the other side:

```sh
hexdump -C /dev/pts/5
```

And send data through the tunnel via telecommands etc.

## Update MavLink generated code

1. Go through the setup steps described in the MavLink wiki:
   https://mavlink.io/en/getting_started/installation.html

2. Clone the MavLink repository in some temporary space on your machine:

   ```sh
   git clone https://github.com/mavlink/mavlink.git --recursive
   cd mavlink
   git submodule update
   cd pymavlink
   git checkout master
   git pull
   cd ..
   ```

   > **NOTE:** All following commands are executed inside the MavLink repository, if not stated otherwise.

3. Export the path to the MavLink root as an environment variable:

   ```sh
   export PYTHONPATH=/home/testuser/tmp/mavlink
   ```

4. Copy the current MavLink definition file (`.xml` ending) into the `message_definitions/v1.0`:

   ```sh
   cp /path/to/project/definitions/daedalus.xml message_definitions/v1.0/
   ```

5. Generate the source code for Java:

   ```sh
   python -m pymavlink.tools.mavgen --lang=Java --wire-protocol=2.0 --output=generated/java message_definitions/v1.0/daedalus.xml
   ```

6. The Mavlink developers forgot to remove a reference to the common library in `com.MAVLink.Messages.MAVLinkStats`.
   Please remove the import statement at the top of the file:

   ```java
   import com.MAVLink.common.msg_radio_status;
   ```

   and replace the constant `msg_radio_status.MAVLINK_MSG_ID_RADIO_STATUS` with the value `109`:

   ```java
   if (ignoreRadioPackets && packet.msgid == 109) {/*...*/}
   ```

7. Generate the source code for python:

   ```sh
   mkdir -p generated/python
   python -m pymavlink.tools.mavgen --lang=Python --wire-protocol=2.0 --output=generated/python/daedalus2.py message_definitions/v1.0/daedalus.xml
   ```

8. Copy the generated source code into the project path:

   ```sh
   cp -r generated/java/* /path/to/project/application/srv/main/com/MAVLink/
   cp generated/python/daedalus2.py /path/to/project/application/d2-tm-sim/
   ```

9. Finished!

## Project Structure

The application structure looks like:

```txt
.
├── .gradle (gradle specific setup; should not be commited)
├── build (build files containing meta-information and binaries from the build process)
├── conf (application configurations)
│   └── config.json (the main configuration file the application uses to start the Vert.x verticles)
├── data (data generated the application while it's running)
│   └── mavlink-logger (containing the raw received mavlink packages with a timestamp for later analysis)
├── src (the source files of the application)
├── build.gradle (the "main" configuration file for gradle)
├── docker-compose.yml (the definition how to set up and connect the different docker containers and profiles)
├── Dockerfile (the definition to successfully build a Docker image from the compiled Application sources)
├── gradle.properties (gradle configuration containing your personal access token)
└── README.md (you're here :P)
```

## Learn More

To get started with the application development, please take a look at the Vert.x documentation which is "the" library in Telestion:

https://vertx.io/docs/

Also take a look at the Telestion Core packages containing different helpers and services that (hopefully) make the development much easier:

https://github.com/wuespace/telestion-core

If you need some inspiration "how" to write your first verticle, you can look at other verticles source code in this project.

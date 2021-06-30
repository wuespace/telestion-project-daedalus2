# Telestion Project Daedalus2

This repository holds project-specific resources for Daedalus2.

It contains the Telestion Application which connects to the Daedalus2 Interface,
handles incoming data, stores them in a MongoDB database
and prepares them for the connected clients.

The graphical Telestion Client connects to the Telestion Application
and visualizes incoming data in a clear way.

## Installation

### The Application

To set up the Telestion Application, please go to the latest release of the project and download the `setup.sh` file:
https://github.com/wuespace/telestion-project-daedalus2/releases/latest

Place this file somewhere in your file system, where you have write access.

> On installation, the script creates a folder in the current working directory containing the needed folders, configurations and executables to run the Telestion Application.

Then, make the setup script executable and run it:

```shell
chmod +x setup.sh && ./setup.sh
```

Now, let the magic happen.

After the setup script has finished, go into the newly created project folder:

```shell
cd ./telestion-project-daedalus2
```

And look for the `telestion` script.
This script manages the Telestion Application. It has many different options.
In the background it uses `docker-compose` to manage the Docker containers.

To start the Telestion Application, simply call:

```shell
./telestion up
```

### Connecting the Serial Device

Now you need to send and receive messages from and to the serial device.
Please install [python](https://www.python.org/) and the [pySerial](https://pyserial.readthedocs.io/en/latest/pyserial.html) package.

Then download this script:
https://github.com/pyserial/pyserial/blob/master/examples/tcp_serial_redirect.py

Please open a shell or command line and call the script with the required arguments:

```shell
python ./tcp_serial_redirect.py -c localhost:9871 /dev/pts/6 38400
# or
python ./tcp_serial_redirect.py -c localhost:9871 /dev/ttyUSB1 57600
# or
python ./tcp_serial_redirect.py -c localhost:9871 COM6 57600
```

And thats it! The serial-to-tcp adapter transmits the information between the serial device and the Telestion Application.

When you want to stop the Application, call:

```
./telestion down
```

And terminate the serial-to-tcp adapter.

### The Client

For the Client setup, you simply need to go to the latest release in GitHub and download the suitable installer for your operating system:
https://github.com/wuespace/telestion-project-daedalus2/releases/latest

Then install the Client and start it.

A window should show up displaying a login page.
Please first check the backend server URL and enter the URl given from the Application management script.
It should look something like `http://192.168.0.10:9870/bridge`.
If you running the Telestion Application on the same system, you can _leave_ the URL like it is.

Then enter your given credentials from the Telestion team and press "Login".

The first dashboard should show up and the Connection Indicator in the navigation bar should switch to "Connected".
If this is not the case, please log out again via the avatar menu in the upper right corner and re-chceck if the entered backend server URL is correct. If the Client still doesn't connect, please inform the Telestion team so we can debug this issue.

## Building

 To build the projects from source,
 please take a look into the part specific descriptions:

 - [Application](./application/README.md)
 - [Client](./client/README.md)

 ## This repository

The overall file structure of this monorepo looks like this:

```
.
├── .github
│   ├── workflows (CI configuration)
│   └── dependabot.yml (Dependabot config)
├── application
|   ├── conf (the verticle configuration for the Application)
|   ├── src (the source files of the Telestion Application)
|   ├── Dockerfile (the definition to successfully build a Docker image from the compiled Application sources)
|   ├── build.gradle (manages dependencies and the build process via Gradle)
|   ├── gradle.properties (contains the required tokens to access required dependencies)
|   ├── gradlew (the current gradle executable for UNIX-based systems)
|   └── gradlew.bat (the current gradle executable for Windows systems)
├── client
|   ├── public (template webpage folder where React will engage)
|   ├── src (the source files of the Telestion Client)
|   └── package.json (manages dependencies and the build process via npm)
├── CHANGELOG.md (DON'T TOUCH! Automatically generated Changelog)
├── README.md (you're here :P)
├── project.json (contains the current project information like the current version etc.)
└── telestion-application (DON'T TOUCH! Used as an indicator for our automation tools)
```

**The [Application](./application/README.md) and the [Client](./client/README.md) folders contain their own `README.md` that describe the different parts more specific.**

 ### Contributing

 For the documentation on contributing to this repository, please take a look at the [Contributing Guidelines](./CONTRIBUTING.md).

 ## Contributors

Thank you to all contributors of this repository:

[![Contributors](https://contrib.rocks/image?repo=wuespace/telestion-project-daedalus2)](https://github.com/wuespace/telestion-project-daedalus2/graphs/contributors)

Made with [contributors-img](https://contrib.rocks).

## About

This is part of [Telestion](https://telestion.wuespace.de/), a project by [WüSpace e.V.](https://www.wuespace.de/).
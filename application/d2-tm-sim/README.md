# D2 Telemetry/Telecommand Simulator

This simulator written in python should act as a "real" MavLink device.
It uses the same xml-definitions as the Telestion application
and is therefore capable to understand all telecommands sent by Telestion
and send fake telemetry to Telestion in regular intervals.

## Getting started

First, we need some dependencies to be installed.
Call pip, the python dependency installer, for help:

```sh
pip install -r requirements.txt
```

Now, run the simulator:

```sh
python main.py
```

You should see some output and if you send some telecommands,
it should print out the TC type and the decoded payload.

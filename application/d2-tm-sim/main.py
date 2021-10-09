import random
import socket
import time
import socket
from typing import BinaryIO

import daedalus2

MESSAGE_INTERVAL = 5

TELESTION_CONNECTION = "localhost", 9871

EJECTOR_ID = 3
SEED_A_ID = 4
SEED_B_ID = 6

STOP_FLAG = False


def loop():
    s: socket.socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.connect((TELESTION_CONNECTION))

    file: BinaryIO = s.makefile("rbw")

    print("Connection to {0} established.".format(str(TELESTION_CONNECTION)))
    print("Sending TM every {0} seconds until you stop the script by pressing Ctrl+C.".format(str(MESSAGE_INTERVAL)))
    print()
    print("(every '*' indicates that a batch of messages has been sent)")

    while not STOP_FLAG:
        ejector = daedalus2.MAVLink(file, srcSystem=EJECTOR_ID, srcComponent=192)  # Ejector
        seedA = daedalus2.MAVLink(file, srcSystem=SEED_A_ID, srcComponent=192)
        seedB = daedalus2.MAVLink(file, srcSystem=SEED_B_ID, srcComponent=192)

        # bytes = generated.MAVLink.seed_heartbeat_encode(
        #     mavlink,
        #     int(time.time()),
        #     1,
        #     random.random(),
        #     1,
        #     0,
        #     [1, 2, 3, 4, 5, 6, 7, 8],
        #     0b110,
        #     0b111,
        #     0b011,
        # ).pack(mavlink, False)

        # Use mavlink.*_send functions here
        #
        # mavlink.ejector_heartbeat_send(
        #     int(time.time()),
        #     4
        # )

        seedA.seed_heartbeat_send(int(time.time()), 2, random.random(), 1, 0, [1, 2, 3, 4, 5, 6, 7, 8], 5, 5, 5)
        ejector.ejector_log_send(int(time.time()), b'Hallo Welt')

        file.flush()

        print("*", end='')

        time.sleep(MESSAGE_INTERVAL)

    s.close()


if __name__ == '__main__':
    print("Schreibtischtest Umgebungssimulator")
    print("===================================")
    loop()

import random
import socket
import threading
import time
from typing import BinaryIO

import daedalus2

MESSAGE_INTERVAL = 5

TELESTION_CONNECTION = "localhost", 9871

EJECTOR_ID = 3
SEED_A_ID = 4
SEED_B_ID = 6

STOP_FLAG = False


def receive(name, s: socket.socket, file):
	print("Starting receive thread " + str(name))
	tc_receiver = daedalus2.MAVLink(file)
	while True:
		try:
			raw = s.recv(1)
			message = tc_receiver.parse_char(raw)
			if message:
				print("\n" + str(message))
				print(str(message.to_dict()))
				print(str(message.get_fieldnames()))
				print(str(message.get_payload()))
				print(str(message.get_header().srcComponent.to_bytes(1, 'big')))
		except:
			break


def loop():
	s: socket.socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
	s.connect(TELESTION_CONNECTION)

	file: BinaryIO = s.makefile("rbw")

	print("Connection to {0} established.".format(str(TELESTION_CONNECTION)))
	print("Sending TM every {0} seconds until you stop the script by pressing Ctrl+C.".format(str(MESSAGE_INTERVAL)))
	print()
	print("(every '*' indicates that a batch of messages has been sent)")

	receiver = threading.Thread(target=receive, args=(1, s, file))
	receiver.start()

	while not STOP_FLAG:
		ejector = daedalus2.MAVLink(file, srcSystem=EJECTOR_ID, srcComponent=192)  # Ejector
		seed_a = daedalus2.MAVLink(file, srcSystem=SEED_A_ID, srcComponent=192)
		seed_b = daedalus2.MAVLink(file, srcSystem=SEED_B_ID, srcComponent=192)

		seed_a.seed_heartbeat_send(int(time.time()), 2, random.random(), 1, 0, [1, 2, 3, 4, 5, 6, 7, 8], 5, 5, 5)
		seed_b.seed_heartbeat_send(int(time.time()), 4, random.random(), 1, 0, [1, 2, 3, 4, 5, 6, 7, 8], 5, 5, 5)
		ejector.ejector_log_send(int(time.time()), b'Hallo Welt')
		ejector.ejector_heartbeat_send(int(time.time()), 5)

		file.flush()

		print("*", end='')

		time.sleep(MESSAGE_INTERVAL)

	s.close()


if __name__ == '__main__':
	print("Schreibtischtest Umgebungssimulator")
	print("===================================")
	loop()

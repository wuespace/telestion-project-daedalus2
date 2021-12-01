import time
import base64
import requests
import urllib.parse
import socket
import getpass

#
# Downloads and packs the Ublox data ready for upload via Telestion.
#

hostname = socket.gethostname()
username = getpass.getuser()

escaped_time = time.strftime("%Y-%m-%d-%H_%M_%S%z")
timestamp = time.strftime("%H:%M:%S %d.%m.%Y")
print("Timestamp: " + timestamp)

# access token - might expire -> generate a new one at:
# https://www.u-blox.com/en/assistnow-service-evaluation-token-request-form
print("==> Read token")
with open('token.txt', 'r') as file:
  token = file.read().rstrip()

### define request parameters ###
# for further information have a look at:
# https://www.u-blox.com/sites/default/files/products/documents/MultiGNSS-Assistance_UserGuide_%28UBX-13004360%29.pdf

# specify format of data return mga or aid
data_format = "mga"

# required datatypes (comma seperated)
#datatype = "eph,alm,aux,pos"
datatype = "eph"

# specify GNSS to be used (comma seperated)
gnss = "gps"

# approx user latitude in WGS84 in deg (min: -90, max: +90)
lat = 49.4

# approx user longitude in WGS84 in deg (min: -180, max: +180)
lon = -8.4

# approx user altitude above WGS84 ellipsoid in meters (min: -1000, max: +50000)
alt = 100

payload = {
  "token": token,
  "datatype": datatype,
  "format": data_format,
  "gnss": gnss,
  "lat": lat,
  "lon": lon,
  "alt": alt
}
payload_str = urllib.parse.urlencode(payload,safe=",")

# redundant server addresses for ublox assist now
# if connection is failed you may want to try the other address
serverAdd1 = "https://online-live1.services.u-blox.com/GetOnlineData.ashx"
serverAdd2 = "https://online-live2.services.u-blox.com/GetOnlineData.ashx"

### Download ###
print("==> Download")
try:
    r = requests.get(serverAdd1, params=payload_str)
    r.raise_for_status()
    #print(r.url)
    #  print(r.content)
except requests.exceptions.HTTPError as err:
    raise SystemExit(err)

### Save as binary ###
print("==> Save binary")
filename = "assist-now-binary-" + escaped_time + ".bin"
with open(filename, "wb") as file:
  file.write(r.content)

### convert and save as telestion binary ###
print("==> Convert and save telestion binary")
title = "assist-now " + timestamp + " on " + username + "@" + hostname + "\n"
encoded = base64.b64encode(r.content)
filename = "assist-now-telestion-" + escaped_time + ".bin"
with open(filename, "wb") as file:
  file.write(title.encode("ascii"))
  file.write(encoded)

print("==> Done")

#!/usr/bin/env python3

import time
import os
import requests

REPO_OWNER="fussel178"
REPO_NAME="telestion-project-automated-setup"

RELEASE_FILES=["telestion", "docker-compose.yml", "config.json"]

#REPO_OWNER="wuespace"
#REPO_NAME="telestion-project-daedalus2"

PWD=os.getcwd()
APP_DIR=os.path.join(PWD, REPO_NAME)

def section(str):
    print("==>", str)

def info(str):
    print("  ->", str)

def error(str):
    print("==> ERROR:", str)
    exit(1)

print("""

                     .oddddoc:'.
                     .WXXXXWMMMMXx:.
                              !oOWMWO:
          ;0d.       .ldxxdl.     !0MMK;
         kMMK'       'MWNNWMMMNk;   ,KMMx
       .KMMx   ..           !lOWMXl   xMMK.
       0MMd   ;ccc,            .OMM0.  dMM0
      ;MMO   ;cccccc,.  .        oMMK.  OMM;
      xMMc  .ccccccccclKMMK:      0MMc  cMMd     Telestion Setup script
      kMM;  'cccccccclNMMMMW.     dMMd  ;MMk     by WüSpace e. V. (c) 2021
      dMMl  .cccccccccl0NX0c      ONN:  :NNo
      ,MMK   ;cccccccccccccc;.
       kMMk   ,ccccccccccccccc:.
       .OMMO.  .;ccccccccccccccc:.
         oWMXc    .;cccccccccc;.     .
          'OMMXo.     ......      .oXWo
            ,xXMMXkl;...  ...;ckXMMNx,
               'lONMMMMMWMMMMMMNOl'
                   ''::cllc::''

""")

time.sleep(0.5)

section("Create app directory...")
info("Create folder...")

os.makedirs(APP_DIR, exist_ok=True)
os.chdir(APP_DIR)

latest_release=requests.get(url="https://api.github.com/repos/{}/{}/releases/latest".format(REPO_OWNER, REPO_NAME)).json()["tag_name"]

info("Latest release: {}".format(latest_release))

for file in RELEASE_FILES:
    info("Download {}...".format(file))
    try:
        with open(file, 'wb') as writer:
            with requests.get("https://github.com/{}/{}/releases/download/{}/{}".format(REPO_OWNER, REPO_NAME, latest_release, file), stream=True) as r:
                writer.write(r.content)
    except EnvironmentError:
        error("Cannot download {}".format(file))

info("Setup folder structure...")
os.makedirs("conf", exist_ok=True)
os.makedirs("data", exist_ok=True)
os.replace("config.json", os.path.join("conf", "config.json"))

section("Pull docker images...")
result = os.system("docker-compose --profile prod pull")
if result > 0:
    error("Failed to pull docker images")

section("Cleaning up...")

os.chdir(PWD)

print("""
Your application is ready!

Go into the app directory:

cd $REPO_NAME

and call:

./telestion.py --help

for an options overview.

Happy hacking.
Your Telestion Team.
""")

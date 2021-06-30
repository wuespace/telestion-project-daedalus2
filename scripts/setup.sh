#!/usr/bin/env bash

set -e

# Downloads and sets up the Telestion Application on the current operating system.
# by WüSpace e. V. (c) 2021

REPO_OWNER="fussel178"
REPO_NAME="telestion-project-automated-setup"

REQUIRED_TOOLS="curl grep sudo docker docker-compose"
RELEASE_FILES="telestion docker-compose.yml config.json"

# === OTHER SCRIPT REQUIRED VARIABLES ===
PWD="$(pwd)"
APP_DIR="$PWD/$REPO_NAME"

# colors
FM_NC="\e[0m"
FM_RED="\e[1;31m"
FM_YELLOW="\e[1;33m"
FM_GREEN="\e[1;32m"
FM_GREY="\e[0;37m"
FM_WHITE="\e[1;37m"
FM_CYAN="\e[1;36m"
FM_PURPLE="\e[1;35m"
FM_BLUE="\e[1;34m"

EXIT_SUCCESS=0
EXIT_FAILURE=1
EXIT_MISSING_TOOL=10
EXIT_PRIVILEGES=20
EXIT_NO_DOWNLOAD=30

section() {
    echo -e "${FM_GREEN}==>${FM_WHITE} $1${FM_NC}"
}

info() {
    echo -e "${FM_BLUE}  ->${FM_WHITE} $1${FM_NC}"
}

error() {
    echo -e "${FM_RED}==> ERROR:${FM_WHITE} $1${FM_NC}"
}

echo -e "${FM_WHITE}

                     .oddddoc:'.
                     .WXXXXWMMMMXx:.
                              !oOWMWO:
          ;0d.       .ldxxdl.     !0MMK;
         kMMK'       'MWNNWMMMNk;   ,KMMx
       .KMMx   ${FM_PURPLE}..${FM_WHITE}           !lOWMXl   xMMK.
       0MMd   ${FM_PURPLE};ccc,${FM_WHITE}            .OMM0.  dMM0
      ;MMO   ${FM_PURPLE};cccccc,.${FM_WHITE}  .        oMMK.  OMM;
      xMMc  ${FM_PURPLE}.ccccccccc${FM_WHITE}lKMMK:      0MMc  cMMd     Telestion Setup script
      kMM;  ${FM_PURPLE}'ccccccccl${FM_WHITE}NMMMMW.     dMMd  ;MMk     by WüSpace e. V. (c) 2021
      dMMl  ${FM_PURPLE}.cccccccccl${FM_WHITE}0NX0${FM_PURPLE}c${FM_WHITE}      ONN:  :NNo
      ,MMK   ${FM_PURPLE};cccccccccccccc;.${FM_WHITE}
       kMMk   ${FM_PURPLE},ccccccccccccccc:.${FM_WHITE}
       .OMMO.  ${FM_PURPLE}.;ccccccccccccccc:.${FM_WHITE}
         oWMXc    ${FM_PURPLE}.;cccccccccc;.${FM_WHITE}     .
          'OMMXo.     ${FM_PURPLE}......${FM_WHITE}      .oXWo
            ,xXMMXkl;...  ...;ckXMMNx,
               'lONMMMMMWMMMMMMNOl'
                   ''::cllc::''

${FM_NC}"

sleep 0.5

section "Check permissions..."

if [ "$(id -u)" == "0" ]; then
    error "Please do not start this script with root privileges"
    exit $EXIT_PRIVILEGES
fi

if ! [ -w "$PWD" ]; then
    error "Sorry, the current directory is not writable. Please check the permissions and try again"
    exit $EXIT_PRIVILEGES
fi

section "Check toolset..."
for tool in $REQUIRED_TOOLS; do
    if ! command -v "$tool" &> /dev/null; then
        error "Command $tool not found. Please ensure you have installed and set up the required toolset before continue."
        exit $EXIT_MISSING_TOOL
    fi
done

section "Create app directory..."
info "Create folder..."

mkdir -p "$APP_DIR"
cd "$APP_DIR"

latest_release="$(curl --silent "https://api.github.com/repos/$REPO_OWNER/$REPO_NAME/releases/latest" |
    grep '"tag_name":' | sed -E 's/.*"([^"]+)".*/\1/')"

info "Latest release: $latest_release"

for file in $RELEASE_FILES; do
    info "Download $file..."
    if ! curl -L "https://github.com/$REPO_OWNER/$REPO_NAME/releases/download/$latest_release/$file" --output "$file"; then
        error "Cannot download $file"
        exit $EXIT_NO_DOWNLOAD
    fi
done

info "Setup folder structure..."
mkdir -p conf data
mv -f config.json conf/
chmod +x telestion

section "Pull docker images..."
if ! sudo docker-compose --profile prod pull; then
    error "Failed to pull docker images"
    exit $EXIT_ERROR
fi

section "Cleaning up..."

cd "$PWD"

echo -e "${FM_WHITE}
Your application is ready!

Go into the app directory:
${FM_CYAN}
cd $REPO_NAME
${FM_WHITE}
and call:
${FM_CYAN}
./telestion --help
${FM_WHITE}
for an options overview.

Happy hacking.
Your Telestion Team.
"

exit $EXIT_SUCCESS

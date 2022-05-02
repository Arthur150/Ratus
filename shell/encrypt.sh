#!/usr/bin/env bash
##
# @Description
# This script encrypt with 'gpg' a file
#
# @Args
# - $1 : Password
# - $2 : Source file
# - $3 : Output file
# @Error
# - 11 : No password
# - 12 : No file found
# - 13 : No output defined
##


# Test if the first arg is not empty
if [ -z "${1}" ]; then
    exit 11
fi

# Test if the second arg is not empty
if [ -z "${2}" ]; then
    exit 12
fi

# Test if the third arg is not empty
if [ -z "${3}" ]; then
    exit 13
fi

gpg -c \
  --quiet \
  --batch \
  --armor \
  --passphrase "${1}" \
  --output "${3}" \
  "${2}"
#!/usr/bin/env bash
FROM="/Users/nowshad/programming/octagon/src/main/resources/shell/local/clean-dbs.sh"
TO="root@mastermcq.com:/root/shell"

scp ${FROM} ${TO}

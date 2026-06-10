FROM ubuntu:latest
LABEL authors="sahoo"

ENTRYPOINT ["top", "-b"]
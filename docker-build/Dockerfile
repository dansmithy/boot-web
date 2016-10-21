FROM java:8

MAINTAINER dansmithy@gmail.com

ENV BOOT_AS_ROOT=yes

WORKDIR /opt

RUN bash -c "cd /usr/local/bin && curl -fsSLo boot https://github.com/boot-clj/boot-bin/releases/download/latest/boot.sh && chmod 755 boot"
RUN boot -u
RUN boot


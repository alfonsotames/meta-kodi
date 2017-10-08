DESCRIPTION = "Free portable AirPlay server implementation"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7fff59c88f17faa814f26f26b06a7100"

PR = "r0"
inherit autotools pkgconfig

SRC_URI = "git://github.com/juhovh/shairplay.git"
SRCREV="ce80e005908f41d0e6fde1c4a21e9cb8ee54007b"
S = "${WORKDIR}/git"


FILES_${PN} += "${libdir}/*.so"




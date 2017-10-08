DESCRIPTION = "Transmission is a cross-platform BitTorrent client that is: easy, lean, native and powerful"
LICENSE = "GPLv2 & MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=a1923fe8f8ff37c33665716af0ec84f1"
DEPENDS = "curl"

inherit autotools 

SRC_URI = "https://transmission.cachefly.net/transmission-2.84.tar.xz"
SRC_URI[md5sum] = "411aec1c418c14f6765710d89743ae42"
SRC_URI[sha256sum] = "a9fc1936b4ee414acc732ada04e84339d6755cd0d097bcbd11ba2cfc540db9eb"

EXTRA_OECONF = "--enable-lightweight"

S = "${WORKDIR}/transmission-2.84"

do_configure() {
        ${S}/autogen.sh --noconfigure
        oe_runconf
}

FILES_${PN} += "/usr/share/icons/hicolor "

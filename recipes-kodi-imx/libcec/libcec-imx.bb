DESCRIPTION = "libCEC allows you in combination with the right hardware to control your device with your TV remote control. Utilising your existing HDMI cabling"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=e296fd6027598da75a7516ce1ae4f56a"

PR = "r0"
inherit autotools pkgconfig

SRC_URI = "git://github.com/alfonsotames/libcec.git;branch=imxsupport"
SRCREV = "f6add0e1657f62bc759abb6be0a5df9ce18e058e"

EXTRA_OECONF="--enable-imx6"

S = "${WORKDIR}/git"

# cec-client and xbmc may need the .so present to work :(
#FILES_${PN} += "${libdir}/*.so"



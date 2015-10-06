DESCRIPTION = "libCEC allows you in combination with the right hardware to control your device with your TV remote control. Utilising your existing HDMI cabling"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://README;md5=2a51a796ca47e91336a4d198147ba58f"

PR = "r0"
inherit autotools pkgconfig

SRC_URI = "git://github.com/alfonsotames/libsquish.git"
SRCREV = "a9b44adc6c9d7ae74e23392a83995ba59b436950"


S = "${WORKDIR}/git"

do_install() {
   cd ${S}
   mkdir ${D}/lib
   mkdir ${D}/lib/pkgconfig
   mkdir ${D}/include
   export INSTALL_DIR=${D}
   make install
}




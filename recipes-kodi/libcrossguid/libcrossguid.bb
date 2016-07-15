DESCRIPTION = "Lightweight cross platform C++ GUID/UUID library"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1373274bc8d8001edc54933919f36f68"

PR = "r0"
inherit autotools pkgconfig

SRC_URI = "git://github.com/alfonsotames/crossguid.git"

SRCREV = "3abbfb94c1972ad5f9742f6987aebf6cfb213aa8"

S = "${WORKDIR}/git"

EXTRA_CXXFLAGS = "-I. -fPIC -Wall -std=c++11 -DGUID_LIBUUID"

do_compile() {
    cd ${S}
    ${CXX} ${CXXFLAGS} ${EXTRA_CXXFLAGS} -c -o guid.o guid.cpp
    ${AR} rvs libcrossguid.a guid.o
}

do_install() {
	install -d ${D}/usr/include
	install -d ${D}/usr/lib/pkgconfig
	install -m 644 ${S}/guid.h ${D}/usr/include
	install -m 644 ${S}/libcrossguid.a ${D}/usr/lib
	install -m 644 ${S}/crossguid.pc ${D}/usr/lib/pkgconfig
}

do_package_qa() {
}

FILES_${PN} = "/"


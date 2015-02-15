DESCRIPTION = "FFMPEG for Kodi"

DEPENDS += " zlib "

LICENSE = "GPLv2"

LIC_FILES_CHKSUM = "file://LICENSE.md;md5=af4fe319be2972a4fa475030d294cf1f"

PR = "r0"

inherit autotools pkgconfig

SRC_URI = "https://github.com/xbmc/FFmpeg/archive/2.4.4-Helix.tar.gz"

SRC_URI[md5sum] = "19b5d29ef6b5a6fc202c652fe3905d9b"
SRC_URI[sha256sum] = "cbaac116254004f993a0c62bb77e13745c9ac00960f2a0ef088baf09b0ad73de"

S = "${WORKDIR}/FFmpeg-2.4.4-Helix/"

EXTRA_OECONF = " \
	--prefix=${prefix} \
        --arch=${TARGET_ARCH} \
        --cross-prefix=${TARGET_PREFIX} \
        --disable-stripping \
        --enable-cross-compile \
        --enable-pthreads \
        --enable-shared \
        --enable-swscale \
	--enable-pic \
        --target-os=linux \
	--enable-gpl \
	--sysroot=${STAGING_DIR_HOST} \
	--disable-vdpau \
	--extra-cflags=' -march=armv7-a -mfloat-abi=hard -mfpu=neon -mtune=cortex-a9' \
"
FULL_OPTIMIZATION_armv7a = "-fexpensive-optimizations  -ftree-vectorize -fomit-frame-pointer -O4 -ffast-math"
BUILD_OPTIMIZATION = "${FULL_OPTIMIZATION}"

#        --enable-libmp3lame gives linking errors with kodi!

do_configure() {
	cd ${S}
        ./configure ${EXTRA_OECONF}
}

do_compile() {
   cd ${S}
   make -j 8 BUILDDIR=${BUILDDIR} DESTDIR=${D}
}

do_install() {
   cd ${S}
   make -j 8 BUILDDIR=${BUILDDIR} DESTDIR=${D} install
}

FILES_${PN} += "/"


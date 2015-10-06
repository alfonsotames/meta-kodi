DESCRIPTION = "FFMPEG for Kodi"

DEPENDS += " zlib "

LICENSE = "GPLv2"

LIC_FILES_CHKSUM = "file://LICENSE.md;md5=4e0e4c9534db149e6b733ea75e421da7"

PR = "r0"

inherit autotools pkgconfig

SRC_URI = "https://github.com/xbmc/FFmpeg/archive/2.6.4-Isengard.tar.gz"

SRC_URI[md5sum] = "3dbd015fbfea2cbedf1fbd0779ab987e"
SRC_URI[sha256sum] = "2487a6d4ad5701ad22582fc064ce39b60c383eec4958ca1e3218379035fa523f"

S = "${WORKDIR}/FFmpeg-2.6.4-Isengard/"

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


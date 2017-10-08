DESCRIPTION = "FFMPEG for Kodi"

DEPENDS += " zlib "

LICENSE = "GPLv2"

LIC_FILES_CHKSUM = "file://LICENSE.md;md5=ca4b6d4d7cd6f1a0b17f43072ef9407b"

PR = "r0"

inherit autotools pkgconfig

SRC_URI = "https://github.com/xbmc/FFmpeg/archive/3.1.11-Krypton-17.5.tar.gz"

SRC_URI[md5sum] = "6cf2d25e2a38fd274d9fb37a7bb4f953"
SRC_URI[sha256sum] = "7df8bce40765b39de5766ca9d08b5b9ac1f498c65c805c989461b907cee6b7c0"

S = "${WORKDIR}/FFmpeg-3.1.11-Krypton-17.5/"

EXTRA_OECONF = " \
	--prefix=${prefix} \
        --arch=${TARGET_ARCH} \
        --cross-prefix=${TARGET_PREFIX} \
        --enable-static \
        --disable-stripping \
        --enable-cross-compile \
        --enable-pthreads \
        --enable-swscale \
	--enable-pic \
        --target-os=linux \
	--enable-gpl \
	--sysroot=${STAGING_DIR_HOST} \
	--disable-vdpau \
	--extra-cflags=' -march=armv7-a -mfloat-abi=hard -mfpu=neon -mtune=cortex-a9 ' \
"
FULL_OPTIMIZATION_armv7a = "-fexpensive-optimizations  -ftree-vectorize -fomit-frame-pointer -O4 -ffast-math"
BUILD_OPTIMIZATION = "${FULL_OPTIMIZATION}"

#        --enable-libmp3lame gives linking errors with kodi!

do_configure() {
	cd ${S}
        ./configure ${EXTRA_OECONF} --enable-static
}

do_compile() {
   cd ${S}
   make -j 8 BUILDDIR=${BUILDDIR} DESTDIR=${D}
}

do_install() {
   cd ${S}
   make -j 8 BUILDDIR=${BUILDDIR} DESTDIR=${D} install
   install -m 644 ${S}/ffmpeg ${D}/usr/bin
   install -m 755 ${S}/ffmpeg ${STAGING_BINDIR}
}

FILES_${PN} += "/"


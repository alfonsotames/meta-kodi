DESCRIPTION = "FFMPEG for Kodi"

DEPENDS += " zlib "

LICENSE = "GPLv2"

LIC_FILES_CHKSUM = "file://LICENSE.md;md5=5c6d1ed56d15ca87ddec48d0c3a2051d"

PR = "r0"

inherit autotools pkgconfig

SRC_URI = "https://github.com/xbmc/FFmpeg/archive/2.8.6-Jarvis-16.1.tar.gz"

SRC_URI[md5sum] = "7f60631a38e7fb604356c4649a1beb86"
SRC_URI[sha256sum] = "567171041491f6ea2eba9b4cda573cf864eeda67fd5c06a1bd82a2244e5ae8e2"

S = "${WORKDIR}/FFmpeg-2.8.6-Jarvis-16.1/"

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


DESCRIPTION = "dcadec is a free DTS Coherent Acoustics decoder with support for HD extensions"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://README.md;md5=5313a289dbd1bc1949617743cbca1473"

PR = "r0"
inherit autotools pkgconfig

SRC_URI = "git://github.com/alfonsotames/dcadec.git"
SRCREV = "b93deed1a231dd6dd7e39b9fe7d2abe05aa00158"


S = "${WORKDIR}/git"

do_compile() {
	PREFIX=/usr make -C ${S}
}

do_install() {
	mkdir -p ${D}/usr/{include,lib/pkgconfig}
	PREFIX=/usr DESTDIR=${D} make -C ${S} install
}

do_package_qa() {
}

FILES_${PN} = "/"



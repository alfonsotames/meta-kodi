FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append_mx6 = " file://Add_Sound_Devices_For_Kodi.patch"

PACKAGE_ARCH_mx6 = "${MACHINE_SOCARCH}"



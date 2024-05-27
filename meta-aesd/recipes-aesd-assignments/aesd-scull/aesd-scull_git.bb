# See https://git.yoctoproject.org/poky/tree/meta/files/common-licenses
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit module

SRC_URI = "git://git@github.com/cu-ecen-aeld/assignment-7-wangmengj.git;protocol=ssh;branch=main"
#PV = "1.0+git${SRCPV}"
SRCREV = "b6de1c787c4cf4b1ba05fd7b67eb5a712517f400"

# This sets your staging directory based on WORKDIR, where WORKDIR is defined at 
# https://docs.yoctoproject.org/ref-manual/variables.html?highlight=workdir#term-WORKDIR
# We reference the "server" directory here to build from the "server" directory
# in your assignments repo
S = "${WORKDIR}/git/scull"

# TODO: Add the aesdsocket application and any other files you need to install
# See https://git.yoctoproject.org/poky/plain/meta/conf/bitbake.conf?h=kirkstone
#FILES:${PN} += "${bindir}/aesdsocket"
#FILES:${PN} += "${sysconfdir}/init.d/aesdsocket-start-stop"
FILES:${PN} += "${sysconfdir}/init.d/S981lddmodules"
FILES:${PN} += "${bindir}/scull_load"
FILES:${PN} += "${bindir}/scull_unload"


# TODO: customize these as necessary for any libraries you need for your application
# (and remove comment)
#TARGET_LDFLAGS += "-pthread -lrt"

#do_configure () {
#	:
#}

EXTRA_OEMAKE:append:task-install = " -C ${STAGING_KERNEL_DIR} M=${S}"
EXTRA_OEMAKE += "KERNELDIR=${STAGING_KERNEL_DIR}"

#do_compile () {
#	oe_runmake 
#}


# Inherit the update-rc.d.bbclass file located in openembedded/classes/.
# This will take care of setting up startup links when the package is
# installed.
#inherit update-rc.d

# Tell the update-rc.d package which program will be used as the startup
# script.
# The script will be called with the "start" command at system
# startup, the "stop" command at system shutdown, and the "restart" command
# when the package is updated.
#INITSCRIPT_NAME = "S981lddmodules"

do_install () {
    module_do_install

	install -d ${D}${bindir}
	install -m 0755 ${S}/scull_load ${D}${bindir}/
	install -m 0755 ${S}/scull_unload ${D}${bindir}/
}

RPROVIDES:${PN} += "kernel-module-scull"

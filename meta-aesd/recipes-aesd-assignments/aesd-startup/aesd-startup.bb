# See https://git.yoctoproject.org/poky/tree/meta/files/common-licenses
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

# TODO: Set this  with the path to your assignments rep.  Use ssh protocol and see lecture notes
# about how to setup ssh-agent for passwordless access
SRC_URI = "file://S981lddmodules"

#PV = "1.0+git${SRCPV}"
# TODO: set to reference a specific commit hash in your assignment repo

# This sets your staging directory based on WORKDIR, where WORKDIR is defined at 
# https://docs.yoctoproject.org/ref-manual/variables.html?highlight=workdir#term-WORKDIR
# We reference the "server" directory here to build from the "server" directory
# in your assignments repo
S = "${WORKDIR}"

# TODO: Add the aesdsocket application and any other files you need to install
# See https://git.yoctoproject.org/poky/plain/meta/conf/bitbake.conf?h=kirkstone


# TODO: customize these as necessary for any libraries you need for your application
# (and remove comment)
#TARGET_LDFLAGS += "-pthread -lrt"

#do_configure () {
#	:
#}

#EXTRA_OEMAKE:append:task-install = " -C ${STAGING_KERNEL_DIR} M=${S}"
#EXTRA_OEMAKE += "KERNELDIR=${STAGING_KERNEL_DIR}"

do_compile () {
}


# Inherit the update-rc.d.bbclass file located in openembedded/classes/.
# This will take care of setting up startup links when the package is
# installed.
inherit update-rc.d

# Tell the update-rc.d package which program will be used as the startup
# script.
# The script will be called with the "start" command at system
# startup, the "stop" command at system shutdown, and the "restart" command
# when the package is updated.
INITSCRIPT_NAME = "S981lddmodules"

do_install () {
	install -d ${D}/etc/init.d
    install -m 0755 ${WORKDIR}/S981lddmodules ${D}/etc/init.d
}


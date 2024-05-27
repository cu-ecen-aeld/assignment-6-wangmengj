# See https://git.yoctoproject.org/poky/tree/meta/files/common-licenses
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit module

# TODO: Set this  with the path to your assignments rep.  Use ssh protocol and see lecture notes
# about how to setup ssh-agent for passwordless access
# SRC_URI = "git://git@github.com/cu-ecen-aeld/<your assignments repo>;protocol=ssh;branch=master"
SRC_URI = "git://git@github.com/cu-ecen-aeld/assignment-7-wangmengj.git;protocol=ssh;branch=main"

#PV = "1.0+git${SRCPV}"
# TODO: set to reference a specific commit hash in your assignment repo
SRCREV = "b6de1c787c4cf4b1ba05fd7b67eb5a712517f400"

# This sets your staging directory based on WORKDIR, where WORKDIR is defined at 
# https://docs.yoctoproject.org/ref-manual/variables.html?highlight=workdir#term-WORKDIR
# We reference the "server" directory here to build from the "server" directory
# in your assignments repo
S = "${WORKDIR}/git/misc-modules"

# TODO: Add the aesdsocket application and any other files you need to install
# See https://git.yoctoproject.org/poky/plain/meta/conf/bitbake.conf?h=kirkstone
FILES:${PN} += "${bindir}/module_load"
FILES:${PN} += "${bindir}/module_unload"
#FILES:${PN} += "${sysconfdir}/init.d/aesdsocket-start-stop"
#FILES:${PN} += "${sysconfdir}/rc3.d/S99aesdsocket"


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

do_install () {
#	# TODO: Install your binaries/scripts here.
#	# Be sure to install the target directory with install -d first
#	# Yocto variables ${D} and ${S} are useful here, which you can read about at 
#	# https://docs.yoctoproject.org/ref-manual/variables.html?highlight=workdir#term-D
#	# and
#	# https://docs.yoctoproject.org/ref-manual/variables.html?highlight=workdir#term-S
#	# See example at https://github.com/cu-ecen-aeld/ecen5013-yocto/blob/ecen5013-hello-world/meta-ecen5013/recipes-ecen5013/ecen5013-hello-world/ecen5013-hello-world_git.bb
    module_do_install

	install -d ${D}${bindir}
	install -m 0755 ${S}/module_load ${D}${bindir}/
	install -m 0755 ${S}/module_unload ${D}${bindir}/

#	ln -sf ../init.d/aesdsocket-start-stop ${D}${sysconfdir}/rc3.d/S99aesdsocket
}

RPROVIDES:${PN} += "kernel-module-complete"
RPROVIDES:${PN} += "kernel-module-hello"
RPROVIDES:${PN} += "kernel-module-jiq"
RPROVIDES:${PN} += "kernel-module-kdataalign"
RPROVIDES:${PN} += "kernel-module-seq"
RPROVIDES:${PN} += "kernel-module-sleepy"
RPROVIDES:${PN} += "kernel-module-faulty"
RPROVIDES:${PN} += "kernel-module-hellop"
RPROVIDES:${PN} += "kernel-module-jit"
RPROVIDES:${PN} += "kernel-module-kdatasize"
RPROVIDES:${PN} += "kernel-module-silly"

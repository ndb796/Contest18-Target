#!/bin/sh
#
# insecure_httpd.sh - startup script for insecure_httpd on FreeBSD
#
# This should be manually installed as:
#  /usr/local/etc/rc.d/insecure_httpd
# It gets run at boot-time.
#
# Variables available:
#   insecure_httpd_enable='YES'
#   insecure_httpd_program='/usr/local/sbin/insecure_httpd'
#   insecure_httpd_pidfile='/var/run/insecure_httpd.pid'
#   insecure_httpd_devfs=...
#   insecure_httpd_flags=...
#
# PROVIDE: insecure_httpd
# REQUIRE: LOGIN FILESYSTEMS
# KEYWORD: shutdown

echo 0 > /proc/sys/kernel/randomize_va_space

. /etc/rc.subr

name='insecure_httpd'
rcvar='insecure_httpd_enable'
start_precmd='insecure_httpd_precmd'
insecure_httpd_enable_defval='NO'

load_rc_config "$name"
command="${insecure_httpd_program:-/usr/local/sbin/${name}}"
pidfile="${insecure_httpd_pidfile:-/var/run/${name}.pid}"
command_args="-i ${pidfile}"

insecure_httpd_precmd ()
{
	if [ -n "$insecure_httpd_devfs" ] ; then
		mount -t devfs devfs "$insecure_httpd_devfs"
		devfs -m "$insecure_httpd_devfs" rule -s 1 applyset
		devfs -m "$insecure_httpd_devfs" rule -s 2 applyset
	fi
}

run_rc_command "$1"

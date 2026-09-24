#!/bin/sh
#
# Copyright © 2015-2026 the original authors.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      https://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

##############################################################################
##
##  Gradle start up script for POSIX
##
##############################################################################

# Attempt to set APP_HOME
APP_HOME=$( cd "$( dirname "$0" )" && pwd )
APP_NAME="Gradle"
APP_BASE_NAME=$( basename "$0" )

# Add default JVM options here. You can also use JAVA_OPTS and GRADLE_OPTS to pass JVM options to this script.
DEFAULT_JVM_OPTS='"-Xmx64m" "-Xms64m"'

# Use the maximum available, or set MAX_FD != -1 to use that value.
MAX_FD="maximum"

warn () {
    echo "$*"
}
die () {
    echo
    echo "$*"
    echo
    exit 1
}

# OS specific support (command for finding readlink)
cygwin=false
msys=false
darwin=false
nonstop=false
case "`uname`" in
  CYGWIN* )
    cygwin=true
    ;;
  Darwin* )
    darwin=true
    ;;
  MINGW* | MSYS* | CYGWIN* )
    msys=true
    ;;
  NONSTOP* )
    nonstop=true
    ;;
esac

CLASSPATH=$APP_HOME/gradle/wrapper/gradle-wrapper.jar

# Determine the Java command to use to start the JVM.
if [ -n "$JAVA_HOME" ] ; then
    if [ -x "$JAVA_HOME/jre/sh/java" ] ; then
        JAVACMD="$JAVA_HOME/jre/sh/java"
    else
        JAVACMD="$JAVA_HOME/bin/java"
    fi
    if [ ! -x "$JAVACMD" ] ; then
        die "ERROR: JAVA_HOME is set to an invalid directory: $JAVA_HOME"
    fi
else
    JAVACMD="java"
    which java >/dev/null 2>&1 || die "ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH."
fi

# Set the classpath
WRAPPER_JAR="$APP_HOME/gradle/wrapper/gradle-wrapper.jar"

# Escape application args
save () {
    for i do printf %s\\n "$i" | sed "s/'/'\\\\''/g;1s/^/'/;\$s/\$/' \\\\/" ; done
    echo " "
}
APP_ARGS=$(save "$@")

# Collect all arguments for the java command, following the Gradle convention
# and allowing any JVM options to be passed via GRADLE_OPTS
GRADLE_APP_ARGS=""
while [ $# -gt 0 ] ; do
    case "$1" in
      --no-daemon | --daemon | --no-watch-fs | --watch-fs )
        GRADLE_APP_ARGS="$GRADLE_APP_ARGS $1"
        shift
        ;;
      -D* | -X* )
        JAVA_OPTS="$JAVA_OPTS $1"
        shift
        ;;
      *)
        break
        ;;
    esac
done

# Split up the JVM_OPTS into an array so that whitespace is preserved
# shellcheck disable=SC2086
set -- $DEFAULT_JVM_OPTS $JAVA_OPTS $GRADLE_OPTS \
     "-Dorg.gradle.appname=$APP_BASE_NAME" \
     -classpath "$CLASSPATH" \
     org.gradle.wrapper.GradleWrapperMain \
     "$@"

# Stop when "xargs" is not available.
if ! command -v xargs >/dev/null 2>&1
then
    die "xargs is not available"
fi

# Use "xargs" to parse quoted arguments and arguments with spaces.
# I'm not sure why the original code didn't just use "$@", but changing it now
# would be a breaking change.
# shellcheck disable=SC2016
eval 'exec "$JAVACMD" "$@"' ${APP_ARGS} ${GRADLE_APP_ARGS}

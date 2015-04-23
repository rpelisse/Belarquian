#!/bin/bash

mvn -Dmaven.test.skip=true clean compile package -Dversion.jboss.logging=3.2.1.Final
if [ ${?} -eq 0 ]; then
  mvn test -Pwildfly -Dversion.jboss.logging=3.2.1.Final
fi

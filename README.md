#summary Instructions to build RPSIM and execute DEMO simulation
#labels Featured

= Build and run demo simulation =



== Dependencies ==

*RPSIM* rule based process simulation project is built on discrete event simulator DESMO-J and integrated business logic engine DROOLS/JBPM. All dependencies except DESMO-J are available in central MAVEN repository and are downloaded automatically. However DESMO-J library should be manually downloaded and installed into local MAVEN repository.

Only DESMO-J core library is required to run *RPSIM*. This library can be downloaded from DESMO-J [http://desmoj.sourceforge.net/download.html download page]. Library can be installed to local MAVEN repository by executing command below.

{{{
mvn install:install-file \
	-Dfile=<path-to-file> \
	-DgroupId=desmoj \
	-DartifactId=desmoj-core \
	-Dversion=<version> \
	-Dpackaging=jar
}}}

For example for DESMO-J core library version 2.3.4 the following command can be used.

{{{
mvn install:install-file \
	-Dfile=desmoj-2.3.4-core-bin.jar \
	-DgroupId=desmoj \
	-DartifactId=desmoj-core \
	-Dversion=2.3.4 \
	-Dpackaging=jar
}}}

== Build ==

To build *RPSIM* library and install it to local MAVEN repository run command below from the root project folder.

{{{
mvn install
}}}

== Run demo simulation ==

*RPSIM* project contains demo simulation. It can be found in rpsim-demo/demo1 subproject. To run simulation the following maven command should be executed from the subproject folder.

{{{
mvn exec:java
}}}

FROM armdocker.rnd.ericsson.se/proj_oss_releases/enm/jboss-as

RUN $JBOSS_HOME/bin/add-user.sh fred12 -p f231-512 -s



ENV POSTGRESQL_VERSION 9.4-1201-jdbc41
ARG DB_HOST=postgresql-server
ARG DB_NAME=sfwkdb
ARG DB_USER=sfwkusr
ARG DB_PASS=1234qwer


#################################
# Postgress DataSource          #
#################################




# Install postgres drivers and datasource
RUN /bin/sh -c '$JBOSS_HOME/bin/standalone.sh &' && \
  sleep 30 && \ 
  cd /tmp && \
  curl --location --output postgresql-${POSTGRESQL_VERSION}.jar --url http://search.maven.org/remotecontent?filepath=org/postgresql/postgresql/${POSTGRESQL_VERSION}/postgresql-${POSTGRESQL_VERSION}.jar && \
  $JBOSS_HOME/bin/jboss-cli.sh --connect --command="deploy /tmp/postgresql-${POSTGRESQL_VERSION}.jar" && \
  $JBOSS_HOME/bin/jboss-cli.sh --connect --command="xa-data-source add --name=demo --jndi-name=java:/jdbc/datasources/demoDS --user-name=${DB_USER} --password=${DB_PASS} --driver-name=postgresql-9.4-1201-jdbc41.jar --xa-datasource-class=org.postgresql.xa.PGXADataSource --xa-datasource-properties=ServerName=${DB_HOST},PortNumber=5432,DatabaseName=${DB_NAME} --valid-connection-checker-class-name=org.jboss.jca.adapters.jdbc.extensions.postgres.PostgreSQLValidConnectionChecker --exception-sorter-class-name=org.jboss.jca.adapters.jdbc.extensions.postgres.PostgreSQLExceptionSorter" && \
  $JBOSS_HOME/bin/jboss-cli.sh --connect --command="/subsystem=datasources/xa-data-source=demo:enable(persistent=true)" && \
  $JBOSS_HOME/bin/jboss-cli.sh --connect --command=:shutdown && \
  sleep 10 && \ 
  rm -rf $JBOSS_HOME/standalone/configuration/standalone_xml_history/ $JBOSS_HOME/standalone/log/* && \  
  rm /tmp/postgresql-9.4*.jar && \
  rm -rf /tmp/postgresql-*.jar
FROM jboss/wildfly:9.0.0.Final
EXPOSE 8080
COPY maven /tmp/82615e3a-1dc7-4564-872b-c908b4169344/opt/jboss/wildfly/standalone/deployments//
USER root
RUN chown -R jboss:jboss /tmp/82615e3a-1dc7-4564-872b-c908b4169344 && cp -rp /tmp/82615e3a-1dc7-4564-872b-c908b4169344/* / && rm -rf /tmp/82615e3a-1dc7-4564-872b-c908b4169344
USER jboss

#IMAGEN MODELO
FROM eclipse-temurin:24.0.2_12-jdk

#DEFINIR EL DIRECTORIO RAIZ DE NUESTRO CONTENEDOR
WORKDIR /root

# ====================== VARIABLES DE ENTORNO ======================

# Database
ENV DB_HOST=""
ENV DB_PORT=""
ENV DB_DEFAULT=""
ENV DB_USERNAME=""
ENV DB_PASSWORD=""

# Server port
ENV BACKEND_PORT=8080

# MQTT server
ENV MQTT_BROKER_URL=tcp://localhost:1883
ENV MQTT_CLIENT_ID=""
ENV MQTT_USERNAME=""
ENV MQTT_PASSWORD=""

# MQTT Topics
ENV INBOUND_COLOR_TOPIC=intersection/color
ENV INBOUND_STATUS_TOPIC=intersection/status
ENV OUTBOUND_ACTIVATE_TOPIC=intersection/activate/

#Google Maps API
ENV GOOGLE_MAPS_API_KEY=""
ENV GOOGLE_MAPS_BASE_URL=""

# ================================================================

#COPIAR Y PEGAR ARCHIVOS DENTRO DEL CONTENEDOR
COPY ./pom.xml /root
COPY ./.mvn /root/.mvn
COPY ./mvnw /root

#DESCARGAR LAS DEPENDENCIAS
RUN ./mvnw dependency:go-offline

#COPIAR EL CODIGO FUENTE DENTRO DEL CONTENEDOR
COPY ./src /root/src

#CONSTRUIR NUESTRA APLICACION
RUN ./mvnw clean install -DskipTests

#LEVANTAR NUESTRA APLICACION CUANDO EL CONTENEDOR INICIE
ENTRYPOINT ["java", "-jar", "/root/target/traffic-light-uis-0.0.1-SNAPSHOT.jar"]
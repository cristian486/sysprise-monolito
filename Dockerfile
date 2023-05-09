FROM amazoncorretto:17.0.6-alpine3.17

ENV DATABASE_URL=jdbc:mysql://sysprise_database:3306/sysprise
ENV DATABASE_USER=root
ENV DATABASE_PASSWORD=f05313181ae0ef5ba1df08d08e0f49de0eecfcdf02e5af371ecc538d84fee67a
ENV SPRING_PROFILES_ACTIVE=prod
ENV SYSPRISE_JWT_SECRET=e6f29c84dff4a5ee86c07ced96b6a1637be860a096cf92e064953a4775d0b570

WORKDIR /sysprise/backend

COPY target/sysprise.jar .

ENTRYPOINT [ "java", "-jar", "sysprise.jar"]
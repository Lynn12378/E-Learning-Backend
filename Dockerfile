# 使用 JDK 21 基本映像檔進行構建
FROM openjdk:21-jdk-slim AS build

# 設定工作目錄
WORKDIR /app

# 複製 gradle 和源碼
COPY gradle gradle
COPY src src
COPY build.gradle settings.gradle ./
COPY gradlew gradlew

# 賦予 gradlew 執行許可權
RUN chmod +x gradlew

# 執行 gradle build，跳過測試
RUN ./gradlew build -x test

# 使用 JDK 21 JRE 映像來運行應用
FROM openjdk:21-jdk-slim

# 設定工作目錄
WORKDIR /app

# 從 build 階段複製 JAR 檔案到容器中
COPY --from=build /app/build/libs/*.jar app.jar

# 這會啟動應用
ENTRYPOINT ["java", "-jar", "app.jar"]

# 開放應用的端口
EXPOSE 8080

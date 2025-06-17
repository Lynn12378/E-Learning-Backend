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
RUN ./gradlew bootJar -x test # 這裡使用 bootJar 以確保只構建可執行 JAR

# 使用 JDK 21 JRE 映像來運行應用
FROM openjdk:21-jdk-slim

# 設定工作目錄
WORKDIR /app

# 從 build 階段複製指定名稱的 JAR 檔案到容器中
COPY --from=build /app/build/libs/app.jar app.jar

# 這會啟動應用
ENTRYPOINT ["java", "-jar", "app.jar"]

# 開放應用的端口
EXPOSE 8080
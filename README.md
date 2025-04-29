# E-Learning-Backend

./gradlew clean
./gradlew build
./gradlew bootRun
docker build -t elearning .  
docker tag elearning:latest lynnwang0108/elearning:latest
docker push lynnwang0108/elearning:latest
1. create all the necessary docker images locally:

cd play
docker build -t "play" .
cd ../mongo
docker build -t "mongo_seed" .
cd ../nginx
docker build -t "nginx:my" .
cd ../ng/client
docker build -t "client" .

2. run it with docker-compose

cd ..
docker-compose build
docker-compose up

3. go to localhost:80
(you should see the angular app)

if you go to localhost:80/api/bikes
you will get all the bikes in json

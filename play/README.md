docker build -t "play:latest" .
docker-compose build
docker-compose up

go to localhost:9000
(you will get empty json response)

go to localhost:9000/api/bikes
you will see the list of all bikes
(for now, there are none)

you can add bikes for example with curl:

curl --header "Content-type: text/json" --request POST --data '{"coords":{"x":34.56, "y":78.89}}' http://localhost:9000/api/bikes/new




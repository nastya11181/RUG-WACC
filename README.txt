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








--------------------------------------------------(that's the old version of readme)-------------------------------------------
Prerequisites
 - Make sure you have NPM installed on your system
 - Install AngularCLI

 
Check that ng works
	- go to the client directory
	- copy all the contents from maps-app/ to the current directory (client/), remove maps-app
	- npm install
	- npm install --save font-awesome
	- ng serve --open
The page should open in the browser. If it doesn't happen, try "ng serve" and then go to localhost:4200

Check that Docker works for that part
	- docker build -t client:cl .
	- docker run -p 80:4200 client:cl
The page should appear in browser on localhost:80

Now we have a prebuilt image for the client, we will use it in the next step
If you skipped this step, edit the docker-compose.yml file:
comment the line:
  image: client:cl
uncomment: 
  build ./client
	

If everything works fine, go up to the ng/ directory
	- docker-compose build
	- docker-compose run 
	


	





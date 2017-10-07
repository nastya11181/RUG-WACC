Prerequisites
 - Make sure you have NPM installed on your system
 - Install AngularCLI

Run the nginx
 - go to the client directory
	ng new client
	ng serve
 - go to  http://localhost:4200
 - go to the ng directory
	docker build -t ng/client .
	docker run --rm -p 80:4200 ng/client
 - go to localhost or to the ipadres of your machine




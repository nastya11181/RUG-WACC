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
(That's the part where everything seems to work fine for me. However, I cannot see anything in the browser)	

	
Run the nginx
 - go to the client directory
	ng new client
	ng serve
 - go to  http://localhost:4200
 - go to the ng directory
	docker build -t ng/client .
	docker run --rm -p 80:4200 ng/client
 - go to localhost or to the ipadres of your machine




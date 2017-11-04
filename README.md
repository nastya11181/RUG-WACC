Prerequisites: docker-machine and Virtualbox installed

It's possible to deploy the docker stack on tho virtual machines or on a local machine and a virtual machine. In this file we describe the latter option for simplicity.

1. Create 1 virtual machines with docker-machine

docker-machine create --driver virtualbox myvm1

If you're working on two virtual machines, use 

eval $(docker-machine env myvm1) 

before proceeding to the next steps

2. Initialize a swarm

docker swarm init 

If it suggests to choose an advertise address choose the 192.168. ... one and re-run:

docker swarm init --advertise-add <address>

Now we know how to add workers to the swarm.

Copy the "docker swarm join ..." line 

3. Add a worker to the swarm

docker-machine ssh myvm1 "docker swarm join .... <paste from above>"

 
4. Create an overlay network called uber:

docker network create -d overlay uber


5. Copy the init.json file to the virtual machine. It will be used in the docker-compose file

docker-machine scp ./init.json myvm1:/home/docker/init.sjon

If the 2nd machine that you're using is not a virtual machine, create a folder /home/docker and put init.json in there as well



6. Deploy a stack called uber. Make sure that you are in the folder with the docker-compose version 3 file.

docker stack deploy -c docker-compose_v3.yml uber

To see what's happening run the vusualizer (optional, but the picture is very nice):

docker run -it -d -p 8080:8080 -v /var/run/docker.sock:/var/run/docker.sock dockersamples/visualizer

go to localhost:8080 to see the containers and their distribution among the hosts


If the mongo containers do not appear it means there's something wrong with the volumes. Check that init.json is in /home/docker/ on all the machines, then remove the stack and recreate it again:

docker stack rm uber
docker stack deploy -c docker-compose.yml uber


7. After all the containers are up and running, import the data to mongodb

docker exec -it uber_mongo.<name of the mongo container> mongoimport --db bikes --collection bikes --type json --file /init.json --jsonArray

Be sure to do so on the primary node (it may be situated on a different machine). 


8. Everything should work now!

Try going to <localhost/ip address of the machine>:80








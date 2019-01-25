Run
===

1) unzip the attached  archive dircontent.zip
2) cd dircontent
3) mvn clean install -f content-server/pom.xml && mvn clean install -f sorter/pom.xml && docker-compose up --build

4) in another terminal 
5) docker network inspect dircontent_default
    and write down the [IPv4Address] of the content-server container
    ex:         "Name": "content-server",
                "EndpointID": "f3488e078bde80e9a1832a5dd28f93443f280fdf8b21fe9f7803e33d33e83410",
                "MacAddress": "02:42:ac:15:00:02",
                "IPv4Address": "172.21.0.2/16",

6) curl http://[IPv4Address]:8081/key?key=medium_content

Notes
=====
The solution is comprised of two microservices:

sorter - very similar to the Java solution I sent earlier this week , 
	yet instead of using redis, sends (in parallel) the content of the files to the
content-server - that is a http / rest wrapper around java.util.concurrent.ConcurrentSkipListSet (a concurreent set that preserves the natural ordering of its elements)

The solution is packed as 2 containers in docker-compose.yml with the posibility to scale up the ingestion service, sorter

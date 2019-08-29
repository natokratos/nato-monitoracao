build: 
	@docker-compose -p tivit-monitoracao build --force-rm

run:
	@docker-compose up -d
	
stop:
	@docker-compose stop

clean:    stop
	@docker-compose rm -f

clean-data: clean

clean-images:
	@sudo docker ps -a -q -f status=exited | docker rm -v
	@docker rmi -f  $(sudo docker images -f "dangling=true" -q)
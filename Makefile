test:
	cd docker/test;	docker compose rm -f
	cd docker/test;	docker compose up -d 
	sleep 10
	./gradlew test

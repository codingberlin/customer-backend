freshdb:
	cd docker/test;	docker compose stop
	cd docker/test;	docker compose rm -f
	cd docker/test;	docker compose up -d
	sleep 10

test:
	./gradlew test

bundle:
	./gradlew bootJar -x test
	docker build -t customer-backend .

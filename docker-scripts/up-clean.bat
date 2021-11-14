echo on
docker-compose -f ../docker-compose.yml down --remove-orphans

docker container prune --force
docker container rm $(docker container ls -q)

docker system prune --volumes --force
docker volume rm $(docker volume ls -q)

docker image prune --force
docker image rm pauloportfolio/web7-mysql
docker image rm pauloportfolio/web7-web

docker system df
docker image ls
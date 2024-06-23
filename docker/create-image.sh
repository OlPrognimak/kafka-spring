docker-compose kill && docker-compose rm -f
docker-compose down
docker-compose down --volumes
docker rmi prognimak.app/kafka-trade:01
#IF you need to DELETE ALL IMAGES defined in docker-compose.yml
# Get the list of images defined in the docker-compose.yml file
#images=$(grep 'image:' docker-compose.yml | awk '{print $2}')

# Remove each image
#for image in $images; do
#  docker rmi $image
#done

docker volume prune --force
docker image prune --force

docker build . -t prognimak.app/kafka-trade:01
docker-compose -f docker-compose.yml up -d
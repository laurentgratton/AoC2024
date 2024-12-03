DAY=$1

echo "Building and running day $DAY"

docker build --tag "aoc-d$DAY" --quiet --build-arg DAY=$DAY .
docker run --rm --quiet aoc-d$DAY
docker rmi aoc-d$DAY